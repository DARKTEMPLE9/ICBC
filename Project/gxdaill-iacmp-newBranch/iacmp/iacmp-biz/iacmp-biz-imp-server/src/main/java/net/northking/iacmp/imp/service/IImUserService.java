package net.northking.iacmp.imp.service;

import net.northking.iacmp.imp.domain.ImUser;

import java.util.List;

/**
 * 客户 服务层
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
public interface IImUserService {
    /**
     * 查询客户信息
     *
     * @param iD 客户ID
     * @return 客户信息
     */
    ImUser selectImUserById(Long iD);

    /**
     * 查询客户列表
     *
     * @param imUser 客户信息
     * @return 客户集合
     */
    List<ImUser> selectImUserList(ImUser imUser);

    /**
     * 新增客户
     *
     * @param imUser 客户信息
     * @return 结果
     */
    int insertImUser(ImUser imUser);

    /**
     * 修改客户
     *
     * @param imUser 客户信息
     * @return 结果
     */
    int updateImUser(ImUser imUser);

    /**
     * 删除客户信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteImUserByIds(String ids);

}
