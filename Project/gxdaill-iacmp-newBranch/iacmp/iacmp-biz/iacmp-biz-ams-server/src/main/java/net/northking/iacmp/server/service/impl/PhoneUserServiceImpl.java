package net.northking.iacmp.server.service.impl;

import iacmp.biz.common.dao.mapper.ams.PhoneUserMapper;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.common.bean.domain.ams.PhoneUser;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.server.service.IPhoneUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 手机用户 服务层实现
 *
 * @author wxy
 * @date 2019-08-01
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class PhoneUserServiceImpl implements IPhoneUserService {
    @Autowired
    private PhoneUserMapper phoneUserMapper;

    /**
     * 查询手机用户信息
     *
     * @param id 手机用户ID
     * @return 手机用户信息
     */
    @Override
    public PhoneUser selectPhoneUserById(String id) {
        return phoneUserMapper.selectPhoneUserById(id);
    }

    /**
     * 查询手机用户列表
     *
     * @param phoneUser 手机用户信息
     * @return 手机用户集合
     */
    @Override
    public List<PhoneUser> selectPhoneUserList(PhoneUser phoneUser) {
        return phoneUserMapper.selectPhoneUserList(phoneUser);
    }

    /**
     * 新增手机用户
     *
     * @param phoneUser 手机用户信息
     * @return 结果
     */
    @Override
    public int insertPhoneUser(PhoneUser phoneUser) {
        return phoneUserMapper.insertPhoneUser(phoneUser);
    }

    /**
     * 修改手机用户
     *
     * @param phoneUser 手机用户信息
     * @return 结果
     */
    @Override
    public int updatePhoneUser(PhoneUser phoneUser) {
        return phoneUserMapper.updatePhoneUser(phoneUser);
    }

    /**
     * 删除手机用户对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePhoneUserByIds(String ids) {
        return phoneUserMapper.deletePhoneUserByIds(Convert.toStrArray(ids));
    }

}
