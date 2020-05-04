package net.northking.iacmp.common.bean.domain.ams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.core.domain.BaseEntity;

/**
 * 库柜表 ams_cabinet
 *
 * @author wxy
 * @date 2019-08-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AmsCabinet extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long cabinetId;
    /**
     * id
     */
    private String id;
    /**
     * 库柜编码
     */
    private String code;
    /**
     * 库柜名称
     */
    private String name;
    /**
     * 库柜状态（0-未满;1-已满;）
     */
    private String status;
    /**
     * 库房名称
     */
    private String depId;
    /**
     * 库房编码
     */
    private String depCode;
    /**
     * 库房名称
     */
    private String depName;
    /**
     * 库房类型
     */
    private String depType;
    /**
     * 责任人
     */
    private String dutyMan;
    /**
     * 档案类型
     */
    private String arcType;
    /**
     * 库房状态（1-正常；0-废弃；）
     */
    private String depStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    public String getDepCode() {
        return depCode;
    }

    public void setDepCode(String depCode) {
        this.depCode = depCode;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getDepType() {
        return depType;
    }

    public void setDepType(String depType) {
        this.depType = depType;
    }

    public String getDutyMan() {
        return dutyMan;
    }

    public void setDutyMan(String dutyMan) {
        this.dutyMan = dutyMan;
    }

    public String getArcType() {
        return arcType;
    }

    public void setArcType(String arcType) {
        this.arcType = arcType;
    }

    public String getDepStatus() {
        return depStatus;
    }

    public void setDepStatus(String depStatus) {
        this.depStatus = depStatus;
    }

    @Override
    public String toString() {
        return "AmsCabinet{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", depId='" + depId + '\'' +
                ", depCode='" + depCode + '\'' +
                ", depName='" + depName + '\'' +
                ", depType='" + depType + '\'' +
                ", dutyMan='" + dutyMan + '\'' +
                ", arcType='" + arcType + '\'' +
                ", depStatus='" + depStatus + '\'' +
                '}';
    }
}
