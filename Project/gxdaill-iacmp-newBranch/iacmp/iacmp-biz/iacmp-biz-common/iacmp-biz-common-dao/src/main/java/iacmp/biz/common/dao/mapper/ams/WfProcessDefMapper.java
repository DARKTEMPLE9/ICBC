package iacmp.biz.common.dao.mapper.ams;


import net.northking.iacmp.common.bean.domain.ams.WfProcessDef;

import java.util.List;

/**
 * 流程实例 数据层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface WfProcessDefMapper {
    /**
     * 查询流程实例信息
     *
     * @param id 流程实例ID
     * @return 流程实例信息
     */
    WfProcessDef selectWfProcessDefById(String id);

    /**
     * 查询流程实例列表
     *
     * @param wfProcessDef 流程实例信息
     * @return 流程实例集合
     */
    List<WfProcessDef> selectWfProcessDefList(WfProcessDef wfProcessDef);

    /**
     * 新增流程实例
     *
     * @param wfProcessDef 流程实例信息
     * @return 结果
     */
    int insertWfProcessDef(WfProcessDef wfProcessDef);

    /**
     * 修改流程实例
     *
     * @param wfProcessDef 流程实例信息
     * @return 结果
     */
    int updateWfProcessDef(WfProcessDef wfProcessDef);

    /**
     * 删除流程实例
     *
     * @param id 流程实例ID
     * @return 结果
     */
    int deleteWfProcessDefById(String id);

    /**
     * 批量删除流程实例
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteWfProcessDefByIds(String[] ids);

}