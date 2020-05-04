package net.northking.iacmp.server.service.impl;

import java.util.List;

import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import iacmp.biz.common.dao.mapper.ams.AmsDestroyMapper;
import net.northking.iacmp.common.bean.domain.ams.AmsDestroy;
import net.northking.iacmp.server.service.IAmsDestroyService;
import net.northking.iacmp.core.text.Convert;

/**
 * 档案销毁 服务层实现
 *
 * @author wxy
 * @date 2019-08-01
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class AmsDestroyServiceImpl implements IAmsDestroyService {
    @Autowired
    private AmsDestroyMapper amsDestroyMapper;

    /**
     * 查询档案销毁信息
     *
     * @param id 档案销毁ID
     * @return 档案销毁信息
     */
    @Override
    public AmsDestroy selectAmsDestroyById(String id) {
        return amsDestroyMapper.selectAmsDestroyById(id);
    }

    /**
     * 查询档案销毁列表
     *
     * @param amsDestroy 档案销毁信息
     * @return 档案销毁集合
     */
    @Override
    public List<AmsDestroy> selectAmsDestroyList(AmsDestroy amsDestroy) {
        return amsDestroyMapper.selectAmsDestroyList(amsDestroy);
    }

    /**
     * 新增档案销毁
     *
     * @param amsDestroy 档案销毁信息
     * @return 结果
     */
    @Override
    public int insertAmsDestroy(AmsDestroy amsDestroy) {
        return amsDestroyMapper.insertAmsDestroy(amsDestroy);
    }

    /**
     * 修改档案销毁
     *
     * @param amsDestroy 档案销毁信息
     * @return 结果
     */
    @Override
    public int updateAmsDestroy(AmsDestroy amsDestroy) {
        return amsDestroyMapper.updateAmsDestroy(amsDestroy);
    }

    /**
     * 删除档案销毁对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAmsDestroyByIds(String ids) {
        return amsDestroyMapper.deleteAmsDestroyByIds(Convert.toStrArray(ids));
    }

}
