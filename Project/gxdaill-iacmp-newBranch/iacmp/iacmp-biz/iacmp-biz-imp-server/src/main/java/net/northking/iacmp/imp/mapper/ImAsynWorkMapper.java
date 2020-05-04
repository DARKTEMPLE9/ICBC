package net.northking.iacmp.imp.mapper;

import net.northking.iacmp.imp.domain.ImAsynWork;

import java.util.List;

/**
 * 异步任务 数据层
 *
 * @author weizhe.fan
 * @date 2019-10-29
 */
public interface ImAsynWorkMapper {
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
     * 删除异步任务
     *
     * @param iD 异步任务ID
     * @return 结果
     */
    public int deleteImAsynWorkById(Long iD);

    /**
     * 批量删除异步任务
     *
     * @param iDs 需要删除的数据ID
     * @return 结果
     */
    public int deleteImAsynWorkByIds(String[] iDs);

}