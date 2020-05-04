package iacmp.biz.common.dao.mapper.ams;


import net.northking.iacmp.common.bean.domain.ams.SmSecret;

import java.util.List;

/**
 * 查询权限不对外公开 数据层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface SmSecretMapper {
    /**
     * 查询查询权限不对外公开信息
     *
     * @param id 查询权限不对外公开ID
     * @return 查询权限不对外公开信息
     */
    SmSecret selectSmSecretById(String id);

    /**
     * 查询查询权限不对外公开列表
     *
     * @param smSecret 查询权限不对外公开信息
     * @return 查询权限不对外公开集合
     */
    List<SmSecret> selectSmSecretList(SmSecret smSecret);

    /**
     * 新增查询权限不对外公开
     *
     * @param smSecret 查询权限不对外公开信息
     * @return 结果
     */
    int insertSmSecret(SmSecret smSecret);

    /**
     * 修改查询权限不对外公开
     *
     * @param smSecret 查询权限不对外公开信息
     * @return 结果
     */
    int updateSmSecret(SmSecret smSecret);

    /**
     * 删除查询权限不对外公开
     *
     * @param id 查询权限不对外公开ID
     * @return 结果
     */
    int deleteSmSecretById(String id);

    /**
     * 批量删除查询权限不对外公开
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteSmSecretByIds(String[] ids);

}