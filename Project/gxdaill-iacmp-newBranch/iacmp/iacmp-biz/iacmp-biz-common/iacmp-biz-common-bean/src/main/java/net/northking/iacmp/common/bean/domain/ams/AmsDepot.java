package net.northking.iacmp.common.bean.domain.ams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.core.domain.BaseEntity;

/**
 * 库房表 ams_depot
 *
 * @author wxy
 * @date 2019-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AmsDepot extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long depotId;
    /**
     * id
     */
    private String id;
    /**
     * 库房名称
     */
    private String name;
    /**
     * 库房代码
     */
    private String code;
    /**
     * 所属部门
     */
    private String orgNo;
    /**
     * 库柜总数
     */
    private Integer allNum;
    /**
     * 所属部门
     */
    private String orgName;
    /**
     * 状态（1-正常;0-废弃;）
     */
    private String status;
    /**
     * 管理员
     */
    private String admin;
    /**
     * 备注
     */
    private String remark;
    /**
     * 库房类型（10部门库房;20-总行库房;）
     */
    private String depotType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo;
    }

    public Integer getAllNum() {
        return allNum;
    }

    public void setAllNum(Integer allNum) {
        this.allNum = allNum;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDepotType() {
        return depotType;
    }

    public void setDepotType(String depotType) {
        this.depotType = depotType;
    }

    @Override
    public String toString() {
        return "AmsDepot{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", orgNo='" + orgNo + '\'' +
                ", allNum=" + allNum +
                ", orgName='" + orgName + '\'' +
                ", status='" + status + '\'' +
                ", admin='" + admin + '\'' +
                ", remark='" + remark + '\'' +
                ", depotType='" + depotType + '\'' +
                '}';
    }
}
