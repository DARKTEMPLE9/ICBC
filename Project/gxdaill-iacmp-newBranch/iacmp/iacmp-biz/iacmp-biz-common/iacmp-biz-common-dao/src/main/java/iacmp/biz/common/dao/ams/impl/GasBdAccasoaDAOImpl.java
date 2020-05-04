package iacmp.biz.common.dao.ams.impl;

import iacmp.biz.common.dao.ams.GasBdAccasoaDAO;
import net.northking.iacmp.common.bean.vo.ams.GasBdAccasoaVO;
import net.northking.iacmp.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 会计科目信息 DAO实现类
 *
 * @Author: wei.chen
 * @Date Created: in 2019/9/12 17:41
 */
@Repository
public class GasBdAccasoaDAOImpl implements GasBdAccasoaDAO {

    @Value("${hive.odb.dbName}")
    private String odbDbName;

    @Autowired
    JdbcTemplate hiveJdbcTemplate;

    @Override
    public List<GasBdAccasoaVO> selectGasBdAccasoaList(GasBdAccasoaVO bdAccasoa) {
        List<Object> argList = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append(getSelectSql());
        sql.append(getSelectParam(bdAccasoa, argList));

        return hiveJdbcTemplate.query(
                sql.toString(), argList.toArray(), new BeanPropertyRowMapper<>(GasBdAccasoaVO.class));
    }

    private String getSelectSql() {
        return "select dispname, name from " + odbDbName +
                ".gas_bd_accasoa";
    }

    private String getSelectParam(GasBdAccasoaVO bdAccasoa, List<Object> argList) {
        StringBuilder sql = new StringBuilder();
        sql.append(" where locate('\\\\', dispname) <> 0");
        if (StringUtils.isNotEmpty(bdAccasoa.getDispname())) {
            sql.append(" and dispname like concat ('%', ?, '%')");
            argList.add(bdAccasoa.getDispname());
        }
        if (StringUtils.isNotEmpty(bdAccasoa.getName())) {
            sql.append(" and name like concat ('%', ?, '%')");
            argList.add(bdAccasoa.getName());
        }

        return sql.toString();
    }
}
