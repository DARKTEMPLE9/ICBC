package net.northking.iacmp.server.service.impl;

import iacmp.biz.common.dao.mapper.ams.ImRemarkMapper;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.common.bean.domain.ams.ImRemark;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.server.service.IImRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 影像备注 服务层实现
 *
 * @author wxy
 * @date 2019-08-01
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class ImRemarkServiceImpl implements IImRemarkService {
    @Autowired
    private ImRemarkMapper imRemarkMapper;

    /**
     * 查询影像备注信息
     *
     * @param id 影像备注ID
     * @return 影像备注信息
     */
    @Override
    public ImRemark selectImRemarkById(String id) {
        return imRemarkMapper.selectImRemarkById(id);
    }

    /**
     * 查询影像备注列表
     *
     * @param imRemark 影像备注信息
     * @return 影像备注集合
     */
    @Override
    public List<ImRemark> selectImRemarkList(ImRemark imRemark) {
        return imRemarkMapper.selectImRemarkList(imRemark);
    }

    /**
     * 新增影像备注
     *
     * @param imRemark 影像备注信息
     * @return 结果
     */
    @Override
    public int insertImRemark(ImRemark imRemark) {
        return imRemarkMapper.insertImRemark(imRemark);
    }

    /**
     * 修改影像备注
     *
     * @param imRemark 影像备注信息
     * @return 结果
     */
    @Override
    public int updateImRemark(ImRemark imRemark) {
        return imRemarkMapper.updateImRemark(imRemark);
    }

    /**
     * 删除影像备注对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteImRemarkByIds(String ids) {
        return imRemarkMapper.deleteImRemarkByIds(Convert.toStrArray(ids));
    }

}
