package net.northking.iacmp.cms.service.impl;


import iacmp.biz.common.dao.mapper.cms.CmsFileMapper;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.cms.service.ICmsFileService;
import net.northking.iacmp.common.bean.domain.cms.CmsFile;
import net.northking.iacmp.common.bean.dto.cms.CmsFileDTO;
import net.northking.iacmp.common.bean.vo.cms.CmsFileVO;

import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文件 服务层实现
 *
 * @author qingyu.yan
 * @date 2019-08-26
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class CmsFileServiceImpl implements ICmsFileService {

    @Autowired
    private CmsFileMapper cmsFileMapper;

    /**
     * 查询文件信息
     *
     * @param id 文件ID
     * @return 文件信息
     */
    @Override
    public CmsFile selectCmsFileById(Long id) {
        return cmsFileMapper.selectCmsFileById(id);
    }

    /**
     * 查询生效文件信息
     *
     * @param id 文件ID
     * @return 文件信息
     */
    @Override
    public CmsFile selectCmsFileByFileId(Long id) {
        return cmsFileMapper.selectCmsFileByFileId(id);
    }

    /**
     * 查询文件列表
     *
     * @param cmsFile 文件信息
     * @return 文件集合
     */
    @Override
    public List<CmsFile> selectCmsFileList(CmsFile cmsFile) {
        return cmsFileMapper.selectCmsFileList(cmsFile);
    }

    /**
     * 新增文件
     *
     * @param cmsFile 文件信息
     * @return 结果
     */
    @Override
    public int insertCmsFile(CmsFile cmsFile) {
        return cmsFileMapper.insertCmsFile(cmsFile);
    }

    /**
     * 修改文件
     *
     * @param cmsFile 文件信息
     * @return 结果
     */
    @Override
    public int updateCmsFile(CmsFile cmsFile) {
        return cmsFileMapper.updateCmsFile(cmsFile);
    }

    /**
     * 删除文件对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCmsFileByIds(String ids) {
        return cmsFileMapper.deleteCmsFileByIds(Convert.toStrArray(ids));
    }


    @Override
    public List<CmsFile> selectCmsFileByIds(Long[] fileIds) {
        return cmsFileMapper.selectCmsFileByIds(fileIds);
    }

    @Override
    public List<CmsFile> selectCmsFileByMd5(String md5, Integer status) {
        return cmsFileMapper.selectCmsFileByMd5(md5, String.valueOf(status));
    }

    @Override
    public CmsFile selectOneFileByMd5(String md5) {
        return cmsFileMapper.selectOneFileByMd5(md5);
    }

    @Override
    public int selectCountCmsFileByMd5(String md5) {
        return cmsFileMapper.selectCountCmsFileByMd5(md5);
    }

    /**
     * 通过batchId,modelId,billId查询文件
     *
     * @param batchId
     * @param billId
     * @return
     */
    @Override
    public List<CmsFile> selectFileByCondition(Long batchId, Long billId) {
        return cmsFileMapper.selectFileByCondition(batchId, billId);
    }

    @Override
    public List<CmsFile> selectFilesByCondition(Long batchId, Long billId) {
        return cmsFileMapper.selectFilesByCondition(batchId, billId);
    }

    /**
     * 删除文件信息
     *
     * @param md5s 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCmsFileByNames(String md5s) {
        return cmsFileMapper.deleteCmsFileByMD5s(Convert.toStrArray(md5s));
    }

    @Override
    public int deleteCmsFileByCondition(Long batchId, Integer billId, List<String> fileNames) {
        return cmsFileMapper.deleteCmsFileByCondition(batchId, billId, fileNames);
    }

    /**
     * 根据条件查询文件和影像信息
     *
     * @param cmsFileVO
     * @return
     */
    @Override
    public List<CmsFileDTO> selectCmsFileListByOpts(CmsFileVO cmsFileVO) {
        return cmsFileMapper.selectCmsFileListByOpts(cmsFileVO);
    }

    @Override
    public List<CmsFileDTO> selectFileImageByIds(Long[] fileIds) {
        return cmsFileMapper.selectFileImageByIds(fileIds);
    }

}
