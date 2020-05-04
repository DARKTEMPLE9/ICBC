package net.northking.iacmp.framework.util;

import lombok.NonNull;
import net.northking.iacmp.framework.shiro.realm.ShiroLdapRealm;
import net.northking.iacmp.framework.shiro.realm.UserRealm;
import net.northking.iacmp.system.domain.SysUser;
import net.northking.iacmp.utils.StringUtils;
import net.northking.iacmp.utils.bean.BeanUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;

/**
 * shiro 工具类
 *
 * @author wxy
 */
public class ShiroUtils {
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    public static void logout() {
        getSubject().logout();
    }

    public static SysUser getSysUser() {
        SysUser user = null;
        Object obj = getSubject().getPrincipal();
        if (StringUtils.isNotNull(obj)) {
            user = new SysUser();
            BeanUtils.copyBeanProp(user, obj);
        }
        return user;
    }

    public static void setSysUser(SysUser user) {
        Subject subject = getSubject();
        PrincipalCollection principalCollection = subject.getPrincipals();
        String realmName = principalCollection.getRealmNames().iterator().next();
        PrincipalCollection newPrincipalCollection = new SimplePrincipalCollection(user, realmName);
        // 重新加载Principal
        subject.runAs(newPrincipalCollection);
    }

    public static void clearCachedAuthorizationInfo() {
        RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();
        if (rsm.getRealms().iterator().next() instanceof UserRealm) {
            UserRealm realm = (UserRealm) rsm.getRealms().iterator().next();
            realm.clearCachedAuthorizationInfo();
        } else if (rsm.getRealms().iterator().next() instanceof ShiroLdapRealm) {
            ShiroLdapRealm realm = (ShiroLdapRealm) rsm.getRealms().iterator().next();
            realm.clearCachedAuthorizationInfo();
        }
    }

    public static Long getUserId() {
        if (null != getSysUser()) {
            SysUser sysUser = getSysUser();
            if (sysUser == null) {
                return null;
            }
            return sysUser.getUserId().longValue();
        }
        return null;
    }

    public static String getLoginName() {
        if (null != getSysUser()) {
            SysUser sysUser = getSysUser();
            if (sysUser == null) {
                return null;
            }
            return sysUser.getLoginName();
        }
        return null;
    }

    public static String getIp() {
        return getSubject().getSession().getHost();
    }

    public static String getSessionId() {
        return String.valueOf(getSubject().getSession().getId());
    }

    /**
     * 生成随机盐
     */
    public static String randomSalt() {
        // 一个Byte占两个字节，此处生成的3字节，字符串长度为6
        SecureRandomNumberGenerator secureRandom = new SecureRandomNumberGenerator();
        String hex = secureRandom.nextBytes(3).toHex();
        return hex;
    }
}
