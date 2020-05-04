package net.northking.iacmp.cms.web.controller.system;


import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.cms.web.controller.userinfo.LadpGainInfoUtil;
import net.northking.iacmp.constant.UserConstants;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.execption.BusinessException;
import net.northking.iacmp.framework.shiro.service.SysPasswordService;
import net.northking.iacmp.framework.util.ShiroUtils;
import net.northking.iacmp.system.domain.SysDept;
import net.northking.iacmp.system.domain.SysUser;
import net.northking.iacmp.system.service.ISysDeptService;
import net.northking.iacmp.system.service.ISysPostService;
import net.northking.iacmp.system.service.ISysRoleService;
import net.northking.iacmp.system.service.ISysUserService;
import net.northking.iacmp.utils.StringUtils;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 用户信息
 *
 * @author wxy
 */
@Controller
@RequestMapping("/system/user")
public class SysUserController extends BaseController {
    private String prefix = "system/user";

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysPostService postService;

    @Autowired
    private SysPasswordService passwordService;

    @Autowired
    private ISysDeptService sysDeptService;

    @Autowired
    private LadpGainInfoUtil ladpGainInfoUtil;

    @RequiresPermissions("system:user:view")
    @GetMapping()
    public String user() {
        return prefix + "/user";
    }

    @RequiresPermissions("system:user:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysUser user) {
        startPage();
        List<SysUser> list = userService.selectUserList(user);
        return getDataTable(list);
    }

    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:user:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(String userIds) {
        List<SysUser> userList = new ArrayList<>();
        if (userIds != null && !"".equals(userIds)) {
            String[] ids = userIds.split(",");
            for (int i = 0; i < ids.length; i++) {
                Long id = Long.parseLong(ids[i]);
                SysUser user = userService.selectUserById(id);
                userList.add(user);
            }
        } else {
            SysUser user = new SysUser();
            List<SysUser> users = userService.selectUserList(user);
            for (SysUser sysUser1 : users) {
                userList.add(sysUser1);
            }
        }
        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        return util.exportExcel(userList, "用户数据");
    }

    @Log(title = "用户管理", businessType = BusinessType.IMPORT)
    @RequiresPermissions("system:user:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<SysUser> util = new ExcelUtil<>(SysUser.class);
        List<SysUser> userList = util.importExcel(file.getInputStream());
        String operName = ShiroUtils.getSysUser().getLoginName();
        String message = userService.importUser(userList, updateSupport, operName);
        return AjaxResult.success(message);
    }

    @RequiresPermissions("system:user:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate() {
        ExcelUtil<SysUser> util = new ExcelUtil<>(SysUser.class);
        return util.importTemplateExcel("用户数据");
    }

    /**
     * 新增用户
     */
    @GetMapping("/add")
    public String add(ModelMap mmap) {
        mmap.put("roles", roleService.selectRoleAll());
        mmap.put("posts", postService.selectPostAll());
        return prefix + "/add";
    }

    /**
     * 新增保存用户
     */
    @RequiresPermissions("system:user:add")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated SysUser user) {
        if (UserConstants.USER_NAME_NOT_UNIQUE.equals(userService.checkLoginNameUnique(user.getLoginName()))) {
            return error(" 新增用户" + user.getLoginName() + " 失败，登录账号已存在");
        } else if (UserConstants.USER_PHONE_NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
            return error(" 新增用户" + user.getLoginName() + " 失败，手机号码已存在");
        } else if (UserConstants.USER_EMAIL_NOT_UNIQUE.equals(userService.checkEmailUnique(user))) {
            return error(" 新增用户" + user.getLoginName() + " 失败，邮箱账号已存在");
        }
        user.setSalt(ShiroUtils.randomSalt());
        user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
        user.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(userService.insertUser(user));
    }

    /**
     * 修改用户
     */
    @GetMapping("/edit/{userId}")
    public String edit(@PathVariable("userId") Long userId, ModelMap mmap) {
        mmap.put("user", userService.selectUserById(userId));
        mmap.put("roles", roleService.selectRolesByUserId(userId));
        mmap.put("posts", postService.selectPostsByUserId(userId));
        return prefix + "/edit";
    }

    /**
     * 修改保存用户
     */
    @RequiresPermissions("system:user:edit")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysUser user) {
        if (StringUtils.isNotNull(user.getUserId()) && SysUser.isAdmin(user.getUserId())) {
            return error("不允许修改超级管理员用户");
        } else if (UserConstants.USER_PHONE_NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
            return error("修改用户" + user.getLoginName() + " 失败，手机号码已存在");
        } else if (UserConstants.USER_EMAIL_NOT_UNIQUE.equals(userService.checkEmailUnique(user))) {
            return error("修改用户" + user.getLoginName() + " 失败，邮箱账号已存在");
        }
        user.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(userService.updateUser(user));
    }

    @RequiresPermissions("system:user:resetPwd")
    @Log(title = "重置密码", businessType = BusinessType.UPDATE)
    @GetMapping("/resetPwd/{userId}")
    public String resetPwd(@PathVariable("userId") Long userId, ModelMap mmap) {
        mmap.put("user", userService.selectUserById(userId));
        return prefix + "/resetPwd";
    }

    @RequiresPermissions("system:user:resetPwd")
    @Log(title = "重置密码", businessType = BusinessType.UPDATE)
    @PostMapping("/resetPwd")
    @ResponseBody
    public AjaxResult resetPwdSave(SysUser user) {
        user.setSalt(ShiroUtils.randomSalt());
        user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
        if (userService.resetUserPwd(user) > 0) {
            if (ShiroUtils.getUserId() == user.getUserId()) {
                ShiroUtils.setSysUser(userService.selectUserById(user.getUserId()));
            }
            return success();
        }
        return error();
    }

    @RequiresPermissions("system:user:remove")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        try {
            return toAjax(userService.deleteUserByIds(ids));
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }

    /**
     * 校验用户名
     */
    @PostMapping("/checkLoginNameUnique")
    @ResponseBody
    public String checkLoginNameUnique(SysUser user) {
        return userService.checkLoginNameUnique(user.getLoginName());
    }

    /**
     * 校验手机号码
     */
    @PostMapping("/checkPhoneUnique")
    @ResponseBody
    public String checkPhoneUnique(SysUser user) {
        return userService.checkPhoneUnique(user);
    }

    /**
     * 校验email邮箱
     */
    @PostMapping("/checkEmailUnique")
    @ResponseBody
    public String checkEmailUnique(SysUser user) {
        return userService.checkEmailUnique(user);
    }

    /**
     * 用户状态修改
     */
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:user:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(SysUser user) {
        return toAjax(userService.changeStatus(user));
    }

    /**
     * 编辑辅部门
     *
     * @param loginName
     * @param mmap
     * @return
     */
    @GetMapping("/editAuxiliaryDept/{loginName}")
    public String editAuxiliaryDept(@PathVariable String loginName, ModelMap mmap) {
        mmap.put("userId", loginName);
        return prefix + "/deptTree";
    }

    /**
     * 修改保存用户
     */
    @RequiresPermissions("system:user:edit")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PostMapping("/editAuxiliaryDept")
    @ResponseBody
    public AjaxResult editAuxiliaryDept(SysUser user) {
        user.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(userService.updateUserInfo(user));
    }

    @GetMapping("/dataUserDeptSyn")
    @Log(title = "组织信息同步", businessType = BusinessType.UPDATE)
    @ResponseBody
    public AjaxResult dataSyn() throws BusinessException {
        //获取admin用户，作为创建用户及机构信息
        SysUser admin = ShiroUtils.getSysUser();
        List<SysUser> userList;
        List<SysDept> deptList;
        try {
            //远程获取用户信息
            userList = ladpGainInfoUtil.initUserInfo();
            deptList = ladpGainInfoUtil.initDeptInfo();
            logger.info("------ad域拉取用户完毕，部门数：" + deptList.size() + ",用户数：" + userList.size() + "-----");
        } catch (Exception e) {
            logger.error("拉取AD域用户及部门信息失败", e);
            throw new BusinessException("拉取AD域用户及部门信息失败！");
        }
        //初始化部门信息
        List<String> deptAd = new ArrayList<>();
        Integer oneDeptOrderNum = 0;
        for (SysDept dept : deptList) {
            dept.setStatus("0");
            dept.setDelFlag("0");
            if (dept.getParentDeptId() == 0) {
                dept.setOrderNum((oneDeptOrderNum++).toString());
            } else {
                Integer num = sysDeptService.selectDeptCount(dept.getParentDeptId()) + 1;
                dept.setOrderNum(num.toString());
            }
            dept.setCreateBy(admin.getLoginName());
            dept.setCreateTime(new Date());
            dept.setUpdateBy(admin.getLoginName());
            dept.setUpdateTime(new Date());
            SysDept sysDept = sysDeptService.selectDeptByDeptName(dept.getDeptName());
            if (sysDept != null) {
                sysDeptService.updateDept(dept);
            } else {
                dept.setDeptId(null);
                if (dept.getParentDeptId() != null && !"0".equalsIgnoreCase(dept.getParentDeptId().toString())) {
                    logger.info("-----存在父部门:" + dept.getParentName() + "，新增部门：" + dept.getDeptName() + "------");
                    SysDept parentDept = null;
                    //获取父部门名称
                    for (int i = 0; i < deptList.size(); i++) {
                        if (dept.getParentDeptId() == deptList.get(i).getDeptId()) {
                            parentDept = deptList.get(i);
                        }
                    }
                    parentDept = sysDeptService.selectDeptByDeptName(parentDept.getDeptName());
                    dept.setParentDeptId(parentDept.getDeptId());
                    dept.setAncestors("0," + parentDept.getDeptId());

                }
                sysDeptService.insertDept(dept);
            }
            deptAd.add(dept.getDeptName());
        }
        List<String> sysDepts = sysDeptService.selectDeptName();
        sysDepts.removeAll(deptAd);
        for (String deptName : sysDepts) {
            sysDeptService.changeStatusBySysDeptName(deptName);
        }

        List<String> adList = new ArrayList<>();
        //初始化用户
        for (SysUser user : userList) {

            if (user == null) {
                continue;
            }
            if (user.getPhonenumber() != null && user.getPhonenumber().getBytes().length > 11) {
                continue;
            }
            user.setSalt(ShiroUtils.randomSalt());
            user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
            if (user.getStatus() == null) {
                user.setStatus(UserConstants.NORMAL);
            }
            user.setSex("2");
            user.setDelFlag("0");
            user.setCreateBy(admin.getLoginName());
            user.setCreateTime(new Date());
            user.setUpdateBy(admin.getLoginName());
            user.setUpdateTime(new Date());
            Long[] roleIds = new Long[]{2l};
            user.setRoleIds(roleIds);

            SysUser sysUser = userService.selectUserByLoginName(user.getLoginName());
            if (sysUser == null) {
                user.setUserId(null);
                userService.insertUser(user);
            } else {
                userService.updateUserInfo(user);
            }
            String adLoginName = "";
            if (!"".equals(user.getLoginName())) {
                adLoginName = user.getLoginName();
            }
            adList.add(adLoginName);
        }
        List<String> dbList = userService.selectLoginName();
        dbList.removeAll(adList);
        for (String loginName : dbList) {
            if ("admin".equalsIgnoreCase(loginName)) {
                continue;
            }
            userService.changeStatusByLoginName(loginName);
        }
        return success();
    }
}