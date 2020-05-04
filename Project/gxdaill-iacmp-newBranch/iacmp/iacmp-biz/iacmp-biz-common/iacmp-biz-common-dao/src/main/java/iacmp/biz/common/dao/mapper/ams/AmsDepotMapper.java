package iacmp.biz.common.dao.mapper.ams;


import net.northking.iacmp.common.bean.domain.ams.AmsDepot;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 库房 数据层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface AmsDepotMapper {
    /**
     * 查询库房信息
     *
     * @param id 库房ID
     * @return 库房信息
     */
    AmsDepot selectAmsDepotById(String id);

    /**
     * 查询库房列表
     *
     * @param amsDepot 库房信息
     * @return 库房集合
     */
    List<AmsDepot> selectAmsDepotList(AmsDepot amsDepot);

    List<AmsDepot> selectAmsDepotListByAuxDep(@Param("amsDepot") AmsDepot amsDepot, @Param("deptList") List<String> deptList);

    /**
     * 新增库房
     *
     * @param amsDepot 库房信息
     * @return 结果
     */
    int insertAmsDepot(AmsDepot amsDepot);

    /**
     * 修改库房
     *
     * @param amsDepot 库房信息
     * @return 结果
     */
    int updateAmsDepot(AmsDepot amsDepot);

    /**
     * 删除库房
     *
     * @param id 库房ID
     * @return 结果
     */
    int deleteAmsDepotById(String id);

    /**
     * 批量删除库房
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteAmsDepotByIds(String[] ids);

}