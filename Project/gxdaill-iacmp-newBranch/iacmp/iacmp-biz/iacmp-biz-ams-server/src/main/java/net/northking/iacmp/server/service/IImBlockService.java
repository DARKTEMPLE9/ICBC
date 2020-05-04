package net.northking.iacmp.server.service;

import net.northking.iacmp.common.bean.domain.ams.ImBlock;

import java.util.List;

/**
 * 切片 服务层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface IImBlockService {
    /**
     * 查询切片信息
     *
     * @param id 切片ID
     * @return 切片信息
     */
    public ImBlock selectImBlockById(String id);

    /**
     * 查询切片列表
     *
     * @param imBlock 切片信息
     * @return 切片集合
     */
    public List<ImBlock> selectImBlockList(ImBlock imBlock);

    /**
     * 新增切片
     *
     * @param imBlock 切片信息
     * @return 结果
     */
    public int insertImBlock(ImBlock imBlock);

    /**
     * 修改切片
     *
     * @param imBlock 切片信息
     * @return 结果
     */
    public int updateImBlock(ImBlock imBlock);

    /**
     * 删除切片信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteImBlockByIds(String ids);

}
