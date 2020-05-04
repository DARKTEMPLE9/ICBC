package net.northking.iacmp.common.bean.domain.ams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.core.domain.BaseEntity;

import java.util.Date;
import java.math.BigDecimal;

/**
 * 用户表 sm_user
 *
 * @author wxy
 * @date 2019-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SmUser extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 逻辑主键
     */
    private String id;
    /**
     * 用户代码
     */
    private String code;
    /**
     * 用户名称
     */
    private String name;
    /**
     * 所属机构
     */
    private String orgId;
    /**
     * 密码
     */
    private String pASSWORD;
    /**
     * 类型
     */
    private String type;
    /**
     * 用户状态（0-正常;99-停用）
     */
    private BigDecimal status;
    /**
     * 最后密码修改日期
     */
    private Date lastPassChgDate;
    /**
     * 当前登录ip
     */
    private String loginIp;
    /**
     * 所属项目
     */
    private String projectId;
    /**
     * 员工ID
     */
    private String proEmployeeId;
    /**
     * 最后登录时间
     */
    private Date lastLogin;
    /**
     * 用户角色
     */
    private String rOLES;

}
