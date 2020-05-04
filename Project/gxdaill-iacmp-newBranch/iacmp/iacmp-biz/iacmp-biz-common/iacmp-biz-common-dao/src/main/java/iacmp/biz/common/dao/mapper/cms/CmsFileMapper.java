package iacmp.biz.common.dao.mapper.cms;


import net.northking.iacmp.common.bean.domain.cms.CmsFile;
import net.northking.iacmp.common.bean.dto.cms.CmsFileDTO;
import net.northking.iacmp.common.bean.vo.cms.CmsFileVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 文件 数据层
 *
 * @author qingyu.yan
 * @date 2019-08-26
 */
public interface CmsFileMapper {
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
     * 删除文件
     *
     * @param id 文件ID
     * @return 结果
     */
    int deleteCmsFileById(Long id);

    /**
     * 批量删除文件
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteCmsFileByIds(String[] ids);

    List<CmsFileDTO> selectCmsFileListByOpts(CmsFileVO cmsFileVO);

    /**
     * 查询文件列表
     *
     * @param batchId 文件批次
     * @return 文件集合
     */
    List<CmsFile> selectFileByBatchId(@Param("batchId") Long batchId);

    /**
     * 条件查询同一文件的不同版本
     *
     * @param batchId
     * @param billId
     * @param names
     * @return
     */
    List<CmsFile> selectCmsFileHistory(@Param("batchId") Long batchId, @Param("billId") Integer billId, @Param("names") String[] names);

    List<CmsFile> selectCmsFileByIds(@Param("fileIds") Long[] fileIds);

    List<CmsFile> selectCmsFileByMd5(@Param("md5") String md5, @Param("status") String status);

    int selectCountCmsFileByMd5(@Param("md5") String md5);

    CmsFile selectOneFileByMd5(@Param("md5") String md5);

    List<CmsFile> selectFilesByCondition(@Param("batchId") Long batchId, @Param("billId") Long billId);

    List<CmsFile> selectFileByCondition(@Param("batchId") Long batchId, @Param("billId") Long billId);

    List<CmsFile> selectFileListByCondition(@Param("batchId") Long batchId, @Param("billId") Long billId);

    Integer selectFileNumByCondition(@Param("batchId") Long batchId, @Param("billId") Long billId);

    Integer selectFileImageNumByCondition(@Param("batchId") Long batchId, @Param("billId") Long billId);

    /**
     * 通过文件编号删除文件信息
     *
     * @param md5s 需要删除的文件编号
     * @return 结果
     */
    int deleteCmsFileByMD5s(String[] md5s);

    List<CmsFileDTO> selectCmsFileListByBillCode(String billCode);

    /**
     * 批量更新
     *
     * @param originCmsFiles
     * @return
     */
    int updateCmsFiles(@Param("originCmsFiles") List<CmsFile> originCmsFiles);

    /**
     * 批量新增
     *
     * @param originCmsFiles
     * @return
     */
    int insertCmsFiles(@Param("originCmsFiles") List<CmsFile> originCmsFiles);

    int deleteCmsFileByCondition(@Param("batchId") Long batchId, @Param("billId") Integer billId, @Param("fileNames") List<String> fileNames);

    int updateCmsFileBill(@Param("batchId") Long batchId, @Param("billIds") Integer[] billIds, @Param("fileNames") String[] fileNames,
                          @Param("targetBillId") Integer targetBillId, @Param("trg") String trg);

    List<CmsFileDTO> selectFileImageByIds(@Param("fileIds") Long[] fileIds);
}