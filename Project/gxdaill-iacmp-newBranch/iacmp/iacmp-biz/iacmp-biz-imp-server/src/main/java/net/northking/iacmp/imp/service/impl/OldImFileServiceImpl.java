package net.northking.iacmp.imp.service.impl;

import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.OldImFile;
import net.northking.iacmp.imp.mapper.OldImFileMapper;
import net.northking.iacmp.imp.service.IOldImFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 文件 服务层实现
 *
 * @author wei.chen
 * @date 2019-10-22
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class OldImFileServiceImpl implements IOldImFileService {
    @Autowired
    private OldImFileMapper imFileMapper;

    /**
     * 查询文件信息
     *
     * @param id 文件ID
     * @return 文件信息
     */
    @Override
    public OldImFile selectImFileById(String id) {
        return imFileMapper.selectImFileById(id);
    }

    /**
     * 查询文件列表
     *
     * @param imFile 文件信息
     * @return 文件集合
     */
    @Override
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public List<OldImFile> selectImFileList(OldImFile imFile) {
        return imFileMapper.selectImFileList(imFile);
    }

    /**
     * 新增文件
     *
     * @param imFile 文件信息
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public int insertImFile(OldImFile imFile) {
        return imFileMapper.insertImFile(imFile);
    }

    /**
     * 修改文件
     *
     * @param imFile 文件信息
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public int updateImFile(OldImFile imFile) {
        return imFileMapper.updateImFile(imFile);
    }

    /**
     * 删除文件对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteImFileByIds(String ids) {
        return imFileMapper.deleteImFileByIds(Convert.toStrArray(ids));
    }

    /**
     * 根据参数查询文件列表
     *
     * @param paramMap 影像信息
     * @return 影像集合
     */
    @Override
    @DataSource(value = DataSourceType.IMP_VERTICAL)
    public List<OldImFile> selectImFileByMap(Map<String, Object> paramMap) {
        return imFileMapper.selectImFileByMap(paramMap);
    }

}
