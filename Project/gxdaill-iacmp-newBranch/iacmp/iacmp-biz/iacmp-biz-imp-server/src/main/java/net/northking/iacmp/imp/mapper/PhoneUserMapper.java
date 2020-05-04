package net.northking.iacmp.imp.mapper;

import net.northking.iacmp.imp.domain.PhoneUser;

import java.util.List;

/**
 * 移动端用户 数据层
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
public interface PhoneUserMapper {
    /**
     * 查询移动端用户信息
     *
     * @param id 移动端用户ID
     * @return 移动端用户信息
     */
    PhoneUser selectPhoneUserById(String id);

    /**
     * 查询移动端用户列表
     *
     * @param phoneUser 移动端用户信息
     * @return 移动端用户集合
     */
    List<PhoneUser> selectPhoneUserList(PhoneUser phoneUser);

    /**
     * 新增移动端用户
     *
     * @param phoneUser 移动端用户信息
     * @return 结果
     */
    int insertPhoneUser(PhoneUser phoneUser);

    /**
     * 修改移动端用户
     *
     * @param phoneUser 移动端用户信息
     * @return 结果
     */
    int updatePhoneUser(PhoneUser phoneUser);

    /**
     * 删除移动端用户
     *
     * @param id 移动端用户ID
     * @return 结果
     */
    int deletePhoneUserById(String id);

    /**
     * 批量删除移动端用户
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deletePhoneUserByIds(String[] ids);

}