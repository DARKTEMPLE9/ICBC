package iacmp.biz.common.dao.mapper.cms;


import net.northking.iacmp.common.bean.domain.cms.CmsBill;
import net.northking.iacmp.common.bean.domain.cms.PmsBatch;
import net.northking.iacmp.common.bean.vo.cms.CmsFileImageVO;
import net.northking.iacmp.common.bean.vo.cms.PmsBatchVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * 影像批次 数据层
 *
 * @author qingyu.yan
 * @date 2019-08-26
 */
public interface PmsBatchMapper {

    /**
     * 查询所有项目
     */
    List<PmsBatch> selectAllPmsBatch(PmsBatch pmsBatch);

    /**
     * 条件查询所有当前用户所有权限的项目
     */
    List<PmsBatch> selectPmsBatchBySysRoles(PmsBatch pmsBatch);

    /**
     * 查询所有项目
     */
    List<PmsBatch> selectPmsBatchByCondition(PmsBatch pmsBatch);

    /**
     * 查询影像批次信息
     *
     * @param id 影像批次ID
     * @return 影像批次信息
     */
    PmsBatch selectPmsBatchById(Long id);

    /**
     * 查询影像批次列表
     *
     * @param pmsBatch 影像批次信息
     * @return 影像批次集合
     */
    List<PmsBatch> selectPmsBatchList(PmsBatch pmsBatch);

    /**
     * 新增影像批次
     *
     * @param pmsBatch 影像批次信息
     * @return 结果
     */
    int insertPmsBatch(PmsBatch pmsBatch);

    /**
     * 修改影像批次
     *
     * @param pmsBatch 影像批次信息
     * @return 结果
     */
    int updatePmsBatch(PmsBatch pmsBatch);

    /**
     * 删除影像批次
     *
     * @param id 影像批次ID
     * @return 结果
     */
    int deletePmsBatchById(Long id);

    /**
     * 批量删除影像批次
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deletePmsBatchByIds(String[] ids);

    List<String> selectAllPmsId(PmsBatch pmsBatch);

    /**
     * 通过条件查询项目id集合
     *
     * @param pmsBatch
     * @return 所有符合条件的项目的id
     */
    List<String> selectPmsIdList(PmsBatch pmsBatch);

    List<String> selectPmsIdListByRole(PmsBatch pmsBatch);

    PmsBatch selectPmsBatchByOpt(@Param("operationCode") String operationCode);

    /**
     * 通过角色查询项目列表
     *
     * @param roleId
     * @return
     */
    List<String> selectRoleProjectTree(Long roleId);

    PmsBatch selectPmsBatchByBatchId(@Param("batchId") Long batchId);

    /**
     * 校验项目名唯一性
     *
     * @param projectName
     * @return
     */
    PmsBatch checkProjectNameUnique(String projectName);

    /**
     * 校验项目编号唯一性
     *
     * @param operationCode
     * @return
     */
    PmsBatch checkOperationCodeUnique(String operationCode);

    /**
     * 校验预算编号唯一性
     *
     * @param budgetId
     * @return
     */
    PmsBatch checkBudgetIdUnique(String budgetId);

    /**
     * 根据batchIds查询pmsbatch
     *
     * @param collect
     * @return
     */
    List<PmsBatch> selectPmsBatchByBatchIds(@Param("collect") Set<Long> collect);

    /**
     * 查询项目
     *
     * @param pmsBatch
     * @return
     */
    List<PmsBatchVO> selectPmsBatchVOBySysRoles(PmsBatch pmsBatch);

    /**
     * 查询角色自定义勾选的项目
     *
     * @param roleId
     * @return
     */
    List<Long> selectPIdsByDataRole(Long roleId);

    /**
     * 查询当前项目下的所有文件
     */
    List<CmsFileImageVO> selectFileByBatchId(@Param("batchId") Long batchId);


}