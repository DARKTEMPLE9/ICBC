package net.northking.iacmp.server.service.impl;

import iacmp.biz.common.dao.mapper.ams.SmSecretMapper;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.common.bean.domain.ams.SmSecret;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.server.service.ISmSecretService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 查询权限不对外公开 服务层实现
 *
 * @author wxy
 * @date 2019-08-01
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class SmSecretServiceImpl implements ISmSecretService {
    @Autowired
    private SmSecretMapper smSecretMapper;

    /**
     * 查询查询权限不对外公开信息
     *
     * @param id 查询权限不对外公开ID
     * @return 查询权限不对外公开信息
     */
    @Override
    public SmSecret selectSmSecretById(String id) {
        return smSecretMapper.selectSmSecretById(id);
    }

    /**
     * 查询查询权限不对外公开列表
     *
     * @param smSecret 查询权限不对外公开信息
     * @return 查询权限不对外公开集合
     */
    @Override
    public List<SmSecret> selectSmSecretList(SmSecret smSecret) {
        return smSecretMapper.selectSmSecretList(smSecret);
    }

    /**
     * 新增查询权限不对外公开
     *
     * @param smSecret 查询权限不对外公开信息
     * @return 结果
     */
    @Override
    public int insertSmSecret(SmSecret smSecret) {
        return smSecretMapper.insertSmSecret(smSecret);
    }

    /**
     * 修改查询权限不对外公开
     *
     * @param smSecret 查询权限不对外公开信息
     * @return 结果
     */
    @Override
    public int updateSmSecret(SmSecret smSecret) {
        return smSecretMapper.updateSmSecret(smSecret);
    }

    /**
     * 删除查询权限不对外公开对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSmSecretByIds(String ids) {
        return smSecretMapper.deleteSmSecretByIds(Convert.toStrArray(ids));
    }

}
