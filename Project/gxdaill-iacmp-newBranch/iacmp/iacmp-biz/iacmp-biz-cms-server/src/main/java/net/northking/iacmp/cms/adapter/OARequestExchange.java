package net.northking.iacmp.cms.adapter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import net.northking.iacmp.cms.service.ICmsReqMappingService;
import net.northking.iacmp.cms.service.ICmsSystemService;
import net.northking.iacmp.common.bean.domain.cms.CmsReqMapping;
import net.northking.iacmp.common.bean.domain.cms.CmsSystem;
import net.northking.iacmp.constant.CmsConstants;
import net.northking.iacmp.utils.spring.SpringUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * OA系统参数转换
 *
 * @Author: wei.chen
 * @Date Created: in 2019/9/16 10:09
 */
@Slf4j
public class OARequestExchange implements RequestExchange {

    static final String FILE_LIST_KEY = "fileList";

    static final String TO_ARC_FLAG_SEPARATOR = "@";

    static final String KEY_VALUE_SEPARATOR = "=";

    static final String PATH_SEPARATOR = ":";

    static final String TO_ARC_KEY = "toArc";

    static final String TO_ARC_VALUE_YES = "1";

    private String sysCode;

    public OARequestExchange(String sysCode) {
        this.sysCode = sysCode;
    }

    /**
     * 请求参数转换
     *
     * @param reqObj 原json报文对象
     * @return 原json报文对象（需要转换的参数已经转换）
     */
    public JSONObject exchangeRequest(JSONObject reqObj) {
        // 查询归档标识
        ICmsSystemService cmsSystemService = SpringUtils.getBean(ICmsSystemService.class);
        CmsSystem cmsSystem = cmsSystemService.selectCmsSystemByCode(sysCode);
        String toArcFlag = cmsSystem.getRemark();
        String[] toArcFlags = toArcFlag.split(TO_ARC_FLAG_SEPARATOR);
        Map<String, Object> flagMap = new HashMap<>();
        for (String flag : toArcFlags) {
            String[] flagArr = flag.split(KEY_VALUE_SEPARATOR);
            flagMap.put(flagArr[0], flagArr[1]);
        }

        // 查询参数转换映射列表
        CmsReqMapping param = new CmsReqMapping();
        param.setSysCode(sysCode);
        ICmsReqMappingService cmsReqMappingService = SpringUtils.getBean(ICmsReqMappingService.class);
        List<CmsReqMapping> mappings = cmsReqMappingService.selectCmsReqMappingList(param);

        String localPath;
        String remotePath;
        Map<String, String> map = new HashMap<>();
        // 根据映射列表提取参数值，数组fileList的参数不在这里取值
        for (CmsReqMapping mapping : mappings) {
            localPath = mapping.getLocalPath();
            remotePath = mapping.getRemotePath();
            if (localPath.indexOf(CmsConstants.FILE_LIST_KEY) != -1) {
                // 数组fileList的参数加入map，后续处理
                map.put(localPath, remotePath);
            } else {
                // 把OA的参数转换为内管需要的参数
                Object value = getJsonValue(reqObj, remotePath);
                if (null != value) {
                    putJsonValue(reqObj, localPath, value);
                }
            }
        }

        // 提取fileList，默认只有一级数组，且数组的属性在一起
        JSONArray jsonArray = (JSONArray) getArrayValue(reqObj, map);
        if (null != jsonArray) {
            if (null == reqObj.get(CmsConstants.FILE_LIST_KEY)) {
                // 如果没有fileList，直接插入数组
                reqObj.put(CmsConstants.FILE_LIST_KEY, jsonArray);
            } else {
                // 如果已有fileList，要把每个元素的属性插入
                Map<String, JSONObject> fileMap = new HashMap<>();
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject fileObj = jsonArray.getJSONObject(i);
                    fileMap.put(fileObj.getString("fileName"), fileObj);
                }
                JSONArray fileArray = (JSONArray) reqObj.get(CmsConstants.FILE_LIST_KEY);
                for (int i = 0; i < fileArray.size(); i++) {
                    JSONObject fileObj = fileArray.getJSONObject(i);
                    String fileName = fileObj.getString("fileName");
                    JSONObject file = fileMap.get(fileName);
                    Iterator<Map.Entry<String, Object>> entryIterator = file.entrySet().iterator();
                    while (entryIterator.hasNext()) {
                        Map.Entry<String, Object> entry = entryIterator.next();
                        // 除了fileName以外，所有参数都要插入
                        if (!"fileName".equals(entry.getKey())) {
                            fileObj.put(entry.getKey(), entry.getValue());
                        }
                    }
                }
            }
        }

        // 获取归档标识的值，与配置的值对比，相等则在fileList所有文件中加上toArc=1
        boolean toArc = true;
        if (flagMap.size() > 0) {
            Iterator<Map.Entry<String, Object>> entryIterator = flagMap.entrySet().iterator();
            while (entryIterator.hasNext()) {
                Map.Entry<String, Object> entry = entryIterator.next();
                String path = entry.getKey();
                Object value = entry.getValue();
                Object valueInJson = getJsonValue(reqObj, path);
                if (null == valueInJson || !valueInJson.equals(value)) {
                    toArc = false;
                    break;
                }
            }
        } else {
            // 如果没有配置归档标识
            toArc = false;
        }
        if (toArc) {
            if (null != reqObj.get(CmsConstants.FILE_LIST_KEY)) {
                // 如果已有fileList
                JSONArray fileArray = (JSONArray) reqObj.get(CmsConstants.FILE_LIST_KEY);
                for (int i = 0; i < fileArray.size(); i++) {
                    JSONObject fileObj = fileArray.getJSONObject(i);
                    fileObj.put(TO_ARC_KEY, TO_ARC_VALUE_YES);
                }
            }
        }

        return reqObj;
    }

    /**
     * 根据path递归提取该参数的值，
     * 注意：可能OA中是数组，但是内管只是一个值，所以组成字符串，以,分隔
     *
     * @param reqObj 原json报文对象
     * @param path   该参数在json报文中的路径
     * @return 参数值
     */
    private Object getJsonValue(JSON reqObj, String path) {
        int pos = path.indexOf(CmsConstants.JSON_PATH_SEPARATOR);
        String path1;
        if (-1 != pos) {
            // 不是叶子对象
            path1 = path.substring(0, pos);
            if (reqObj instanceof JSONObject) {
                // 是对象，就递归取值
                return getJsonValue((JSON) ((JSONObject) reqObj).get(path1), path.substring(pos + 1));
            } else if (reqObj instanceof JSONArray) {
                // 是数组，就把数组元素递归取值组成字符串，以逗号分隔
                StringBuilder sb = new StringBuilder();
                JSONObject jsonObject;
                for (int i = 0; i < ((JSONArray) reqObj).size(); i++) {
                    jsonObject = ((JSONArray) reqObj).getJSONObject(i);
                    sb.append(getJsonValue((JSON) jsonObject.get(path1), path.substring(pos + 1)));
                    if (i < ((JSONObject) reqObj).size() - 1) {
                        sb.append(",");
                    }
                }
                return sb.toString();
            } else {
                // 格式错误
                log.error("报文格式错误！[{}]", path);
            }
        } else {
            // 已是叶子对象
            if (reqObj instanceof JSONObject) {
                return ((JSONObject) reqObj).get(path);
            } else if (reqObj instanceof JSONArray) {
                // 是数组，就把数组元素取值组成字符串，以逗号分隔
                StringBuilder sb = new StringBuilder();
                JSONObject jsonObject;
                for (int i = 0; i < ((JSONArray) reqObj).size(); i++) {
                    jsonObject = ((JSONArray) reqObj).getJSONObject(i);
                    sb.append(jsonObject.get(path));
                    if (i < ((JSONArray) reqObj).size() - 1) {
                        sb.append(",");
                    }
                }
                return sb.toString();
            } else {
                // 格式错误
                log.error("报文格式错误！[{}]", path);
            }
        }

        return null;
    }

    private Object getArrayValue(JSON reqObj, Map<String, String> arrayMap) {
        Map.Entry<String, String> entry = arrayMap.entrySet().iterator().next();
        String remotePath = entry.getValue();
        JSON json = reqObj;
        Object obj;
        String arrayPath = "";
        // 找到数组对象
        String[] paths = remotePath.split(CmsConstants.JSON_PATH_SEPARATOR);
        for (int i = 0; i < paths.length; i++) {
            obj = ((JSONObject) json).get(paths[i]);
            if (obj instanceof JSONArray) {
                json = (JSONArray) obj;
                arrayPath = paths[i];
                break;
            } else if (obj instanceof JSONObject) {
                json = (JSONObject) obj;
            } else {
                break;
            }
        }
        if (!(json instanceof JSONArray)) {
            log.error("报文格式错误，没有数组！[{}]", remotePath);
            return null;
        } else {
            // 提取数组中的参数
            JSONArray array = new JSONArray();
            JSONObject jsonObject;
            JSONObject newObject;
            int pos;
            String localPath;
            Iterator<Map.Entry<String, String>> entryIter = arrayMap.entrySet().iterator();
            Map<String, String> newMap = new HashMap<>();
            // 原映射路径截取fileList后面的，取值和赋值使用
            while (entryIter.hasNext()) {
                entry = entryIter.next();
                localPath = entry.getKey();
                pos = localPath.indexOf(CmsConstants.FILE_LIST_KEY);
                localPath = localPath.substring(
                        pos + CmsConstants.FILE_LIST_KEY.length() + 1);
                remotePath = entry.getValue();
                pos = remotePath.indexOf(arrayPath);
                // 如果远端路径中没有数组元素，该映射不合理，跳过
                if (pos == -1) {
                    continue;
                }
                remotePath = remotePath.substring(pos + arrayPath.length() + 1);
                entry.setValue(remotePath);
                newMap.put(localPath, remotePath);
            }
            // 从报文数组对象中取值，赋值到新的fileList数组中
            for (int i = 0; i < ((JSONArray) json).size(); i++) {
                jsonObject = ((JSONArray) json).getJSONObject(i);
                newObject = new JSONObject();
                entryIter = newMap.entrySet().iterator();
                while (entryIter.hasNext()) {
                    entry = entryIter.next();
                    if (null != jsonObject.get(entry.getValue())) {
                        newObject.put(entry.getKey(), jsonObject.get(entry.getValue()));
                    }
                }
                array.add(newObject);
            }

            return array;
        }
    }

    /**
     * 按照path所示路径把value加入json报文对象
     *
     * @param reqObj 原json报文对象
     * @param path   value对应的参数路径
     * @param value  参数值
     */
    private void putJsonValue(JSON reqObj, String path, Object value) {
        int pos = path.indexOf(CmsConstants.JSON_PATH_SEPARATOR);
        String path1;
        if (-1 != pos) {
            path1 = path.substring(0, pos);
            if (reqObj instanceof JSONObject) {
                if (null == ((JSONObject) reqObj).get(path1)) {
                    ((JSONObject) reqObj).put(path1, new JSONObject());
                }
                putJsonValue((JSON) ((JSONObject) reqObj).get(path1),
                        path.substring(pos + 1), value);
            }
        } else {
            if (reqObj instanceof JSONObject) {
                ((JSONObject) reqObj).put(path, value);
            }
        }
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }
}
