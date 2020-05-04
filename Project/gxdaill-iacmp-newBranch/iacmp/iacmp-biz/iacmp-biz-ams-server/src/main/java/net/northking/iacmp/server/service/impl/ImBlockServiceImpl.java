package net.northking.iacmp.server.service.impl;

import iacmp.biz.common.dao.mapper.ams.ImBlockMapper;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.common.bean.domain.ams.ImBlock;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.server.service.IImBlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 切片 服务层实现
 *
 * @author wxy
 * @date 2019-08-01
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class ImBlockServiceImpl implements IImBlockService {
    @Autowired
    private ImBlockMapper imBlockMapper;

    /**
     * 查询切片信息
     *
     * @param id 切片ID
     * @return 切片信息
     */
    @Override
    public ImBlock selectImBlockById(String id) {
        return imBlockMapper.selectImBlockById(id);
    }

    /**
     * 查询切片列表
     *
     * @param imBlock 切片信息
     * @return 切片集合
     */
    @Override
    public List<ImBlock> selectImBlockList(ImBlock imBlock) {
        return imBlockMapper.selectImBlockList(imBlock);
    }

    /**
     * 新增切片
     *
     * @param imBlock 切片信息
     * @return 结果
     */
    @Override
    public int insertImBlock(ImBlock imBlock) {
        return imBlockMapper.insertImBlock(imBlock);
    }

    /**
     * 修改切片
     *
     * @param imBlock 切片信息
     * @return 结果
     */
    @Override
    public int updateImBlock(ImBlock imBlock) {
        return imBlockMapper.updateImBlock(imBlock);
    }

    /**
     * 删除切片对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteImBlockByIds(String ids) {
        return imBlockMapper.deleteImBlockByIds(Convert.toStrArray(ids));
    }

}
