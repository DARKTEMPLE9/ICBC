package net.northking.iacmp.imp.mapper;

import net.northking.iacmp.imp.domain.SmUserOrgan;

import java.util.List;

/**
 * 用户机构关联 数据层
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
public interface SmUserOrganMapper {
    /**
     * 查询用户机构关联信息
     *
     * @param id 用户机构关联ID
     * @return 用户机构关联信息
     */
    SmUserOrgan selectSmUserOrganById(String id);

    /**
     * 查询用户机构关联列表
     *
     * @param smUserOrgan 用户机构关联信息
     * @return 用户机构关联集合
     */
    List<SmUserOrgan> selectSmUserOrganList(SmUserOrgan smUserOrgan);

    /**
     * 新增用户机构关联
     *
     * @param smUserOrgan 用户机构关联信息
     * @return 结果
     */
    int insertSmUserOrgan(SmUserOrgan smUserOrgan);

    /**
     * 修改用户机构关联
     *
     * @param smUserOrgan 用户机构关联信息
     * @return 结果
     */
    int updateSmUserOrgan(SmUserOrgan smUserOrgan);

    /**
     * 删除用户机构关联
     *
     * @param id 用户机构关联ID
     * @return 结果
     */
    int deleteSmUserOrganById(String id);

    /**
     * 批量删除用户机构关联
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteSmUserOrganByIds(String[] ids);

}