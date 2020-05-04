package net.northking.iacmp.imp.service.impl;


import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.PhoneUser;
import net.northking.iacmp.imp.mapper.PhoneUserMapper;
import net.northking.iacmp.imp.service.IPhoneUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 移动端用户 服务层实现
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class PhoneUserServiceImpl implements IPhoneUserService {
    @Autowired
    private PhoneUserMapper phoneUserMapper;

    /**
     * 查询移动端用户信息
     *
     * @param id 移动端用户ID
     * @return 移动端用户信息
     */
    @Override
    public PhoneUser selectPhoneUserById(String id) {
        return phoneUserMapper.selectPhoneUserById(id);
    }

    /**
     * 查询移动端用户列表
     *
     * @param phoneUser 移动端用户信息
     * @return 移动端用户集合
     */
    @Override
    public List<PhoneUser> selectPhoneUserList(PhoneUser phoneUser) {
        return phoneUserMapper.selectPhoneUserList(phoneUser);
    }

    /**
     * 新增移动端用户
     *
     * @param phoneUser 移动端用户信息
     * @return 结果
     */
    @Override
    public int insertPhoneUser(PhoneUser phoneUser) {
        return phoneUserMapper.insertPhoneUser(phoneUser);
    }

    /**
     * 修改移动端用户
     *
     * @param phoneUser 移动端用户信息
     * @return 结果
     */
    @Override
    public int updatePhoneUser(PhoneUser phoneUser) {
        return phoneUserMapper.updatePhoneUser(phoneUser);
    }

    /**
     * 删除移动端用户对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePhoneUserByIds(String ids) {
        return phoneUserMapper.deletePhoneUserByIds(Convert.toStrArray(ids));
    }

}
