package net.northking.iacmp.imp.controller;

import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.imp.domain.ImHadoopUploadLog;
import net.northking.iacmp.imp.service.IImHadoopUploadLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.imp.domain.ImHadoopUploadLog;
import net.northking.iacmp.imp.service.IImHadoopUploadLogService;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 大数据上传日志 信息操作处理
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@RestController
@RequestMapping("/uip/imHadoopUploadLog")
public class ImHadoopUploadLogController extends BaseController {

    @Autowired
    private IImHadoopUploadLogService imHadoopUploadLogService;

    /**
     * 查询大数据上传日志列表
     */
    @PostMapping("/list")
    public TableDataInfo list(ImHadoopUploadLog imHadoopUploadLog) {
        startPage();
        List<ImHadoopUploadLog> list = imHadoopUploadLogService.selectImHadoopUploadLogList(imHadoopUploadLog);
        return getDataTable(list);
    }

    /**
     * 新增保存大数据上传日志
     */
    @Log(title = "大数据上传日志", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult addSave(@RequestBody ImHadoopUploadLog imHadoopUploadLog) {
        return toAjax(imHadoopUploadLogService.insertImHadoopUploadLog(imHadoopUploadLog));
    }


    /**
     * 修改保存大数据上传日志
     */
    @Log(title = "大数据上传日志", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult editSave(ImHadoopUploadLog imHadoopUploadLog) {
        return toAjax(imHadoopUploadLogService.updateImHadoopUploadLog(imHadoopUploadLog));
    }

    /**
     * 删除大数据上传日志
     */
    @Log(title = "大数据上传日志", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    public AjaxResult remove(String ids) {
        return toAjax(imHadoopUploadLogService.deleteImHadoopUploadLogByIds(ids));
    }

}
