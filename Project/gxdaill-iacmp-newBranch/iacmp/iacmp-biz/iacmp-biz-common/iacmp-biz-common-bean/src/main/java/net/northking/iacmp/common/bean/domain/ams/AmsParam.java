package net.northking.iacmp.common.bean.domain.ams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.core.domain.BaseEntity;

/**
 * 档案参数表 ams_param
 *
 * @author wxy
 * @date 2019-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AmsParam extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long paramId;

    /**
     * id
     */
    private String id;
    /**
     * 参数种类代码
     */
    private String paramSpeciesCode;
    /**
     * 参数种类名称
     */
    private String paramSpeciesName;
    /**
     * 参数代码
     */
    private String paramCode;
    /**
     * 参数名称
     */
    private String paramName;
    /**
     * 备注
     */
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParamSpeciesCode() {
        return paramSpeciesCode;
    }

    public void setParamSpeciesCode(String paramSpeciesCode) {
        this.paramSpeciesCode = paramSpeciesCode;
    }

    public String getParamSpeciesName() {
        return paramSpeciesName;
    }

    public void setParamSpeciesName(String paramSpeciesName) {
        this.paramSpeciesName = paramSpeciesName;
    }

    public String getParamCode() {
        return paramCode;
    }

    public void setParamCode(String paramCode) {
        this.paramCode = paramCode;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "AmsParam{" +
                "id='" + id + '\'' +
                ", paramSpeciesCode='" + paramSpeciesCode + '\'' +
                ", paramSpeciesName='" + paramSpeciesName + '\'' +
                ", paramCode='" + paramCode + '\'' +
                ", paramName='" + paramName + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
