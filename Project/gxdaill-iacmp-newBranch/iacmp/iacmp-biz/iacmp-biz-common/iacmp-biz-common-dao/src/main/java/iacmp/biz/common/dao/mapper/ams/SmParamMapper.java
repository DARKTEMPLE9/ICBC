package iacmp.biz.common.dao.mapper.ams;


import net.northking.iacmp.common.bean.domain.ams.SmParam;

import java.util.List;

/**
 * 参数 数据层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface SmParamMapper {
    /**
     * 查询参数信息
     *
     * @param id 参数ID
     * @return 参数信息
     */
    SmParam selectSmParamById(String id);

    /**
     * 查询参数列表
     *
     * @param smParam 参数信息
     * @return 参数集合
     */
    List<SmParam> selectSmParamList(SmParam smParam);

    /**
     * 新增参数
     *
     * @param smParam 参数信息
     * @return 结果
     */
    int insertSmParam(SmParam smParam);

    /**
     * 修改参数
     *
     * @param smParam 参数信息
     * @return 结果
     */
    int updateSmParam(SmParam smParam);

    /**
     * 删除参数
     *
     * @param id 参数ID
     * @return 结果
     */
    int deleteSmParamById(String id);

    /**
     * 批量删除参数
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteSmParamByIds(String[] ids);

}