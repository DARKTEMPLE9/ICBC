package net.northking.iacmp.imp.mapper;

import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.ImUser;

import java.util.List;

/**
 * 客户 数据层
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
public interface ImUserMapper {
    /**
     * 查询客户信息
     *
     * @param id 客户ID
     * @return 客户信息
     */
    ImUser selectImUserById(Long id);

    /**
     * 查询客户列表
     *
     * @param imUser 客户信息
     * @return 客户集合
     */
    List<ImUser> selectImUserList(ImUser imUser);

    /**
     * 新增客户
     *
     * @param imUser 客户信息
     * @return 结果
     */
    int insertImUser(ImUser imUser);

    /**
     * 修改客户
     *
     * @param imUser 客户信息
     * @return 结果
     */
    int updateImUser(ImUser imUser);

    /**
     * 删除客户
     *
     * @param id 客户ID
     * @return 结果
     */
    int deleteImUserById(Long id);

    /**
     * 批量删除客户
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteImUserByIds(String[] ids);

    /**
     * 通过流水号查询客户信息
     *
     * @param busino
     * @return
     */
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    ImUser selectImUserByBusino(String busino);
}