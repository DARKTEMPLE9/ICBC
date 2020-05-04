package iacmp.biz.common.dao.mapper.cms;


import net.northking.iacmp.common.bean.domain.cms.CmsBill;
import net.northking.iacmp.common.bean.vo.cms.CmsBillVO;
import net.northking.iacmp.common.bean.vo.cms.CmsFileImageVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 分类 数据层
 *
 * @author qingyu.yan
 * @date 2019-08-27
 */
public interface CmsBillMapper {
    /**
     * 查询分类信息
     *
     * @param id 分类ID
     * @return 分类信息
     */
    CmsBill selectCmsBillById(Long id);


    /**
     * 查询子分类id
     *
     * @param parentId
     */
    List<String> selectIdByParentId(Long parentId);

    /**
     * 查询分类列表
     *
     * @param cmsBill 分类信息
     * @return 分类集合
     */
    List<CmsBill> selectCmsBillList(CmsBill cmsBill);

    /**
     * 新增分类
     *
     * @param cmsBill 分类信息
     * @return 结果
     */
    int insertCmsBill(CmsBill cmsBill);

    /**
     * 修改分类
     *
     * @param cmsBill 分类信息
     * @return 结果
     */
    int updateCmsBill(CmsBill cmsBill);

    /**
     * 修改子分类
     */
    int updateSubBillDisplay(CmsBill cmsBill);

    /**
     * 删除分类
     *
     * @param id 分类ID
     * @return 结果
     */
    int deleteCmsBillById(Long id);

    /**
     * 批量删除分类
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteCmsBillByIds(String[] ids);

    /**
     * 查询所有分类
     *
     * @return
     */
    List<CmsBill> selectBillAll();

    /**
     * 通过用户号获取所有分类
     *
     * @param userId
     * @return
     */
    List<CmsBill> selectBillAllByUserId(Long userId);

    /**
     * 通过角色号查找分类
     *
     * @param roleId
     * @return
     */
    List<String> selectBillTree(Long roleId);

    /**
     * 通过模型号查找分类
     *
     * @param modelId
     * @return
     */
    List<String> selectModelBillTree(Long modelId);

    /**
     * 通过用户id及分类信息查找分类列表
     *
     * @param cmsBill 分类信息
     * @return
     */
    List<CmsBill> selectBillListByUserId(CmsBill cmsBill);

    /**
     * 通过父分类Id获取子分类数
     *
     * @param billId
     * @return
     */
    int selectCountBillByParentId(Long billId);


    /**
     * 查询所有分类Vo
     *
     * @return
     */
    List<CmsBillVO> selectBillAllVo();

    /**
     * 通过用户号获取所有分类
     *
     * @param userId
     * @return
     */
    List<CmsBillVO> selectBillAllVoByUserId(Long userId);

    CmsBill selectCmsBillByCode(@Param("billCode") String billCode);

    List<CmsBill> selectCmsBillListByModelAndLeaf(@Param("modelId") String modelId);

    List<CmsBill> selectCmsBillByParentId(@Param("id") Long id);

    Integer selectFileNumByCode(@Param("billCode") String billCode);

    List<CmsBill> selectFileImageBills(@Param("batchId") Long batchId, @Param("billId") Long billId);

    List<CmsBill> selectFileBillsByFileParentId(@Param("batchId") Long batchId, @Param("parentId") Long parentId);

    List<CmsBill> selectFileBillsByImageParentId(@Param("batchId") Long batchId, @Param("parentId") Long parentId);

    /**
     * 根据项目batchId查询项目下所有文件的分类
     *
     * @param batchId
     * @return
     */
    List<CmsBill> selectBillsByFileBatchId(@Param("batchId") Long batchId);

    /**
     * 批量查询分类
     *
     * @param allBillId
     * @return
     */
    List<CmsBill> selectBillByIds(@Param("allBillId") Set<Long> allBillId);

    /**
     * 核对分类名称是否唯一
     *
     * @param billName
     * @return
     */
    CmsBill checkRoleNameUnique(String billName);

    /**
     * 核对分类编码是否唯一
     *
     * @param billCode
     * @return
     */
    CmsBill checkBillCodeUnique(String billCode);

    /**
     * 获得全部分类(除叶子节点)
     */
    List<CmsBill> selectAllCmsBill();

    /**
     * 获得全部分类(包含叶子节点)
     */
    List<CmsBill> selectAllCmsBills(@Param("dataRoleIds") List<Long> dataRoleIds);


    List<String> selectBillIdOfPairing();

    List<Long> selectBillIdOfUpload();

    List<Long> selectBillIdOfHistory();

    List<Long> selectBillIdsOfPairing();

    CmsBill selectNewFileByBatchIdBillId(@Param("batchId") Long batchId, @Param("billId") Long billId);

    CmsBill selectNewByImageBatchIdBillId(@Param("batchId") Long batchId, @Param("billId") Long billId);

    List<Long> selectBillIdByPmsId(@Param("pmsBatchId") Long pmsBatchId);

    Map<String, Integer> selectBillMonitorNum(@Param("pmsBatchId") Long pmsBatchId, @Param("billId") Long billId);

    void updateBillMonitorNum(@Param("pmsBatchId") Long pmsBatchId, @Param("billId") Long billId, @Param("fileNum") Integer fileNum);

    void insertBillMonitorNum(@Param("pmsBatchId") Long pmsBatchId, @Param("billId") Long billId, @Param("fileNum") Integer fileNum);

    int updateManualUpload(@Param("billIds") List<String> billIds, @Param("targetManual") String targetManual);

    /**
     * 通过角色id查找分类集合
     *
     * @param roleId
     * @return
     */
    List<CmsBill> selectCmsBillsByRoleId(Long roleId);

    List<CmsFileImageVO> selectFileImageOfBill(@Param("billId") Long billId, @Param("batchId") Long batchId);

    List<CmsFileImageVO> selectFileByBatchId(@Param("batchId") Long batchId);

    List<Long> selectBillOffOfModel(@Param("modelIds") String[] modelIds);
}