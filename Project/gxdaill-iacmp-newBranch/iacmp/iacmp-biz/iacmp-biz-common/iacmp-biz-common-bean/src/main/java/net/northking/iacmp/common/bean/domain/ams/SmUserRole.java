package net.northking.iacmp.common.bean.domain.ams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.core.domain.BaseEntity;

/**
 * 用户角色表 sm_user_role
 *
 * @author wxy
 * @date 2019-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SmUserRole extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 用户
     */
    private String userId;
    /**
     * 逻辑主键
     */
    private String id;
    /**
     * 角色
     */
    private String roleId;
    /**
     * 主键
     */
    private Long userRoleId;

}
