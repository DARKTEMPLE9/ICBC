package net.northking.iacmp.server.service;

import net.northking.iacmp.common.bean.domain.ams.SmUserOrgan;

import java.util.List;

/**
 * 用户机构 服务层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface ISmUserOrganService {
    /**
     * 查询用户机构信息
     *
     * @param id 用户机构ID
     * @return 用户机构信息
     */
    public SmUserOrgan selectSmUserOrganById(String id);

    /**
     * 查询用户机构列表
     *
     * @param smUserOrgan 用户机构信息
     * @return 用户机构集合
     */
    public List<SmUserOrgan> selectSmUserOrganList(SmUserOrgan smUserOrgan);

    /**
     * 新增用户机构
     *
     * @param smUserOrgan 用户机构信息
     * @return 结果
     */
    public int insertSmUserOrgan(SmUserOrgan smUserOrgan);

    /**
     * 修改用户机构
     *
     * @param smUserOrgan 用户机构信息
     * @return 结果
     */
    public int updateSmUserOrgan(SmUserOrgan smUserOrgan);

    /**
     * 删除用户机构信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSmUserOrganByIds(String ids);

}
