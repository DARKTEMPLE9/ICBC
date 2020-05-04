package iacmp.biz.common.dao.ams.impl;

import iacmp.biz.common.dao.ams.GasOrgAccountingbookDAO;
import net.northking.iacmp.common.bean.vo.ams.GasOrgAccountingbookVO;
import net.northking.iacmp.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 账簿信息 DAO实现类
 *
 * @Author: wei.chen
 * @Date Created: in 2019/9/12 17:41
 */
@Repository
public class GasOrgAccountingbookDAOImpl implements GasOrgAccountingbookDAO {

    @Value("${hive.odb.dbName}")
    private String odbDbName;

    @Autowired
    JdbcTemplate hiveJdbcTemplate;

    @Override
    public List<GasOrgAccountingbookVO> selectGasOrgAccountingbookList(GasOrgAccountingbookVO orgAccountingbook) {
        List<Object> argList = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append(getSelectSql());
        sql.append(getSelectParam(orgAccountingbook, argList));

        return hiveJdbcTemplate.query(
                sql.toString(), argList.toArray(), new BeanPropertyRowMapper<>(GasOrgAccountingbookVO.class));
    }

    private String getSelectSql() {
        return "select code, name, pk_accountingbook as pkAccountingbook from " + odbDbName +
                ".gas_org_accountingbook";
    }

    private String getSelectParam(GasOrgAccountingbookVO orgAccountingbook, List<Object> argList) {
        StringBuilder sql = new StringBuilder();
        sql.append(" where 1=1");
        if (StringUtils.isNotEmpty(orgAccountingbook.getCode())) {
            sql.append(" and code = ?");
            argList.add(orgAccountingbook.getCode());
        }
        if (StringUtils.isNotEmpty(orgAccountingbook.getName())) {
            sql.append(" and name = ?");
            argList.add(orgAccountingbook.getName());
        }
        if (StringUtils.isNotEmpty(orgAccountingbook.getPkAccountingbook())) {
            sql.append(" and pk_accountingbook = ?");
            argList.add(orgAccountingbook.getPkAccountingbook());
        }

        return sql.toString();
    }
}
