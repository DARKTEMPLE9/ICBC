package net.northking.iacmp.cms.service;


import net.northking.iacmp.common.bean.domain.cms.CmsFile;
import net.northking.iacmp.common.bean.dto.cms.CmsFileDTO;
import net.northking.iacmp.common.bean.vo.cms.CmsFileVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 文件 服务层
 *
 * @author qingyu.yan
 * @date 2019-08-26
 */
public interface ICmsFileService {
    /**
     * 查询文件信息
     *
     * @param id 文件ID
     * @return 文件信息
     */
    CmsFile selectCmsFileById(Long id);

    /**
     * 查询生效文件信息
     *
     * @param id 文件ID
     * @return 文件信息
     */
    CmsFile selectCmsFileByFileId(Long id);

    /**
     * 查询文件列表
     *
     * @param cmsFile 文件信息
     * @return 文件集合
     */
    List<CmsFile> selectCmsFileList(CmsFile cmsFile);

    /**
     * 新增文件
     *
     * @param cmsFile 文件信息
     * @return 结果
     */
    int insertCmsFile(CmsFile cmsFile);

    /**
     * 修改文件
     *
     * @param cmsFile 文件信息
     * @return 结果
     */
    int updateCmsFile(CmsFile cmsFile);

    /**
     * 删除文件信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteCmsFileByIds(String ids);

    List<CmsFile> selectCmsFileByIds(Long[] fileIds);

    List<CmsFile> selectCmsFileByMd5(String md5, Integer status);

    CmsFile selectOneFileByMd5(String md5);

    int selectCountCmsFileByMd5(String md5);

    /**
     * 通过batchId,modelId,billId查询文件
     *
     * @param batchId
     * @param billId
     * @return
     */
    List<CmsFile> selectFileByCondition(Long batchId, Long billId);


    List<CmsFile> selectFilesByCondition(Long batchId, Long billId);

    /**
     * 删除文件信息
     *
     * @param fileNos 需要删除的数据ID
     * @return 结果
     */
    int deleteCmsFileByNames(String fileNos);

    /**
     * 条件批量删除文件信息
     *
     * @param batchId
     * @param billId
     * @param fileNames
     * @return
     */
    int deleteCmsFileByCondition(Long batchId, Integer billId, List<String> fileNames);

    /**
     * 根据条件查询文件和影像信息
     *
     * @param cmsFileVO
     * @return
     */
    List<CmsFileDTO> selectCmsFileListByOpts(CmsFileVO cmsFileVO);

    List<CmsFileDTO> selectFileImageByIds(Long[] fileIds);

}
