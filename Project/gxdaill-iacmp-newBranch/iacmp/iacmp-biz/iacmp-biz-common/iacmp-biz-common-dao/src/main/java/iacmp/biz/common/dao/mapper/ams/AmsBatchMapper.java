package iacmp.biz.common.dao.mapper.ams;


import net.northking.iacmp.common.bean.domain.ams.AmsBatch;
import net.northking.iacmp.common.bean.dto.ams.AmsBatchDTO;
import net.northking.iacmp.common.bean.vo.ams.AmsBatchVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 档案著录 数据层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface AmsBatchMapper {
    /**
     * 查询档案著录信息
     *
     * @param id 档案著录ID
     * @return 档案著录信息
     */
    AmsBatch selectAmsBatchById(String id);

    /**
     * 通过档案编号查询档案信息
     *
     * @param id
     * @return
     */
    AmsBatch selectAmsBatchByarcId(String id);

    /**
     * 查询档案著录列表
     *
     * @param amsBatch 档案著录信息
     * @return 档案著录集合
     */
    List<AmsBatch> selectAmsBatchList(AmsBatch amsBatch);

    /**
     * 查询档案接收管理列表
     *
     * @param amsBatch
     * @return
     */
    List<AmsBatch> selectarchReceiveList(AmsBatch amsBatch);

    /**
     * 查询我的档案接收管理
     *
     * @param amsBatch
     * @return
     */
    List<AmsBatch> selectmyReceiveList(AmsBatch amsBatch);

    /**
     * 查询deptname
     */
    List queryDeptName();

    /**
     * 查询档案统计queryqueryResult
     */
    List<Map<String, Object>> queryqueryResult(String orgName);

    /**
     * 查询所有部门信息
     */
    List queryAllDept();

    /**
     * 新增档案著录
     *
     * @param amsBatch 档案著录信息
     * @return 结果
     */
    int insertAmsBatch(AmsBatch amsBatch);

    /**
     * 修改档案著录
     *
     * @param amsBatch 档案著录信息
     * @return 结果
     */
    int updateAmsBatch(AmsBatch amsBatch);

    /**
     * 删除档案著录
     *
     * @param id 档案著录ID
     * @return 结果
     */
    int deleteAmsBatchById(String id);

    /**
     * 批量删除档案著录
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteAmsBatchByIds(String[] ids);

    /**
     * 档案接收打印预览
     *
     * @param ids
     * @return
     */
    List<AmsBatch> selectAmsBatchByIds(String[] ids);

    /**
     * 档案批量接收
     *
     * @param ids
     * @return
     */
    int updateAmsBatchByIds(String[] ids);


    /**
     * @param amsBatch
     * @return
     */
    List<AmsBatch> selectAmsBatchListByOpts(AmsBatch amsBatch);

    /**
     * @param amsBatch
     * @return
     */
    List<AmsBatchDTO> selectTransferApplyByCrtNoAndStatus(AmsBatch amsBatch);

    /**
     * @param amsBatchVO
     * @return
     */
    List<AmsBatch> selectMyTransferApplyByCrtNoAndStatus(AmsBatchVO amsBatchVO);

    /**
     * @param amsBatchVO
     * @return
     */
    List<AmsBatch> selectArrangeListByReceiveNoAndStatus(AmsBatchVO amsBatchVO);

    /**
     * 通过AmsBatchVO查询档案著录列表
     *
     * @param amsBatchVO
     * @return
     */
    List<AmsBatch> selectAmsBatchVOList(AmsBatchVO amsBatchVO);

    /**
     * 通过AmsBatchVO查询档案著录列表(包含辅部门)
     *
     * @param amsBatchVO
     * @return
     */
    List<AmsBatch> selectAmsBatchVOs(@Param("amsBatchVO") AmsBatchVO amsBatchVO, @Param("deptList") List<String> deptList);

    /**
     * 根据ID查询档案移交及等级信息
     */
    AmsBatchDTO selectAmsBatchDTOById(String id);

}