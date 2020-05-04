package net.northking.iacmp.server.service.impl;

import java.util.List;
import java.util.UUID;

import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import iacmp.biz.common.dao.mapper.ams.AmsDepotMapper;
import net.northking.iacmp.common.bean.domain.ams.AmsDepot;
import net.northking.iacmp.server.service.IAmsDepotService;
import net.northking.iacmp.core.text.Convert;

/**
 * 库房 服务层实现
 *
 * @author wxy
 * @date 2019-08-01
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class AmsDepotServiceImpl implements IAmsDepotService {
    @Autowired
    private AmsDepotMapper amsDepotMapper;

    /**
     * 查询库房信息
     *
     * @param id 库房ID
     * @return 库房信息
     */
    @Override
    public AmsDepot selectAmsDepotById(String id) {
        return amsDepotMapper.selectAmsDepotById(id);
    }

    /**
     * 查询库房列表
     *
     * @param amsDepot 库房信息
     * @return 库房集合
     */
    @Override
    public List<AmsDepot> selectAmsDepotList(AmsDepot amsDepot) {
        return amsDepotMapper.selectAmsDepotList(amsDepot);
    }

    @Override
    public List<AmsDepot> selectAmsDepotList(AmsDepot amsDepot, List<String> deptList) {
        return amsDepotMapper.selectAmsDepotListByAuxDep(amsDepot, deptList);
    }

    /**
     * 新增库房
     *
     * @param amsDepot 库房信息
     * @return 结果
     */
    @Override
    public int insertAmsDepot(AmsDepot amsDepot) {
        if (amsDepot.getId() == null || amsDepot.getId().equals("")) {
            String id = UUID.randomUUID().toString().replace("-", "");
            amsDepot.setId(id);
        }
        return amsDepotMapper.insertAmsDepot(amsDepot);
    }

    /**
     * 修改库房
     *
     * @param amsDepot 库房信息
     * @return 结果
     */
    @Override
    public int updateAmsDepot(AmsDepot amsDepot) {
        return amsDepotMapper.updateAmsDepot(amsDepot);
    }

    /**
     * 删除库房对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAmsDepotByIds(String ids) {
        return amsDepotMapper.deleteAmsDepotByIds(Convert.toStrArray(ids));
    }

}
