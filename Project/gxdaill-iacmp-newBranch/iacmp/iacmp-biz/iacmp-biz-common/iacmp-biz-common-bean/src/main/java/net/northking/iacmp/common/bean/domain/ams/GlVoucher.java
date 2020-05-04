package net.northking.iacmp.common.bean.domain.ams;

import net.northking.iacmp.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 凭证表 gl_voucher
 *
 * @author wei.chen
 * @date 2019-09-23
 */
public class GlVoucher extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 凭证主键
     */
    private String pkVoucher;
    /**
     * 审核人
     */
    private String approver;
    /**
     * 制单人
     */
    private String billmaker;
    /**
     * 审核日期
     */
    private String checkeddate;
    /**
     * 凭证编码
     */
    private Integer num;
    /**
     * 冲销凭证
     */
    private String offervoucher;
    /**
     * 会计期间
     */
    private String period;
    /**
     * 核算账簿
     */
    private String pkAccountingbook;
    /**
     * 出纳
     */
    private String pkCasher;
    /**
     * 凭证类别
     */
    private String pkVouchertype;
    /**
     * 制单日期
     */
    private String prepareddate;
    /**
     * 签字日期
     */
    private String signdate;
    /**
     * 借方合计
     */
    private BigDecimal totaldebit;
    /**
     * 会计年度
     */
    private String yearv;

    public void setPkVoucher(String pkVoucher) {
        this.pkVoucher = pkVoucher;
    }

    public String getPkVoucher() {
        return pkVoucher;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public String getApprover() {
        return approver;
    }

    public void setBillmaker(String billmaker) {
        this.billmaker = billmaker;
    }

    public String getBillmaker() {
        return billmaker;
    }

    public void setCheckeddate(String checkeddate) {
        this.checkeddate = checkeddate;
    }

    public String getCheckeddate() {
        return checkeddate;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getNum() {
        return num;
    }

    public void setOffervoucher(String offervoucher) {
        this.offervoucher = offervoucher;
    }

    public String getOffervoucher() {
        return offervoucher;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getPeriod() {
        return period;
    }

    public void setPkAccountingbook(String pkAccountingbook) {
        this.pkAccountingbook = pkAccountingbook;
    }

    public String getPkAccountingbook() {
        return pkAccountingbook;
    }

    public void setPkCasher(String pkCasher) {
        this.pkCasher = pkCasher;
    }

    public String getPkCasher() {
        return pkCasher;
    }

    public void setPkVouchertype(String pkVouchertype) {
        this.pkVouchertype = pkVouchertype;
    }

    public String getPkVouchertype() {
        return pkVouchertype;
    }

    public void setPrepareddate(String prepareddate) {
        this.prepareddate = prepareddate;
    }

    public String getPrepareddate() {
        return prepareddate;
    }

    public void setSigndate(String signdate) {
        this.signdate = signdate;
    }

    public String getSigndate() {
        return signdate;
    }

    public void setTotaldebit(BigDecimal totaldebit) {
        this.totaldebit = totaldebit;
    }

    public BigDecimal getTotaldebit() {
        return totaldebit;
    }

    public void setYearv(String yearv) {
        this.yearv = yearv;
    }

    public String getYearv() {
        return yearv;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("pkVoucher", getPkVoucher())
                .append("approver", getApprover())
                .append("billmaker", getBillmaker())
                .append("checkeddate", getCheckeddate())
                .append("num", getNum())
                .append("offervoucher", getOffervoucher())
                .append("period", getPeriod())
                .append("pkAccountingbook", getPkAccountingbook())
                .append("pkCasher", getPkCasher())
                .append("pkVouchertype", getPkVouchertype())
                .append("prepareddate", getPrepareddate())
                .append("signdate", getSigndate())
                .append("totaldebit", getTotaldebit())
                .append("yearv", getYearv())
                .toString();
    }
}
