package net.northking.iacmp.server.service;

import net.northking.iacmp.common.bean.domain.ams.SmParam;

import java.util.List;

/**
 * 参数 服务层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface ISmParamService {
    /**
     * 查询参数信息
     *
     * @param id 参数ID
     * @return 参数信息
     */
    public SmParam selectSmParamById(String id);

    /**
     * 查询参数列表
     *
     * @param smParam 参数信息
     * @return 参数集合
     */
    public List<SmParam> selectSmParamList(SmParam smParam);

    /**
     * 新增参数
     *
     * @param smParam 参数信息
     * @return 结果
     */
    public int insertSmParam(SmParam smParam);

    /**
     * 修改参数
     *
     * @param smParam 参数信息
     * @return 结果
     */
    public int updateSmParam(SmParam smParam);

    /**
     * 删除参数信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSmParamByIds(String ids);

}
