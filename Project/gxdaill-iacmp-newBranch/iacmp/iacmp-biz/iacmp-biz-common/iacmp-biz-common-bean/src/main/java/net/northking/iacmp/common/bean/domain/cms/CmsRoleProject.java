package net.northking.iacmp.common.bean.domain.cms;


import lombok.Data;
import net.northking.iacmp.core.domain.BaseEntity;

/**
 * 项目权限关联表 cms_role_project
 *
 * @author qingyu.yan
 * @date 2019-11-20
 */
@Data
public class CmsRoleProject extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * pms项目ID
     */
    private Long pmsBatchId;

}
