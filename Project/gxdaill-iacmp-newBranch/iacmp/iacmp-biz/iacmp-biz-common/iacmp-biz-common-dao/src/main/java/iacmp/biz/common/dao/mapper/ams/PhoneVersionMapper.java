package iacmp.biz.common.dao.mapper.ams;


import net.northking.iacmp.common.bean.domain.ams.PhoneVersion;

import java.util.List;

/**
 * 手机版本 数据层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface PhoneVersionMapper {
    /**
     * 查询手机版本信息
     *
     * @param id 手机版本ID
     * @return 手机版本信息
     */
    PhoneVersion selectPhoneVersionById(String id);

    /**
     * 查询手机版本列表
     *
     * @param phoneVersion 手机版本信息
     * @return 手机版本集合
     */
    List<PhoneVersion> selectPhoneVersionList(PhoneVersion phoneVersion);

    /**
     * 新增手机版本
     *
     * @param phoneVersion 手机版本信息
     * @return 结果
     */
    int insertPhoneVersion(PhoneVersion phoneVersion);

    /**
     * 修改手机版本
     *
     * @param phoneVersion 手机版本信息
     * @return 结果
     */
    int updatePhoneVersion(PhoneVersion phoneVersion);

    /**
     * 删除手机版本
     *
     * @param id 手机版本ID
     * @return 结果
     */
    int deletePhoneVersionById(String id);

    /**
     * 批量删除手机版本
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deletePhoneVersionByIds(String[] ids);

}