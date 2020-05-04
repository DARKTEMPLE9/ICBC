package net.northking.iacmp.server.service;

import net.northking.iacmp.common.bean.domain.ams.SmSecret;

import java.util.List;

/**
 * 查询权限不对外公开 服务层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface ISmSecretService {
    /**
     * 查询查询权限不对外公开信息
     *
     * @param id 查询权限不对外公开ID
     * @return 查询权限不对外公开信息
     */
    public SmSecret selectSmSecretById(String id);

    /**
     * 查询查询权限不对外公开列表
     *
     * @param smSecret 查询权限不对外公开信息
     * @return 查询权限不对外公开集合
     */
    public List<SmSecret> selectSmSecretList(SmSecret smSecret);

    /**
     * 新增查询权限不对外公开
     *
     * @param smSecret 查询权限不对外公开信息
     * @return 结果
     */
    public int insertSmSecret(SmSecret smSecret);

    /**
     * 修改查询权限不对外公开
     *
     * @param smSecret 查询权限不对外公开信息
     * @return 结果
     */
    public int updateSmSecret(SmSecret smSecret);

    /**
     * 删除查询权限不对外公开信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSmSecretByIds(String ids);

}
