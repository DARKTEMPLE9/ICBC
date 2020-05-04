package net.northking.iacmp.common.bean.domain.ams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.core.domain.BaseEntity;

/**
 * 角色菜单表 sm_role_menu
 *
 * @author wxy
 * @date 2019-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SmRoleMenu extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 菜单id
     */
    private String menuId;
    /**
     * 逻辑主键
     */
    private String id;
    /**
     * 角色id
     */
    private String roleId;

}
