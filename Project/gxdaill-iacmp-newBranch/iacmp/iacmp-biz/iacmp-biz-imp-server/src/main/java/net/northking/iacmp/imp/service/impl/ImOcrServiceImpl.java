package net.northking.iacmp.imp.service.impl;


import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.ImOcr;
import net.northking.iacmp.imp.mapper.ImOcrMapper;
import net.northking.iacmp.imp.service.IImOcrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;

/**
 * 识别结果 服务层实现
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class ImOcrServiceImpl implements IImOcrService {
    @Autowired
    private ImOcrMapper imOcrMapper;

    /**
     * 查询识别结果信息
     *
     * @param id 识别结果ID
     * @return 识别结果信息
     */
    @Override
    public HashMap selectById(String id) {
        return imOcrMapper.selectById(id);
    }

    /**
     * 查询识别结果列表
     *
     * @param imOcr 识别结果信息
     * @return 识别结果集合
     */
    @Override
    public List<ImOcr> selectImOcrList(HashMap imOcr) {
        return imOcrMapper.selectImOcrList(imOcr);
    }

    @Override
    public Integer count(HashMap map) {
        return imOcrMapper.count(map);
    }

    /**
     * 新增识别结果
     *
     * @param imOcr 识别结果信息
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public int insertImOcr(@RequestBody ImOcr imOcr) {
        return imOcrMapper.insertImOcr(imOcr);
    }

    /**
     * 修改识别结果
     *
     * @param imOcr 识别结果信息
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public int updateImOcr(@RequestBody ImOcr imOcr) {
        return imOcrMapper.updateImOcr(imOcr);
    }

    /**
     * 删除识别结果对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public int deleteImOcrByIds(String ids) {
        return imOcrMapper.deleteImOcrByIds(Convert.toStrArray(ids));
    }

}
