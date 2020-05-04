package net.northking.iacmp.imp.service;

import net.northking.iacmp.imp.domain.ImDict;

import java.util.HashMap;
import java.util.List;

/**
 * 分类字典 服务层
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
public interface IImDictService {
    /**
     * 查询分类字典信息
     *
     * @param iD 分类字典ID
     * @return 分类字典信息
     */
    ImDict selectImDictById(Long iD);

    /**
     * 查询分类字典列表
     *
     * @param imDict 分类字典信息
     * @return 分类字典集合
     */
    List<ImDict> selectImDictList(HashMap imDict);

    Integer count(HashMap map);

    /**
     * 新增分类字典
     *
     * @param imDict 分类字典信息
     * @return 结果
     */
    Integer addDict(HashMap imDict);

    /**
     * 修改分类字典
     *
     * @param imDict 分类字典信息
     * @return 结果
     */
    int updateImDict(ImDict imDict);

    /**
     * 删除分类字典信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteImDictByIds(String ids);

}
