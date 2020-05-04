package net.northking.iacmp.imp.mapper;

import net.northking.iacmp.imp.domain.ImBusino;

import java.util.List;

/**
 * 异常流水 数据层
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
public interface ImBusinoMapper {
    /**
     * 查询异常流水信息
     *
     * @param id 异常流水ID
     * @return 异常流水信息
     */
    ImBusino selectImBusinoById(Long id);

    /**
     * 查询异常流水列表
     *
     * @param imBusino 异常流水信息
     * @return 异常流水集合
     */
    List<ImBusino> selectImBusinoList(ImBusino imBusino);

    /**
     * 新增异常流水
     *
     * @param imBusino 异常流水信息
     * @return 结果
     */
    int insertImBusino(ImBusino imBusino);

    /**
     * 修改异常流水
     *
     * @param imBusino 异常流水信息
     * @return 结果
     */
    int updateImBusino(ImBusino imBusino);

    /**
     * 删除异常流水
     *
     * @param id 异常流水ID
     * @return 结果
     */
    int deleteImBusinoById(Long id);

    /**
     * 批量删除异常流水
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteImBusinoByIds(String[] ids);

}