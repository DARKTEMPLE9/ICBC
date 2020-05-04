package net.northking.iacmp.imp.mapper;

import net.northking.iacmp.imp.domain.SmOrgan;

import java.util.HashMap;
import java.util.List;

/**
 * 部门机构 数据层
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
public interface SmOrganMapper {
    /**
     * 查询部门机构信息
     *
     * @param id 部门机构ID
     * @return 部门机构信息
     */
    SmOrgan selectSmOrganById(String id);

    /**
     * 查询部门机构列表
     *
     * @param smOrgan 部门机构信息
     * @return 部门机构集合
     */
    List<SmOrgan> selectSmOrganList(HashMap smOrgan);

    Integer count(HashMap map);

    List<SmOrgan> findByOrganCode(String code);

    /**
     * 新增部门机构
     *
     * @param smOrgan 部门机构信息
     * @return 结果
     */
    Integer insertSmOrgan(SmOrgan smOrgan);

    /**
     * 修改部门机构
     *
     * @param smOrgan 部门机构信息
     * @return 结果
     */
    int updateSmOrgan(SmOrgan smOrgan);

    /**
     * 删除部门机构
     *
     * @param id 部门机构ID
     * @return 结果
     */
    int deleteSmOrganById(String id);

    /**
     * 批量删除部门机构
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteSmOrganByIds(String[] ids);

}