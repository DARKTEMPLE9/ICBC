package iacmp.biz.common.dao.ams;

import net.northking.iacmp.common.bean.vo.ams.GasOrgOrgsVO;

import java.util.List;

/**
 * 部门表 DAO层
 *
 * @Author: wei.chen
 * @Date Created: in 2019/9/23 17:36
 */
public interface GasOrgOrgsDAO {

    /**
     * 查询部门列表
     *
     * @param orgOrgs 部门查询信息
     * @return 部门集合
     */
    List<GasOrgOrgsVO> selectGasOrgOrgsList(GasOrgOrgsVO orgOrgs);
}
