package net.northking.iacmp.imp.service.impl;


import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.ImUser;
import net.northking.iacmp.imp.mapper.ImUserMapper;
import net.northking.iacmp.imp.service.IImUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 客户 服务层实现
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class ImUserServiceImpl implements IImUserService {
    @Autowired
    private ImUserMapper imUserMapper;

    /**
     * 查询客户信息
     *
     * @param id 客户ID
     * @return 客户信息
     */
    @Override
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public ImUser selectImUserById(Long id) {
        return imUserMapper.selectImUserById(id);
    }

    /**
     * 查询客户列表
     *
     * @param imUser 客户信息
     * @return 客户集合
     */
    @Override
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public List<ImUser> selectImUserList(ImUser imUser) {
        return imUserMapper.selectImUserList(imUser);
    }

    /**
     * 新增客户
     *
     * @param imUser 客户信息
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public int insertImUser(ImUser imUser) {
        return imUserMapper.insertImUser(imUser);
    }

    /**
     * 修改客户
     *
     * @param imUser 客户信息
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public int updateImUser(ImUser imUser) {
        return imUserMapper.updateImUser(imUser);
    }

    /**
     * 删除客户对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteImUserByIds(String ids) {
        return imUserMapper.deleteImUserByIds(Convert.toStrArray(ids));
    }

}
