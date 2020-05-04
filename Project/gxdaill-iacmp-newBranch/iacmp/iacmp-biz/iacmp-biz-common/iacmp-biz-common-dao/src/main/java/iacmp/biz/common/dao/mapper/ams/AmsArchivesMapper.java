package iacmp.biz.common.dao.mapper.ams;


import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.common.bean.domain.ams.AmsArchives;
import net.northking.iacmp.common.bean.dto.ams.AmsArchivesDTO;
import net.northking.iacmp.common.bean.vo.ams.AmsArchivesVO;
import net.northking.iacmp.enums.DataSourceType;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 档案登记 数据层
 *
 * @author wxy
 * @date 2019-08-01
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public interface AmsArchivesMapper {
    /**
     * 查询档案登记信息
     *
     * @param id 档案登记ID
     * @return 档案登记信息
     */
    AmsArchives selectAmsArchivesById(String id);

    /**
     * 根据batchid 查询档案登记信息
     *
     * @param batchId 著录ID
     * @return 档案登记信息
     */
    AmsArchives selectAmsArchivesByBatchId(String batchId);

    /**
     * 根据角色id查询用户名
     *
     * @param roleId
     * @return
     */
    List<String> selectNextUserByroleId(String roleId);

    /**
     * 查询档案登记列表
     *
     * @param archivesVO 档案登记信息
     * @return 档案登记集合
     */
    List<AmsArchives> selectAmsArchivesList(AmsArchivesVO archivesVO);

    /**
     * 查询档案登记列表
     *
     * @param archivesVO 档案登记信息
     * @return 档案登记集合
     */
    List<AmsArchivesDTO> selectAmsArchivesListAll(AmsArchivesVO archivesVO);

    /**
     * 档案检索（行档案管理员）
     *
     * @param archivesVO 档案登记信息
     * @return 档案登记集合
     */
    List<AmsArchivesDTO> selectAmsArchivesListAllForAdmin(@Param("amsArchives") AmsArchivesVO archivesVO, @Param("treeList") List<String> treeNodeList);

    /**
     * 查询档案登记列表
     *
     * @param deptId 档案登记信息
     * @return 档案登记集合
     */
    List<AmsArchivesDTO> selectAmsArchivesListNew(@Param("deptId") String deptId, @Param("amsArchives") AmsArchivesVO amsArchives, @Param("loginName") String loginName, @Param("treeList") List<String> treeNodeList);

    /**
     * 根据盒ID查询档案登记信息
     *
     * @param iD 档案登记ID
     * @return 档案登记信息
     */
    List<AmsArchives> selectAmsArchivesByboxId(String iD);

    /**
     * 新增档案登记
     *
     * @param amsArchives 档案登记信息
     * @return 结果
     */
    int insertAmsArchives(AmsArchives amsArchives);

    /**
     * 修改档案登记
     *
     * @param amsArchives 档案登记信息
     * @return 结果
     */
    int updateAmsArchives(AmsArchives amsArchives);

    /**
     * 删除档案登记
     *
     * @param id 档案登记ID
     * @return 结果
     */
    int deleteAmsArchivesById(String id);

    /**
     * 批量删除档案登记
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteAmsArchivesByIds(String[] ids);

    /**
     * 查询全部销毁档案
     *
     * @return
     */
    List<AmsArchivesDTO> selectArchDestroyList(@Param("amsArchives") AmsArchivesVO amsArchives, @Param("deptList") List<String> deptList);

    /**
     * 查询待销毁档案
     *
     * @return
     */
    List<AmsArchives> selectArchDestroyListND();

    /**
     * 查询已过期档案
     *
     * @return
     */
    List<AmsArchives> selectArchDestroyListAL();

    int updateAmsArchivesByIds(String[] ids);

    /**
     * 批量获取档案
     *
     * @param iDs 需要获取到档案的数据ID
     * @return 结果
     */
    List<AmsArchives> selectAmsArchivesByIds(String[] iDs);

    /**
     * 根据部门查询可查看影像信息
     *
     * @param deptId
     * @param amsArchives
     * @return
     */
    List<AmsArchivesDTO> selectAmsArchivesBydeptId(@Param("deptId") List<String> deptId, @Param("amsArchives") AmsArchivesVO amsArchives, @Param("treeList") List<String> treeNodeList);

    /**
     * 查询可移库档案
     *
     * @param amsArchives
     * @return
     */
    List<AmsArchives> selectMatterArc(AmsArchives amsArchives);

    List<AmsArchivesDTO> selectArchDestroyHisList(@Param("archivesVO") AmsArchivesVO archivesVO, @Param("deptList") List<String> deptList);

    List<AmsArchivesDTO> selectArchByMatterType(@Param("amsArchivesVO") AmsArchivesVO amsArchivesVO);

    List<AmsArchivesDTO> selectArchByMatter(@Param("amsArchivesVO") AmsArchivesVO amsArchivesVO, @Param("deptList") List<String> deptList);


    int updateEntityAmsArchivesByIds(String[] ids);

    /**
     * 查询要打印的销毁列表
     */
    List<AmsArchives> selectPrintDestoryList(String[] iDs);

    /**
     * 查询收藏的档案信息
     */
    List<AmsArchivesDTO> selectAmsArchivesByFavorite(@Param("iDs") String[] iDs, @Param("amsArchives") AmsArchivesVO amsArchives);

    int updateAmsBatchByIds(String[] toStrArray);

    int updateEntityAmsBatchByIds(String[] toStrArray);

}