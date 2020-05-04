package net.northking.iacmp.ams.web.controller.mytask;


import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.common.bean.domain.ams.AmsApproveInfo;
import net.northking.iacmp.common.bean.domain.ams.AmsBorrowInfo;
import net.northking.iacmp.common.bean.domain.ams.AmsProcessInfo;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.framework.util.ShiroUtils;
import net.northking.iacmp.server.service.IAmsApproveInfoService;
import net.northking.iacmp.server.service.IAmsBatchService;
import net.northking.iacmp.server.service.IAmsBorrowInfoService;
import net.northking.iacmp.server.service.IAmsProcessInfoService;
import net.northking.iacmp.system.domain.SysUser;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 我的任务--->已审批
 * 操作表----->ams_process_info
 *
 * @author wxy
 * @date 2019-08-07
 */
@Controller
@RequestMapping("/myTask/alreadyProcessInfo")
public class AlreadyProcessInfoController extends BaseController {
    private String prefix = "myTask/alreadyProcessInfo";

    @Autowired
    private IAmsProcessInfoService amsProcessInfoService;
    @Autowired
    //审批意见服务
    private IAmsApproveInfoService amsApproveInfoService;
    @Autowired
    private IAmsBatchService amsBatchService;
    @Autowired
    private IAmsBorrowInfoService amsBorrowInfoService;


    @RequiresPermissions("myTask:alreadyProcessInfo:view")
    @GetMapping()
    public String amsProcessInfo() {
        return prefix + "/amsProcessInfo";
    }

    /**
     * 查询审批列表
     */
    @RequiresPermissions("myTask:alreadyProcessInfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AmsProcessInfo amsProcessInfo) {
        SysUser sysUser = ShiroUtils.getSysUser();
        amsProcessInfo.setNowDispOpNo(sysUser.getUserId().toString());
        amsProcessInfo.setFoSearch("1");
        startPage();
        List<AmsProcessInfo> list = amsProcessInfoService.selectAlreadyProcessInfoList(amsProcessInfo);
        return getDataTable(list);
    }


    /**
     * 导出审批列表
     */
    @RequiresPermissions("myTask:alreadyProcessInfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AmsProcessInfo amsProcessInfo) {
        List<AmsProcessInfo> list = amsProcessInfoService.selectAmsProcessInfoList(amsProcessInfo);
        ExcelUtil<AmsProcessInfo> util = new ExcelUtil<>(AmsProcessInfo.class);
        return util.exportExcel(list, "amsProcessInfo");
    }

    /**
     * 新增审批
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存审批
     */
    @RequiresPermissions("myTask:alreadyProcessInfo:add")
    @Log(title = "审批", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AmsProcessInfo amsProcessInfo) {
        return toAjax(amsProcessInfoService.insertAmsProcessInfo(amsProcessInfo));
    }

    /**
     * 修改审批
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        AmsProcessInfo amsProcessInfo = amsProcessInfoService.selectAmsProcessInfoById(id);
        mmap.put("amsProcessInfo", amsProcessInfo);
        return prefix + "/edit";
    }

    /**
     * 查看审批
     */
    @GetMapping("/detail/{id}")
    @RequiresPermissions("myTask:alreadyProcessInfo:detail")
    public String detailProcess(@PathVariable("id") String id, ModelMap mmap) {
        AmsProcessInfo amsProcessInfo = amsProcessInfoService.selectAmsProcessInfoById(id);
        String arcid = amsProcessInfo.getArcNo();
        AmsBorrowInfo amsBorrowInfo = amsBorrowInfoService.selectAmsBorrowInfoByarcId(arcid);
        if (amsBorrowInfo == null) {
            amsProcessInfo.setBorrowId("");
        } else {
            amsProcessInfo.setBorrowId(amsBorrowInfo.getAppReason());
        }
        mmap.put("amsBorrowInfo", amsBorrowInfo);
        mmap.put("amsProcessInfo", amsProcessInfo);
        return prefix + "/detail";
    }

    /**
     * 查询审批记录
     *
     * @param id
     * @param mmap
     * @return
     */
    //@RequiresPermissions("myTask:waitProcessInfo:approvalHistory")
    @GetMapping("/approvalHistory/{id}")
    public String approvalHistory(@PathVariable("id") String id, ModelMap mmap) {
        AmsProcessInfo amsProcessInfo = amsProcessInfoService.selectAmsProcessInfoById(id);
        String exaappid = amsProcessInfo.getExaAppId();
        List<AmsApproveInfo> amsApproveInfolist = amsApproveInfoService.selectAmsApproveInfoByexaAppId(exaappid);
        mmap.put("amsApproveInfolist", amsApproveInfolist);
        return prefix + "/apprrovalHistory";
    }

    /**
     * 修改保存审批
     */
    @RequiresPermissions("myTask:alreadyProcessInfo:edit")
    @Log(title = "审批", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AmsProcessInfo amsProcessInfo) {
        return toAjax(amsProcessInfoService.updateAmsProcessInfo(amsProcessInfo));
    }

    /**
     * 删除审批
     */
    @RequiresPermissions("myTask:alreadyProcessInfo:remove")
    @Log(title = "审批", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(amsProcessInfoService.deleteAmsProcessInfoByIds(ids));
    }

    /**
     * 调转到显示部门树页面
     *
     * @return
     */
    @GetMapping("/deptTree")
    public String deptTree() {
        return prefix + "/tree";
    }

}
