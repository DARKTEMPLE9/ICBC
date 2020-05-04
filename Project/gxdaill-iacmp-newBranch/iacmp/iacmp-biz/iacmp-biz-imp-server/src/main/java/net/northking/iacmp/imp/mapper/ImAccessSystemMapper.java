package net.northking.iacmp.imp.mapper;


import net.northking.iacmp.imp.domain.ImAccessSystem;

import java.util.HashMap;
import java.util.List;

/**
 * 接入系统 数据层
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
public interface ImAccessSystemMapper {
    /**
     * 查询接入系统信息
     *
     * @param id 接入系统ID
     * @return 接入系统信息
     */
    ImAccessSystem selectImAccessSystemById(String id);

    HashMap querySystemById(String systemId);

    List<ImAccessSystem> queryBySysFlagInt(Integer sysFlagInt);

    List<ImAccessSystem> queryBySysFlagInt2(HashMap map);

    /**
     * 查询接入系统列表
     *
     * @param imAccessSystem 接入系统信息
     * @return 接入系统集合
     */
    List<ImAccessSystem> selectImAccessSystemList(ImAccessSystem imAccessSystem);

    List<ImAccessSystem> queryAllSystem(HashMap map);

    Integer selectSystemCount(HashMap map);

    /**
     * 新增接入系统
     *
     * @param imAccessSystem 接入系统信息
     * @return 结果
     */
    Integer insertImAccessSystem(HashMap map);

    /**
     * 修改接入系统
     *
     * @param imAccessSystem 接入系统信息
     * @return 结果
     */
    Integer updateById(HashMap map);

    /**
     * 删除接入系统
     *
     * @param id 接入系统ID
     * @return 结果
     */
    Integer deleteImAccessSystemById(String id);

    /**
     * 批量删除接入系统
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    Integer deleteImAccessSystemByIds(String id);

}