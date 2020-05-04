package net.northking.iacmp.server.service.impl;

import iacmp.biz.common.dao.mapper.ams.SmUserMapper;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.common.bean.domain.ams.SmUser;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.server.service.ISmUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户 服务层实现
 *
 * @author wxy
 * @date 2019-08-01
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

    /**
     * 查询用户列表
     *
     * @param smUser 用户信息
     * @return 用户集合
     */
    @Override
    public List<SmUser> selectSmUserList(SmUser smUser) {
        return smUserMapper.selectSmUserList(smUser);
    }

    /**
     * 新增用户
     *
     * @param smUser 用户信息
     * @return 结果
     */
    @Override
    public int insertSmUser(SmUser smUser) {
        return smUserMapper.insertSmUser(smUser);
    }

    /**
     * 修改用户
     *
     * @param smUser 用户信息
     * @return 结果
     */
    @Override
    public int updateSmUser(SmUser smUser) {
        return smUserMapper.updateSmUser(smUser);
    }

    /**
     * 删除用户对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSmUserByIds(String ids) {
        return smUserMapper.deleteSmUserByIds(Convert.toStrArray(ids));
    }

    /**
     * @Author: weizhe.fan
     * @Description:获取所有角色为档案管理员的用户
     * @CreateDate: 18:19.2019/8/2
     */
    @Override
    public List<SmUser> getArchManager() {
        List<SmUser> list = smUserMapper.getArchManager();
        return list;
    }

}
