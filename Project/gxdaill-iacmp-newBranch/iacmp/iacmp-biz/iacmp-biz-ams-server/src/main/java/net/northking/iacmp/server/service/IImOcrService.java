package net.northking.iacmp.server.service;

import net.northking.iacmp.common.bean.domain.ams.ImOcr;

import java.util.List;

/**
 * 影像识别 服务层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface IImOcrService {
    /**
     * 查询影像识别信息
     *
     * @param id 影像识别ID
     * @return 影像识别信息
     */
    public ImOcr selectImOcrById(String id);

    /**
     * 查询影像识别列表
     *
     * @param imOcr 影像识别信息
     * @return 影像识别集合
     */
    public List<ImOcr> selectImOcrList(ImOcr imOcr);

    /**
     * 新增影像识别
     *
     * @param imOcr 影像识别信息
     * @return 结果
     */
    public int insertImOcr(ImOcr imOcr);

    /**
     * 修改影像识别
     *
     * @param imOcr 影像识别信息
     * @return 结果
     */
    public int updateImOcr(ImOcr imOcr);

    /**
     * 删除影像识别信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteImOcrByIds(String ids);

}
