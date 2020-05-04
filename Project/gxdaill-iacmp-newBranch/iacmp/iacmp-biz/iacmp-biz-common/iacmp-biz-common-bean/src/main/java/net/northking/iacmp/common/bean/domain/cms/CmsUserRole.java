package net.northking.iacmp.common.bean.domain.cms;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.core.domain.BaseEntity;

/**
 * 用户和角色关联表 cms_user_role
 *
 * @author qingyu.yan
 * @date 2019-08-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CmsUserRole extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 主键
     */
    private Long id;
}
