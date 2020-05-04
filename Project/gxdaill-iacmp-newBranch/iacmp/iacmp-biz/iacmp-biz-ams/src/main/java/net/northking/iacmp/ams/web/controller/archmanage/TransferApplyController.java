package net.northking.iacmp.ams.web.controller.archmanage;

import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.common.bean.domain.ams.AmsBatch;
import net.northking.iacmp.common.bean.domain.ams.SmParam;
import net.northking.iacmp.common.bean.dto.ams.AmsBatchDTO;
import net.northking.iacmp.constant.CmsConstants;
import net.northking.iacmp.constant.Constants;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.framework.util.ShiroUtils;
import net.northking.iacmp.framework.util.SysConfigInitParamsUtils;
import net.northking.iacmp.server.service.IAmsArchivesService;
import net.northking.iacmp.server.service.IAmsBatchService;
import net.northking.iacmp.server.service.ISmParamService;
import net.northking.iacmp.server.service.ISmUserService;
import net.northking.iacmp.system.domain.SysUser;
import net.northking.iacmp.system.service.ISysUserService;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * 移交申请
 *
 * @author weizhe.fan
 * @date 2019-08-01
 */
@Controller
@RequestMapping("/archManage/transApply")
public class TransferApplyController extends BaseController {
    private String prefix = "archManage/transApply";

    @Autowired
    private IAmsBatchService iAmsBatchService;
    @Autowired
    private IAmsArchivesService amsArchivesService;
    @Autowired
    private ISmUserService smUserService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISmParamService smParamService;

    @RequiresPermissions("archManage:transApply:view")
    @GetMapping()
    public String amsArchives() {
        return prefix + "/transApply";
    }

    /**
     * 查询移交申请信息列表
     */
    @RequiresPermissions("archManage:transApply:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AmsBatch amsBatch) {
        SysUser sysUser = ShiroUtils.getSysUser();
        amsBatch.setCrtNo(String.valueOf(sysUser.getUserId()));
        amsBatch.setArcHasMoveBank("1");
        startPage();
        // 查询条件为当前登录用户,状态为1(已著录),6(退回)
        List<AmsBatchDTO> list = iAmsBatchService.selectTransferApplyByCrtNoAndStatus(amsBatch);
        return getDataTable(list);
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
     * @Author: weizhe.fan
     * @Description:跳转到查询档案管理员页面
     * @CreateDate: 21:02.2019/8/6
     */
    @GetMapping("/queryAdmin")
    public String jumpToQueryAdmin(String ids, ModelMap modelMap) {
        List<SysUser> archManager = sysUserService.getArchManager();
        modelMap.put("archManager", archManager);
        modelMap.put("ids", ids);
        return prefix + "/toBAR";
    }

    /**
     * @Author: weizhe.fan
     * @Description:移交行档室
     * @CreateDate: 17:23.2019/8/1
     */
    @RequiresPermissions("archManage:transApply:transToBAR")
    @Log(title = "移交行档室", businessType = BusinessType.UPDATE)
    @PostMapping("/toBAR")
    @ResponseBody
    public AjaxResult transToBAR(AmsBatch amsBatch, Long id, String batchIds) {
        // 传过来这个用户选中的档案，和要传给的用户的id
        SysUser sysUser = sysUserService.selectUserById(id);
        // 根据userid查询用户
        String approveOnOff = SysConfigInitParamsUtils.getConfig(CmsConstants.APPROVEONOFF);
        // 将这条档案update为新用户id
        int count = 0;
        String[] split = batchIds.split(",");
        for (String archManagerId : split) {
            AmsBatch batch = new AmsBatch();
            batch.setId(archManagerId);
            if ("true".equals(approveOnOff)) {
                batch.setArcTransfer(ShiroUtils.getSysUser().getUserName());
                batch.setStatus(Constants.APPLYING);
                batch.setReceiveNo(String.valueOf(sysUser.getUserId()));
                batch.setReceiveName(sysUser.getUserName());
                batch.setTurnInNo(String.valueOf(sysUser.getUserId()));
                batch.setTurnInName(sysUser.getUserName());
                batch.setTurnInTime(new java.sql.Timestamp(System.currentTimeMillis()));
            } else {
                batch.setArcTransfer(ShiroUtils.getSysUser().getUserName());
                batch.setStatus(Constants.WAIT_SUBMIT);
            }
            int i = iAmsBatchService.updateAmsBatch(batch);
            count += i;
        }
        return toAjax(count);
    }

    /**
     * @Author: weizhe.fan
     * @Description:查询详情
     * @CreateDate: 15:30.2019/8/5
     */
    @RequiresPermissions("archManage:transApply:detail")
    @GetMapping("/{id}")
    public String queryOne(@PathVariable String id, ModelMap modelMap) {
        AmsBatch amsBatch = iAmsBatchService.selectAmsBatchById(id);
        modelMap.addAttribute("amsBatch", amsBatch);
        return prefix + "/detail";
    }


    /**
     * 导出档案登记列表
     */
    @RequiresPermissions("archManage:transApply:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(String batchIds) {
        List<AmsBatchDTO> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        if (batchIds != null && !"".equals(batchIds)) {
            String[] ids = batchIds.split(",");
            for (int i = 0; i < ids.length; i++) {
                AmsBatchDTO amsBatch = iAmsBatchService.selectAmsBatchDTOById(ids[i]);
                String year = sdf.format(amsBatch.getCrtTime());
                amsBatch.setYear(year);
                list.add(amsBatch);
            }
        } else {
            SysUser sysUser = ShiroUtils.getSysUser();
            AmsBatch amsBatch = new AmsBatch();
            amsBatch.setCrtNo(String.valueOf(sysUser.getUserId()));
            //查询当前登录用户,状态为1(已著录),6(退回)的移交申请信息
            List<AmsBatchDTO> amsBatches = iAmsBatchService.selectTransferApplyByCrtNoAndStatus(amsBatch);
            for (AmsBatchDTO obj : amsBatches) {
                String year = sdf.format(obj.getCrtTime());
                obj.setYear(year);
                list.add(obj);
            }
        }
        ExcelUtil<AmsBatchDTO> util = new ExcelUtil<>(AmsBatchDTO.class);
        return util.exportExcel(list, "list");
    }

    /**
     * 新增档案登记
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存档案登记
     */
    @RequiresPermissions("archManage:transApply:add")
    @Log(title = "档案登记", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AmsBatch amsBatch) {
        return toAjax(iAmsBatchService.insertAmsBatch(amsBatch));
    }

    /**
     * 修改档案登记
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        AmsBatch amsBatch = iAmsBatchService.selectAmsBatchById(id);
        mmap.put("amsBatch", amsBatch);
        return prefix + "/edit";
    }

    /**
     * 修改保存档案登记
     */
    @RequiresPermissions("archManage:transApply:edit")
    @Log(title = "档案登记", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AmsBatch amsBatch) {
        return toAjax(iAmsBatchService.updateAmsBatch(amsBatch));
    }

    /**
     * 删除档案登记
     */
    @RequiresPermissions("archManage:transApply:remove")
    @Log(title = "档案登记", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(iAmsBatchService.deleteAmsBatchByIds(ids));
    }

}
