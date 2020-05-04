package net.northking.iacmp.common.bean.domain.cms;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.core.domain.BaseEntity;

/**
 * 角色与分类关联表 cms_role_bill
 *
 * @author qingyu.yan
 * @date 2019-08-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CmsRoleBill extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 分类ID
     */
    private Integer billId;
    /**
     * 权限类型 R读 W写
     */
    private String authType;
    /**
     * 主键
     */
    private Long id;
}
