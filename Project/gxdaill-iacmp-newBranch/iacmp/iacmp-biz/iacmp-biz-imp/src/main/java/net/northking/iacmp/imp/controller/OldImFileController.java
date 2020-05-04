package net.northking.iacmp.imp.controller;

import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.imp.constant.ImpConstants;
import net.northking.iacmp.imp.domain.OldImFile;
import net.northking.iacmp.imp.service.IOldImFileService;
import net.northking.iacmp.utils.bean.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 文件 信息操作处理
 *
 * @author wei.chen
 * @date 2019-10-22
 */
@Controller
@RequestMapping("/uip/oldImFile")
public class OldImFileController extends BaseController {

    @Autowired
    private IOldImFileService oldImFileService;

    /**
     * 查询文件列表
     */
    @PostMapping("/list")
    @ResponseBody
    public List<OldImFile> list(OldImFile imFile) {
        List<OldImFile> list = oldImFileService.selectImFileList(imFile);
        return list;
    }

    /**
     * 新增保存文件
     */
    @Log(title = "文件", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestBody OldImFile imFile) {
        return toAjax(oldImFileService.insertImFile(imFile));
    }

    /**
     * 修改保存文件
     */
    @Log(title = "文件", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OldImFile imFile) {
        return toAjax(oldImFileService.updateImFile(imFile));
    }

    /**
     * 删除文件
     */
    @Log(title = "文件", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(oldImFileService.deleteImFileByIds(ids));
    }

    /**
     * 查询文件列表
     */
    @PostMapping("/listFile")
    @ResponseBody
    public List<Map<String, Object>> listFile(@RequestBody Map<String, Object> paramMap) {
        OldImFile oldImFile = new OldImFile();
        oldImFile.setBatchId((String) paramMap.get(ImpConstants.BATCHID));
        if (paramMap.containsKey(ImpConstants.ID)) {
            oldImFile.setId((String) paramMap.get(ImpConstants.ID));
        }
        List<OldImFile> oldFileList = oldImFileService.selectImFileList(oldImFile);

        List<Map<String, Object>> mapList = new ArrayList<>();
        Map<String, Object> fileMap = null;
        if (null != oldFileList) {
            for (OldImFile oldImFile1 : oldFileList) {
                try {
                    fileMap = BeanUtils.bean2Map(oldImFile1);
                } catch (IntrospectionException e) {
                    logger.error(e.getMessage());
                    continue;
                } catch (InvocationTargetException e) {
                    logger.error(e.getMessage());
                    continue;
                } catch (IllegalAccessException e) {
                    logger.error(e.getMessage());
                    continue;
                }
                mapList.add(fileMap);
            }
        }
        return mapList;
    }

    /**
     * 修改文件
     */
    @Log(title = "文件", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    @ResponseBody
    public AjaxResult update(@RequestBody Map<String, Object> imFileMap) {

        OldImFile oldImFile = new OldImFile();
        oldImFile.setId((String) imFileMap.get(ImpConstants.ID));
        if (imFileMap.containsKey(ImpConstants.STATUS)) {
            oldImFile.setStatus((String) imFileMap.get(ImpConstants.STATUS));
        }
        if (imFileMap.containsKey(ImpConstants.SERIALNO)) {
            oldImFile.setSerialno(new BigDecimal((Integer) imFileMap.get(ImpConstants.SERIALNO)));
        }
        if (imFileMap.containsKey(ImpConstants.USERCODEID)) {
            oldImFile.setUserCodeId((String) imFileMap.get(ImpConstants.USERCODEID));
        }
        return toAjax(oldImFileService.updateImFile(oldImFile));
    }

    /**
     * 创建文件
     */
    @Log(title = "文件", businessType = BusinessType.INSERT)
    @PostMapping("/create")
    @ResponseBody
    public AjaxResult create(@RequestBody Map<String, Object> imFileMap) {

        OldImFile oldImFile = new OldImFile();
        try {
            BeanUtils.map2bean(imFileMap, oldImFile);
        } catch (IntrospectionException e) {
            logger.error(e.getMessage());
            return toAjax(false);
        } catch (InvocationTargetException e) {
            logger.error(e.getMessage());
            return toAjax(false);
        } catch (IllegalAccessException e) {
            logger.error(e.getMessage());
            return toAjax(false);
        } catch (ParseException e) {
            logger.error(e.getMessage());
            return toAjax(false);
        }
        oldImFile.setCreateTime(new Date());
        return toAjax(oldImFileService.insertImFile(oldImFile));
    }
}
