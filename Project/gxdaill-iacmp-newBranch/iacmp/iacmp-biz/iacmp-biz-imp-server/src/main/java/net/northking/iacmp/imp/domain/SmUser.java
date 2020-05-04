package net.northking.iacmp.imp.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户表 sm_user
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Data
public class SmUser {
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
    private String password;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date lastLogin;
    /**
     * 主键
     */
    private Long userId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date createTime;

}
