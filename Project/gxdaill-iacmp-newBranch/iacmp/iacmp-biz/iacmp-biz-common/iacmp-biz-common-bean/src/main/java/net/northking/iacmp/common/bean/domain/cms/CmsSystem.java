package net.northking.iacmp.common.bean.domain.cms;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.core.domain.BaseEntity;

/**
 * 接入系统表 cms_system
 *
 * @author qingyu.yan
 * @date 2019-08-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CmsSystem extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 系统编码
     */
    private String sysCode;
    /**
     * 系统名称
     */
    private String sysName;
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
    private String sysIp;
    /**
     * 系统状态（0-启用，1-停用）
     */
    private String status;
    /**
     * 是否使用适配器(0--使用 1--不使用)
     */
    private String useAdapter;

    /**
     * 归档信息
     */
    private String arcInfo;
}
