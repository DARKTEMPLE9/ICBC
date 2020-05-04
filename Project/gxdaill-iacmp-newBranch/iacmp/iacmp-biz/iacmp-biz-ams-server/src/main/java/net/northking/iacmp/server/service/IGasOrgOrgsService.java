package net.northking.iacmp.server.service;

import net.northking.iacmp.common.bean.vo.ams.GasOrgOrgsVO;

import java.util.List;

/**
 * 部门 服务层
 *
 * @author wei.chen
 * @date 2019-09-23
 */
public interface IGasOrgOrgsService {

    /**
     * 查询部门列表
     *
     * @param orgOrgs 部门信息
     * @return 部门集合
     */
    public List<GasOrgOrgsVO> selectGasOrgOrgsList(GasOrgOrgsVO orgOrgs);

}
