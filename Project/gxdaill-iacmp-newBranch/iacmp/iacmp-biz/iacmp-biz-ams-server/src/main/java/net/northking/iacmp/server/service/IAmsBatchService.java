package net.northking.iacmp.server.service;

import net.northking.iacmp.common.bean.domain.ams.AmsBatch;
import net.northking.iacmp.common.bean.dto.ams.AmsBatchDTO;
import net.northking.iacmp.common.bean.vo.ams.AmsBatchVO;
import net.northking.iacmp.system.domain.SysUser;

import java.util.List;

/**
 * 档案著录 服务层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface IAmsBatchService {
    /**
     * 查询档案著录信息
     *
     * @param id 档案著录ID
     * @return 档案著录信息
     */
    AmsBatch selectAmsBatchById(String id);

    /**
     * 通过档案号查询档案著录信息
     *
     * @param arcid
     * @return
     */
    AmsBatch selectAmsBatchByarcId(String arcid);

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
     * 查询我的档案接收管理列表
     */
    List<AmsBatch> selectmyReceiveList(AmsBatch amsBatch);

    /**
     * 查询档案统计
     */
    List<List<String>> queryResultByDeptName();

    /**
     * 查询所有部门信息
     */
    List<String> queryAllDept();

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
     * 删除档案著录信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteAmsBatchByIds(String ids);

    /**
     * 档案接收打印预览
     *
     * @param ids
     * @return
     */
    List<AmsBatch> selectAmsBatchByIds(String ids);

    //档案批量接收
    int updateAmsBatchByIds(String ids);

    List<AmsBatch> selectAmsBatchListByOpts(AmsBatch amsBatch);

    List<AmsBatchDTO> selectTransferApplyByCrtNoAndStatus(AmsBatch amsBatch);

    List<AmsBatch> selectMyTransferApplyByCrtNoAndStatus(AmsBatchVO amsBatchVO);

    List<AmsBatch> selectArrangeList(AmsBatchVO amsBatchVO, SysUser sysUser);

    /**
     * 通过AmsBatchVo查询档案列表
     *
     * @param amsBatchVO
     * @return
     */
    List<AmsBatch> selectAmsBatchVOList(AmsBatchVO amsBatchVO);

    /**
     * 通过AmsBatchVo查询档案列表(包含辅部门)
     *
     * @param amsBatchVO
     * @return
     */
    List<AmsBatch> selectAmsBatchVOList(AmsBatchVO amsBatchVO, List<String> deptList);

    /**
     * 根据ID查询档案移交及等级信息
     */
    AmsBatchDTO selectAmsBatchDTOById(String id);
}
