package net.northking.iacmp.imp.service.impl;

import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.ImAsynWork;
import net.northking.iacmp.imp.mapper.ImAsynWorkMapper;
import net.northking.iacmp.imp.service.IImAsynWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 异步任务 服务层实现
 *
 * @author weizhe.fan
 * @date 2019-10-29
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class ImAsynWorkServiceImpl implements IImAsynWorkService {
    @Autowired
    private ImAsynWorkMapper imAsynWorkMapper;

    /**
     * 查询异步任务信息
     *
     * @param iD 异步任务ID
     * @return 异步任务信息
     */
    @Override
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public ImAsynWork selectImAsynWorkById(Long iD) {
        return imAsynWorkMapper.selectImAsynWorkById(iD);
    }

    /**
     * 查询异步任务列表
     *
     * @param imAsynWork 异步任务信息
     * @return 异步任务集合
     */
    @Override
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public List<ImAsynWork> selectImAsynWorkList(ImAsynWork imAsynWork) {
        return imAsynWorkMapper.selectImAsynWorkList(imAsynWork);
    }

    /**
     * 新增异步任务
     *
     * @param imAsynWork 异步任务信息
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public int insertImAsynWork(@RequestBody ImAsynWork imAsynWork) {
        return imAsynWorkMapper.insertImAsynWork(imAsynWork);
    }

    /**
     * 修改异步任务
     *
     * @param imAsynWork 异步任务信息
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public int updateImAsynWork(@RequestBody ImAsynWork imAsynWork) {
        return imAsynWorkMapper.updateImAsynWork(imAsynWork);
    }

    /**
     * 删除异步任务对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public int deleteImAsynWorkByIds(String ids) {
        return imAsynWorkMapper.deleteImAsynWorkByIds(Convert.toStrArray(ids));
    }

}
