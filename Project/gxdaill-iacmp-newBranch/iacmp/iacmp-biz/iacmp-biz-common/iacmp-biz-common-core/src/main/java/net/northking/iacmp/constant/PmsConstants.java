package net.northking.iacmp.constant;


/**
 * @Author：Yinrui
 * @ClassName:PmsConstants
 * @Description：内容管理平台PMO常量
 * @Date：Create in 4:55 PM2019/11/19
 */
public class PmsConstants {

    /**
     * 百信银行简称
     */
    public static final String AIBANK = "AIBANK";
    /**
     * 管理员权限userId
     */
    public static final Long ADMIN_USER_ID = 1L;

    /**
     * 管理员权限roleKey
     */
    public static final String ADMIN_ROLE_KEY = "admin";

    /**
     * 系统管理员角色的roleName
     */
    public static final String ADMIN_ROLE_NAME = "系统管理员";

    /**
     * 科技项目经理权限roleKey
     */
    public static final String TECH_PROJECT_MANAGER = "technologyProjectManager";

    /**
     * 项目产品经理权限roleKey
     */
    public static final String PROUDUCT_MANAGER = "projectProductManager";
    /**
     * 文件展示形式 1-只显示最新
     */
    public static final String DISPLAY_NEW = "1";
    /**
     * 文件展示形式 2-展示历史版本
     */
    public static final String DISPLAY_HISTORY = "2";
    /**
     * 文件展示形式 3-按日期成对出现
     */
    public static final String DISPLAY_PAIRING = "3";
    /**
     * 是否为页面节点 0-是
     */
    public static final String LEFT = "0";
    /**
     * 是否为页面节点 1-不是
     */
    public static final String NOTLEFT = "1";

    /**
     * PMO的syscode
     */
    public static final String PMO_SYSCODE = "PMO";

    /**
     * PMO的authcode
     */
    public static final String PMO_AUTHCODE = "PMO";

    /**
     * 项目状态    0-未启动
     */
    public static final String PMO_STATUS_NORMAL = "0";
    /**
     * 项目状态    1-已废弃
     */
    public static final String PMO_STATUS_DISCARD = "1";
    /**
     * 项目状态    2-已结项
     */
    public static final String PMO_STATUS_FINISH = "2";
    /**
     * 项目状态    2-已结项
     */
    public static final String PMO_STATUS_DOING = "3";

    /**
     * 项目名称是否唯一的返回结果码
     */
    public static final String PROJECT_NAME_UNIQUE = "0";
    public static final String PROJECT_NAME_NOT_UNIQUE = "1";

    /**
     * 项目编号是否唯一的返回结果码
     */
    public static final String OPERATIONCODE_NAME_UNIQUE = "0";
    public static final String OPERATIONCODE_NAME_NOT_UNIQUE = "1";

    /**
     * 预算编号是否唯一的返回结果码
     */
    public static final String BUDGETID_NAME_UNIQUE = "0";
    public static final String BUDGETID_NAME_NOT_UNIQUE = "1";

    /**
     * 是否关联项目(1-是，0-否)
     */
    public static final String PMS_PROJECT_YES = "1";
    public static final String PMS_PROJECT_NO = "0";

    /**
     * 未知部门编号 0000,部门名称-未知
     */
    public static final String PMS_UNKNOWN_DEPT = "0000";
    public static final String PMS_UNKNOWN_DEPTNAME = "未知";
    /**
     * 无项目名称
     */
    public static final String PMS_UNKNOWN_PROJECTNAME = "无项目名";

    /**
     * 模型分类状态 1-启动，2-停用
     */
    public static final String PMS_BILL_ON = "1";
    public static final String PMS_BILL_OFF = "0";

}
