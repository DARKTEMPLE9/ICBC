package iacmp.biz.common.dao.mapper.ams;


import net.northking.iacmp.common.bean.domain.ams.AmsBillDept;

import java.util.List;

/**
 * 部门档案配置 数据层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface AmsBillDeptMapper {
    /**
     * 查询部门档案配置信息
     *
     * @param id 部门档案配置ID
     * @return 部门档案配置信息
     */
    AmsBillDept selectAmsBillDeptById(String id);

    /**
     * 查询部门档案配置列表
     *
     * @param amsBillDept 部门档案配置信息
     * @return 部门档案配置集合
     */
    List<AmsBillDept> selectAmsBillDeptList(AmsBillDept amsBillDept);

    /**
     * 新增部门档案配置
     *
     * @param amsBillDept 部门档案配置信息
     * @return 结果
     */
    int insertAmsBillDept(AmsBillDept amsBillDept);

    /**
     * 修改部门档案配置
     *
     * @param amsBillDept 部门档案配置信息
     * @return 结果
     */
    int updateAmsBillDept(AmsBillDept amsBillDept);

    /**
     * 删除部门档案配置
     *
     * @param id 部门档案配置ID
     * @return 结果
     */
    int deleteAmsBillDeptById(String id);

    /**
     * 批量删除部门档案配置
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteAmsBillDeptByIds(String[] ids);

    /**
     * @param organCode
     * @return
     */
    int deleteAmsBillDeptBySysId(String organCode);
}