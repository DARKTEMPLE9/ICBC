package iacmp.biz.common.dao.ams.impl;

import iacmp.biz.common.dao.ams.GftEntryDtlDAO;
import net.northking.iacmp.common.bean.domain.ams.GftEntryDtl;
import net.northking.iacmp.common.bean.dto.ams.GftEntryDtlDto;
import net.northking.iacmp.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 总账分录明细 DAO实现类
 *
 * @Author: wei.chen
 * @Date Created: in 2019/9/12 17:41
 */
@Repository
public class GftEntryDtlDAOImpl implements GftEntryDtlDAO {

    @Value("${hive.odb.dbName}")
    private String odbDbName;

    @Value("${hive.gas.dbName}")
    private String gasDbName;

    @Autowired
    JdbcTemplate hiveJdbcTemplate;

    @Override
    public GftEntryDtl selectGftEntryDtlById(String pkEntryDtl) {
        StringBuilder sql = new StringBuilder();
        sql.append(getSelectSql());
        sql.append(" where entry.pk_entry_dtl = ?");
        return hiveJdbcTemplate.queryForObject(
                sql.toString(), new BeanPropertyRowMapper<>(GftEntryDtl.class), pkEntryDtl);
    }

    @Override
    public List<GftEntryDtl> selectGftEntryDtlList(GftEntryDtlDto gftEntryDtl) {
        List<Object> argList = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append(getSelectSql());
        sql.append(getSelectParam(gftEntryDtl, argList));

        return hiveJdbcTemplate.query(
                sql.toString(), argList.toArray(), new BeanPropertyRowMapper<>(GftEntryDtl.class));
    }

    @Override
    public Integer selectGftEntryDtlCount(GftEntryDtlDto gftEntryDtl) {
        List<Object> argList = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("select count(1) from ");
        sql.append(gasDbName);
        sql.append(".gft_entry_dtl entry");
        sql.append(" inner join ");
        sql.append(odbDbName);
        sql.append(".gas_bd_accasoa acc on 1=1");
        sql.append(getSelectParam(gftEntryDtl, argList));

        return hiveJdbcTemplate.queryForObject(
                sql.toString(), argList.toArray(), Integer.class);
    }

    private String getSelectSql() {
        StringBuilder sb = new StringBuilder();
        sb.append("select entry.pk_entry_dtl pkEntryDtl, entry.tally_dt tallyDt,");
        sb.append(" entry.gft_entry_dtl_id gftEntryDtlId, entry.deal_num dealNum,");
        sb.append(" book.name bookId,");
        sb.append(" case when entry.voucher_cate = '001' then '总账凭证'");
        sb.append(" when entry.voucher_cate = '002' then '核心凭证'");
        sb.append(" when entry.voucher_cate = '003' then '消费信贷凭证'");
        sb.append(" when entry.voucher_cate = '004' then '结转凭证'");
        sb.append(" when entry.voucher_cate = '005' then '总账凭证-金融工具'");
        sb.append(" when entry.voucher_cate = '006' then '总账凭证-资产减值'");
        sb.append(" when entry.voucher_cate = '007' then '总账凭证-所得税'");
        sb.append(" when entry.voucher_cate = '008' then '总账凭证-中间业务收入'");
        sb.append(" when entry.voucher_cate = '009' then '总账凭证-增值税'");
        sb.append(" when entry.voucher_cate = '010' then '财务凭证'");
        sb.append(" when entry.voucher_cate = '011' then '黄金凭证'");
        sb.append(" when entry.voucher_cate = '099' then '单边凭证'");
        sb.append(" end voucherCate,");
        sb.append(" entry.voucher_id voucherId, entry.deal_acct dealAcct,");
        sb.append(" aud.user_name auditor,");
        sb.append(" pre.user_name preparer,");
        sb.append(" kee.user_name bookkeeper, entry.accts_year acctsYear,");
        sb.append(" entry.accts_month acctsMonth, entry.adj_period adjPeriod,");
        sb.append(" entry.audit_dt auditDt, entry.prepare_dt prepareDt,");
        sb.append(" entry.add_bill_num addBillNum, entry.prepare_sys prepareSys,");
        sb.append(" entry.entry_id entryId, entry.entry_cont entryCont,");
        sb.append(" acc.name subjId,");
        sb.append(" org.name asstOrg,");
        sb.append(" entry.asst_src_sys asstSrcSys, entry.ccy_id ccyId,");
        sb.append(" entry.ex_rate exRate, entry.amt_dir amtDir, entry.ori_deb_amt oriDebAmt,");
        sb.append(" entry.ori_crd_amt oriCrdAmt, entry.loc_deb_amt locDebAmt,");
        sb.append(" entry.loc_crd_amt locCrdAmt, entry.is_eyear_turn isEyearTurn,");
        sb.append(" entry.is_off isOff, entry.def1, entry.def2, entry.def3, entry.def4,");
        sb.append(" entry.def5, entry.def6, entry.def7 def7, entry.def8, entry.def9, entry.def10,");
        sb.append(" entry.ts from ");
        sb.append(gasDbName);
        sb.append(".gft_entry_dtl entry");
        sb.append(" left join ");
        sb.append(odbDbName);
        sb.append(".gas_bd_accasoa acc on 1=1");
        sb.append(" left join ");
        sb.append(odbDbName);
        sb.append(".gas_org_accountingbook book on book.code = entry.book_id");
        sb.append(" left join ");
        sb.append(odbDbName);
        sb.append(".gas_sm_user aud on aud.user_code = entry.auditor");
        sb.append(" left join ");
        sb.append(odbDbName);
        sb.append(".gas_sm_user pre on pre.user_code = entry.preparer");
        sb.append(" left join ");
        sb.append(odbDbName);
        sb.append(".gas_sm_user kee on kee.user_code = entry.bookkeeper");
        sb.append(" left join ");
        sb.append(odbDbName);
        sb.append(".gas_org_orgs org on org.code = entry.asst_org");
        return sb.toString();
    }

    private String getSelectParam(GftEntryDtlDto gftEntryDtl, List<Object> argList) {
        StringBuilder sql = new StringBuilder();
        sql.append(" where substr(acc.dispname, 1, length(entry.subj_id)) = entry.subj_id");
        if (StringUtils.isNotEmpty(gftEntryDtl.getDealNum())) {
            sql.append(" and entry.deal_num like concat ('%', ?, '%')");
            argList.add(gftEntryDtl.getDealNum());
        }
        if (StringUtils.isNotEmpty(gftEntryDtl.getTallyDtStart())) {
            sql.append(" and entry.tally_dt >= ?");
            argList.add(gftEntryDtl.getTallyDtStart());
        }
        if (StringUtils.isNotEmpty(gftEntryDtl.getTallyDtEnd())) {
            sql.append(" and entry.tally_dt <= ?");
            argList.add(gftEntryDtl.getTallyDtEnd());
        }
        if (StringUtils.isNotEmpty(gftEntryDtl.getVoucherCate())) {
            sql.append(" and entry.voucher_cate = ?");
            argList.add(gftEntryDtl.getVoucherCate());
        }
        if (StringUtils.isNotEmpty(gftEntryDtl.getVoucherId())) {
            sql.append(" and entry.voucher_id like concat ('%', ?, '%')");
            argList.add(gftEntryDtl.getVoucherId());
        }
        if (StringUtils.isNotEmpty(gftEntryDtl.getAsstSrcSys())) {
            sql.append(" and entry.asst_src_sys = ?");
            argList.add(gftEntryDtl.getAsstSrcSys());
        }
        if (StringUtils.isNotEmpty(gftEntryDtl.getSubjId())) {
            sql.append(" and entry.subj_id = ?");
            argList.add(gftEntryDtl.getSubjId());
        }
        if (StringUtils.isNotEmpty(gftEntryDtl.getBookId())) {
            sql.append(" and entry.book_id like concat ('%', ?, '%')");
            argList.add(gftEntryDtl.getBookId());
        }
        if (StringUtils.isNotEmpty(gftEntryDtl.getDealAcct())) {
            sql.append(" and entry.deal_acct like concat ('%', ?, '%')");
            argList.add(gftEntryDtl.getDealAcct());
        }
        if (StringUtils.isNotEmpty(gftEntryDtl.getAuditor())) {
            sql.append(" and entry.auditor = ?");
            argList.add(gftEntryDtl.getAuditor());
        }
        if (StringUtils.isNotEmpty(gftEntryDtl.getPreparer())) {
            sql.append(" and entry.preparer = ?");
            argList.add(gftEntryDtl.getPreparer());
        }
        if (StringUtils.isNotEmpty(gftEntryDtl.getBookkeeper())) {
            sql.append(" and entry.bookkeeper = ?");
            argList.add(gftEntryDtl.getBookkeeper());
        }
        if (StringUtils.isNotEmpty(gftEntryDtl.getAcctsYear())) {
            sql.append(" and entry.accts_year = ?");
            argList.add(gftEntryDtl.getAcctsYear());
        }
        if (StringUtils.isNotEmpty(gftEntryDtl.getAcctsMonth())) {
            sql.append(" and entry.accts_month = ?");
            argList.add(gftEntryDtl.getAcctsMonth());
        }
        if (StringUtils.isNotEmpty(gftEntryDtl.getAdjPeriod())) {
            sql.append(" and entry.adj_period = ?");
            argList.add(gftEntryDtl.getAdjPeriod());
        }
        if (StringUtils.isNotEmpty(gftEntryDtl.getAuditDt())) {
            sql.append(" and entry.audit_dt = ?");
            argList.add(gftEntryDtl.getAuditDt());
        }
        if (StringUtils.isNotEmpty(gftEntryDtl.getPrepareDt())) {
            sql.append(" and entry.prepare_dt = ?");
            argList.add(gftEntryDtl.getPrepareDt());
        }
        if (StringUtils.isNotEmpty(gftEntryDtl.getAddBillNum())) {
            sql.append(" and entry.add_bill_num = ?");
            argList.add(gftEntryDtl.getAddBillNum());
        }
        if (StringUtils.isNotEmpty(gftEntryDtl.getPrepareSys())) {
            sql.append(" and entry.prepare_sys = ?");
            argList.add(gftEntryDtl.getPrepareSys());
        }
        if (StringUtils.isNotEmpty(gftEntryDtl.getEntryId())) {
            sql.append(" and entry.entry_id like concat ('%', ?, '%')");
            argList.add(gftEntryDtl.getEntryId());
        }
        if (StringUtils.isNotEmpty(gftEntryDtl.getEntryCont())) {
            sql.append(" and entry.entry_cont = ?");
            argList.add(gftEntryDtl.getEntryCont());
        }
        if (StringUtils.isNotEmpty(gftEntryDtl.getAsstOrg())) {
            sql.append(" and entry.asst_org = ?");
            argList.add(gftEntryDtl.getAsstOrg());
        }
        if (StringUtils.isNotEmpty(gftEntryDtl.getCcyId())) {
            sql.append(" and entry.ccy_id = ?");
            argList.add(gftEntryDtl.getCcyId());
        }
        if (StringUtils.isNotNull(gftEntryDtl.getExRate())) {
            sql.append(" and entry.ex_rate = ?");
            argList.add(gftEntryDtl.getExRate());
        }
        if (StringUtils.isNotEmpty(gftEntryDtl.getAmtDir())) {
            sql.append(" and entry.amt_dir = ?");
            argList.add(gftEntryDtl.getAmtDir());
        }
        if (StringUtils.isNotNull(gftEntryDtl.getOriDebAmt())) {
            sql.append(" and entry.ori_deb_amt = ?");
            argList.add(gftEntryDtl.getOriDebAmt());
        }
        if (StringUtils.isNotNull(gftEntryDtl.getOriCrdAmt())) {
            sql.append(" and entry.ori_crd_amt = ?");
            argList.add(gftEntryDtl.getOriCrdAmt());
        }
        if (StringUtils.isNotNull(gftEntryDtl.getLocDebAmt())) {
            sql.append(" and entry.loc_deb_amt = ?");
            argList.add(gftEntryDtl.getLocDebAmt());
        }
        if (StringUtils.isNotNull(gftEntryDtl.getLocCrdAmt())) {
            sql.append(" and entry.loc_crd_amt = ?");
            argList.add(gftEntryDtl.getLocCrdAmt());
        }
        if (StringUtils.isNotNull(gftEntryDtl.getIsEyearTurn())) {
            sql.append(" and entry.is_eyear_turn = ?");
            argList.add(gftEntryDtl.getIsEyearTurn());
        }
        if (StringUtils.isNotNull(gftEntryDtl.getIsOff())) {
            sql.append(" and entry.is_off = ?");
            argList.add(gftEntryDtl.getIsOff());
        }

        return sql.toString();
    }
}
