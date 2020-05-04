package iacmp.biz.common.dao.mapper.ams;


import net.northking.iacmp.common.bean.domain.ams.SmOrgan;

import java.util.List;

/**
 * 机构 数据层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface SmOrganMapper {
    /**
     * 查询机构信息
     *
     * @param id 机构ID
     * @return 机构信息
     */
    SmOrgan selectSmOrganById(String id);

    /**
     * 查询机构列表
     *
     * @param smOrgan 机构信息
     * @return 机构集合
     */
    List<SmOrgan> selectSmOrganList(SmOrgan smOrgan);

    /**
     * 新增机构
     *
     * @param smOrgan 机构信息
     * @return 结果
     */
    int insertSmOrgan(SmOrgan smOrgan);

    /**
     * 修改机构
     *
     * @param smOrgan 机构信息
     * @return 结果
     */
    int updateSmOrgan(SmOrgan smOrgan);

    /**
     * 删除机构
     *
     * @param id 机构ID
     * @return 结果
     */
    int deleteSmOrganById(String id);

    /**
     * 批量删除机构
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteSmOrganByIds(String[] ids);

}