package net.northking.iacmp.cms.web.systask;

import net.northking.iacmp.cms.web.controller.userinfo.LadpGainInfoUtil;
import net.northking.iacmp.constant.UserConstants;
import net.northking.iacmp.execption.BusinessException;
import net.northking.iacmp.framework.shiro.service.SysPasswordService;
import net.northking.iacmp.framework.util.ShiroUtils;
import net.northking.iacmp.system.domain.SysDept;
import net.northking.iacmp.system.domain.SysUser;
import net.northking.iacmp.system.service.ISysDeptService;
import net.northking.iacmp.system.service.ISysUserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author：Yanqingyu
 * @ClassName:AsyUserTask
 * @Description：定时同步AD域用户
 * @Date：Create in 10:32 AM2019/12/27
 * @Modified by:
 * @Version:1.0
 */
@Component("asyUserTask")
public class AsyUserTask {

    @Autowired
    private ISysUserService userService;

    @Autowired
    private SysPasswordService passwordService;

    @Autowired
    private ISysDeptService sysDeptService;

    @Autowired
    private LadpGainInfoUtil ladpGainInfoUtil;

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(AsyUserTask.class);

    public void asyUserTask() {
        //获取admin用户，作为创建用户及机构信息
        SysUser admin = userService.selectUserByLoginName("zhangxinxing");
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
            if (admin != null) {
                dept.setUpdateBy(admin.getLoginName());
                dept.setUpdateTime(new Date());
            }
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
            if (admin != null) {
                user.setUpdateBy(admin.getLoginName());
                user.setUpdateTime(new Date());
            }
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
    }

}
