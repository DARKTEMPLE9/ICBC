package net.northking.iacmp.imp.service.impl;


import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.SmParam;
import net.northking.iacmp.imp.mapper.SmParamMapper;
import net.northking.iacmp.imp.service.ISmParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * 参数配置 服务层实现
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class SmParamServiceImpl implements ISmParamService {
    @Autowired
    private SmParamMapper smParamMapper;

    /**
     * 查询参数配置信息
     *
     * @param id 参数配置ID
     * @return 参数配置信息
     */
    @Override
    public SmParam selectSmParamById(String id) {
        return smParamMapper.selectSmParamById(id);
    }

    @Override
    public HashMap queryById(String id) {
        return smParamMapper.queryById(id);
    }

    /**
     * 查询参数配置列表
     *
     * @param smParam 参数配置信息
     * @return 参数配置集合
     */
    @Override
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public List<SmParam> selectSmParamList(SmParam smParam) {
        return smParamMapper.selectSmParamList(smParam);
    }

    @Override
    public List<SmParam> listAll(HashMap smParam) {
        return smParamMapper.listAll(smParam);
    }

    @Override
    public Integer count(HashMap smParam) {
        return smParamMapper.count(smParam);
    }

    /**
     * 新增参数配置
     *
     * @param smParam 参数配置信息
     * @return 结果
     */
    @Override
    public int insertSmParam(SmParam smParam) {
        return smParamMapper.insertSmParam(smParam);
    }

    /**
     * 修改参数配置
     *
     * @param smParam 参数配置信息
     * @return 结果
     */
    @Override
    public Integer updateSmParam(HashMap smParam) {
        return smParamMapper.updateSmParam(smParam);
    }

    /**
     * 删除参数配置对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public Integer deleteById(String id) {
        return smParamMapper.deleteById(id);
    }

}
