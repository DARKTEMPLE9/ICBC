package net.northking.iacmp.server.service.impl;

import iacmp.biz.common.dao.mapper.ams.AmsBoxMapper;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.common.bean.domain.ams.AmsBox;
import net.northking.iacmp.common.bean.vo.ams.AmsBoxVO;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.server.service.IAmsBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 档案盒 服务层实现
 *
 * @author wxy
 * @date 2019-08-01
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class AmsBoxServiceImpl implements IAmsBoxService {
    @Autowired
    private AmsBoxMapper amsBoxMapper;

    /**
     * 查询档案盒信息
     *
     * @param id 档案盒ID
     * @return 档案盒信息
     */
    @Override
    public AmsBox selectAmsBoxById(String id) {
        return amsBoxMapper.selectAmsBoxById(id);
    }

    /**
     * 查询档案盒列表
     *
     * @param amsBox 档案盒信息
     * @return 档案盒集合
     */
    @Override
    public List<AmsBox> selectAmsBoxList(AmsBoxVO amsBox) {
        return amsBoxMapper.selectAmsBoxList(amsBox);
    }

    @Override
    public List<AmsBox> selectAmsBoxList(AmsBoxVO amsBox, List<String> deptList) {
        return amsBoxMapper.selectAmsBoxes(amsBox, deptList);
    }


    /**
     * 新增档案盒
     *
     * @param amsBox 档案盒信息
     * @return 结果
     */
    @Override
    public int insertAmsBox(AmsBox amsBox) {
        return amsBoxMapper.insertAmsBox(amsBox);
    }

    /**
     * 修改档案盒
     *
     * @param amsBox 档案盒信息
     * @return 结果
     */
    @Override
    public int updateAmsBox(AmsBox amsBox) {
        return amsBoxMapper.updateAmsBox(amsBox);
    }

    /**
     * 删除档案盒对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAmsBoxByIds(String ids) {
        return amsBoxMapper.deleteAmsBoxByIds(Convert.toStrArray(ids));
    }

    @Override
    public List<AmsBox> selectArchStoreList(AmsBoxVO amsBox, List<String> deptList) {
        List<AmsBox> list = amsBoxMapper.selectArchStoreList(amsBox, deptList);
        return list;
    }

    @Override
    public List<AmsBox> selectMoveCabList(AmsBoxVO amsBox, List<String> deptList) {
        List<AmsBox> list = amsBoxMapper.selectMoveCabList(amsBox, deptList);
        return list;
    }

    /**
     * 查询档案移库
     *
     * @param amsBox
     * @return
     */
    @Override
    public List<AmsBox> selectArchMoveCab(AmsBox amsBox) {
        List<AmsBox> list = amsBoxMapper.selectArchMoveCab(amsBox);
        return list;
    }

    @Override
    public List<AmsBox> selectAmsBoxListByOpts(AmsBoxVO amsBox, List<String> deptList) {
        return amsBoxMapper.selectAmsBoxListByOpts(amsBox, deptList);
    }
}
