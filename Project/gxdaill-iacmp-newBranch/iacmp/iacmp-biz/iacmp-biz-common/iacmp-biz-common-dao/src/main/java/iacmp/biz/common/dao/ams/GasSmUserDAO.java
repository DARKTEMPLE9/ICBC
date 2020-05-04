package iacmp.biz.common.dao.ams;

import net.northking.iacmp.common.bean.vo.ams.GasSmUserVO;

import java.util.List;

/**
 * 用户表 DAO层
 *
 * @Author: wei.chen
 * @Date Created: in 2019/9/23 17:36
 */
public interface GasSmUserDAO {

    /**
     * 查询用户列表
     *
     * @param smUser 用户查询信息
     * @return 用户集合
     */
    List<GasSmUserVO> selectGasSmUserList(GasSmUserVO smUser);
}
