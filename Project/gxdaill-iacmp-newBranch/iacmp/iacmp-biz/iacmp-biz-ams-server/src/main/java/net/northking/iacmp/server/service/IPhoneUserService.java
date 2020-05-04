package net.northking.iacmp.server.service;

import net.northking.iacmp.common.bean.domain.ams.PhoneUser;

import java.util.List;

/**
 * 手机用户 服务层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface IPhoneUserService {
    /**
     * 查询手机用户信息
     *
     * @param id 手机用户ID
     * @return 手机用户信息
     */
    public PhoneUser selectPhoneUserById(String id);

    /**
     * 查询手机用户列表
     *
     * @param phoneUser 手机用户信息
     * @return 手机用户集合
     */
    public List<PhoneUser> selectPhoneUserList(PhoneUser phoneUser);

    /**
     * 新增手机用户
     *
     * @param phoneUser 手机用户信息
     * @return 结果
     */
    public int insertPhoneUser(PhoneUser phoneUser);

    /**
     * 修改手机用户
     *
     * @param phoneUser 手机用户信息
     * @return 结果
     */
    public int updatePhoneUser(PhoneUser phoneUser);

    /**
     * 删除手机用户信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePhoneUserByIds(String ids);

}
