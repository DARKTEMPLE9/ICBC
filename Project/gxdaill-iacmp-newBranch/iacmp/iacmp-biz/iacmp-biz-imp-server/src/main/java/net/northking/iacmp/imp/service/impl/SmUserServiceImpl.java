package net.northking.iacmp.imp.service.impl;


import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.SmUser;
import net.northking.iacmp.imp.mapper.SmUserMapper;
import net.northking.iacmp.imp.service.ISmUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户 服务层实现
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class SmUserServiceImpl implements ISmUserService {
    @Autowired
    private SmUserMapper smUserMapper;

    /**
     * 查询用户信息
     *
     * @param id 用户ID
     * @return 用户信息
     */
    @Override
    public SmUser selectSmUserById(String id) {
        return smUserMapper.selectSmUserById(id);
    }

    @Override
    public HashMap selectUserAndOrgNameByUserId(String userId) {
        return smUserMapper.selectUserAndOrgNameByUserId(userId);
    }

    ;

    /**
     * 查询用户列表
     *
     * @return 用户集合
     */
    @Override
    public List<SmUser> selectSmUserList(Map map) {
        return smUserMapper.selectSmUserList(map);
    }

    @Override
    public Integer selectSmUserCount(HashMap map) {
        return smUserMapper.selectSmUserCount(map);
    }

    @Override
    public List<SmUser> findByUserCode(String userCode) {
        return smUserMapper.findByUserCode(userCode);
    }

    /**
     * 新增用户
     *
     * @param smUser 用户信息
     * @return 结果
     */
    @Override
    public Integer insertSmUser(SmUser smUser) {
        return smUserMapper.insertSmUser(smUser);
    }

    /**
     * 修改用户
     *
     * @param smUser 用户信息
     * @return 结果
     */
    @Override
    public Integer updateSmUser(SmUser smUser) {
        return smUserMapper.updateSmUser(smUser);
    }

    /**
     * 删除用户对象
     *
     * @return 结果
     */
    @Override
    public Integer deleteSmUserById(String id) {
        return smUserMapper.deleteSmUserById(id);
    }


}
