package net.northking.iacmp.framework.shiro.realm;

import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import lombok.SneakyThrows;
import net.northking.iacmp.common.bean.vo.ams.GasSmUserVO;
import net.northking.iacmp.constant.RoleConstants;
import net.northking.iacmp.framework.shiro.service.SysLoginService;
import net.northking.iacmp.framework.util.ShiroUtils;
import net.northking.iacmp.system.domain.SysUser;
import net.northking.iacmp.system.service.ISysMenuService;
import net.northking.iacmp.system.service.ISysRoleService;
import net.northking.iacmp.system.service.ISysUserService;
import net.northking.iacmp.utils.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;


public class ShiroLdapRealm extends AuthorizingRealm {
    private static final Logger logger = LoggerFactory.getLogger(ShiroLdapRealm.class);
    @Value("${ladp.ladpUrl}")
    private String ldapUrl;

    @Value("${ladp.searchFilter}")
    private String searchFilter;

    @Value("${ladp.searchBaseBank}")
    private String searchBase;

    @Value("${ladp.loginSuffix}")
    private String loginSuffix;

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private SysLoginService loginService;

    public ShiroLdapRealm() {
        setAuthenticationTokenClass(UsernamePasswordToken.class);
    }

    /**
     * 认证回调函数,登录时调用.
     */
    @SneakyThrows
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
        UsernamePasswordToken authcToken = (UsernamePasswordToken) token;
        //验证用户名
        String username = authcToken.getUsername();
        String password = String.valueOf(authcToken.getPassword());

        if (StringUtils.isBlank(username)) {
            throw new AuthenticationException("用户名不可以为空");
        }
        /*Hashtable<String, Object> env = new Hashtable<>();
        String name = username +"@"+ loginSuffix ;
        logger.info("-----ad域地址：:" + ldapUrl);
        logger.info("-----ad域用户名：:" + name);
        logger.info("-----ad域searchBase：:" + searchBase);
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");//LDAP工厂类
        env.put(Context.SECURITY_AUTHENTICATION, "simple");//认证类型
        env.put(Context.SECURITY_PRINCIPAL, name);//用户名
        env.put(Context.SECURITY_CREDENTIALS, password);//密码
        env.put(Context.PROVIDER_URL, ldapUrl);//LDAP的地址：端口

	     LdapContext ctx ;
	     *//*try {
	     //1.3 登入LDAP
	            ctx = new InitialLdapContext(env, null); 
	            logger.info("AD域身份验证成功");
	            ctx.close();
                SysUser user = sysUserService.selectUserByLoginName(username);
                if(null==user){
                    throw new Exception("用户不存在") ;
                }
        		return  new SimpleAuthenticationInfo(user, password, getName());
	     }catch (CommunicationException e) {
	    	 logger.error("AD域连接失败",e);
             throw new AuthenticationException("用户不存在/密码错误");
         }catch(AuthenticationException e) {
	    	 logger.error("身份验证失败",e);
             throw new AuthenticationException("用户不存在/密码错误");
         }catch (NamingException e) {
	    	 logger.error("身份验证未知异常",e);
             throw new AuthenticationException("用户不存在/密码错误");
         }catch (Exception e) {
             logger.error("用户不存在",e);
             throw new AuthenticationException("用户不存在/密码错误");
         }*//*
        ctx = new InitialLdapContext(env, null);
        logger.info("AD域身份验证成功");
        ctx.close();*/
        SysUser user = sysUserService.selectUserByLoginName(username);
        if (null == user) {
            try {
                throw new Exception("用户不存在");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new SimpleAuthenticationInfo(user, password, getName());
    }


    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SysUser user = ShiroUtils.getSysUser();
        // 角色列表
        Set<String> roles;
        // 功能列表
        Set<String> menus;
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        roles = roleService.selectRoleKeys(user.getUserId());
        // 管理员拥有所有权限
        if (roles.contains(RoleConstants.CMS_ROLE_ADMIN)) {
            info.addRole(RoleConstants.CMS_ROLE_ADMIN);
            info.addStringPermission(RoleConstants.CMS_ROLE_ALL_PERMISSION);
        } else {
            roles = roleService.selectRoleKeys(user.getUserId());
            menus = menuService.selectPermsByUserId(user.getUserId());
            // 角色加入AuthorizationInfo认证对象
            info.setRoles(roles);
            // 权限加入AuthorizationInfo认证对象
            info.setStringPermissions(menus);
        }
        return info;
    }


    /**
     * 更新用户授权信息缓存.
     */
    public void clearCachedAuthorizationInfo(String principal) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
        clearCachedAuthorizationInfo(principals);
    }

    /**
     * 清理缓存权限
     */
    public void clearCachedAuthorizationInfo() {
        this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }

    /**
     * 清除所有用户授权信息缓存.
     */
    public void clearAllCachedAuthorizationInfo() {
        Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
        if (cache != null) {
            for (Object key : cache.keys()) {
                cache.remove(key);
            }
        }
    }
}
