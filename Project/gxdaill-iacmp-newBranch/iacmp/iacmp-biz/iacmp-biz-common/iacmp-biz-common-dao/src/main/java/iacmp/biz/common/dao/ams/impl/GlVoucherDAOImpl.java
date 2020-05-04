package iacmp.biz.common.dao.ams.impl;

import iacmp.biz.common.dao.ams.GlVoucherDAO;
import net.northking.iacmp.common.bean.vo.ams.GlDetailVO;
import net.northking.iacmp.common.bean.vo.ams.GlVoucherVO;
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
public class GlVoucherDAOImpl implements GlVoucherDAO {

    @Value("${hive.odb.dbName}")
    private String odbDbName;

    //@Value("${hive.gas.dbName}")
    //private String gasDbName;

    @Autowired
    JdbcTemplate hiveJdbcTemplate;

    @Override
    public List<GlVoucherVO> selectGlVoucherById(String pkVoucher) {
        StringBuilder sql = new StringBuilder();
        sql.append(getSelectSql());
        sql.append(" where voucher.pk_voucher = ?");
        return hiveJdbcTemplate.query(
                sql.toString(), new BeanPropertyRowMapper<>(GlVoucherVO.class), pkVoucher);
    }

    @Override
    public List<GlVoucherVO> selectGlVoucherList(GlVoucherVO glVoucher) {
        List<Object> argList = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append(getSelectSql());
        sql.append(getSelectParam(glVoucher, argList));

        return hiveJdbcTemplate.query(
                sql.toString(), argList.toArray(), new BeanPropertyRowMapper<>(GlVoucherVO.class));
    }

    @Override
    public Integer selectGlVoucherCount(GlVoucherVO glVoucher) {
        List<Object> argList = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("select count(1) from ");
        sql.append(odbDbName);
        sql.append(".gas_gl_voucher voucher ");
        sql.append(getSelectParam(glVoucher, argList));

        return hiveJdbcTemplate.queryForObject(
                sql.toString(), argList.toArray(), Integer.class);
    }

    private String getSelectSql() {
        StringBuilder sb = new StringBuilder();
        sb.append("select ");
        sb.append(" voucher.pk_voucher as pkVoucher,");
        sb.append(" voucher.checkeddate as checkeddate,");
        sb.append(" app.user_name as approver,");
        sb.append(" bm.user_name as billmaker,");
        sb.append(" voucher.num,");
        sb.append(" voucher.offervoucher as offervoucher,");
        sb.append(" voucher.period as period,");
        sb.append(" book.name as pkAccountingbook,");
        sb.append(" voucher.pk_casher as pkCasher,");
        sb.append(" voucher.pk_vouchertype as pkVouchertype,");
        sb.append(" voucher.prepareddate as prepareddate,");
        sb.append(" voucher.signdate as signdate,");
        sb.append(" voucher.totaldebit as totaldebit,");
        sb.append(" voucher.year as year from ");
        sb.append(odbDbName);
        sb.append(".gas_gl_voucher as voucher");
        sb.append(" left join ");
        sb.append(odbDbName);
        sb.append(".gas_sm_user as app on app.cuserid = voucher.pk_checked");
        sb.append(" left join ");
        sb.append(odbDbName);
        sb.append(".gas_sm_user as bm on bm.cuserid = voucher.billmaker");
        sb.append(" left join ");
        sb.append(odbDbName);
        sb.append(".gas_org_accountingbook as book on book.pk_accountingbook = voucher.pk_accountingbook");
        return sb.toString();
    }

    private String getSelectParam(GlVoucherVO glVoucher, List<Object> argList) {
        StringBuilder sql = new StringBuilder();
        sql.append(" where 1=1");
        if (StringUtils.isNotEmpty(glVoucher.getPkVoucher())) {
            sql.append(" and voucher.pk_voucher = ?");
            argList.add(glVoucher.getPkVoucher());
        }
        if (StringUtils.isNotEmpty(glVoucher.getApprover())) {
            sql.append(" and voucher.pk_checked = ?");
            argList.add(glVoucher.getApprover());
        }
        if (StringUtils.isNotEmpty(glVoucher.getBillmaker())) {
            sql.append(" and voucher.billmaker = ?");
            argList.add(glVoucher.getBillmaker());
        }
        if (StringUtils.isNotEmpty(glVoucher.getCheckeddate())) {
            sql.append(" and voucher.checkeddate like concat(?, '%')");
            argList.add(glVoucher.getCheckeddate());
        }
        if (StringUtils.isNotNull(glVoucher.getNum())) {
            sql.append(" and voucher.num = ?");
            argList.add(glVoucher.getNum());
        }
        if (StringUtils.isNotEmpty(glVoucher.getOffervoucher())) {
            sql.append(" and voucher.offervoucher = ?");
            argList.add(glVoucher.getOffervoucher());
        }
        if (StringUtils.isNotEmpty(glVoucher.getPeriod())) {
            sql.append(" and voucher.period = ?");
            argList.add(glVoucher.getPeriod());
        }
        if (StringUtils.isNotEmpty(glVoucher.getPkAccountingbook())) {
            sql.append(" and voucher.pk_accountingbook like concat('%', ?, '%')");
            argList.add(glVoucher.getPkAccountingbook());
        }
        if (StringUtils.isNotEmpty(glVoucher.getPkCasher())) {
            sql.append(" and voucher.pk_casher = ?");
            argList.add(glVoucher.getPkCasher());
        }
        if (StringUtils.isNotEmpty(glVoucher.getPkVouchertype())) {
            sql.append(" and voucher.pk_vouchertype = ?");
            argList.add(glVoucher.getPkVouchertype());
        }
        if (StringUtils.isNotEmpty(glVoucher.getPrepareddate())) {
            sql.append(" and voucher.prepareddate like concat(?, '%')");
            argList.add(glVoucher.getPrepareddate());
        }
        if (StringUtils.isNotEmpty(glVoucher.getSigndate())) {
            sql.append(" and voucher.signdate = ?");
            argList.add(glVoucher.getSigndate());
        }
        if (StringUtils.isNotNull(glVoucher.getTotaldebit())) {
            sql.append(" and voucher.totaldebit = ?");
            argList.add(glVoucher.getTotaldebit());
        }
        if (StringUtils.isNotEmpty(glVoucher.getYear())) {
            sql.append(" and voucher.year = ?");
            argList.add(glVoucher.getYear());
        }
        /*if (StringUtils.isNotEmpty(glVoucher.getYearv())) {
            sql.append(" and yearv = ?");
            argList.add(glVoucher.getYearv());
        }
        if (StringUtils.isNotEmpty(glVoucher.getPkDetail())) {
            sql.append(" and pk_detail = ?");
            argList.add(glVoucher.getPkDetail());
        }
        if (StringUtils.isNotEmpty(glVoucher.getAssid())) {
            sql.append(" and assid like concat('%', ?, '%')");
            argList.add(glVoucher.getAssid());
        }
        if (StringUtils.isNotEmpty(glVoucher.getBankaccount())) {
            sql.append(" and bankaccount like concat('%', ?, '%')");
            argList.add(glVoucher.getBankaccount());
        }
        if (StringUtils.isNotNull(glVoucher.getContrastflag())) {
            sql.append(" and detail.contrastflag = ?");
            argList.add(glVoucher.getContrastflag());
        }
        if (StringUtils.isNotNull(glVoucher.getCreditamount())) {
            sql.append(" and creditamount = ?");
            argList.add(glVoucher.getCreditamount());
        }
        if (StringUtils.isNotNull(glVoucher.getCreditquantity())) {
            sql.append(" and creditquantity = ?");
            argList.add(glVoucher.getCreditquantity());
        }
        if (StringUtils.isNotNull(glVoucher.getDebitamount())) {
            sql.append(" and debitamount = ?");
            argList.add(glVoucher.getDebitamount());
        }
        if (StringUtils.isNotNull(glVoucher.getDebitquantity())) {
            sql.append(" and debitquantity = ?");
            argList.add(glVoucher.getDebitquantity());
        }
        if (StringUtils.isNotNull(glVoucher.getDetailindex())) {
            sql.append(" and detailindex = ?");
            argList.add(glVoucher.getDetailindex());
        }
        if (StringUtils.isNotEmpty(glVoucher.getDirection())) {
            sql.append(" and direction = ?");
            argList.add(glVoucher.getDirection());
        }
        if (StringUtils.isNotEmpty(glVoucher.getDiscardflagv())) {
            sql.append(" and discardflagv = ?");
            argList.add(glVoucher.getDiscardflagv());
        }
        if (StringUtils.isNotNull(glVoucher.getDr())) {
            sql.append(" and detail.dr = ?");
            argList.add(glVoucher.getDr());
        }
        if (StringUtils.isNotEmpty(glVoucher.getOppositesubj())) {
            sql.append(" and oppositesubj = ?");
            argList.add(glVoucher.getOppositesubj());
        }
        if (StringUtils.isNotEmpty(glVoucher.getPkCurrtype())) {
            sql.append(" and pk_currtype = ?");
            argList.add(glVoucher.getPkCurrtype());
        }
        if (StringUtils.isNotEmpty(glVoucher.getPkManagerv())) {
            sql.append(" and pk_managerv = ?");
            argList.add(glVoucher.getPkManagerv());
        }
        if (StringUtils.isNotEmpty(glVoucher.getPkOrg())) {
            sql.append(" and detail.pk_org = ?");
            argList.add(glVoucher.getPkOrg());
        }
        if (StringUtils.isNotEmpty(glVoucher.getPkUnit())) {
            sql.append(" and pk_unit like concat('%', ?, '%')");
            argList.add(glVoucher.getPkUnit());
        }
        if (StringUtils.isNotEmpty(glVoucher.getPkUnitV())) {
            sql.append(" and pk_unit_v like concat('%', ?, '%')");
            argList.add(glVoucher.getPkUnitV());
        }
        if (StringUtils.isNotNull(glVoucher.getVoucherkindv())) {
            sql.append(" and voucherkindv = ?");
            argList.add(glVoucher.getVoucherkindv());
        }*/

        return sql.toString();
    }

    @Override
    public List<GlDetailVO> selectGlDetailList(String pkVoucher) {
        List<Object> argList = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append(getSelectDetailSql());
        sql.append(" where detail.pk_voucher = ?");

        return hiveJdbcTemplate.query(
                sql.toString(), new BeanPropertyRowMapper<>(GlDetailVO.class), pkVoucher);
    }

    private String getSelectDetailSql() {
        StringBuilder sb = new StringBuilder();
        sb.append("select ");
        sb.append(" detail.assid as assid,");
        sb.append(" detail.bankaccount as bankaccount,");
        sb.append(" detail.contrastflag as contrastflag,");
        sb.append(" detail.creditamount as creditamount,");
        sb.append(" detail.creditquantity as creditquantity,");
        sb.append(" detail.debitamount as debitamount,");
        sb.append(" detail.debitquantity as debitquantity,");
        sb.append(" detail.detailindex as detailindex,");
        sb.append(" detail.direction as direction,");
        sb.append(" detail.discardflagv as discardflagv,");
        sb.append(" detail.dr as dr,");
        sb.append(" opp.name as oppositesubj,");
        sb.append(" cur.name as pkCurrtype,");
        sb.append(" man.user_name as pkManagerv,");
        sb.append(" org.name as pkOrg,");
        sb.append(" detail.pk_preparedv as pkPreparedv,");
        sb.append(" uni.name as pkUnit,");
        sb.append(" detail.pk_unit_v as pkUnitV,");
        sb.append(" detail.prepareddatev as prepareddatev,");
        sb.append(" detail.voucherkindv as voucherkindv,");
        sb.append(" detail.yearv as yearv from ");
        sb.append(odbDbName);
        sb.append(".gas_gl_detail detail");
        sb.append(" left join ");
        sb.append(odbDbName);
        sb.append(".gas_bd_accasoa as opp on opp.pk_accasoa = detail.oppositesubj");
        sb.append(" left join ");
        sb.append(odbDbName);
        sb.append(".gas_bd_currtype as cur on cur.pk_currtype = detail.pk_currtype");
        sb.append(" left join ");
        sb.append(odbDbName);
        sb.append(".gas_sm_user as man on man.cuserid = detail.pk_managerv");
        sb.append(" left join ");
        sb.append(odbDbName);
        sb.append(".gas_org_orgs as org on org.pk_org = detail.pk_org");
        sb.append(" left join ");
        sb.append(odbDbName);
        sb.append(".gas_org_orgs as uni on uni.pk_org = detail.pk_unit");

        return sb.toString();
    }
}
