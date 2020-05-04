package net.northking.iacmp.common.bean.domain.ams;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import net.northking.iacmp.core.domain.BaseEntity;

import java.math.BigDecimal;

/**
 * 总账分录明细表 gft_entry_dtl
 *
 * @author wxy
 * @date 2019-08-20
 */
public class GftEntryDtl extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String pkEntryDtl;
    /**
     * 记账日期
     */
    private String tallyDt;
    /**
     *
     */
    private String gftEntryDtlId;
    /**
     * 交易流水号
     */
    private String dealNum;
    /**
     * 账簿编号
     */
    private String bookId;
    /**
     * 凭证类别
     */
    private String voucherCate;
    /**
     * 凭证号
     */
    private String voucherId;
    /**
     * 交易账户
     */
    private String dealAcct;
    /**
     * 审核人
     */
    private String auditor;
    /**
     * 制单人
     */
    private String preparer;
    /**
     * 记账人
     */
    private String bookkeeper;
    /**
     * 会计年度
     */
    private String acctsYear;
    /**
     * 会计月份
     */
    private String acctsMonth;
    /**
     * 调整期间
     */
    private String adjPeriod;
    /**
     * 审核日期
     */
    private String auditDt;
    /**
     * 制单日期
     */
    private String prepareDt;
    /**
     * 附属单据
     */
    private String addBillNum;
    /**
     * 制单系统
     */
    private String prepareSys;
    /**
     * 分录号
     */
    private String entryId;
    /**
     * 分录摘要
     */
    private String entryCont;
    /**
     * 会计科目
     */
    private String subjId;
    /**
     * 辅助核算项部门
     */
    private String asstOrg;
    /**
     * 辅助核算项来源系统
     */
    private String asstSrcSys;
    /**
     * 币种编号
     */
    private String ccyId;
    /**
     * 汇率
     */
    private BigDecimal exRate;
    /**
     * 发生额方向
     */
    private String amtDir;
    /**
     * 原币借方发生额
     */
    private BigDecimal oriDebAmt;
    /**
     * 原币贷方发生额
     */
    private BigDecimal oriCrdAmt;
    /**
     * 本币借方发生额
     */
    private BigDecimal locDebAmt;
    /**
     * 本币贷方发生额
     */
    private BigDecimal locCrdAmt;
    /**
     * 是否年末结转
     */
    private Integer isEyearTurn;
    /**
     * 是否表外
     */
    private Integer isOff;
    /**
     *
     */
    private String def1;
    /**
     *
     */
    private String def2;
    /**
     *
     */
    private String def3;
    /**
     *
     */
    private String def4;
    /**
     *
     */
    private String def5;
    /**
     *
     */
    private String def6;
    /**
     *
     */
    private String def7;
    /**
     *
     */
    private String def8;
    /**
     *
     */
    private String def9;
    /**
     *
     */
    private String def10;
    /**
     *
     */
    private String ts;

    public void setPkEntryDtl(String pkEntryDtl) {
        this.pkEntryDtl = pkEntryDtl;
    }

    public String getPkEntryDtl() {
        return pkEntryDtl;
    }

    public void setTallyDt(String tallyDt) {
        this.tallyDt = tallyDt;
    }

    public String getTallyDt() {
        return tallyDt;
    }

    public void setGftEntryDtlId(String gftEntryDtlId) {
        this.gftEntryDtlId = gftEntryDtlId;
    }

    public String getGftEntryDtlId() {
        return gftEntryDtlId;
    }

    public void setDealNum(String dealNum) {
        this.dealNum = dealNum;
    }

    public String getDealNum() {
        return dealNum;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setVoucherCate(String voucherCate) {
        this.voucherCate = voucherCate;
    }

    public String getVoucherCate() {
        return voucherCate;
    }

    public void setVoucherId(String voucherId) {
        this.voucherId = voucherId;
    }

    public String getVoucherId() {
        return voucherId;
    }

    public void setDealAcct(String dealAcct) {
        this.dealAcct = dealAcct;
    }

    public String getDealAcct() {
        return dealAcct;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setPreparer(String preparer) {
        this.preparer = preparer;
    }

    public String getPreparer() {
        return preparer;
    }

    public void setBookkeeper(String bookkeeper) {
        this.bookkeeper = bookkeeper;
    }

    public String getBookkeeper() {
        return bookkeeper;
    }

    public void setAcctsYear(String acctsYear) {
        this.acctsYear = acctsYear;
    }

    public String getAcctsYear() {
        return acctsYear;
    }

    public void setAcctsMonth(String acctsMonth) {
        this.acctsMonth = acctsMonth;
    }

    public String getAcctsMonth() {
        return acctsMonth;
    }

    public void setAdjPeriod(String adjPeriod) {
        this.adjPeriod = adjPeriod;
    }

    public String getAdjPeriod() {
        return adjPeriod;
    }

    public void setAuditDt(String auditDt) {
        this.auditDt = auditDt;
    }

    public String getAuditDt() {
        return auditDt;
    }

    public void setPrepareDt(String prepareDt) {
        this.prepareDt = prepareDt;
    }

    public String getPrepareDt() {
        return prepareDt;
    }

    public void setAddBillNum(String addBillNum) {
        this.addBillNum = addBillNum;
    }

    public String getAddBillNum() {
        return addBillNum;
    }

    public void setPrepareSys(String prepareSys) {
        this.prepareSys = prepareSys;
    }

    public String getPrepareSys() {
        return prepareSys;
    }

    public void setEntryId(String entryId) {
        this.entryId = entryId;
    }

    public String getEntryId() {
        return entryId;
    }

    public void setEntryCont(String entryCont) {
        this.entryCont = entryCont;
    }

    public String getEntryCont() {
        return entryCont;
    }

    public void setSubjId(String subjId) {
        this.subjId = subjId;
    }

    public String getSubjId() {
        return subjId;
    }

    public void setAsstOrg(String asstOrg) {
        this.asstOrg = asstOrg;
    }

    public String getAsstOrg() {
        return asstOrg;
    }

    public void setAsstSrcSys(String asstSrcSys) {
        this.asstSrcSys = asstSrcSys;
    }

    public String getAsstSrcSys() {
        return asstSrcSys;
    }

    public void setCcyId(String ccyId) {
        this.ccyId = ccyId;
    }

    public String getCcyId() {
        return ccyId;
    }

    public void setExRate(BigDecimal exRate) {
        this.exRate = exRate;
    }

    public BigDecimal getExRate() {
        return exRate;
    }

    public void setAmtDir(String amtDir) {
        this.amtDir = amtDir;
    }

    public String getAmtDir() {
        return amtDir;
    }

    public void setOriDebAmt(BigDecimal oriDebAmt) {
        this.oriDebAmt = oriDebAmt;
    }

    public BigDecimal getOriDebAmt() {
        return oriDebAmt;
    }

    public void setOriCrdAmt(BigDecimal oriCrdAmt) {
        this.oriCrdAmt = oriCrdAmt;
    }

    public BigDecimal getOriCrdAmt() {
        return oriCrdAmt;
    }

    public void setLocDebAmt(BigDecimal locDebAmt) {
        this.locDebAmt = locDebAmt;
    }

    public BigDecimal getLocDebAmt() {
        return locDebAmt;
    }

    public void setLocCrdAmt(BigDecimal locCrdAmt) {
        this.locCrdAmt = locCrdAmt;
    }

    public BigDecimal getLocCrdAmt() {
        return locCrdAmt;
    }

    public void setIsEyearTurn(Integer isEyearTurn) {
        this.isEyearTurn = isEyearTurn;
    }

    public Integer getIsEyearTurn() {
        return isEyearTurn;
    }

    public void setIsOff(Integer isOff) {
        this.isOff = isOff;
    }

    public Integer getIsOff() {
        return isOff;
    }

    public void setDef1(String def1) {
        this.def1 = def1;
    }

    public String getDef1() {
        return def1;
    }

    public void setDef2(String def2) {
        this.def2 = def2;
    }

    public String getDef2() {
        return def2;
    }

    public void setDef3(String def3) {
        this.def3 = def3;
    }

    public String getDef3() {
        return def3;
    }

    public void setDef4(String def4) {
        this.def4 = def4;
    }

    public String getDef4() {
        return def4;
    }

    public void setDef5(String def5) {
        this.def5 = def5;
    }

    public String getDef5() {
        return def5;
    }

    public void setDef6(String def6) {
        this.def6 = def6;
    }

    public String getDef6() {
        return def6;
    }

    public void setDef7(String def7) {
        this.def7 = def7;
    }

    public String getDef7() {
        return def7;
    }

    public void setDef8(String def8) {
        this.def8 = def8;
    }

    public String getDef8() {
        return def8;
    }

    public void setDef9(String def9) {
        this.def9 = def9;
    }

    public String getDef9() {
        return def9;
    }

    public void setDef10(String def10) {
        this.def10 = def10;
    }

    public String getDef10() {
        return def10;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public String getTs() {
        return ts;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("pkEntryDtl", getPkEntryDtl())
                .append("tallyDt", getTallyDt())
                .append("gftEntryDtlId", getGftEntryDtlId())
                .append("dealNum", getDealNum())
                .append("bookId", getBookId())
                .append("voucherCate", getVoucherCate())
                .append("voucherId", getVoucherId())
                .append("dealAcct", getDealAcct())
                .append("auditor", getAuditor())
                .append("preparer", getPreparer())
                .append("bookkeeper", getBookkeeper())
                .append("acctsYear", getAcctsYear())
                .append("acctsMonth", getAcctsMonth())
                .append("adjPeriod", getAdjPeriod())
                .append("auditDt", getAuditDt())
                .append("prepareDt", getPrepareDt())
                .append("addBillNum", getAddBillNum())
                .append("prepareSys", getPrepareSys())
                .append("entryId", getEntryId())
                .append("entryCont", getEntryCont())
                .append("subjId", getSubjId())
                .append("asstOrg", getAsstOrg())
                .append("asstSrcSys", getAsstSrcSys())
                .append("ccyId", getCcyId())
                .append("exRate", getExRate())
                .append("amtDir", getAmtDir())
                .append("oriDebAmt", getOriDebAmt())
                .append("oriCrdAmt", getOriCrdAmt())
                .append("locDebAmt", getLocDebAmt())
                .append("locCrdAmt", getLocCrdAmt())
                .append("isEyearTurn", getIsEyearTurn())
                .append("isOff", getIsOff())
                .append("def1", getDef1())
                .append("def2", getDef2())
                .append("def3", getDef3())
                .append("def4", getDef4())
                .append("def5", getDef5())
                .append("def6", getDef6())
                .append("def7", getDef7())
                .append("def8", getDef8())
                .append("def9", getDef9())
                .append("def10", getDef10())
                .append("ts", getTs())
                .toString();
    }
}
