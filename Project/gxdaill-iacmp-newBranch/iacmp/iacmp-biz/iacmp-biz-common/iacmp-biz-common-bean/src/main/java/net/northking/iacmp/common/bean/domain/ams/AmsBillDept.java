package net.northking.iacmp.common.bean.domain.ams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.core.domain.BaseEntity;

/**
 * 部门档案配置表 ams_bill_dept
 *
 * @author wxy
 * @date 2019-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AmsBillDept extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;
    /**
     * 类型名称
     */
    private String name;
    /**
     * 类型编码
     */
    private String code;
    /**
     * 关联机构
     */
    private String sysId;
    /**
     * 类型排序
     */
    private String billOrder;

    /**
     * 部门档案主键
     */
    private Long billDeptId;
}
