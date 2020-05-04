package net.northking.iacmp.cms.web.controller.system;

import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.constant.UserConstants;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.framework.util.ShiroUtils;
import net.northking.iacmp.framework.util.SysConfigInitParamsUtils;
import net.northking.iacmp.system.domain.SysConfig;
import net.northking.iacmp.system.service.ISysConfigService;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 参数配置 信息操作处理
 *
 * @author wxy
 */
@Controller
@RequestMapping("/system/config")
public class SysConfigController extends BaseController {
    private String prefix = "system/config";

    @Autowired
    private ISysConfigService configService;

    @RequiresPermissions("system:config:view")
    @GetMapping()
    public String config() {
        return prefix + "/config";
    }

    /**
     * 查询参数配置列表
     */
    @RequiresPermissions("system:config:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysConfig config) {
        startPage();
        List<SysConfig> list = configService.selectConfigList(config);
        SysConfigInitParamsUtils.getSysConfigInitParamsUtils().refreshCacheSysConfig();
        return getDataTable(list);
    }

    @Log(title = "参数管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:config:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(String configIds) {
        List<SysConfig> configList = new ArrayList<>();
        if (configIds != null && !"".equals(configIds)) {
            String[] ids = configIds.split(",");
            for (int i = 0; i < ids.length; i++) {
                Long id = Long.parseLong(ids[i]);
                SysConfig config = configService.selectConfigById(id);
                configList.add(config);
            }
        } else {
            SysConfig config = new SysConfig();
            List<SysConfig> roles = configService.selectConfigList(config);
            for (SysConfig sysConfig : roles) {
                configList.add(sysConfig);
            }
        }
        ExcelUtil<SysConfig> util = new ExcelUtil<SysConfig>(SysConfig.class);
        return util.exportExcel(configList, "参数数据");
    }

    /**
     * 新增参数配置
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存参数配置
     */
    @RequiresPermissions("system:config:add")
    @Log(title = "参数管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated SysConfig config) {
        if (UserConstants.CONFIG_KEY_NOT_UNIQUE.equals(configService.checkConfigKeyUnique(config))) {
            return error("新增参数'" + config.getConfigName() + " 失败，参数键名已存在");
        }
        config.setCreateBy(ShiroUtils.getLoginName());
        int i = configService.insertConfig(config);
        if (i > 0) {
            SysConfigInitParamsUtils.getSysConfigInitParamsUtils().refreshCacheSysConfig();
        }
        return toAjax(i);
    }

    /**
     * 修改参数配置
     */
    @GetMapping("/edit/{configId}")
    public String edit(@PathVariable("configId") Long configId, ModelMap mmap) {
        mmap.put("config", configService.selectConfigById(configId));
        return prefix + "/edit";
    }

    /**
     * 修改保存参数配置
     */
    @RequiresPermissions("system:config:edit")
    @Log(title = "参数管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysConfig config) {
        if (UserConstants.CONFIG_KEY_NOT_UNIQUE.equals(configService.checkConfigKeyUnique(config))) {
            return error("修改参数'" + config.getConfigName() + " 失败，参数键名已存在");
        }
        config.setUpdateBy(ShiroUtils.getLoginName());
        String newUrl = StringEscapeUtils.unescapeHtml(config.getConfigValue());
        config.setConfigValue(newUrl);
        int i = configService.updateConfig(config);
        if (i > 0) {
            SysConfigInitParamsUtils.getSysConfigInitParamsUtils().refreshCacheSysConfig();
        }
        return toAjax(i);
    }

    /**
     * 删除参数配置
     */
    @RequiresPermissions("system:config:remove")
    @Log(title = "参数管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        int i = configService.deleteConfigByIds(ids);
        if (i > 0) {
            SysConfigInitParamsUtils.getSysConfigInitParamsUtils().refreshCacheSysConfig();
        }
        return toAjax(i);
    }

    /**
     * 校验参数键名
     */
    @PostMapping("/checkConfigKeyUnique")
    @ResponseBody
    public String checkConfigKeyUnique(SysConfig config) {
        return configService.checkConfigKeyUnique(config);
    }

    /**
     * 是否添加水印
     */
    @PostMapping("/getWaterMark")
    @ResponseBody
    public String getWaterMark() {
        SysConfig config = new SysConfig();
        config.setConfigKey("waterMarkStatus");
        List<SysConfig> list = configService.selectConfigList(config);
        String waterMarkStatus = "";
        if (list.size() > 0) {
            waterMarkStatus = list.get(0).getConfigValue();
        }
        return waterMarkStatus;
    }
}
