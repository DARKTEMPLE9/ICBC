package net.northking.iacmp.common.bean.domain.ams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.core.domain.BaseEntity;

/**
 * 接入系统表 im_access_system
 *
 * @author wxy
 * @date 2019-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ImAccessSystem extends BaseEntity {
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
    private String iP;
    /**
     * 系统标识
     */
    private Integer sysFlagInt;
    /**
     * 主键
     */
    private Long accId;

}
