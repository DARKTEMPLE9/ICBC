package net.northking.iacmp.server.service;

import net.northking.iacmp.common.bean.domain.ams.AmsDestroy;

import java.util.List;

/**
 * 档案销毁 服务层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface IAmsDestroyService {
    /**
     * 查询档案销毁信息
     *
     * @param id 档案销毁ID
     * @return 档案销毁信息
     */
    public AmsDestroy selectAmsDestroyById(String id);

    /**
     * 查询档案销毁列表
     *
     * @param amsDestroy 档案销毁信息
     * @return 档案销毁集合
     */
    public List<AmsDestroy> selectAmsDestroyList(AmsDestroy amsDestroy);

    /**
     * 新增档案销毁
     *
     * @param amsDestroy 档案销毁信息
     * @return 结果
     */
    public int insertAmsDestroy(AmsDestroy amsDestroy);

    /**
     * 修改档案销毁
     *
     * @param amsDestroy 档案销毁信息
     * @return 结果
     */
    public int updateAmsDestroy(AmsDestroy amsDestroy);

    /**
     * 删除档案销毁信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAmsDestroyByIds(String ids);

}
