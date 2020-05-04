package net.northking.iacmp.server.service;

import net.northking.iacmp.common.bean.domain.ams.AmsDepot;

import java.util.List;

/**
 * 库房 服务层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface IAmsDepotService {
    /**
     * 查询库房信息
     *
     * @param id 库房ID
     * @return 库房信息
     */
    public AmsDepot selectAmsDepotById(String id);

    /**
     * 查询库房列表
     *
     * @param amsDepot 库房信息
     * @return 库房集合
     */
    public List<AmsDepot> selectAmsDepotList(AmsDepot amsDepot);

    public List<AmsDepot> selectAmsDepotList(AmsDepot amsDepot, List<String> deptList);

    /**
     * 新增库房
     *
     * @param amsDepot 库房信息
     * @return 结果
     */
    public int insertAmsDepot(AmsDepot amsDepot);

    /**
     * 修改库房
     *
     * @param amsDepot 库房信息
     * @return 结果
     */
    public int updateAmsDepot(AmsDepot amsDepot);

    /**
     * 删除库房信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAmsDepotByIds(String ids);

}
