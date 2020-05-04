package iacmp.biz.common.dao.mapper.ams;


import net.northking.iacmp.common.bean.domain.ams.SmUser;

import java.util.List;

/**
 * 用户 数据层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface SmUserMapper {
    /**
     * 查询用户信息
     *
     * @param id 用户ID
     * @return 用户信息
     */
    SmUser selectSmUserById(String id);

    /**
     * 查询用户列表
     *
     * @param smUser 用户信息
     * @return 用户集合
     */
    List<SmUser> selectSmUserList(SmUser smUser);

    /**
     * 新增用户
     *
     * @param smUser 用户信息
     * @return 结果
     */
    int insertSmUser(SmUser smUser);

    /**
     * 修改用户
     *
     * @param smUser 用户信息
     * @return 结果
     */
    int updateSmUser(SmUser smUser);

    /**
     * 删除用户
     *
     * @param id 用户ID
     * @return 结果
     */
    int deleteSmUserById(String id);

    /**
     * 批量删除用户
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteSmUserByIds(String[] ids);

    /**
     * @return
     */
    List<SmUser> getArchManager();
}