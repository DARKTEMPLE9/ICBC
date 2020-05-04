package iacmp.biz.common.dao.mapper.ams;


import net.northking.iacmp.common.bean.domain.ams.ImAccessSystem;

import java.util.List;

/**
 * 接入系统 数据层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface ImAccessSystemMapper {
    /**
     * 查询接入系统信息
     *
     * @param id 接入系统ID
     * @return 接入系统信息
     */
    ImAccessSystem selectImAccessSystemById(String id);

    /**
     * 查询接入系统列表
     *
     * @param imAccessSystem 接入系统信息
     * @return 接入系统集合
     */
    List<ImAccessSystem> selectImAccessSystemList(ImAccessSystem imAccessSystem);

    /**
     * 新增接入系统
     *
     * @param imAccessSystem 接入系统信息
     * @return 结果
     */
    int insertImAccessSystem(ImAccessSystem imAccessSystem);

    /**
     * 修改接入系统
     *
     * @param imAccessSystem 接入系统信息
     * @return 结果
     */
    int updateImAccessSystem(ImAccessSystem imAccessSystem);

    /**
     * 删除接入系统
     *
     * @param id 接入系统ID
     * @return 结果
     */
    int deleteImAccessSystemById(String id);

    /**
     * 批量删除接入系统
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteImAccessSystemByIds(String[] ids);

}