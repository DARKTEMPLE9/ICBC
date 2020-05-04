package net.northking.iacmp.imp.mapper;

import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.imp.domain.ImFile;
import net.northking.iacmp.imp.dto.ImFileDTO;
import net.northking.iacmp.imp.vo.ImBillVO;
import net.northking.iacmp.imp.vo.ImFileVO;

import java.util.List;
import java.util.Map;

/**
 * 文件 数据层
 *
 * @author weizhe.fan
 * @date 2019-10-14
 */
public interface ImFileMapper {
    /**
     * 查询文件信息
     *
     * @param imFile 文件
     * @return 文件信息
     */
    ImFile selectImFileById(ImFile imFile);

    Integer selectByBillId(String billId);

    /**
     * 查询文件列表
     *
     * @param imFile 文件信息
     * @return 文件集合
     */
    List<ImFile> selectImFileList(ImFile imFile);

    List<ImFile> selectFilesByBusino(String busino);

    /**
     * 新增文件
     *
     * @param imFile 文件信息
     * @return 结果
     */
    int insertImFile(ImFile imFile);

    /**
     * 修改文件
     *
     * @param imFile 文件信息
     * @return 结果
     */
    int updateImFile(ImFile imFile);

    /**
     * 删除文件
     *
     * @param id 文件ID
     * @return 结果
     */
    int deleteImFileById(String id);

    /**
     * 批量删除文件
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteImFileByIds(String[] ids);

    /**
     * 按分类分组查找文件
     *
     * @param imFile
     * @return
     */
    List<ImBillVO> selectImFileGroupByBill(ImFile imFile);

    /**
     * 根据影像获取接口查询文件列表
     *
     * @param paramMap 接口参数map
     * @return 文件集合
     */
    List<ImFile> selectImFileByMap(Map<String, Object> paramMap);

    /**
     * 查看文件列表
     *
     * @param fileVO
     * @return
     */
    @DataSource(value = DataSourceType.IMP_HORIZONTAL)
    List<ImFile> selectImFileVOList(ImFileVO fileVO);

    /**
     * 通过文件编号查找文件信息
     *
     * @param imFileDTO
     * @return
     */
    List<ImFile> selectFilesByIds(ImFileDTO imFileDTO);
}