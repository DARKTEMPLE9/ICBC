package net.northking.iacmp.imp.mapper;

import net.northking.iacmp.imp.domain.SmParam;

import java.util.HashMap;
import java.util.List;

/**
 * 参数配置 数据层
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
public interface SmParamMapper {
    /**
     * 查询参数配置信息
     *
     * @param id 参数配置ID
     * @return 参数配置信息
     */
    SmParam selectSmParamById(String id);

    HashMap queryById(String id);

    /**
     * 查询参数配置列表
     *
     * @param smParam 参数配置信息
     * @return 参数配置集合
     */
    List<SmParam> selectSmParamList(SmParam smParam);

    List<SmParam> listAll(HashMap smParam);

    Integer count(HashMap smParam);

    /**
     * 新增参数配置
     *
     * @param smParam 参数配置信息
     * @return 结果
     */
    int insertSmParam(SmParam smParam);

    /**
     * 修改参数配置
     *
     * @param smParam 参数配置信息
     * @return 结果
     */
    Integer updateSmParam(HashMap smParam);

    /**
     * 删除参数配置
     *
     * @param id 参数配置ID
     * @return 结果
     */
    Integer deleteById(String id);

    /**
     * 批量删除参数配置
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteSmParamByIds(String[] ids);

}