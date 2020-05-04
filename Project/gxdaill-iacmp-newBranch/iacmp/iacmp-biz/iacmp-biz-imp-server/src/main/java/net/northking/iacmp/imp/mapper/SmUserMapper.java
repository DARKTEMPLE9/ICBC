package net.northking.iacmp.imp.mapper;

import net.northking.iacmp.imp.domain.SmUser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户 数据层
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
public interface SmUserMapper {
    /**
     * 查询用户信息
     *
     * @param id 用户ID
     * @return 用户信息
     */
    SmUser selectSmUserById(String id);

    HashMap selectUserAndOrgNameByUserId(String userId);

    /**
     * 查询用户列表
     *
     * @param smUser 用户信息
     * @return 用户集合
     */
    List<SmUser> selectSmUserList(Map map);

    Integer selectSmUserCount(HashMap map);

    List<SmUser> findByUserCode(String userCode);

    /**
     * 新增用户
     *
     * @param smUser 用户信息
     * @return 结果
     */
    Integer insertSmUser(SmUser smUser);

    /**
     * 修改用户
     *
     * @param smUser 用户信息
     * @return 结果
     */
    Integer updateSmUser(SmUser smUser);

    /**
     * 删除用户
     *
     * @param id 用户ID
     * @return 结果
     */
    Integer deleteSmUserById(String id);

    /**
     * 批量删除用户
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteSmUserByIds(String[] ids);


}