package net.northking.iacmp.common.bean.vo.ams;

import net.northking.iacmp.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 账簿表 gas_org_accountingbook
 *
 * @author wei.chen
 * @date 2019-09-23
 */
public class GasOrgAccountingbookVO extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 账簿编码
     */
    private String code;
    /**
     * 账簿名称
     */
    private String name;
    /**
     * 账簿主键
     */
    private String pkAccountingbook;

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

    public String getPkAccountingbook() {
        return pkAccountingbook;
    }

    public void setPkAccountingbook(String pkAccountingbook) {
        this.pkAccountingbook = pkAccountingbook;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("code", getCode())
                .append("name", getName())
                .append("pkAccountingbook", getPkAccountingbook())
                .toString();
    }
}
