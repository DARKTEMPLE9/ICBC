package net.northking.iacmp.cms.web.controller.bill;


import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.cms.service.ICmsModelService;
import net.northking.iacmp.common.bean.domain.cms.CmsModel;
import net.northking.iacmp.common.bean.domain.cms.CmsRole;
import net.northking.iacmp.common.bean.dto.cms.CmsModelBillDTO;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.framework.util.ShiroUtils;
import net.northking.iacmp.utils.StringUtils;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 模型 信息操作处理
 *
 * @author qingyu.yan
 * @date 2019-08-27
 */
@Controller
@RequestMapping("/cms/cmsModel")
public class CmsModelController extends BaseController {
    private String prefix = "bill/cmsModel";

    @Autowired
    private ICmsModelService cmsModelService;

    @RequiresPermissions("cms:cmsModel:view")
    @GetMapping()
    public String cmsModel() {
        return prefix + "/cmsModel";
    }

    /**
     * 查询模型列表
     */
    @RequiresPermissions("cms:cmsModel:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CmsModel cmsModel) {
        startPage();
        List<CmsModel> list = cmsModelService.selectCmsModelList(cmsModel);
        return getDataTable(list);
    }


    /**
     * 导出模型列表
     */
    @RequiresPermissions("cms:cmsModel:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(String modelIds) {
        List<CmsModel> modelList = new ArrayList<>();
        if (modelIds != null && !"".equals(modelIds)) {
            String[] ids = modelIds.split(",");
            for (int i = 0; i < ids.length; i++) {
                Long id = Long.parseLong(ids[i]);
                CmsModel model = cmsModelService.selectCmsModelById(id);
                modelList.add(model);
            }
        } else {
            CmsModel model = new CmsModel();
            List<CmsModel> roles = cmsModelService.selectCmsModelList(model);
            for (CmsModel sysRole1 : roles) {
                modelList.add(sysRole1);
            }
        }
        ExcelUtil<CmsModel> util = new ExcelUtil<CmsModel>(CmsModel.class);
        return util.exportExcel(modelList, "模型数据");
    }

    /**
     * 导出模型列表
     */
    @RequiresPermissions("cms:cmsModel:export")
    @PostMapping("/exportModalDict")
    @ResponseBody
    public AjaxResult exportModalJson(String id) {
        List<CmsModelBillDTO> list = cmsModelService.selectCmsModelDTO(Long.valueOf(id));
        ExcelUtil<CmsModelBillDTO> util = new ExcelUtil<CmsModelBillDTO>(CmsModelBillDTO.class);
        return util.exportExcel(list, "cmsModelDict");

    }

    /**
     * 新增模型
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存模型
     */
    @RequiresPermissions("cms:cmsModel:add")
    @Log(title = "模型", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CmsModel cmsModel) {
        cmsModel.setCreateBy(ShiroUtils.getUserId().toString());
        return toAjax(cmsModelService.insertCmsModel(cmsModel));
    }

    /**
     * 修改模型
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        CmsModel cmsModel = cmsModelService.selectCmsModelById(id);
        mmap.put("model", cmsModel);
        return prefix + "/edit";
    }

    /**
     * 获取模型信息
     *
     * @param id
     * @param mmap
     * @return
     */
    @GetMapping("/search/{id}")
    public String search(@PathVariable("id") Long id, ModelMap mmap) {
        CmsModel cmsModel = cmsModelService.selectCmsModelById(id);
        mmap.put("cmsModel", cmsModel);
        return "cmsBill/cmsBill";
    }


    /**
     * 修改保存模型
     */
    @RequiresPermissions("cms:cmsModel:edit")
    @Log(title = "模型", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CmsModel cmsModel) {

        return toAjax(cmsModelService.updateCmsModel(cmsModel));
    }

    /**
     * 删除模型
     */
    @RequiresPermissions("cms:cmsModel:remove")
    @Log(title = "模型", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        if (StringUtils.isNull(ids)) {
            return null;
        }

        if (cmsModelService.selectCountProjectByModelId(ids) > 0) {
            return AjaxResult.warn("已有项目使用此模型,不允许删除");
        }

        return toAjax(cmsModelService.deleteCmsModelByIds(ids));
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
     * 校验模型名称
     */
    @PostMapping("/checkModelNameUnique")
    @ResponseBody
    public String checkModelNameUnique(CmsModel model) {
        return cmsModelService.checkModelNameUnique(model);
    }

    /**
     * 校验模型名称
     */
    @PostMapping("/checkModelCodeUnique")
    @ResponseBody
    public String checkModelCodeUnique(CmsModel model) {
        return cmsModelService.checkModelCodeUnique(model);
    }

}
