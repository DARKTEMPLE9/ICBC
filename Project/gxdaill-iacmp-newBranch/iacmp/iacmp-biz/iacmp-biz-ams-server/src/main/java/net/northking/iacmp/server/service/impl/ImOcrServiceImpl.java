package net.northking.iacmp.server.service.impl;

import iacmp.biz.common.dao.mapper.ams.ImOcrMapper;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.common.bean.domain.ams.ImOcr;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.server.service.IImOcrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 影像识别 服务层实现
 *
 * @author wxy
 * @date 2019-08-01
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class ImOcrServiceImpl implements IImOcrService {
    @Autowired
    private ImOcrMapper imOcrMapper;

    /**
     * 查询影像识别信息
     *
     * @param id 影像识别ID
     * @return 影像识别信息
     */
    @Override
    public ImOcr selectImOcrById(String id) {
        return imOcrMapper.selectImOcrById(id);
    }

    /**
     * 查询影像识别列表
     *
     * @param imOcr 影像识别信息
     * @return 影像识别集合
     */
    @Override
    public List<ImOcr> selectImOcrList(ImOcr imOcr) {
        return imOcrMapper.selectImOcrList(imOcr);
    }

    /**
     * 新增影像识别
     *
     * @param imOcr 影像识别信息
     * @return 结果
     */
    @Override
    public int insertImOcr(ImOcr imOcr) {
        return imOcrMapper.insertImOcr(imOcr);
    }

    /**
     * 修改影像识别
     *
     * @param imOcr 影像识别信息
     * @return 结果
     */
    @Override
    public int updateImOcr(ImOcr imOcr) {
        return imOcrMapper.updateImOcr(imOcr);
    }

    /**
     * 删除影像识别对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteImOcrByIds(String ids) {
        return imOcrMapper.deleteImOcrByIds(Convert.toStrArray(ids));
    }

}
