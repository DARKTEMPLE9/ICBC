package net.northking.iacmp.ams.web.controller.mytask;


import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.common.bean.domain.ams.AmsBatch;
import net.northking.iacmp.common.bean.domain.ams.AmsBill;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.framework.util.ShiroUtils;
import net.northking.iacmp.server.service.IAmsBatchService;
import net.northking.iacmp.server.service.IAmsBillService;
import net.northking.iacmp.system.domain.SysUser;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的任务——————>我的档案接收
 * 操作表---->ams_batch
 *
 * @author wxy
 * @date 2019-08-07
 */
@Controller
@RequestMapping("/myTask/myArchReceive")
public class MyArchReceiveController extends BaseController {
    private String prefix = "myTask/myArchReceive";

    @Autowired
    private IAmsBatchService amsBatchService;
    @Autowired
    private IAmsBillService amsBillService;

    @RequiresPermissions("myTask:myArchReceive:view")
    @GetMapping()
    public String amsBatch() {
        return prefix + "/amsBatch";
    }

    /**
     * 查询我的档案接收管理列表
     */
    @RequiresPermissions("myTask:myArchReceive:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AmsBatch amsBatch) {
        SysUser sysUser = ShiroUtils.getSysUser();
        amsBatch.setReceiveNo(sysUser.getUserId().toString());
        List<AmsBatch> list;
        if (null == amsBatch.getStatus() || amsBatch.getStatus().isEmpty()) {
            amsBatch.setStatus("3");
        } else {
            amsBatch.setStatus(amsBatch.getStatus());
        }
        startPage();
        list = amsBatchService.selectarchReceiveList(amsBatch);
        return getDataTable(list);
    }


    /**
     * 导出档案著录列表
     */
    @Log(title = "我的档案接收管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("myTask:myArchReceive:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(String batchIds) {
        List<AmsBatch> amsBatchList = new ArrayList<>();
        if (batchIds != null && !"".equals(batchIds)) {
            String[] ids = batchIds.split(",");
            for (int i = 0; i < ids.length; i++) {
                AmsBatch operLog = amsBatchService.selectAmsBatchById(ids[i]);
                amsBatchList.add(operLog);
            }
        } else {
            AmsBatch config = new AmsBatch();
            List<AmsBatch> batches = amsBatchService.selectAmsBatchList(config);
            for (AmsBatch amsBatch : batches) {
                amsBatchList.add(amsBatch);
            }
        }
        ExcelUtil<AmsBatch> util = new ExcelUtil<>(AmsBatch.class);
        return util.exportExcel(amsBatchList, "操作日志");
    }

    /**
     * 新增档案著录
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存档案著录
     */
    @RequiresPermissions("myTask:myArchReceive:add")
    @Log(title = "档案著录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AmsBatch amsBatch) {
        return toAjax(amsBatchService.insertAmsBatch(amsBatch));
    }

    /**
     * 接收档案退回
     */
    @RequiresPermissions("myTask:myArchReceive:edit")
    @GetMapping("/edit/{id}")
    public String editBack(@PathVariable("id") String id, ModelMap mmap) {
        AmsBatch amsBatch = amsBatchService.selectAmsBatchById(id);
        mmap.put("amsBatch", amsBatch);
        return prefix + "/editBack";
    }

    @GetMapping("/detail/{id}")
    @RequiresPermissions("myTask:myArchReceive:detail")
    public String detail(@PathVariable("id") String id, ModelMap mmap) {
        AmsBatch amsBatch = amsBatchService.selectAmsBatchById(id);
        String arcBillCode = amsBatch.getArcBillDeptCode();
        AmsBill amsBill = amsBillService.selectAmsBillById(arcBillCode);
        mmap.put("mouldStr", amsBill.getMouldStr());
        mmap.put("amsBatch", amsBatch);
        return prefix + "/detail";
    }

    /**
     * 保存接受档案退回
     */
    @RequiresPermissions("myTask:myArchReceive:edit")
    @Log(title = "档案著录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editBackSave(AmsBatch amsBatch) {
        amsBatch.setStatus("6");
        amsBatch.setReceivingTime(new java.sql.Timestamp(System.currentTimeMillis()));
        return toAjax(amsBatchService.updateAmsBatch(amsBatch));
    }

    /**
     * 删除档案著录
     */
    @RequiresPermissions("myTask:myArchReceive:remove")
    @Log(title = "档案著录", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(amsBatchService.deleteAmsBatchByIds(ids));
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

    /**
     * 调转到显示档案类型树页面
     *
     * @return
     */
    @GetMapping("/arcBillTree")
    public String arcBillTree() {
        return prefix + "/arcBillTree";
    }

}
