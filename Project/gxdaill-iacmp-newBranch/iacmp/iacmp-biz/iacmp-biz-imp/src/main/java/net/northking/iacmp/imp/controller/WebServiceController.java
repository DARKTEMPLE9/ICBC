package net.northking.iacmp.imp.controller;

import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.imp.domain.WebService;
import net.northking.iacmp.imp.service.IWebServiceService;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 报文 信息操作处理
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@RestController
@RequestMapping("/uip/webService")
public class WebServiceController extends BaseController {

    @Autowired
    private IWebServiceService webServiceService;

    /**
     * 查询报文列表
     */
    @PostMapping("/list")
    public List<WebService> list(@RequestBody WebService webService) {
        List<WebService> list = webServiceService.selectWebServiceList(webService);
        return list;
    }


    /**
     * 导出报文列表
     */
    @RequiresPermissions("uip:webService:export")
    @PostMapping("/export")
    public AjaxResult export(WebService webService) {
        List<WebService> list = webServiceService.selectWebServiceList(webService);
        ExcelUtil<WebService> util = new ExcelUtil<WebService>(WebService.class);
        return util.exportExcel(list, "webService");
    }


    /**
     * 新增保存报文
     */
    @Log(title = "报文", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestBody WebService webService) {
        return toAjax(webServiceService.insertWebService(webService));
    }

    /**
     * 修改报文
     */
    @GetMapping("/edit/{id}")
    public WebService edit(@PathVariable("id") String id, ModelMap mmap) {
        WebService webService = webServiceService.selectWebServiceById(id);
        return webService;
    }

    /**
     * 修改保存报文
     */
    @Log(title = "报文", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WebService webService) {
        return toAjax(webServiceService.updateWebService(webService));
    }

    /**
     * 删除报文
     */
    @Log(title = "报文", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(webServiceService.deleteWebServiceByIds(ids));
    }

}
