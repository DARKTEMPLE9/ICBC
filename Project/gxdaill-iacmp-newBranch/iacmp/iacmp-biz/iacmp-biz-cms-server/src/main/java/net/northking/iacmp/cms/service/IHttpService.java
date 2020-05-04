package net.northking.iacmp.cms.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.northking.iacmp.common.bean.domain.cms.PmsBatch;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.utils.SnowFlakeUtils;


/**
 * @Description:
 * @Author: weizhe.fan
 * @CreateDate: 2019/9/26
 */
public interface IHttpService {
    /**
     * 档案著录上传结构化
     */
    JSONArray amsUpload(JSONObject jsonObject, String json);

    /**
     * 内管上传结构化
     */
    JSONArray cmsUpload(JSONObject jsonObject, String json);

    JSONObject cmsQuery(JSONObject jsonObject);

    void projectInfoSyncho(JSONObject jsonObject);

    JSONObject projectBillList(PmsBatch pmsBatch);

    PmsBatch savePmsBatch(SnowFlakeUtils snowFlakeUtils, JSONObject object, String json, JSONObject jsonObject);

    PmsBatch updatePmsBatch(JSONObject object, String json, PmsBatch pmsBatchByOpt);

    JSONObject projectBillQuery(String modelId);

    AjaxResult fileInfoBind(JSONObject jsonObject);
}
