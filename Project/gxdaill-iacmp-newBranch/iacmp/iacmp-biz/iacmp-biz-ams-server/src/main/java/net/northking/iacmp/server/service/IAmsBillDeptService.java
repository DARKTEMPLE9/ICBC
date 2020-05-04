package net.northking.iacmp.server.service;

import net.northking.iacmp.common.bean.domain.ams.AmsBillDept;

import java.util.List;

/**
 * 部门档案配置 服务层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface IAmsBillDeptService {
    /**
     * 查询部门档案配置信息
     *
     * @param id 部门档案配置ID
     * @return 部门档案配置信息
     */
    public AmsBillDept selectAmsBillDeptById(String id);

    /**
     * 查询部门档案配置列表
     *
     * @param amsBillDept 部门档案配置信息
     * @return 部门档案配置集合
     */
    public List<AmsBillDept> selectAmsBillDeptList(AmsBillDept amsBillDept);

    /**
     * 新增部门档案配置
     *
     * @param amsBillDept 部门档案配置信息
     * @return 结果
     */
    public int insertAmsBillDept(AmsBillDept amsBillDept);

    /**
     * 修改部门档案配置
     *
     * @param amsBillDept 部门档案配置信息
     * @return 结果
     */
    public int updateAmsBillDept(AmsBillDept amsBillDept);

    /**
     * 删除部门档案配置信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAmsBillDeptByIds(String ids);

    /**
     * 删除部门档案配置信息
     *
     * @param organCode 需要删除的数据机构ID
     * @return
     */
    public int deleteAmsBillDeptBySysId(String organCode);
}
