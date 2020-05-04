package net.northking.iacmp.server.service.impl;

import java.util.List;
import java.util.UUID;

import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.common.bean.vo.ams.AmsCabinetVO;
import net.northking.iacmp.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import iacmp.biz.common.dao.mapper.ams.AmsCabinetMapper;
import net.northking.iacmp.common.bean.domain.ams.AmsCabinet;
import net.northking.iacmp.server.service.IAmsCabinetService;
import net.northking.iacmp.core.text.Convert;

/**
 * 库柜 服务层实现
 *
 * @author wxy
 * @date 2019-08-08
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class AmsCabinetServiceImpl implements IAmsCabinetService {
    @Autowired
    private AmsCabinetMapper amsCabinetMapper;

    /**
     * 查询库柜信息
     *
     * @param id 库柜ID
     * @return 库柜信息
     */
    @Override
    public AmsCabinet selectAmsCabinetById(String id) {
        return amsCabinetMapper.selectAmsCabinetById(id);
    }

    /**
     * 查询库柜列表
     *
     * @param amsCabinet 库柜信息
     * @return 库柜集合
     */
    @Override
    public List<AmsCabinet> selectAmsCabinetList(AmsCabinet amsCabinet) {
        return amsCabinetMapper.selectAmsCabinetList(amsCabinet);
    }

    @Override
    public List<AmsCabinetVO> selectAmsCabList(AmsCabinet amsCabinet, List<String> deptList) {
        return amsCabinetMapper.selectAmsCabList(amsCabinet, deptList);
    }

    /**
     * 新增库柜
     *
     * @param amsCabinet 库柜信息
     * @return 结果
     */
    @Override
    public int insertAmsCabinet(AmsCabinet amsCabinet) {
        if (amsCabinet.getId() == null || amsCabinet.getId().equals("")) {
            String id = UUID.randomUUID().toString().replace("-", "");
            amsCabinet.setId(id);
        }
        return amsCabinetMapper.insertAmsCabinet(amsCabinet);
    }

    /**
     * 修改库柜
     *
     * @param amsCabinet 库柜信息
     * @return 结果
     */
    @Override
    public int updateAmsCabinet(AmsCabinet amsCabinet) {
        return amsCabinetMapper.updateAmsCabinet(amsCabinet);
    }

    /**
     * 删除库柜对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAmsCabinetByIds(String ids) {
        return amsCabinetMapper.deleteAmsCabinetByIds(Convert.toStrArray(ids));
    }

}
