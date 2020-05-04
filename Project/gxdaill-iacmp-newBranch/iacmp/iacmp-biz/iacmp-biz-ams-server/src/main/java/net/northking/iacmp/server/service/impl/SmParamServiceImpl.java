package net.northking.iacmp.server.service.impl;

import iacmp.biz.common.dao.mapper.ams.SmParamMapper;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.common.bean.domain.ams.SmParam;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.server.service.ISmParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 参数 服务层实现
 *
 * @author wxy
 * @date 2019-08-01
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class SmParamServiceImpl implements ISmParamService {
    @Autowired
    private SmParamMapper smParamMapper;

    /**
     * 查询参数信息
     *
     * @param id 参数ID
     * @return 参数信息
     */
    @Override
    public SmParam selectSmParamById(String id) {
        return smParamMapper.selectSmParamById(id);
    }

    /**
     * 查询参数列表
     *
     * @param smParam 参数信息
     * @return 参数集合
     */
    @Override
    public List<SmParam> selectSmParamList(SmParam smParam) {
        return smParamMapper.selectSmParamList(smParam);
    }

    /**
     * 新增参数
     *
     * @param smParam 参数信息
     * @return 结果
     */
    @Override
    public int insertSmParam(SmParam smParam) {
        return smParamMapper.insertSmParam(smParam);
    }

    /**
     * 修改参数
     *
     * @param smParam 参数信息
     * @return 结果
     */
    @Override
    public int updateSmParam(SmParam smParam) {
        return smParamMapper.updateSmParam(smParam);
    }

    /**
     * 删除参数对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSmParamByIds(String ids) {
        return smParamMapper.deleteSmParamByIds(Convert.toStrArray(ids));
    }

}
