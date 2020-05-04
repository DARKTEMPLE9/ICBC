package net.northking.iacmp.server.service.impl;

import java.util.List;

import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import iacmp.biz.common.dao.mapper.ams.AmsCodeMapper;
import net.northking.iacmp.common.bean.domain.ams.AmsCode;
import net.northking.iacmp.server.service.IAmsCodeService;
import net.northking.iacmp.core.text.Convert;

/**
 * 编号生成 服务层实现
 *
 * @author wxy
 * @date 2019-08-01
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class AmsCodeServiceImpl implements IAmsCodeService {
    @Autowired
    private AmsCodeMapper amsCodeMapper;

    /**
     * 查询编号生成信息
     *
     * @param id 编号生成ID
     * @return 编号生成信息
     */
    @Override
    public AmsCode selectAmsCodeById(String id) {
        return amsCodeMapper.selectAmsCodeById(id);
    }

    /**
     * 查询编号生成列表
     *
     * @param amsCode 编号生成信息
     * @return 编号生成集合
     */
    @Override
    public List<AmsCode> selectAmsCodeList(AmsCode amsCode) {
        return amsCodeMapper.selectAmsCodeList(amsCode);
    }

    /**
     * 新增编号生成
     *
     * @param amsCode 编号生成信息
     * @return 结果
     */
    @Override
    public int insertAmsCode(AmsCode amsCode) {
        return amsCodeMapper.insertAmsCode(amsCode);
    }

    /**
     * 修改编号生成
     *
     * @param amsCode 编号生成信息
     * @return 结果
     */
    @Override
    public int updateAmsCode(AmsCode amsCode) {
        return amsCodeMapper.updateAmsCode(amsCode);
    }

    /**
     * 删除编号生成对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAmsCodeByIds(String ids) {
        return amsCodeMapper.deleteAmsCodeByIds(Convert.toStrArray(ids));
    }

}
