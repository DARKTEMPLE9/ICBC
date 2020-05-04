package iacmp.biz.common.dao.mapper.ams;


import net.northking.iacmp.common.bean.domain.ams.ImFile;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 上传文件 数据层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface ImFileMapper {
    /**
     * 查询上传文件信息
     *
     * @param id 上传文件ID
     * @return 上传文件信息
     */
    ImFile selectImFileById(String id);

    /**
     * 查询上传文件列表
     *
     * @param imFile 上传文件信息
     * @return 上传文件集合
     */
    List<ImFile> selectImFileList(ImFile imFile);

    /**
     * 新增上传文件
     *
     * @param imFile 上传文件信息
     * @return 结果
     */
    int insertImFile(ImFile imFile);

    /**
     * 修改上传文件
     *
     * @param imFile 上传文件信息
     * @return 结果
     */
    int updateImFile(ImFile imFile);

    /**
     * 删除上传文件
     *
     * @param id 上传文件ID
     * @return 结果
     */
    int deleteImFileById(String id);

    /**
     * 批量删除上传文件
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteImFileByIds(String[] ids);

    /**
     * 通过batchId查找相关影像
     *
     * @param batchId
     * @return
     */
    List<ImFile> selectImFilesByBatchId(String batchId);

    /**
     * 通过全局流水号查找相关文件
     *
     * @param busiNo
     * @return
     */
    List<ImFile> selectImFilesByBusiNo(String busiNo);

    /**
     * 通过全局流水号查找相关文件
     *
     * @param fileIds
     * @return
     */
    List<ImFile> selectCmsFileByIds(String[] fileIds);

    /**
     * 查询上传文件信息
     *
     * @param md5 上传文件MD5
     * @return 上传文件数量
     */
    int selectImFileByMd5(String md5);

    /**
     * 更新batchId
     */
    int updateBatchId(@Param("batchId") String batchId, @Param("regId") String regId);

    /**
     * 通过batchId集合查找相关影像
     *
     * @param batchIds
     * @return
     */
    List<ImFile> selectImFilesByBatchIdList(@Param("batchIds") List<String> batchIds);
}