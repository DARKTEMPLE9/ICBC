package net.northking.iacmp.imp.service;

import net.northking.iacmp.imp.domain.ImRemark;
import net.northking.iacmp.imp.vo.ImRemarkVO;

import java.util.List;

/**
 * 注解 服务层
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
public interface IImRemarkService {
    /**
     * 查询注解信息
     *
     * @param iD 注解ID
     * @return 注解信息
     */
    ImRemark selectImRemarkById(String iD);

    /**
     * 查询注解列表
     *
     * @param imRemark 注解信息
     * @return 注解集合
     */
    List<ImRemark> selectImRemarkList(ImRemark imRemark);

    /**
     * 新增注解
     *
     * @param imRemark 注解信息
     * @return 结果
     */
    int insertImRemark(ImRemark imRemark);

    /**
     * 修改注解
     *
     * @param imRemark 注解信息
     * @return 结果
     */
    int updateImRemark(ImRemark imRemark);

    /**
     * 删除注解信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteImRemarkByIds(String ids);

    /**
     * 通过批次号查询注解信息
     *
     * @param imRemark
     * @return
     */
    List<ImRemark> queryRemarksByBatchId(ImRemarkVO imRemark);
}
