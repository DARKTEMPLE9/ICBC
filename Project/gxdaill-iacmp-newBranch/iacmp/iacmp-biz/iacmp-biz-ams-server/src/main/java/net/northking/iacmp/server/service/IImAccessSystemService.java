package net.northking.iacmp.server.service;

import net.northking.iacmp.common.bean.domain.ams.ImAccessSystem;

import java.util.List;

/**
 * 接入系统 服务层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface IImAccessSystemService {
    /**
     * 查询接入系统信息
     *
     * @param id 接入系统ID
     * @return 接入系统信息
     */
    public ImAccessSystem selectImAccessSystemById(String id);

    /**
     * 查询接入系统列表
     *
     * @param imAccessSystem 接入系统信息
     * @return 接入系统集合
     */
    public List<ImAccessSystem> selectImAccessSystemList(ImAccessSystem imAccessSystem);

    /**
     * 新增接入系统
     *
     * @param imAccessSystem 接入系统信息
     * @return 结果
     */
    public int insertImAccessSystem(ImAccessSystem imAccessSystem);

    /**
     * 修改接入系统
     *
     * @param imAccessSystem 接入系统信息
     * @return 结果
     */
    public int updateImAccessSystem(ImAccessSystem imAccessSystem);

    /**
     * 删除接入系统信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteImAccessSystemByIds(String ids);

}
