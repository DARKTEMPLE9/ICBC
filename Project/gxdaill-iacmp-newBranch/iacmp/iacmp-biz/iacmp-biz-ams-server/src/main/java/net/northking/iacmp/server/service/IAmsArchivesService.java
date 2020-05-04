package net.northking.iacmp.server.service;

import net.northking.iacmp.common.bean.domain.ams.AmsArchives;
import net.northking.iacmp.common.bean.dto.ams.AmsArchivesDTO;
import net.northking.iacmp.common.bean.vo.ams.AmsArchivesVO;

import java.util.List;

/**
 * 档案登记 服务层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface IAmsArchivesService {
    /**
     * 查询档案登记信息
     *
     * @param id 档案登记ID
     * @return 档案登记信息
     */
    AmsArchives selectAmsArchivesById(String id);

    /**
     * 根据batchid查询档案登记信息
     *
     * @param batchId
     * @return
     */
    AmsArchives selectAmsArchivesByBatchId(String batchId);

    /**
     * 查询档案登记列表
     *
     * @param amsArchivesVO 档案登记信息
     * @return 档案登记集合
     */
    List<AmsArchives> selectAmsArchivesList(AmsArchivesVO amsArchivesVO);

    /**
     * 查询档案登记列表
     *
     * @param amsArchivesVO 档案登记信息
     * @return 档案登记集合
     */
    List<AmsArchivesDTO> selectAmsArchivesListAll(AmsArchivesVO amsArchivesVO);

    /**
     * （行档案管理员）
     *
     * @param amsArchivesVO 档案登记信息
     * @return 档案登记集合
     */
    List<AmsArchivesDTO> selectAmsArchivesListAllForAdmin(AmsArchivesVO amsArchivesVO, List<String> treeNodeList);

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
    public int updateAmsArchives(AmsArchives amsArchives);

    /**
     * 删除档案登记信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAmsArchivesByIds(String ids);

    /**
     * @return
     */
    List<AmsArchivesDTO> selectArchDestroyList(AmsArchivesVO amsArchives, List<String> deptList);

    /**
     * @return
     */
    List<AmsArchives> selectArchDestroyListND();

    /**
     * @return
     */
    List<AmsArchives> selectArchDestroyListAL();

    /**
     * @param ids
     * @return
     */
    int updateAmsArchivesByIds(String ids);

    int updateEntityAmsArchivesByIds(String ids);


    List<AmsArchives> selectAmsArchivesByIds(String ids);

    /**
     * 根据部门数据权限查询amsarchibes
     *
     * @param deptId
     * @return
     */
    List<AmsArchivesDTO> selectAmsArchivesBydeptId(List<String> deptId, AmsArchivesVO amsArchives, List<String> treeNodeList);

    /**
     * 根据部门数据权限查询amsarchibes
     *
     * @param deptId
     * @return
     */
    List<AmsArchivesDTO> selectAmsArchivesListNew(String deptId, AmsArchivesVO amsArchives, String loginName, List<String> treeNodeList);

    /**
     * 查询可移库档案
     *
     * @param amsArchives
     * @return
     */
    List<AmsArchives> selectMatterArc(AmsArchives amsArchives);

    List<AmsArchivesDTO> selectArchDestroyHisList(AmsArchivesVO archivesVO, List<String> deptList);

    List<AmsArchivesDTO> selectArchByMatterType(AmsArchivesVO amsArchivesVO);

    List<AmsArchivesDTO> selectArchByMatterType(AmsArchivesVO amsArchivesVO, List<String> deptList);


    /**
     * 查询要打印的销毁列表
     */
    List<AmsArchives> selectPrintDestoryList(String ids);

    /**
     * 查询收藏的档案信息
     */
    List<AmsArchivesDTO> selectAmsArchivesByFavorite(String ids, AmsArchivesVO amsArchives);
}
