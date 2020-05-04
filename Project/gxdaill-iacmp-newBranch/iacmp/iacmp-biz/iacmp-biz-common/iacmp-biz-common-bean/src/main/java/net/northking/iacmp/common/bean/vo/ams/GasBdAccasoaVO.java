package net.northking.iacmp.common.bean.vo.ams;

import net.northking.iacmp.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 会计科目表 gas_bd_accasoa
 *
 * @author wei.chen
 * @date 2019-09-23
 */
public class GasBdAccasoaVO extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 科目编码
     */
    private String dispname;
    /**
     * 科目名称
     */
    private String name;
    /**
     * 科目主键
     */
    private String pkAccasoa;

    public String getDispname() {
        return dispname;
    }

    public void setDispname(String dispname) {
        this.dispname = dispname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPkAccasoa() {
        return pkAccasoa;
    }

    public void setPkAccasoa(String pkAccasoa) {
        this.pkAccasoa = pkAccasoa;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("dispname", getDispname())
                .append("name", getName())
                .append("pkAccasoa", getPkAccasoa())
                .toString();
    }
}
