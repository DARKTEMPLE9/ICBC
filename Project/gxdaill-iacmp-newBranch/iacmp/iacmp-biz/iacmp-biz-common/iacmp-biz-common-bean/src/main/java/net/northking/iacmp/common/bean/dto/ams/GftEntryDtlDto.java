package net.northking.iacmp.common.bean.dto.ams;

import lombok.Data;
import net.northking.iacmp.common.bean.domain.ams.GftEntryDtl;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 总账科目明细表 gft_entry_dtl
 *
 * @author wxy
 * @date 2019-08-20
 */
@Data
public class GftEntryDtlDto extends GftEntryDtl {
    private static final long serialVersionUID = 1L;

    /**
     * 记账日期开始
     */
    private String tallyDtStart;

    /**
     * 记账日期结束
     */
    private String tallyDtEnd;

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
                .append("tallyDtStart", getTallyDtStart())
                .append("tallyDtEnd", getTallyDtEnd())
                .toString();
    }
}
