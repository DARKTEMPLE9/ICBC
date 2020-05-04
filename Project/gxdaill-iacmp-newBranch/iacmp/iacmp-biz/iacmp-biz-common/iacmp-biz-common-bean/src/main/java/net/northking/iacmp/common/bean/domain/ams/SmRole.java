package net.northking.iacmp.common.bean.domain.ams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.core.domain.BaseEntity;

import java.math.BigDecimal;

/**
 * 角色表 sm_role
 *
 * @author wxy
 * @date 2019-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SmRole extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 逻辑主键
     */
    private String id;
    /**
     * 角色代码
     */
    private String code;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色状态（0-正常；1-停用）
     */
    private BigDecimal status;
    /**
     * 角色类型（01-综合角色；02-特定角色；03=录入角色）
     */
    private String type;
    /**
     * 角色等级
     */
    private BigDecimal gRAD;
    /**
     * 备注(html编辑)
     */
    private String htmlContent;

}
