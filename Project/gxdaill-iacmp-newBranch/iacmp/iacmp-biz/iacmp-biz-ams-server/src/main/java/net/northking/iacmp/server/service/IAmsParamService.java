package net.northking.iacmp.server.service;

import net.northking.iacmp.common.bean.domain.ams.AmsParam;
import net.northking.iacmp.core.domain.Ztree;

import java.util.List;

/**
 * 档案参数 服务层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface IAmsParamService {
    /**
     * 查询档案参数信息
     *
     * @param id 档案参数ID
     * @return 档案参数信息
     */
    public AmsParam selectAmsParamById(String id);

    /**
     * 查询档案参数列表
     *
     * @param amsParam 档案参数信息
     * @return 档案参数集合
     */
    public List<AmsParam> selectAmsParamList(AmsParam amsParam);

    /**
     * 新增档案参数
     *
     * @param amsParam 档案参数信息
     * @return 结果
     */
    public int insertAmsParam(AmsParam amsParam);

    /**
     * 修改档案参数
     *
     * @param amsParam 档案参数信息
     * @return 结果
     */
    public int updateAmsParam(AmsParam amsParam);

    /**
     * 删除档案参数信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAmsParamByIds(String ids);

    /**
     * 查询实物类型
     *
     * @param amsParam
     * @return
     */
    List<Ztree> selectAmsParamMatterType(AmsParam amsParam);
}
