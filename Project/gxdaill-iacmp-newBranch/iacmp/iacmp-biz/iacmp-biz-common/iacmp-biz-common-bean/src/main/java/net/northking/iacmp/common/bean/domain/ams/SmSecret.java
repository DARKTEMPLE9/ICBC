package net.northking.iacmp.common.bean.domain.ams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.core.domain.BaseEntity;

/**
 * 查询权限不对外公开表 sm_secret
 *
 * @author wxy
 * @date 2019-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SmSecret extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long secretId;
    /**
     * 逻辑主键
     */
    private String id;
    /**
     * 部门档案类型
     */
    private String arcBill;
    /**
     * 档案类型编号
     */
    private String arcBillCode;
    /**
     * 二级档案类型
     */
    private String arcBillDept;
    /**
     * 二级档案类型code
     */
    private String arcBillDeptCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArcBill() {
        return arcBill;
    }

    public void setArcBill(String arcBill) {
        this.arcBill = arcBill;
    }

    public String getArcBillCode() {
        return arcBillCode;
    }

    public void setArcBillCode(String arcBillCode) {
        this.arcBillCode = arcBillCode;
    }

    public String getArcBillDept() {
        return arcBillDept;
    }

    public void setArcBillDept(String arcBillDept) {
        this.arcBillDept = arcBillDept;
    }

    public String getArcBillDeptCode() {
        return arcBillDeptCode;
    }

    public void setArcBillDeptCode(String arcBillDeptCode) {
        this.arcBillDeptCode = arcBillDeptCode;
    }

    @Override
    public String toString() {
        return "SmSecret{" +
                "id='" + id + '\'' +
                ", arcBill='" + arcBill + '\'' +
                ", arcBillCode='" + arcBillCode + '\'' +
                ", arcBillDept='" + arcBillDept + '\'' +
                ", arcBillDeptCode='" + arcBillDeptCode + '\'' +
                '}';
    }
}
