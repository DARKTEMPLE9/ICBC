package iacmp.biz.common.dao.mapper.ams;


import net.northking.iacmp.common.bean.domain.ams.AmsDestroy;

import java.util.List;

/**
 * 档案销毁 数据层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface AmsDestroyMapper {
    /**
     * 查询档案销毁信息
     *
     * @param id 档案销毁ID
     * @return 档案销毁信息
     */
    AmsDestroy selectAmsDestroyById(String id);

    /**
     * 查询档案销毁列表
     *
     * @param amsDestroy 档案销毁信息
     * @return 档案销毁集合
     */
    List<AmsDestroy> selectAmsDestroyList(AmsDestroy amsDestroy);

    /**
     * 新增档案销毁
     *
     * @param amsDestroy 档案销毁信息
     * @return 结果
     */
    int insertAmsDestroy(AmsDestroy amsDestroy);

    /**
     * 修改档案销毁
     *
     * @param amsDestroy 档案销毁信息
     * @return 结果
     */
    int updateAmsDestroy(AmsDestroy amsDestroy);

    /**
     * 删除档案销毁
     *
     * @param id 档案销毁ID
     * @return 结果
     */
    int deleteAmsDestroyById(String id);

    /**
     * 批量删除档案销毁
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteAmsDestroyByIds(String[] ids);

}