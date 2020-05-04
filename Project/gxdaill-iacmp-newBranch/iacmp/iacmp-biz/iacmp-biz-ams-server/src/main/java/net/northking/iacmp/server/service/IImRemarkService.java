package net.northking.iacmp.server.service;

import net.northking.iacmp.common.bean.domain.ams.ImRemark;

import java.util.List;

/**
 * 影像备注 服务层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface IImRemarkService {
    /**
     * 查询影像备注信息
     *
     * @param id 影像备注ID
     * @return 影像备注信息
     */
    public ImRemark selectImRemarkById(String id);

    /**
     * 查询影像备注列表
     *
     * @param imRemark 影像备注信息
     * @return 影像备注集合
     */
    public List<ImRemark> selectImRemarkList(ImRemark imRemark);

    /**
     * 新增影像备注
     *
     * @param imRemark 影像备注信息
     * @return 结果
     */
    public int insertImRemark(ImRemark imRemark);

    /**
     * 修改影像备注
     *
     * @param imRemark 影像备注信息
     * @return 结果
     */
    public int updateImRemark(ImRemark imRemark);

    /**
     * 删除影像备注信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteImRemarkByIds(String ids);

}
