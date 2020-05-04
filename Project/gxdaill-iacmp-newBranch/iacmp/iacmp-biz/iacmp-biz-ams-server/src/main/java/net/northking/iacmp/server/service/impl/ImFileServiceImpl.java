package net.northking.iacmp.server.service.impl;

import iacmp.biz.common.dao.mapper.ams.ImFileMapper;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.common.bean.domain.ams.ImFile;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.server.service.IImFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 上传文件 服务层实现
 *
 * @author wxy
 * @date 2019-08-01
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class ImFileServiceImpl implements IImFileService {
    @Autowired
    private ImFileMapper imFileMapper;

    /**
     * 查询上传文件信息
     *
     * @param id 上传文件ID
     * @return 上传文件信息
     */
    @Override
    public ImFile selectImFileById(String id) {
        return imFileMapper.selectImFileById(id);
    }

    /**
     * 查询上传文件列表
     *
     * @param imFile 上传文件信息
     * @return 上传文件集合
     */
    @Override
    public List<ImFile> selectImFileList(ImFile imFile) {
        return imFileMapper.selectImFileList(imFile);
    }

    /**
     * 新增上传文件
     *
     * @param imFile 上传文件信息
     * @return 结果
     */
    @Override
    public int insertImFile(ImFile imFile) {
        return imFileMapper.insertImFile(imFile);
    }

    /**
     * 修改上传文件
     *
     * @param imFile 上传文件信息
     * @return 结果
     */
    @Override
    public int updateImFile(ImFile imFile) {
        return imFileMapper.updateImFile(imFile);
    }

    /**
     * 删除上传文件对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteImFileByIds(String ids) {
        return imFileMapper.deleteImFileByIds(Convert.toStrArray(ids));
    }

    /**
     * 通过著录编号查找相关文件
     *
     * @param batchId
     * @return
     */
    @Override
    public List<ImFile> selectImFilesByBatchId(String batchId) {
        return imFileMapper.selectImFilesByBatchId(batchId);
    }

    /**
     * 通过全局流水号查找相关文件
     *
     * @param busiNo
     * @return
     */
    @Override
    public List<ImFile> selectImFilesByBusiNo(String busiNo) {
        return imFileMapper.selectImFilesByBusiNo(busiNo);
    }

    @Override
    public List<ImFile> selectCmsFileByIds(String[] fileIds) {
        return imFileMapper.selectCmsFileByIds(fileIds);
    }

    @Override
    public int selectImFileByMd5(String md5) {
        return imFileMapper.selectImFileByMd5(md5);
    }

    /**
     * 更新batchId
     */
    @Override
    public int updateBatchId(String batchId, String regId) {
        return imFileMapper.updateBatchId(batchId, regId);
    }

    /**
     * 通过batchId集合查找相关影像
     *
     * @param batchIds
     * @return
     */
    @Override
    public List<ImFile> selectImFilesByBatchIdList(List<String> batchIds) {
        return imFileMapper.selectImFilesByBatchIdList(batchIds);
    }

}
