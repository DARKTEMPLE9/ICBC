package net.northking.iacmp.imp.service;

import net.northking.iacmp.imp.domain.OldImFile;

import java.util.List;
import java.util.Map;

/**
 * 文件 服务层
 *
 * @author wei.chen
 * @date 2019-10-22
 */
public interface IOldImFileService {
    /**
     * 查询文件信息
     *
     * @param id 文件ID
     * @return 文件信息
     */
    public OldImFile selectImFileById(String id);

    /**
     * 查询文件列表
     *
     * @param imFile 文件信息
     * @return 文件集合
     */
    public List<OldImFile> selectImFileList(OldImFile imFile);

    /**
     * 新增文件
     *
     * @param imFile 文件信息
     * @return 结果
     */
    public int insertImFile(OldImFile imFile);

    /**
     * 修改文件
     *
     * @param imFile 文件信息
     * @return 结果
     */
    public int updateImFile(OldImFile imFile);

    /**
     * 删除文件信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteImFileByIds(String ids);

    /**
     * 根据参数查询文件列表
     *
     * @param paramMap 影像信息
     * @return 影像集合
     */
    public List<OldImFile> selectImFileByMap(Map<String, Object> paramMap);

}
