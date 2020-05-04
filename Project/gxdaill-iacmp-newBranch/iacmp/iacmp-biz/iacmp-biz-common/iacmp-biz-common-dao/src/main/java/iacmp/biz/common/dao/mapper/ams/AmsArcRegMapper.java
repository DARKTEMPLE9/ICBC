package iacmp.biz.common.dao.mapper.ams;


import net.northking.iacmp.common.bean.domain.ams.AmsArcReg;
import net.northking.iacmp.common.bean.vo.ams.AmsArcRegVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 档案 数据层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface AmsArcRegMapper {
    /**
     * 查询档案信息
     *
     * @param id 档案ID
     * @return 档案信息
     */
    AmsArcReg selectAmsArcRegById(String id);

    /**
     * 查询档案列表
     *
     * @param amsArcReg 档案信息
     * @return 档案集合
     */
    List<AmsArcReg> selectAmsArcRegList(AmsArcRegVO amsArcReg);

    /**
     * 新增档案
     *
     * @param amsArcReg 档案信息
     * @return 结果
     */
    int insertAmsArcReg(AmsArcReg amsArcReg);

    /**
     * 修改档案
     *
     * @param amsArcReg 档案信息
     * @return 结果
     */
    int updateAmsArcReg(AmsArcReg amsArcReg);

    /**
     * 删除档案
     *
     * @param id 档案ID
     * @return 结果
     */
    int deleteAmsArcRegById(String id);

    /**
     * 批量删除档案
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteAmsArcRegByIds(String[] ids);

    /**
     * 批量获取档案
     *
     * @param ids 需要获取到档案的数据ID
     * @return 结果
     */
    List<AmsArcReg> selectAmsArcRegByIds(String[] ids);

    /**
     * 通过用户Id查找所有该用户可见档案登记列表
     *
     * @param amsArcReg
     * @return
     */
    List<AmsArcReg> selectAmsArcRegListByDeptId(AmsArcRegVO amsArcReg);

    /**
     * 通过用户Id查找所有该用户可见档案登记列表(包含辅部门)
     *
     * @param amsArcReg
     * @return
     */
    List<AmsArcReg> selectAmsArcRegListByDeptIds(@Param("amsArcReg") AmsArcRegVO amsArcReg, @Param("deptList") List<String> deptList);
}