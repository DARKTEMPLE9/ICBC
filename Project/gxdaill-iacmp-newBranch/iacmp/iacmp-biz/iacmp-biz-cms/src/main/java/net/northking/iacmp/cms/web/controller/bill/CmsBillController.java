package net.northking.iacmp.cms.web.controller.bill;


import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.cms.service.ICmsBillService;
import net.northking.iacmp.cms.service.ICmsFileService;
import net.northking.iacmp.cms.service.ICmsImageService;
import net.northking.iacmp.cms.web.util.CmsInitParamsUtil;
import net.northking.iacmp.common.bean.domain.cms.CmsBill;
import net.northking.iacmp.common.bean.domain.cms.CmsModel;
import net.northking.iacmp.common.bean.domain.cms.CmsRole;
import net.northking.iacmp.constant.CmsConstants;
import net.northking.iacmp.constant.PmsConstants;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.domain.Ztree;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.framework.util.ShiroUtils;
import net.northking.iacmp.utils.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * 分类 信息操作处理
 *
 * @author qingyu.yan
 * @date 2019-08-27
 */
@Controller
@RequestMapping("/cms/cmsBill")
public class CmsBillController extends BaseController {
    private String prefix = "bill/cmsBill";

    @Autowired
    private ICmsBillService cmsBillService;
    @Autowired
    private ICmsFileService cmsFileService;
    @Autowired
    private ICmsImageService cmsImageService;

    @RequiresPermissions("cms:cmsBill:view")
    @GetMapping()
    public String cmsBill() {
        return prefix + "/cmsBill";
    }

    /**
     * 查询分类列表
     */
    @RequiresPermissions("cms:cmsBill:list")
    @PostMapping("/list")
    @ResponseBody
    public List<CmsBill> list(CmsBill cmsBill) {
        Long userId = ShiroUtils.getUserId();
        return cmsBillService.selectCmsBillList(cmsBill, userId);
    }


    /**
     * 新增分类
     */
    @GetMapping("/add/{id}")
    public String add(@PathVariable("id") Long id, ModelMap mmap) {
        CmsBill bill = null;
        if (0 != id) {
            bill = cmsBillService.selectCmsBillById(id);
        } else {
            bill = new CmsBill();
            bill.setId(Long.valueOf(0));
            bill.setBillName("根节点");
        }
        mmap.put("bill", bill);
        return prefix + "/add";
    }

    /**
     * 新增保存分类
     */
    @RequiresPermissions("cms:cmsBill:add")
    @Log(title = "分类", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CmsBill cmsBill) {

        CmsBill parentBill = cmsBillService.selectCmsBillById(Long.valueOf(cmsBill.getParentId().toString()));
        if (parentBill != null) {
            parentBill.setLeaf(CmsConstants.BILL_IS_PARENT);
            cmsBillService.updateCmsBill(parentBill);
        }

        cmsBill.setAllPath(getAllPath(new StringBuffer(), cmsBill));
        cmsBill.setLeaf(CmsConstants.BILL_IS_LEAF);
        cmsBill.setCreateBy(ShiroUtils.getUserId().toString());
        cmsBill.setCreateTime(new Date());
        int i = cmsBillService.insertCmsBill(cmsBill);
        if (i > 0) {
            CmsInitParamsUtil.getCmsInitParamsUtil().refreshCacheCmsBill();
        }
        return toAjax(i);
    }

    /**
     * 获取祖级字符串
     *
     * @param cmsBill
     * @return
     */
    public String getAllPath(StringBuffer sb, CmsBill cmsBill) {

        if (sb == null) {
            sb = new StringBuffer();
        }

        if (cmsBill.getParentId() == null || "0".equals(cmsBill.getParentId().toString())) {
            sb.append("0");
            String[] str = sb.toString().split(",");
            Collections.reverse(Arrays.asList(str));
            return StringUtils.join(str, ",");
        }
        sb.append(cmsBill.getParentId()).append(",");
        CmsBill cmsParentBill = cmsBillService.selectCmsBillById(cmsBill.getParentId());
        return getAllPath(sb, cmsParentBill);
    }

    /**
     * 修改分类
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        CmsBill cmsBill = cmsBillService.selectCmsBillById(id);
        CmsBill cmsParentBill = cmsBillService.selectCmsBillById(cmsBill.getParentId());
        if (cmsParentBill == null) {
            cmsParentBill = new CmsBill();
            cmsParentBill.setId(0L);
            cmsParentBill.setBillName("根节点");
        }
        mmap.put("cmsBill", cmsBill);
        mmap.put("cmsParentBill", cmsParentBill);
        return prefix + "/edit";
    }

    /**
     * 修改保存分类
     */
    @RequiresPermissions("cms:cmsBill:edit")
    @Log(title = "分类", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CmsBill cmsBill) {
        int i = cmsBillService.updateCmsBill(cmsBill);
        if (!PmsConstants.LEFT.equals(cmsBill.getLeaf())) {
            i += cmsBillService.updateSubBillDisplay(cmsBill);
        }
        if (i > 0) {
            CmsInitParamsUtil.getCmsInitParamsUtil().refreshCacheCmsBill();
        }
        return toAjax(i);
    }

    /**
     * 删除分类
     */
    @Log(title = "删除分类", businessType = BusinessType.DELETE)
    @RequiresPermissions("cms:cmsBill:remove")
    @GetMapping("/remove/{billId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("billId") Long billId) {
        if (cmsBillService.selectCountBillByParentId(billId) > 0) {
            return AjaxResult.warn("存在子分类,不允许删除");
        }
        if (cmsBillService.selectCountRoleBillByBillId(billId) > 0) {
            return AjaxResult.warn("分类角色已分配,不允许删除");
        }
        if (cmsBillService.selectCountModelBillByBillId(billId) > 0) {
            return AjaxResult.warn("模型中已分配,不允许删除");
        }
        ShiroUtils.clearCachedAuthorizationInfo();
        int i = cmsBillService.deleteCmsBillById(billId);
        if (i > 0) {
            CmsInitParamsUtil.getCmsInitParamsUtil().refreshCacheCmsBill();
        }
        return toAjax(i);
    }

    @RequestMapping("/billTree")
    public String toTree() {

        return prefix + "/billTree";
    }

    /**
     * 加载角色分类列表树
     */
    @GetMapping("/roleBillTreeData")
    @ResponseBody
    public List<Ztree> roleMenuTreeData(CmsRole role) {
        Long userId = ShiroUtils.getUserId();
        List<Ztree> ztrees = cmsBillService.roleBillTreeData(role, userId);
        return ztrees;
    }

    /**
     * 加载模型分类列表树
     */
    @GetMapping("/modelBillTreeData")
    @ResponseBody
    public List<Ztree> modelBillTreeData(CmsModel model) {
        Long userId = ShiroUtils.getUserId();
        List<Ztree> ztrees = cmsBillService.modelBillTreeData(model, userId);
        return ztrees;
    }

    /**
     * 核对分类名称是否唯一
     *
     * @return
     */
    @PostMapping("/checkBillNameUnique")
    @ResponseBody
    public String checkBillNameUnique(CmsBill cmsBill) {

        return cmsBillService.checkBillNameUnique(cmsBill);
    }

    /**
     * 核对分类编码是否唯一
     *
     * @return
     */
    @PostMapping("/checkBillCodeUnique")
    @ResponseBody
    public String checkBillCodeUnique(CmsBill cmsBill) {
        return cmsBillService.checkBillCodeUnique(cmsBill);
    }


    /**
     * 更改分类是否可人工上传
     *
     * @return
     */
    @PostMapping("/updateManual")
    @ResponseBody
    public Map<String, Object> updateManual(String billId, String targetManual) {
        Map<String, Object> resMap = new HashMap<>();
        CmsBill cmsBill = cmsBillService.selectCmsBillById(Long.valueOf(billId));
        List<String> billIds = new ArrayList<>();
        billIds.add(billId);
        if (!PmsConstants.LEFT.equals(cmsBill.getLeaf())) {
            List<String> subBillIds = cmsBillService.selectIdByParentId(cmsBill.getId());
            billIds.addAll(subBillIds);
            resMap.put("subBillIds", subBillIds);
        }
        int i = cmsBillService.updateManualUpload(billIds, targetManual);
        resMap.put("count", i);
        return resMap;
    }
}
