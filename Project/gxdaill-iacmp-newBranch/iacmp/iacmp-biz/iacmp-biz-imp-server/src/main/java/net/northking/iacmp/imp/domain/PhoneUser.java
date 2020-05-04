package net.northking.iacmp.imp.domain;


import lombok.Data;

/**
 * 移动端用户表 phone_user
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Data
public class PhoneUser {
    private static final long serialVersionUID = 1L;

    /**
     * 用户主键
     */
    private String id;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 用户编码
     */
    private String userCode;
    /**
     * 电话号码
     */
    private String phoneNum;
    /**
     * 验证密匙
     */
    private String token;
    /**
     * 用户密码
     */
    private String userPass;
    /**
     * 主键
     */
    private Long pUserId;


}
