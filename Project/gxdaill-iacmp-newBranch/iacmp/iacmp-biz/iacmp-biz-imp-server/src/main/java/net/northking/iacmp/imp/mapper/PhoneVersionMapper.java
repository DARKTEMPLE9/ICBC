package net.northking.iacmp.imp.mapper;

import net.northking.iacmp.imp.domain.PhoneVersion;

import java.util.List;

/**
 * 移动端版本 数据层
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
public interface PhoneVersionMapper {
    /**
     * 查询移动端版本信息
     *
     * @param id 移动端版本ID
     * @return 移动端版本信息
     */
    PhoneVersion selectPhoneVersionById(String id);

    /**
     * 查询移动端版本列表
     *
     * @param phoneVersion 移动端版本信息
     * @return 移动端版本集合
     */
    List<PhoneVersion> selectPhoneVersionList(PhoneVersion phoneVersion);

    /**
     * 新增移动端版本
     *
     * @param phoneVersion 移动端版本信息
     * @return 结果
     */
    int insertPhoneVersion(PhoneVersion phoneVersion);

    /**
     * 修改移动端版本
     *
     * @param phoneVersion 移动端版本信息
     * @return 结果
     */
    int updatePhoneVersion(PhoneVersion phoneVersion);

    /**
     * 删除移动端版本
     *
     * @param id 移动端版本ID
     * @return 结果
     */
    int deletePhoneVersionById(String id);

    /**
     * 批量删除移动端版本
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deletePhoneVersionByIds(String[] ids);

}