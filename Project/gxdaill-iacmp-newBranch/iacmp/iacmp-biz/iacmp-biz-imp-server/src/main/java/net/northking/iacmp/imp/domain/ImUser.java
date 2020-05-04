package net.northking.iacmp.imp.domain;

import lombok.Data;

/**
 * 客户表 im_user
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Data
public class ImUser {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 客户号
     */
    private String userCode;
    /**
     * 客户姓名
     */
    private String userName;
    /**
     * 客户证件号码
     */
    private String idCard;
    /**
     * 证件类型
     */
    private String cardType;
    /**
     * 预留字段1
     */
    private String biz1;
    /**
     * 预留字段2你
     */
    private String biz2;

}
