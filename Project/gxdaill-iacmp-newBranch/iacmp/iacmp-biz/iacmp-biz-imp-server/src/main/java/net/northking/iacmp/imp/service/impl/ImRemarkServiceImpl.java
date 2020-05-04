package net.northking.iacmp.imp.service.impl;


import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.ImRemark;
import net.northking.iacmp.imp.mapper.ImRemarkMapper;
import net.northking.iacmp.imp.service.IImRemarkService;
import net.northking.iacmp.imp.vo.ImRemarkVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 注解 服务层实现
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class ImRemarkServiceImpl implements IImRemarkService {
    @Autowired
    private ImRemarkMapper imRemarkMapper;

    /**
     * 查询注解信息
     *
     * @param id 注解ID
     * @return 注解信息
     */
    @Override
    public ImRemark selectImRemarkById(String id) {
        return imRemarkMapper.selectImRemarkById(id);
    }

    /**
     * 查询注解列表
     *
     * @param imRemark 注解信息
     * @return 注解集合
     */
    @Override
    public List<ImRemark> selectImRemarkList(ImRemark imRemark) {
        return imRemarkMapper.selectImRemarkList(imRemark);
    }

    /**
     * 新增注解
     *
     * @param imRemark 注解信息
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public int insertImRemark(ImRemark imRemark) {
        return imRemarkMapper.insertImRemark(imRemark);
    }

    /**
     * 修改注解
     *
     * @param imRemark 注解信息
     * @return 结果
     */
    @Override
    public int updateImRemark(ImRemark imRemark) {
        return imRemarkMapper.updateImRemark(imRemark);
    }

    /**
     * 删除注解对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteImRemarkByIds(String ids) {
        return imRemarkMapper.deleteImRemarkByIds(Convert.toStrArray(ids));
    }

    /**
     * 通过批次号查询注解信息
     *
     * @param imRemark
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public List<ImRemark> queryRemarksByBatchId(ImRemarkVO imRemark) {
        return imRemarkMapper.queryRemarksByBatchId(imRemark);
    }

}
