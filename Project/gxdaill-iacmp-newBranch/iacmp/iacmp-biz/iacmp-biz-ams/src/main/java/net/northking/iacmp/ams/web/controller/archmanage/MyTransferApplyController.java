package net.northking.iacmp.ams.web.controller.archmanage;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.common.bean.domain.ams.AmsArchives;
import net.northking.iacmp.common.bean.domain.ams.AmsBatch;
import net.northking.iacmp.common.bean.vo.ams.AmsArchivesVO;
import net.northking.iacmp.common.bean.vo.ams.AmsBatchVO;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.framework.util.ShiroUtils;
import net.northking.iacmp.server.service.IAmsArchivesService;
import net.northking.iacmp.server.service.IAmsBatchService;
import net.northking.iacmp.system.domain.SysUser;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 我的移交申请
 *
 * @author weizhe.fan
 * @date 2019-08-01
 */
@Controller
@RequestMapping("/archManage/myTransApply")
public class MyTransferApplyController extends BaseController {

    private String prefix = "archManage/myTransApply";
    @Autowired
    private IAmsBatchService iAmsBatchService;
    @Autowired
    private IAmsArchivesService amsArchivesService;

    @RequiresPermissions("archManage:myTransApply:view")
    @GetMapping()
    public String amsArchives() {
        return prefix + "/myTransApply";
    }

    /**
     * 查询我的移交申请列表
     * 查询条件：当前登录用户,状态(2,3,4,5,6)
     */
    @RequiresPermissions("archManage:myTransApply:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AmsBatchVO amsBatchVO) {
        SysUser sysUser = ShiroUtils.getSysUser();
        amsBatchVO.setCrtNo(String.valueOf(sysUser.getUserId()));
        startPage();
        List<AmsBatch> list = iAmsBatchService.selectMyTransferApplyByCrtNoAndStatus(amsBatchVO);
        return getDataTable(list);
    }

    /**
     * @Author: weizhe.fan
     * @Description:模糊条件查询
     * @CreateDate: 17:23.2019/8/1
     */
    @PostMapping("/opsMyList")
    @ResponseBody
    public TableDataInfo opsList(AmsBatch amsBatch) {
        startPage();
        List<AmsBatch> list = iAmsBatchService.selectAmsBatchListByOpts(amsBatch);
        return getDataTable(list);
    }

    /**
     * @Author: weizhe.fan
     * @Description:查询详情
     * @CreateDate: 15:34.2019/8/5
     */
    @RequiresPermissions("archManage:myTransApply:detail")
    @GetMapping("{id}")
    public String queryOne(@PathVariable String id) {
        ObjectMapper om = new ObjectMapper();
        String json = null;
        AmsBatch amsBatch = iAmsBatchService.selectAmsBatchById(id);
        try {
            json = om.writeValueAsString(amsBatch);
        } catch (JsonProcessingException e) {
            logger.error("Json数据格式化失败", e.getMessage());
        }
        return json;
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
     * 导出档案登记列表
     */
    @RequiresPermissions("archManage:myTransApply:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AmsArchivesVO amsArchives) {
        List<AmsArchives> list = amsArchivesService.selectAmsArchivesList(amsArchives);
        ExcelUtil<AmsArchives> util = new ExcelUtil<>(AmsArchives.class);
        return util.exportExcel(list, "amsArchives");
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
    @RequiresPermissions("archManage:myTransApply:add")
    @Log(title = "档案登记", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AmsArchives amsArchives) {
        return toAjax(amsArchivesService.insertAmsArchives(amsArchives));
    }

    /**
     * 修改档案登记
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        AmsArchives amsArchives = amsArchivesService.selectAmsArchivesById(id);
        mmap.put("amsArchives", amsArchives);
        return prefix + "/edit";
    }

    /**
     * 修改保存档案登记
     */
    @RequiresPermissions("archManage:myTransApply:edit")
    @Log(title = "档案登记", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AmsArchives amsArchives) {
        return toAjax(amsArchivesService.updateAmsArchives(amsArchives));
    }

    /**
     * 删除档案登记
     */
    @RequiresPermissions("archManage:myTransApply:remove")
    @Log(title = "档案登记", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(amsArchivesService.deleteAmsArchivesByIds(ids));
    }

}
