package net.northking.iacmp.server.service;

import net.northking.iacmp.common.bean.domain.ams.SmUser;

import java.util.List;

/**
 * 用户 服务层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface ISmUserService {
    /**
     * 查询用户信息
     *
     * @param id 用户ID
     * @return 用户信息
     */
    public SmUser selectSmUserById(String id);

    /**
     * 查询用户列表
     *
     * @param smUser 用户信息
     * @return 用户集合
     */
    public List<SmUser> selectSmUserList(SmUser smUser);

    /**
     * 新增用户
     *
     * @param smUser 用户信息
     * @return 结果
     */
    public int insertSmUser(SmUser smUser);

    /**
     * 修改用户
     *
     * @param smUser 用户信息
     * @return 结果
     */
    public int updateSmUser(SmUser smUser);

    /**
     * 删除用户信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSmUserByIds(String ids);

    /**
     * @Author: weizhe.fan
     * @Description:获取所有角色为档案管理员的用户
     * @CreateDate: 18:19.2019/8/2
     */
    public List<SmUser> getArchManager();

}
