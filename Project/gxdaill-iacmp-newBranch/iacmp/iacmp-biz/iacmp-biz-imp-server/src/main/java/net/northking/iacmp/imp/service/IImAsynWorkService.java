package net.northking.iacmp.imp.service;

import net.northking.iacmp.imp.domain.ImAsynWork;

import java.util.List;

/**
 * 异步任务 服务层
 *
 * @author weizhe.fan
 * @date 2019-10-29
 */
public interface IImAsynWorkService {
    /**
     * 查询异步任务信息
     *
     * @param iD 异步任务ID
     * @return 异步任务信息
     */
    public ImAsynWork selectImAsynWorkById(Long iD);

    /**
     * 查询异步任务列表
     *
     * @param imAsynWork 异步任务信息
     * @return 异步任务集合
     */
    public List<ImAsynWork> selectImAsynWorkList(ImAsynWork imAsynWork);

    /**
     * 新增异步任务
     *
     * @param imAsynWork 异步任务信息
     * @return 结果
     */
    public int insertImAsynWork(ImAsynWork imAsynWork);

    /**
     * 修改异步任务
     *
     * @param imAsynWork 异步任务信息
     * @return 结果
     */
    public int updateImAsynWork(ImAsynWork imAsynWork);

    /**
     * 删除异步任务信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteImAsynWorkByIds(String ids);

}
