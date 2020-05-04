package net.northking.iacmp.imp.mapper;

import net.northking.iacmp.imp.domain.ImBlock;

import java.util.List;

/**
 * 切片 数据层
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
public interface ImBlockMapper {
    /**
     * 查询切片信息
     *
     * @param id 切片ID
     * @return 切片信息
     */
    ImBlock selectImBlockById(String id);

    /**
     * 查询切片列表
     *
     * @param imBlock 切片信息
     * @return 切片集合
     */
    List<ImBlock> selectImBlockList(ImBlock imBlock);

    /**
     * 新增切片
     *
     * @param imBlock 切片信息
     * @return 结果
     */
    int insertImBlock(ImBlock imBlock);

    /**
     * 修改切片
     *
     * @param imBlock 切片信息
     * @return 结果
     */
    int updateImBlock(ImBlock imBlock);

    /**
     * 删除切片
     *
     * @param id 切片ID
     * @return 结果
     */
    int deleteImBlockById(String id);

    /**
     * 批量删除切片
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteImBlockByIds(String[] ids);

}