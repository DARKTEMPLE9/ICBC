package net.northking.iacmp.common.bean.vo.ams;

import net.northking.iacmp.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 部门表 gas_org_orgs
 *
 * @author wei.chen
 * @date 2019-09-23
 */
public class GasOrgOrgsVO extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 部门编码
     */
    private String code;
    /**
     * 部门名称
     */
    private String name;
    /**
     * 部门主键
     */
    private String pkOrg;

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

    public String getPkOrg() {
        return pkOrg;
    }

    public void setPkOrg(String pkOrg) {
        this.pkOrg = pkOrg;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("code", getCode())
                .append("name", getName())
                .append("pkOrg", getPkOrg())
                .toString();
    }
}
