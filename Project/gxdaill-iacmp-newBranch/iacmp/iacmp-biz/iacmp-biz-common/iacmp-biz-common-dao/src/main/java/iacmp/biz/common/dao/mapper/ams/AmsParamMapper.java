package iacmp.biz.common.dao.mapper.ams;


import net.northking.iacmp.common.bean.domain.ams.AmsParam;

import java.util.List;

/**
 * 档案参数 数据层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface AmsParamMapper {
    /**
     * 查询档案参数信息
     *
     * @param id 档案参数ID
     * @return 档案参数信息
     */
    AmsParam selectAmsParamById(String id);

    /**
     * 查询档案参数列表
     *
     * @param amsParam 档案参数信息
     * @return 档案参数集合
     */
    List<AmsParam> selectAmsParamList(AmsParam amsParam);

    /**
     * 新增档案参数
     *
     * @param amsParam 档案参数信息
     * @return 结果
     */
    int insertAmsParam(AmsParam amsParam);

    /**
     * 修改档案参数
     *
     * @param amsParam 档案参数信息
     * @return 结果
     */
    int updateAmsParam(AmsParam amsParam);

    /**
     * 删除档案参数
     *
     * @param id 档案参数ID
     * @return 结果
     */
    int deleteAmsParamById(String id);

    /**
     * 批量删除档案参数
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteAmsParamByIds(String[] ids);

}