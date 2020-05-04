package net.northking.iacmp.common.bean.vo.ams;

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
public class GlVoucherVO extends BaseEntity {
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
    private String year;

    /** GlDetail */
    /**
     * 分录标识
     */
    private String pkDetail;
    /**
     * 辅助核算
     */
    private String assid;
    /**
     * 银行账户
     */
    private String bankaccount;
    /**
     * 对账标志
     */
    private Integer contrastflag;
    /**
     * 原币贷方金额
     */
    private BigDecimal creditamount;
    /**
     * 贷方数量
     */
    private BigDecimal creditquantity;
    /**
     * 原币借方金额
     */
    private BigDecimal debitamount;
    /**
     * 借方数量
     */
    private BigDecimal debitquantity;
    /**
     * 分录号
     */
    private Integer detailindex;
    /**
     * 发生额方向
     */
    private String direction;
    /**
     * 作废标志
     */
    private String discardflagv;
    /**
     * 删除标志
     */
    private Integer dr;
    /**
     * 对方科目
     */
    private String oppositesubj;
    /**
     * 币种
     */
    private String pkCurrtype;
    /**
     * 记账人
     */
    private String pkManagerv;
    /**
     * 财务组织
     */
    private String pkOrg;
    /**
     * 业务单元
     */
    private String pkUnit;
    /**
     * 业务单元
     */
    private String pkUnitV;
    /**
     * 凭证类型
     */
    private Integer voucherkindv;
    /**
     * 年度
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

    /**
     * GlDetail
     */
    public void setPkDetail(String pkDetail) {
        this.pkDetail = pkDetail;
    }

    public String getPkDetail() {
        return pkDetail;
    }

    public void setAssid(String assid) {
        this.assid = assid;
    }

    public String getAssid() {
        return assid;
    }

    public void setBankaccount(String bankaccount) {
        this.bankaccount = bankaccount;
    }

    public String getBankaccount() {
        return bankaccount;
    }

    public void setContrastflag(Integer contrastflag) {
        this.contrastflag = contrastflag;
    }

    public Integer getContrastflag() {
        return contrastflag;
    }

    public void setCreditamount(BigDecimal creditamount) {
        this.creditamount = creditamount;
    }

    public BigDecimal getCreditamount() {
        return creditamount;
    }

    public void setCreditquantity(BigDecimal creditquantity) {
        this.creditquantity = creditquantity;
    }

    public BigDecimal getCreditquantity() {
        return creditquantity;
    }

    public void setDebitamount(BigDecimal debitamount) {
        this.debitamount = debitamount;
    }

    public BigDecimal getDebitamount() {
        return debitamount;
    }

    public void setDebitquantity(BigDecimal debitquantity) {
        this.debitquantity = debitquantity;
    }

    public BigDecimal getDebitquantity() {
        return debitquantity;
    }

    public void setDetailindex(Integer detailindex) {
        this.detailindex = detailindex;
    }

    public Integer getDetailindex() {
        return detailindex;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }

    public void setDiscardflagv(String discardflagv) {
        this.discardflagv = discardflagv;
    }

    public String getDiscardflagv() {
        return discardflagv;
    }

    public void setDr(Integer dr) {
        this.dr = dr;
    }

    public Integer getDr() {
        return dr;
    }

    public void setOppositesubj(String oppositesubj) {
        this.oppositesubj = oppositesubj;
    }

    public String getOppositesubj() {
        return oppositesubj;
    }

    public void setPkCurrtype(String pkCurrtype) {
        this.pkCurrtype = pkCurrtype;
    }

    public String getPkCurrtype() {
        return pkCurrtype;
    }

    public void setPkManagerv(String pkManagerv) {
        this.pkManagerv = pkManagerv;
    }

    public String getPkManagerv() {
        return pkManagerv;
    }

    public void setPkOrg(String pkOrg) {
        this.pkOrg = pkOrg;
    }

    public String getPkOrg() {
        return pkOrg;
    }

    public void setPkUnit(String pkUnit) {
        this.pkUnit = pkUnit;
    }

    public String getPkUnit() {
        return pkUnit;
    }

    public void setPkUnitV(String pkUnitV) {
        this.pkUnitV = pkUnitV;
    }

    public String getPkUnitV() {
        return pkUnitV;
    }

    public void setVoucherkindv(Integer voucherkindv) {
        this.voucherkindv = voucherkindv;
    }

    public Integer getVoucherkindv() {
        return voucherkindv;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
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
                .append("year", getYear())

                .append("pkDetail", getPkDetail())
                .append("assid", getAssid())
                .append("bankaccount", getBankaccount())
                .append("contrastflag", getContrastflag())
                .append("creditamount", getCreditamount())
                .append("creditquantity", getCreditquantity())
                .append("debitamount", getDebitamount())
                .append("debitquantity", getDebitquantity())
                .append("detailindex", getDetailindex())
                .append("direction", getDirection())
                .append("discardflagv", getDiscardflagv())
                .append("dr", getDr())
                .append("oppositesubj", getOppositesubj())
                .append("pkCurrtype", getPkCurrtype())
                .append("pkManagerv", getPkManagerv())
                .append("pkOrg", getPkOrg())
                .append("pkUnit", getPkUnit())
                .append("pkUnitV", getPkUnitV())
                .append("voucherkindv", getVoucherkindv())
                .append("yearv", getYearv())
                .toString();
    }
}
