package net.northking.iacmp.server.service.impl;

import iacmp.biz.common.dao.mapper.ams.PhoneVersionMapper;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.common.bean.domain.ams.PhoneVersion;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.server.service.IPhoneVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 手机版本 服务层实现
 *
 * @author wxy
 * @date 2019-08-01
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class PhoneVersionServiceImpl implements IPhoneVersionService {
    @Autowired
    private PhoneVersionMapper phoneVersionMapper;

    /**
     * 查询手机版本信息
     *
     * @param id 手机版本ID
     * @return 手机版本信息
     */
    @Override
    public PhoneVersion selectPhoneVersionById(String id) {
        return phoneVersionMapper.selectPhoneVersionById(id);
    }

    /**
     * 查询手机版本列表
     *
     * @param phoneVersion 手机版本信息
     * @return 手机版本集合
     */
    @Override
    public List<PhoneVersion> selectPhoneVersionList(PhoneVersion phoneVersion) {
        return phoneVersionMapper.selectPhoneVersionList(phoneVersion);
    }

    /**
     * 新增手机版本
     *
     * @param phoneVersion 手机版本信息
     * @return 结果
     */
    @Override
    public int insertPhoneVersion(PhoneVersion phoneVersion) {
        return phoneVersionMapper.insertPhoneVersion(phoneVersion);
    }

    /**
     * 修改手机版本
     *
     * @param phoneVersion 手机版本信息
     * @return 结果
     */
    @Override
    public int updatePhoneVersion(PhoneVersion phoneVersion) {
        return phoneVersionMapper.updatePhoneVersion(phoneVersion);
    }

    /**
     * 删除手机版本对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePhoneVersionByIds(String ids) {
        return phoneVersionMapper.deletePhoneVersionByIds(Convert.toStrArray(ids));
    }

}
