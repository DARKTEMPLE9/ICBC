package net.northking.iacmp.common.bean.domain.ams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.core.domain.BaseEntity;

/**
 * 手机用户表 phone_user
 *
 * @author wxy
 * @date 2019-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PhoneUser extends BaseEntity {
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
    private Long puserId;

}
