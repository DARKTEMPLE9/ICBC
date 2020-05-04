package iacmp.biz.common.dao.mapper.ams;


import net.northking.iacmp.common.bean.domain.ams.SmRole;

import java.util.List;

/**
 * 角色 数据层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface SmRoleMapper {
    /**
     * 查询角色信息
     *
     * @param id 角色ID
     * @return 角色信息
     */
    SmRole selectSmRoleById(String id);

    /**
     * 查询角色列表
     *
     * @param smRole 角色信息
     * @return 角色集合
     */
    List<SmRole> selectSmRoleList(SmRole smRole);

    /**
     * 新增角色
     *
     * @param smRole 角色信息
     * @return 结果
     */
    int insertSmRole(SmRole smRole);

    /**
     * 修改角色
     *
     * @param smRole 角色信息
     * @return 结果
     */
    int updateSmRole(SmRole smRole);

    /**
     * 删除角色
     *
     * @param id 角色ID
     * @return 结果
     */
    int deleteSmRoleById(String id);

    /**
     * 批量删除角色
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteSmRoleByIds(String[] ids);

}