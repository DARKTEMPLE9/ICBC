package net.northking.iacmp.server.service;

import net.northking.iacmp.common.bean.domain.ams.PhoneVersion;

import java.util.List;

/**
 * 手机版本 服务层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface IPhoneVersionService {
    /**
     * 查询手机版本信息
     *
     * @param id 手机版本ID
     * @return 手机版本信息
     */
    public PhoneVersion selectPhoneVersionById(String id);

    /**
     * 查询手机版本列表
     *
     * @param phoneVersion 手机版本信息
     * @return 手机版本集合
     */
    public List<PhoneVersion> selectPhoneVersionList(PhoneVersion phoneVersion);

    /**
     * 新增手机版本
     *
     * @param phoneVersion 手机版本信息
     * @return 结果
     */
    public int insertPhoneVersion(PhoneVersion phoneVersion);

    /**
     * 修改手机版本
     *
     * @param phoneVersion 手机版本信息
     * @return 结果
     */
    public int updatePhoneVersion(PhoneVersion phoneVersion);

    /**
     * 删除手机版本信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePhoneVersionByIds(String ids);

}
