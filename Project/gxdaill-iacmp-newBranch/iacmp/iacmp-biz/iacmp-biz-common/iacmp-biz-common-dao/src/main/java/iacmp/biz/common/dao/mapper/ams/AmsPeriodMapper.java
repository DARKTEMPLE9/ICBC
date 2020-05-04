package iacmp.biz.common.dao.mapper.ams;


import net.northking.iacmp.common.bean.domain.ams.AmsPeriod;

import java.util.List;

/**
 * 档案期限 数据层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface AmsPeriodMapper {
    /**
     * 查询档案期限信息
     *
     * @param id 档案期限ID
     * @return 档案期限信息
     */
    AmsPeriod selectAmsPeriodById(String id);

    /**
     * 查询档案期限列表
     *
     * @param amsPeriod 档案期限信息
     * @return 档案期限集合
     */
    List<AmsPeriod> selectAmsPeriodList(AmsPeriod amsPeriod);

    /**
     * 新增档案期限
     *
     * @param amsPeriod 档案期限信息
     * @return 结果
     */
    int insertAmsPeriod(AmsPeriod amsPeriod);

    /**
     * 修改档案期限
     *
     * @param amsPeriod 档案期限信息
     * @return 结果
     */
    int updateAmsPeriod(AmsPeriod amsPeriod);

    /**
     * 删除档案期限
     *
     * @param id 档案期限ID
     * @return 结果
     */
    int deleteAmsPeriodById(String id);

    /**
     * 批量删除档案期限
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteAmsPeriodByIds(String[] ids);

}