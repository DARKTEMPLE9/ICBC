package iacmp.biz.common.dao.ams.impl;

import iacmp.biz.common.dao.ams.GasSmUserDAO;
import net.northking.iacmp.common.bean.vo.ams.GasSmUserVO;
import net.northking.iacmp.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户信息 DAO实现类
 *
 * @Author: wei.chen
 * @Date Created: in 2019/9/12 17:41
 */
@Repository
public class GasSmUserDAOImpl implements GasSmUserDAO {

    @Value("${hive.odb.dbName}")
    private String odbDbName;

    @Autowired
    JdbcTemplate hiveJdbcTemplate;

    @Override
    public List<GasSmUserVO> selectGasSmUserList(GasSmUserVO smUser) {
        List<Object> argList = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append(getSelectSql());
        sql.append(getSelectParam(smUser, argList));

        return hiveJdbcTemplate.query(
                sql.toString(), argList.toArray(), new BeanPropertyRowMapper<>(GasSmUserVO.class));
    }

    private String getSelectSql() {
        return "select user_code as userCode, user_name as userName, cuserid from " + odbDbName +
                ".gas_sm_user";
    }

    private String getSelectParam(GasSmUserVO smUser, List<Object> argList) {
        StringBuilder sql = new StringBuilder();
        sql.append(" where 1=1");
        if (StringUtils.isNotEmpty(smUser.getUserCode())) {
            sql.append(" and user_code = ?");
            argList.add(smUser.getUserCode());
        }
        if (StringUtils.isNotEmpty(smUser.getUserName())) {
            sql.append(" and user_name = ?");
            argList.add(smUser.getUserName());
        }
        if (StringUtils.isNotEmpty(smUser.getCuserid())) {
            sql.append(" and cuserid = ?");
            argList.add(smUser.getCuserid());
        }

        return sql.toString();
    }
}
