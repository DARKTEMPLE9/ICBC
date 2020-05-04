package iacmp.biz.common.dao.mapper.ams;


import net.northking.iacmp.common.bean.domain.ams.PhoneUser;

import java.util.List;

/**
 * 手机用户 数据层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface PhoneUserMapper {
    /**
     * 查询手机用户信息
     *
     * @param id 手机用户ID
     * @return 手机用户信息
     */
    PhoneUser selectPhoneUserById(String id);

    /**
     * 查询手机用户列表
     *
     * @param phoneUser 手机用户信息
     * @return 手机用户集合
     */
    List<PhoneUser> selectPhoneUserList(PhoneUser phoneUser);

    /**
     * 新增手机用户
     *
     * @param phoneUser 手机用户信息
     * @return 结果
     */
    int insertPhoneUser(PhoneUser phoneUser);

    /**
     * 修改手机用户
     *
     * @param phoneUser 手机用户信息
     * @return 结果
     */
    int updatePhoneUser(PhoneUser phoneUser);

    /**
     * 删除手机用户
     *
     * @param id 手机用户ID
     * @return 结果
     */
    int deletePhoneUserById(String id);

    /**
     * 批量删除手机用户
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deletePhoneUserByIds(String[] ids);

}