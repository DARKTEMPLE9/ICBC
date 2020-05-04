package net.northking.iacmp.imp.domain;

import lombok.Data;

/**
 * 接入系统表 im_access_system
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Data
public class ImAccessSystem {
    private static final long serialVersionUID = 1L;

    /**
     * 逻辑主键
     */
    private String id;
    /**
     * 系统编码
     */
    private String code;
    /**
     * 系统名称
     */
    private String name;
    /**
     * 身份验证信息
     */
    private String authentInfo;
    /**
     * 备注
     */
    private String remark;
    /**
     * 服务器地址
     */
    private String ip;
    /**
     * 系统标识
     */
    private Integer sysFlagInt;
    /**
     * 主键
     */
    private Long accId;

}
