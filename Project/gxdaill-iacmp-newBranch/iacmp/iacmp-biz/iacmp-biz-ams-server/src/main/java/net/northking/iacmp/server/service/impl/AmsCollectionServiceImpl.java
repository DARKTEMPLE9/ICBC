package net.northking.iacmp.server.service.impl;

import java.util.List;

import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import iacmp.biz.common.dao.mapper.ams.AmsCollectionMapper;
import net.northking.iacmp.common.bean.domain.ams.AmsCollection;
import net.northking.iacmp.server.service.IAmsCollectionService;
import net.northking.iacmp.core.text.Convert;

/**
 * 专题库 服务层实现
 *
 * @author wxy
 * @date 2019-08-01
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class AmsCollectionServiceImpl implements IAmsCollectionService {
    @Autowired
    private AmsCollectionMapper amsCollectionMapper;

    /**
     * 查询专题库信息
     *
     * @param id 专题库ID
     * @return 专题库信息
     */
    @Override
    public AmsCollection selectAmsCollectionById(Integer id) {
        return amsCollectionMapper.selectAmsCollectionById(id);
    }

    /**
     * 查询专题库列表
     *
     * @param amsCollection 专题库信息
     * @return 专题库集合
     */
    @Override
    public List<AmsCollection> selectAmsCollectionList(AmsCollection amsCollection) {
        return amsCollectionMapper.selectAmsCollectionList(amsCollection);
    }

    /**
     * 新增专题库
     *
     * @param amsCollection 专题库信息
     * @return 结果
     */
    @Override
    public int insertAmsCollection(AmsCollection amsCollection) {
        return amsCollectionMapper.insertAmsCollection(amsCollection);
    }

    /**
     * 修改专题库
     *
     * @param amsCollection 专题库信息
     * @return 结果
     */
    @Override
    public int updateAmsCollection(AmsCollection amsCollection) {
        return amsCollectionMapper.updateAmsCollection(amsCollection);
    }

    /**
     * 删除专题库对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAmsCollectionByIds(String ids) {
        return amsCollectionMapper.deleteAmsCollectionByIds(Convert.toStrArray(ids));
    }

}
