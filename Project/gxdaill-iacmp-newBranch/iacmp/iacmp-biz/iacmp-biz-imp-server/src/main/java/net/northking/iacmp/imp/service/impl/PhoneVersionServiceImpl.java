package net.northking.iacmp.imp.service.impl;


import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.PhoneVersion;
import net.northking.iacmp.imp.mapper.PhoneVersionMapper;
import net.northking.iacmp.imp.service.IPhoneVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 移动端版本 服务层实现
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class PhoneVersionServiceImpl implements IPhoneVersionService {
    @Autowired
    private PhoneVersionMapper phoneVersionMapper;

    /**
     * 查询移动端版本信息
     *
     * @param id 移动端版本ID
     * @return 移动端版本信息
     */
    @Override
    public PhoneVersion selectPhoneVersionById(String id) {
        return phoneVersionMapper.selectPhoneVersionById(id);
    }

    /**
     * 查询移动端版本列表
     *
     * @param phoneVersion 移动端版本信息
     * @return 移动端版本集合
     */
    @Override
    public List<PhoneVersion> selectPhoneVersionList(PhoneVersion phoneVersion) {
        return phoneVersionMapper.selectPhoneVersionList(phoneVersion);
    }

    /**
     * 新增移动端版本
     *
     * @param phoneVersion 移动端版本信息
     * @return 结果
     */
    @Override
    public int insertPhoneVersion(PhoneVersion phoneVersion) {
        return phoneVersionMapper.insertPhoneVersion(phoneVersion);
    }

    /**
     * 修改移动端版本
     *
     * @param phoneVersion 移动端版本信息
     * @return 结果
     */
    @Override
    public int updatePhoneVersion(PhoneVersion phoneVersion) {
        return phoneVersionMapper.updatePhoneVersion(phoneVersion);
    }

    /**
     * 删除移动端版本对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePhoneVersionByIds(String ids) {
        return phoneVersionMapper.deletePhoneVersionByIds(Convert.toStrArray(ids));
    }

}
