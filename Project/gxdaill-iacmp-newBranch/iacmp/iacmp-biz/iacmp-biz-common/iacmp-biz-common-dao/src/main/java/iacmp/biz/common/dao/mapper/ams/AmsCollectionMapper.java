package iacmp.biz.common.dao.mapper.ams;


import net.northking.iacmp.common.bean.domain.ams.AmsCollection;

import java.util.List;

/**
 * 专题库 数据层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface AmsCollectionMapper {
    /**
     * 查询专题库信息
     *
     * @param id 专题库ID
     * @return 专题库信息
     */

    AmsCollection selectAmsCollectionById(Integer id);

    /**
     * 查询专题库列表
     *
     * @param amsCollection 专题库信息
     * @return 专题库集合
     */
    List<AmsCollection> selectAmsCollectionList(AmsCollection amsCollection);

    /**
     * 新增专题库
     *
     * @param amsCollection 专题库信息
     * @return 结果
     */
    int insertAmsCollection(AmsCollection amsCollection);

    /**
     * 修改专题库
     *
     * @param amsCollection 专题库信息
     * @return 结果
     */
    int updateAmsCollection(AmsCollection amsCollection);

    /**
     * 删除专题库
     *
     * @param id 专题库ID
     * @return 结果
     */
    int deleteAmsCollectionById(Integer id);

    /**
     * 批量删除专题库
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteAmsCollectionByIds(String[] ids);

}