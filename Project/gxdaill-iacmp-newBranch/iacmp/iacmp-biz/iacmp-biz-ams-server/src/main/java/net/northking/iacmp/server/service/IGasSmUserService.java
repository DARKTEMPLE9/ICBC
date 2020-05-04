package net.northking.iacmp.server.service;

import net.northking.iacmp.common.bean.vo.ams.GasSmUserVO;

import java.util.List;

/**
 * 用户 服务层
 *
 * @author wei.chen
 * @date 2019-09-23
 */
public interface IGasSmUserService {

    /**
     * 查询用户列表
     *
     * @param smUser 用户信息
     * @return 用户集合
     */
    public List<GasSmUserVO> selectGasSmUserList(GasSmUserVO smUser);

}
