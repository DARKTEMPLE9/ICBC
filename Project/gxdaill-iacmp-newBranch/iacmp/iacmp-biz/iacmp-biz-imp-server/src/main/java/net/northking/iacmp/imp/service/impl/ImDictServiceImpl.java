package net.northking.iacmp.imp.service.impl;


import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.ImDict;
import net.northking.iacmp.imp.mapper.ImDictMapper;
import net.northking.iacmp.imp.service.IImDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * 分类字典 服务层实现
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class ImDictServiceImpl implements IImDictService {
    @Autowired
    private ImDictMapper imDictMapper;

    /**
     * 查询分类字典信息
     *
     * @param id 分类字典ID
     * @return 分类字典信息
     */
    @Override
    public ImDict selectImDictById(Long id) {
        return imDictMapper.selectImDictById(id);
    }

    /**
     * 查询分类字典列表
     *
     * @param imDict 分类字典信息
     * @return 分类字典集合
     */
    @Override
    public List<ImDict> selectImDictList(HashMap imDict) {
        return imDictMapper.selectImDictList(imDict);
    }

    @Override
    public Integer count(HashMap map) {
        return imDictMapper.count(map);
    }

    /**
     * 新增分类字典
     *
     * @param imDict 分类字典信息
     * @return 结果
     */
    @Override
    public Integer addDict(HashMap imDict) {
        return imDictMapper.addDict(imDict);
    }

    /**
     * 修改分类字典
     *
     * @param imDict 分类字典信息
     * @return 结果
     */
    @Override
    public int updateImDict(ImDict imDict) {
        return imDictMapper.updateImDict(imDict);
    }

    /**
     * 删除分类字典对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteImDictByIds(String ids) {
        return imDictMapper.deleteImDictByIds(Convert.toStrArray(ids));
    }

}
