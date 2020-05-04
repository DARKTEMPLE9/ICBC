package net.northking.iacmp.server.service.impl;

import iacmp.biz.common.dao.mapper.ams.AmsBatchMapper;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.common.bean.domain.ams.AmsBatch;
import net.northking.iacmp.common.bean.dto.ams.AmsBatchDTO;
import net.northking.iacmp.common.bean.vo.ams.AmsBatchVO;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.server.service.IAmsBatchService;
import net.northking.iacmp.system.domain.SysUser;
import net.northking.iacmp.system.mapper.SysRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 档案著录 服务层实现
 *
 * @author wxy
 * @date 2019-08-01
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class AmsBatchServiceImpl implements IAmsBatchService {

    @Autowired
    private AmsBatchMapper amsBatchMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    /**
     * 查询档案著录信息
     *
     * @param id 档案著录ID
     * @return 档案著录信息
     */
    @Override
    public AmsBatch selectAmsBatchById(String id) {
        return amsBatchMapper.selectAmsBatchById(id);
    }

    /**
     * 通过档案编号获取档案信息
     *
     * @param arcid
     * @return
     */
    @Override
    public AmsBatch selectAmsBatchByarcId(String arcid) {
        return amsBatchMapper.selectAmsBatchByarcId(arcid);
    }

    /**
     * 查询档案著录列表
     *
     * @param amsBatch 档案著录信息
     * @return 档案著录集合
     */
    @Override
    public List<AmsBatch> selectAmsBatchList(AmsBatch amsBatch) {
        return amsBatchMapper.selectAmsBatchList(amsBatch);
    }

    /**
     * 查询档案接收管理列表
     */
    @Override
    public List<AmsBatch> selectarchReceiveList(AmsBatch amsBatch) {
        return amsBatchMapper.selectarchReceiveList(amsBatch);
    }

    //查询我的档案接收管理列表
    @Override
    public List<AmsBatch> selectmyReceiveList(AmsBatch amsBatch) {
        return amsBatchMapper.selectmyReceiveList(amsBatch);
    }

    /**
     * 查询要循环的部门list
     */
    public List queryDeptName() {
        List deptList = amsBatchMapper.queryDeptName();
        return deptList;
    }

    /**
     * 查询档案统计
     */
    @Override
    public List<List<String>> queryResultByDeptName() {
        //存储按部门查找的数据

        List<List<String>> allQueryByDeptList = new ArrayList<>();
        List<String> DeptNameList = queryDeptName();
        for (int i = 0; i < DeptNameList.size(); i++) {
            List<Map<String, Object>> resultList = amsBatchMapper.queryqueryResult(DeptNameList.get(i));
            List<Object[]> obj = new ArrayList<Object[]>();
            for (Map<String, Object> map : resultList) {
                System.out.println(map.values());
                Collection values = map.values();
                List list = new ArrayList(values);
                obj.add(list.toArray());
            }
            List<String> temp = new ArrayList();
            for (Object[] message : obj) {
                temp.add(Arrays.toString(message));
            }
            allQueryByDeptList.add(temp);
        }
        return allQueryByDeptList;


    }

    /**
     * 查询所有部门信息
     */
    @Override
    public List<String> queryAllDept() {
        List<String> allDept;
        allDept = amsBatchMapper.queryAllDept();
        return allDept;
    }

    /**
     * 新增档案著录
     *
     * @param amsBatch 档案著录信息
     * @return 结果
     */
    @Override
    public int insertAmsBatch(AmsBatch amsBatch) {
        return amsBatchMapper.insertAmsBatch(amsBatch);
    }

    /**
     * 修改档案著录
     *
     * @param amsBatch 档案著录信息
     * @return 结果
     */
    @Override
    public int updateAmsBatch(AmsBatch amsBatch) {
        return amsBatchMapper.updateAmsBatch(amsBatch);
    }

    /**
     * 删除档案著录对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAmsBatchByIds(String ids) {
        return amsBatchMapper.deleteAmsBatchByIds(Convert.toStrArray(ids));
    }

    /**
     * 档案接收打印预览
     *
     * @param ids
     * @return
     */
    @Override
    public List<AmsBatch> selectAmsBatchByIds(String ids) {
        return amsBatchMapper.selectAmsBatchByIds(Convert.toStrArray(ids));
    }

    //档案批量接收
    @Override
    public int updateAmsBatchByIds(String ids) {
        return amsBatchMapper.updateAmsBatchByIds(Convert.toStrArray(ids));
    }

    /**
     * 条件查询
     *
     * @param amsBatch
     * @return
     */
    @Override
    public List<AmsBatch> selectAmsBatchListByOpts(AmsBatch amsBatch) {
        List<AmsBatch> list = amsBatchMapper.selectAmsBatchListByOpts(amsBatch);
        return list;
    }

    /**
     * 移交申请列表查询
     *
     * @param amsBatch
     * @param
     * @return
     */
    @Override
    public List<AmsBatchDTO> selectTransferApplyByCrtNoAndStatus(AmsBatch amsBatch) {
        List<AmsBatchDTO> list = amsBatchMapper.selectTransferApplyByCrtNoAndStatus(amsBatch);
        return list;
    }

    /**
     * 我的移交申请列表查询
     *
     * @param
     * @param amsBatchVO
     * @return
     */
    @Override
    public List<AmsBatch> selectMyTransferApplyByCrtNoAndStatus(AmsBatchVO amsBatchVO) {
        List<AmsBatch> list = amsBatchMapper.selectMyTransferApplyByCrtNoAndStatus(amsBatchVO);
        return list;
    }

    /**
     * 整理组卷列表查询
     *
     * @param amsBatchVO
     * @return
     */
    @Override
    public List<AmsBatch> selectArrangeList(AmsBatchVO amsBatchVO, SysUser sysUser) {
        amsBatchVO.setReceiveNo(String.valueOf(sysUser.getUserId()));
        List<AmsBatch> amsBatches = amsBatchMapper.selectArrangeListByReceiveNoAndStatus(amsBatchVO);
        return amsBatches;
    }

    /**
     * 通过AmsBatchVO查询档案著录列表
     *
     * @param amsBatchVO
     * @return
     */
    @Override
    public List<AmsBatch> selectAmsBatchVOList(AmsBatchVO amsBatchVO) {
        return amsBatchMapper.selectAmsBatchVOList(amsBatchVO);
    }

    /**
     * 通过AmsBatchVo查询档案列表(包含辅部门)
     */
    @Override
    public List<AmsBatch> selectAmsBatchVOList(AmsBatchVO amsBatchVO, List<String> deptList) {
        return amsBatchMapper.selectAmsBatchVOs(amsBatchVO, deptList);
    }

    /**
     * 根据ID查询档案移交及等级信息
     */
    @Override
    public AmsBatchDTO selectAmsBatchDTOById(String id) {
        return amsBatchMapper.selectAmsBatchDTOById(id);
    }
}
