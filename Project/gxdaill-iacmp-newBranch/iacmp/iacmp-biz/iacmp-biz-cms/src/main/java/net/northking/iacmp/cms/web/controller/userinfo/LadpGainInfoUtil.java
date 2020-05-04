package net.northking.iacmp.cms.web.controller.userinfo;

import net.northking.iacmp.constant.UserConstants;
import net.northking.iacmp.system.domain.SysDept;
import net.northking.iacmp.system.domain.SysUser;
import net.northking.iacmp.utils.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import java.util.*;

/**
 * @Author：Yanqingyu
 * @ClassName:GetUserInfoController
 * @Description：远程获取组织信息工具类
 * @Date：Create in 9:30 AM2019/8/7
 * @Modified by:
 * @Version:1.0
 */
@Component
public class LadpGainInfoUtil {

    /**
     * 手机号
     */
    private static final String MOBILE = "mobile";
    private static final String DISPLAY_NAME = "displayName";
    private static final String DISTINGUISHED_NAME = "distinguishedName";
    private static final String USER_ACCOUNT_CONTROL = "userAccountControl";


    @Value("${ladp.ladpUrl}")
    private String ldapUrl;

    @Value("${ladp.adminName}")
    private String adminName;

    @Value("${ladp.adminPassword}")
    private String adminPassword;

    @Value("${ladp.searchFilter}")
    private String searchFilter;

    @Value("${ladp.searchBaseBank}")
    private String searchBase;

    @Value("${ladp.initPwd}")
    private String initPwd;


    /**
     * 远程连接ladp
     *
     * @return
     * @throws NamingException
     */
    private LdapContext connetLDAP() throws NamingException {

        Hashtable<String, Object> env = new Hashtable<>();
        //用户名
        env.put(Context.SECURITY_PRINCIPAL, adminName);
        //密码
        env.put(Context.SECURITY_CREDENTIALS, adminPassword);
        //LDAP的地址：端口
        env.put(Context.PROVIDER_URL, ldapUrl);
        //LDAP工厂类
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        //认证类型
        env.put(Context.SECURITY_AUTHENTICATION, "simple");

        return new InitialLdapContext(env, null);
    }

    /**
     * 从AD域获取用户信息
     *
     * @return
     * @throws NamingException
     */
    public List<SysUser> getUserInfoToLadp() throws NamingException {
        return getUserInfo(null, null, null);
    }

    /**
     * 从AD域获取部门信息
     *
     * @return
     * @throws NamingException
     */
    public List<SysDept> getDeptInfoToLadp() throws NamingException {
        return getDeptInfo(null, null);
    }

    /**
     * 获取用户信息
     *
     * @param startUserId 起始用户id，之后递增
     * @param stair       起始一级部门id,之后递增
     * @param second      起始二级部门id,之后递增
     * @return
     * @throws NamingException
     */
    private List<SysUser> getUserInfo(Integer startUserId, Integer stair, Integer second) throws NamingException {

        List<SysUser> userList = new ArrayList<>();
        List<SysDept> deptList = getDeptInfo(stair, second);

        LdapContext ctx = connetLDAP();
        //设置搜索控制类
        SearchControls searchCtls = new SearchControls();
        //设置搜索域
        searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        //定制返回属性
        String[] returnedAtts = {"name", MOBILE, "mail", DISPLAY_NAME,
                DISTINGUISHED_NAME, USER_ACCOUNT_CONTROL};
        //设置返回属性集
        searchCtls.setReturningAttributes(returnedAtts);
        //获取结果集
        NamingEnumeration ret = ctx.search(searchBase, searchFilter, searchCtls);

        while (ret.hasMoreElements()) {

            SearchResult sr = (SearchResult) ret.next();
            Attributes attributes = sr.getAttributes();

            SysUser sysUser = new SysUser();
            //获得用户所属部门
            if (attributes.get(DISTINGUISHED_NAME) != null) {
                String distinguishedName = attributes.get(DISTINGUISHED_NAME).getAll().next().toString();
                String twoDeptName = distinguishedName.split(",")[1].split("=")[1];

                //获取用户名称（中文）
                String displayName = "";
                String userNameCh = "";
                if (attributes.get(DISPLAY_NAME) != null) {
                    displayName = attributes.get(DISPLAY_NAME).getAll().next().toString();
                    userNameCh = displayName.split("\\.")[0];
                    sysUser.setUserName(userNameCh);
                }

                //获取用户手机号
                String mobile = "";
                if (attributes.get(MOBILE) != null) {
                    mobile = attributes.get(MOBILE).getAll().next().toString();
                    sysUser.setPhonenumber(mobile);
                }

                //获取用户邮箱
                String mail = "";
                if (attributes.get("mail") != null) {
                    mail = attributes.get("mail").getAll().next().toString();
                    sysUser.setEmail(mail);
                }

                //获取用户名称
                String loginName = "";
                if (attributes.get("name") != null) {
                    loginName = attributes.get("name").getAll().next().toString();
                    sysUser.setLoginName(loginName);
                }
                //获取用户状态
                String status = "";
                if (attributes.get(USER_ACCOUNT_CONTROL) != null) {
                    status = attributes.get(USER_ACCOUNT_CONTROL).getAll().next().toString();
                    if ("512".equals(status)) {
                        sysUser.setStatus(UserConstants.NORMAL);//正常
                    } else {
                        sysUser.setStatus(UserConstants.USER_BLOCKED);//离职
                    }
                }

                //获取用户所在部门信息
                SysDept sysDept = getDeptByDeptName(deptList, twoDeptName);
                sysUser.setDept(sysDept);

                //获取用户部门ID
                if (stair != null && second != null) {
                    SysDept dept = getDeptByDeptName(deptList, twoDeptName);
                    Long deptId = null;
                    if (null != dept) {
                        deptId = dept.getDeptId();
                    }
                    sysUser.setDeptId(deptId);
                }
                //设置用户ID
                if (startUserId != null) {
                    Integer newStartUserId = startUserId++;
                    sysUser.setUserId(Long.valueOf(newStartUserId));
                }
                //设置初始化密码
                sysUser.setPassword(initPwd);
                userList.add(sysUser);
            }
        }

        return userList;
    }


    /**
     * 获取部门信息
     *
     * @param stair  一级部门
     * @param second 二级部门
     * @return
     * @throws NamingException
     */
    private List<SysDept> getDeptInfo(Integer stair, Integer second) throws NamingException, NullPointerException {

        List<SysDept> deptList = new ArrayList<>();
        LdapContext ctx = connetLDAP();
        //Create the search controls
        SearchControls searchCtls = new SearchControls();
        //Specify the search scope
        searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        //定制返回属性
        String[] returnedAtts = {DISTINGUISHED_NAME};
        //设置返回属性集
        searchCtls.setReturningAttributes(returnedAtts);

        NamingEnumeration ret = ctx.search(searchBase, searchFilter, searchCtls);
        while (ret.hasMoreElements()) {

            SearchResult sr = (SearchResult) ret.next();
            Attributes attributes = sr.getAttributes();

            String[] str = sr.getName().split("=");
            //一级部门名称
            String deptName = str[str.length - 1];
            //一级部门信息
            if (!isDept(deptList, deptName)) {

                SysDept sysDept = new SysDept();
                sysDept.setDeptName(deptName);
                sysDept.setAncestors("0");
                sysDept.setParentDeptId(Long.valueOf(0));

                if (stair != null && second != null) {
                    Integer newStair = stair++;
                    sysDept.setDeptId(Long.valueOf(newStair));
                }
                deptList.add(sysDept);
            }

            //判定是否存在二级部门
            String distinguishedName = attributes.get(DISTINGUISHED_NAME).getAll().next().toString();

            //将distinguishedName按逗号截开，若长度大于6则存在二级部门（若多级部门需判定层级条件重新编码）
            if (distinguishedName.split(",").length > 6) {
                String twoDeptName = distinguishedName.split(",")[1].split("=")[1];

                if (!isDept(deptList, twoDeptName)) {
                    SysDept sysDept = new SysDept();
                    if (stair != null && second != null) {
                        Integer newStair = second++;
                        sysDept.setDeptId(Long.valueOf(newStair));
                    }
                    sysDept.setDeptName(twoDeptName);
                    if (null != getDeptByDeptName(deptList, deptName)) {
                        SysDept dept = getDeptByDeptName(deptList, deptName);
                        if (dept == null) {
                            throw new NullPointerException("从AD域拉取部门,未手动设置ID");
                        }
                        sysDept.setParentDeptId(dept.getDeptId());
                    }

                    sysDept.setParentName(deptName);
                    if (stair != null && second != null) {
                        StringBuilder strBuilder = new StringBuilder(sysDept.getParentDeptId().toString());
                        String[] strDeptName = getAmceseorsNoDatabaseDate(sysDept, strBuilder, deptList).toString().split(",");
                        Collections.reverse(Arrays.asList(strDeptName));
                        sysDept.setAncestors(StringUtils.join(strDeptName, ","));
                    }

                    deptList.add(sysDept);
                }
            }
        }
        return deptList;
    }

    /**
     * 获得初始化组织部门信息(只在第一次创建时调用)
     * 起始一级部门id:1000 起始二级部门id:2000
     *
     * @return
     * @throws NamingException
     */
    public List<SysDept> initDeptInfo() throws NamingException, NullPointerException {
        return getDeptInfo(1000, 2000);
    }

    /**
     * 获得初始化组织用户信息(只在第一次创建时调用)
     * 起始用户id：100000  起始一级部门id:1000 起始二级部门id:2000
     *
     * @return
     * @throws NamingException
     */
    public List<SysUser> initUserInfo() throws NamingException, NullPointerException {
        return getUserInfo(100000, 1000, 2000);
    }


    /**
     * 递归获取部门祖级（父级---子级）需进行反转
     *
     * @param sysDept 用户当前部门
     * @param ret     返回结果
     * @return
     */
    private StringBuilder getAmceseorsNoDatabaseDate(SysDept sysDept, StringBuilder ret, List<SysDept> sysDeptList) throws NullPointerException {
        if (sysDept.getParentDeptId() == 0) {
            return ret;
        }
        //获取其父级部门
        SysDept dept = new SysDept();
        if (null != getDeptByDeptName(sysDeptList, sysDept.getParentName())) {
            dept = getDeptByDeptName(sysDeptList, sysDept.getParentName());
        }
        if (dept == null) {
            throw new NullPointerException("从AD域拉取部门,未手动设置ID");
        }
        ret.append("," + dept.getParentDeptId());

        return this.getAmceseorsNoDatabaseDate(dept, ret, sysDeptList);
    }

    /**
     * 判断是否已存在此部门
     *
     * @param deptList 部门列表
     * @param deptName 用户所在部门名称
     * @return
     */
    private boolean isDept(List<SysDept> deptList, String deptName) {

        for (SysDept sysDept : deptList) {
            if (deptName.equals(sysDept.getDeptName())) {
                return true;
            }
        }
        return false;

    }

    /**
     * 根据部门名称获取部门列表部门信息
     *
     * @param deptList
     * @param deptName
     * @return
     */
    private SysDept getDeptByDeptName(List<SysDept> deptList, String deptName) {

        for (SysDept sysDept : deptList) {
            if (deptName.equals(sysDept.getDeptName())) {
                return sysDept;
            }
        }
        return null;
    }

}
