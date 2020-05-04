package iacmp.biz.common.dao.ams.impl;

import iacmp.biz.common.dao.ams.GasOrgOrgsDAO;
import net.northking.iacmp.common.bean.vo.ams.GasOrgAccountingbookVO;
import net.northking.iacmp.common.bean.vo.ams.GasOrgOrgsVO;
import net.northking.iacmp.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 部门信息 DAO实现类
 *
 * @Author: wei.chen
 * @Date Created: in 2019/9/12 17:41
 */
@Repository
public class GasOrgOrgsDAOImpl implements GasOrgOrgsDAO {

    @Value("${hive.odb.dbName}")
    private String odbDbName;

    @Autowired
    JdbcTemplate hiveJdbcTemplate;

    @Override
    public List<GasOrgOrgsVO> selectGasOrgOrgsList(GasOrgOrgsVO orgOrgs) {
        List<Object> argList = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append(getSelectSql());
        sql.append(getSelectParam(orgOrgs, argList));

        return hiveJdbcTemplate.query(
                sql.toString(), argList.toArray(), new BeanPropertyRowMapper<>(GasOrgOrgsVO.class));
    }

    private String getSelectSql() {
        return "select code, name, pk_org as pkOrg from " + odbDbName +
                ".gas_org_orgs";
    }

    private String getSelectParam(GasOrgOrgsVO orgOrgs, List<Object> argList) {
        StringBuilder sql = new StringBuilder();
        sql.append(" where 1=1");
        if (StringUtils.isNotEmpty(orgOrgs.getCode())) {
            sql.append(" and code = ?");
            argList.add(orgOrgs.getCode());
        }
        if (StringUtils.isNotEmpty(orgOrgs.getName())) {
            sql.append(" and name = ?");
            argList.add(orgOrgs.getName());
        }
        if (StringUtils.isNotEmpty(orgOrgs.getPkOrg())) {
            sql.append(" and pk_org = ?");
            argList.add(orgOrgs.getPkOrg());
        }

        return sql.toString();
    }
}
