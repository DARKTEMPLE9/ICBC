package net.northking.iacmp.framework.shiro.realm;

import java.util.*;

import net.northking.iacmp.config.ApplicationContextHelper;
import net.northking.iacmp.constant.RoleConstants;
import net.northking.iacmp.system.domain.Agent;
import net.northking.iacmp.system.domain.SysMenu;
import net.northking.iacmp.system.domain.SysRole;
import net.northking.iacmp.system.service.IAgentService;
import net.northking.iacmp.system.service.ISysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import net.northking.iacmp.execption.user.CaptchaException;
import net.northking.iacmp.execption.user.RoleBlockedException;
import net.northking.iacmp.execption.user.UserBlockedException;
import net.northking.iacmp.execption.user.UserNotExistsException;
import net.northking.iacmp.execption.user.UserPasswordNotMatchException;
import net.northking.iacmp.execption.user.UserPasswordRetryLimitExceedException;
import net.northking.iacmp.system.domain.SysUser;
import net.northking.iacmp.system.service.ISysMenuService;
import net.northking.iacmp.system.service.ISysRoleService;

import net.northking.iacmp.framework.shiro.service.SysLoginService;
import net.northking.iacmp.framework.util.ShiroUtils;

/**
 * 自定义Realm 处理登录 权限
 *
 * @author wxy
 */
public class UserRealm extends AuthorizingRealm {
    private static final Logger log = LoggerFactory.getLogger(UserRealm.class);

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private SysLoginService loginService;

    @Autowired
    private IAgentService amsAgentService;

    @Autowired
    private ISysUserService sysUserService;

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        SysUser user = ShiroUtils.getSysUser();
        // 角色列表
        Set<String> roles = new HashSet<>();
        ;
        // 功能列表
        Set<String> menus = new HashSet<>();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        roles = roleService.selectRoleKeys(user.getUserId());

        // 管理员拥有所有权限
        if (roles.contains(RoleConstants.CMS_ROLE_ADMIN)) {
            info.addRole(RoleConstants.CMS_ROLE_ADMIN);
            info.addStringPermission(RoleConstants.CMS_ROLE_ALL_PERMISSION);
        } else {
            //操作代理
            Agent amsAgent = new Agent();
            //代理人号
            amsAgent.setAgentCode(user.getLoginName());

            Object cmsApplication = ApplicationContextHelper.getBean("ApplicationNameBean");
            if (cmsApplication.getClass().getName().contains("Ams")) {
                // ams
                // 查询代理信息
                List<Agent> agentList = amsAgentService.selectAmsAgentList(amsAgent);
                if (agentList != null && agentList.size() > 0) {
                    //添加代理人角色
                    roles = roleService.selectRoleKeys(user.getUserId());
                    //添加代理人菜单
                    menus = menuService.selectPermsByUserId(user.getUserId());
                    for (int i = 0; i < agentList.size(); i++) {
                        //代理开始日期
                        Date startDate = agentList.get(i).getAgentStartDate();
                        //代理结束日期
                        Date endDate = agentList.get(i).getAgentEndDate();
                        //代理权限在当日24点[即次日0点]结束
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(endDate);
                        calendar.add(Calendar.DAY_OF_MONTH, 1);
                        //当前日期
                        Date now = new Date();
                        //判断代理是否结束
                        if (now.getTime() >= startDate.getTime()) {
                            if (now.getTime() < calendar.getTimeInMillis()) {
                                //委托人号
                                String trustorCode = agentList.get(i).getTrustorCode();
                                //委托人用户
                                SysUser trustorUser = new SysUser();
                                trustorUser.setLoginName(trustorCode);
                                List<SysUser> userList = sysUserService.selectUserList(trustorUser);
                                if (userList.size() > 0) {
                                    trustorUser = userList.get(0);
                                    Long userId = trustorUser.getUserId();
                                    //添加委托人角色
                                    roles.addAll(roleService.selectRoleKeys(userId));
                                }
                                //添加委托人菜单
                                menus.addAll(menuService.selectPermsByUserId(trustorUser.getUserId()));

                            } else {
                                if ("1".equals(agentList.get(i).getAgentStatus())) {
                                    Agent agent = agentList.get(i);
                                    agent.setAgentStatus("0");
                                    amsAgentService.updateAmsAgent(agent);
                                }
                            }
                        } else {//当前时间小于代理开始时间（代理未开始）
                            Agent agent = agentList.get(i);
                            agent.setAgentStatus("2");
                            amsAgentService.updateAmsAgent(agent);
                        }
                    }
                    if (!roles.isEmpty()) {
                        // 角色加入AuthorizationInfo认证对象
                        info.setRoles(roles);
                        // 权限加入AuthorizationInfo认证对象
                        info.setStringPermissions(menus);
                        return info;
                    }
                }

                roles = roleService.selectRoleKeys(user.getUserId());
                menus = menuService.selectPermsByUserId(user.getUserId());
                // 角色加入AuthorizationInfo认证对象
                info.setRoles(roles);
                // 权限加入AuthorizationInfo认证对象
                info.setStringPermissions(menus);
            } else {
                // cms
                roles = roleService.selectRoleKeys(user.getUserId());
                menus = menuService.selectPermsByUserId(user.getUserId());
                // 角色加入AuthorizationInfo认证对象
                info.setRoles(roles);
                // 权限加入AuthorizationInfo认证对象
                info.setStringPermissions(menus);
            }
        }
        return info;
    }

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        String password = null;
        if (upToken.getPassword() != null) {
            password = new String(upToken.getPassword());
        }

        SysUser user = null;
        try {
            user = loginService.login(username, password);
        } catch (CaptchaException e) {
            throw new AuthenticationException(e.getMessage(), e);
        } catch (UserNotExistsException e) {
            throw new UnknownAccountException(e.getMessage(), e);
        } catch (UserPasswordNotMatchException e) {
            throw new IncorrectCredentialsException(e.getMessage(), e);
        } catch (UserPasswordRetryLimitExceedException e) {
            throw new ExcessiveAttemptsException(e.getMessage(), e);
        } catch (UserBlockedException e) {
            throw new LockedAccountException(e.getMessage(), e);
        } catch (RoleBlockedException e) {
            throw new LockedAccountException(e.getMessage(), e);
        } catch (Exception e) {
            log.info("对用户[" + username + "]进行登录验证..验证未通过{}", e.getMessage());
            throw new AuthenticationException(e.getMessage(), e);
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
        return info;
    }

    /**
     * 清理缓存权限
     */
    public void clearCachedAuthorizationInfo() {
        this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }
}
