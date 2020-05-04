package net.northking.iacmp.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.common.bean.dto.ams.AmsArchivesDTO;
import net.northking.iacmp.common.bean.vo.ams.AmsArchivesVO;
import net.northking.iacmp.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import iacmp.biz.common.dao.mapper.ams.AmsArchivesMapper;
import net.northking.iacmp.common.bean.domain.ams.AmsArchives;
import net.northking.iacmp.server.service.IAmsArchivesService;
import net.northking.iacmp.core.text.Convert;

/**
 * 档案登记 服务层实现
 *
 * @author wxy
 * @date 2019-08-01
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class AmsArchivesServiceImpl implements IAmsArchivesService {
    @Autowired
    private AmsArchivesMapper amsArchivesMapper;

    /**
     * 查询档案登记信息
     *
     * @param id 档案登记ID
     * @return 档案登记信息
     */
    @Override
    public AmsArchives selectAmsArchivesById(String id) {
        return amsArchivesMapper.selectAmsArchivesById(id);
    }

    /**
     * 根据batchid 查询档案登记信息
     *
     * @param batchId
     * @return
     */
    @Override
    public AmsArchives selectAmsArchivesByBatchId(String batchId) {
        return amsArchivesMapper.selectAmsArchivesByBatchId(batchId);
    }

    /**
     * 查询档案登记列表
     *
     * @param amsArchivesVO 档案登记信息
     * @return 档案登记集合
     */
    @Override
    public List<AmsArchives> selectAmsArchivesList(AmsArchivesVO amsArchivesVO) {
        return amsArchivesMapper.selectAmsArchivesList(amsArchivesVO);
    }

    /**
     * 查询档案登记列表
     *
     * @param amsArchivesVO 档案登记信息
     * @return 档案登记集合
     */
    @Override
    public List<AmsArchivesDTO> selectAmsArchivesListAll(AmsArchivesVO amsArchivesVO) {
        List<AmsArchivesDTO> amsArchivesDTOS = amsArchivesMapper.selectAmsArchivesListAll(amsArchivesVO);
        if (null != amsArchivesDTOS && amsArchivesDTOS.size() > 0) {
            for (AmsArchivesDTO amsArchivesDTO : amsArchivesDTOS) {
                amsArchivesDTO.setShowIamge(true);
            }
        }
        return amsArchivesDTOS;
    }

    /**
     * 档案检索（行档案管理员）
     *
     * @param amsArchivesVO 档案登记信息
     * @return 档案登记集合
     */
    @Override
    public List<AmsArchivesDTO> selectAmsArchivesListAllForAdmin(AmsArchivesVO amsArchivesVO, List<String> treeNodeList) {
        //sql修改到代码--ll
        List<AmsArchivesDTO> amsArchivesDTOS = amsArchivesMapper.selectAmsArchivesListAllForAdmin(amsArchivesVO, treeNodeList);
        if (null != amsArchivesDTOS && amsArchivesDTOS.size() > 0) {
            for (AmsArchivesDTO amsArchivesDTO : amsArchivesDTOS) {
                amsArchivesDTO.setShowIamge(true);
            }
        }
        return amsArchivesDTOS;
    }

    /**
     * 根据盒ID查询档案登记信息
     *
     * @param iD 档案登记ID
     * @return 档案登记信息
     */
    @Override
    public List<AmsArchives> selectAmsArchivesByboxId(String iD) {
        return amsArchivesMapper.selectAmsArchivesByboxId(iD);
    }

    /**
     * 新增档案登记
     *
     * @param amsArchives 档案登记信息
     * @return 结果
     */
    @Override
    public int insertAmsArchives(AmsArchives amsArchives) {
        return amsArchivesMapper.insertAmsArchives(amsArchives);
    }

    /**
     * 修改档案登记
     *
     * @param amsArchives 档案登记信息
     * @return 结果
     */
    @Override
    public int updateAmsArchives(AmsArchives amsArchives) {
        return amsArchivesMapper.updateAmsArchives(amsArchives);
    }

    /**
     * 删除档案登记对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAmsArchivesByIds(String ids) {
        return amsArchivesMapper.deleteAmsArchivesByIds(Convert.toStrArray(ids));
    }

    /**
     * 查询全部销毁
     *
     * @return
     */
    @Override
    public List<AmsArchivesDTO> selectArchDestroyList(AmsArchivesVO amsArchives, List<String> deptList) {
        List<AmsArchivesDTO> list = amsArchivesMapper.selectArchDestroyList(amsArchives, deptList);
        return list;
    }

    /**
     * 查询待销毁
     *
     * @return
     */
    @Override
    public List<AmsArchives> selectArchDestroyListND() {
        List<AmsArchives> list = amsArchivesMapper.selectArchDestroyListND();
        return list;
    }

    /**
     * 查询已过期
     *
     * @return
     */
    @Override
    public List<AmsArchives> selectArchDestroyListAL() {
        List<AmsArchives> list = amsArchivesMapper.selectArchDestroyListAL();
        return list;
    }

    @Override
    public int updateAmsArchivesByIds(String ids) {
        int i = amsArchivesMapper.updateAmsArchivesByIds(Convert.toStrArray(ids));
        int j = amsArchivesMapper.updateAmsBatchByIds(Convert.toStrArray(ids));
        return j;
    }


    @Override
    public int updateEntityAmsArchivesByIds(String ids) {
        int i = amsArchivesMapper.updateEntityAmsArchivesByIds(Convert.toStrArray(ids));
        int j = amsArchivesMapper.updateEntityAmsBatchByIds(Convert.toStrArray(ids));
        return j;
    }

    /**
     * 批量查找档案信息
     *
     * @param ids 需要获取到档案的数据ID
     * @return
     */
    @Override
    public List<AmsArchives> selectAmsArchivesByIds(String ids) {
        return amsArchivesMapper.selectAmsArchivesByIds(Convert.toStrArray(ids));
    }

    /**
     * 根据部门查询amsarchives
     *
     * @param deptId
     * @return
     */
    @Override
    public List<AmsArchivesDTO> selectAmsArchivesBydeptId(List<String> deptId, AmsArchivesVO amsArchives, List<String> treeNodeList) {
        return amsArchivesMapper.selectAmsArchivesBydeptId(deptId, amsArchives, treeNodeList);
    }

    /**
     * 根据部门查询amsarchives
     *
     * @param deptId
     * @return
     */
    @Override
    public List<AmsArchivesDTO> selectAmsArchivesListNew(String deptId, AmsArchivesVO amsArchives, String loginName, List<String> treeNodeList) {
        return amsArchivesMapper.selectAmsArchivesListNew(deptId, amsArchives, loginName, treeNodeList);
    }

    /**
     * 查询可移库档案
     *
     * @param amsArchives
     * @return
     */
    @Override
    public List<AmsArchives> selectMatterArc(AmsArchives amsArchives) {
        return amsArchivesMapper.selectMatterArc(amsArchives);
    }

    @Override
    public List<AmsArchivesDTO> selectArchDestroyHisList(AmsArchivesVO archivesVO, List<String> deptList) {
        return amsArchivesMapper.selectArchDestroyHisList(archivesVO, deptList);
    }

    @Override
    public List<AmsArchivesDTO> selectArchByMatterType(AmsArchivesVO amsArchivesVO) {
        List<AmsArchivesDTO> amsArchivesDTOS = amsArchivesMapper.selectArchByMatterType(amsArchivesVO);
        if (null != amsArchivesDTOS && amsArchivesDTOS.size() > 0) {
            for (AmsArchivesDTO archivesDTO : amsArchivesDTOS) {
                archivesDTO.setShowIamge(true);
            }
        }
        return amsArchivesDTOS;
    }

    @Override
    public List<AmsArchivesDTO> selectArchByMatterType(AmsArchivesVO amsArchivesVO, List<String> deptList) {
        List<AmsArchivesDTO> amsArchivesDTOS = amsArchivesMapper.selectArchByMatter(amsArchivesVO, deptList);
        if (null != amsArchivesDTOS && amsArchivesDTOS.size() > 0) {
            if (null != amsArchivesDTOS && amsArchivesDTOS.size() > 0) {
                for (AmsArchivesDTO archivesDTO : amsArchivesDTOS) {
                    archivesDTO.setShowIamge(true);
                }
            }
        }
        return amsArchivesDTOS;
    }

    /**
     * 查询要打印的销毁列表
     */
    @Override
    public List<AmsArchives> selectPrintDestoryList(String ids) {
        return amsArchivesMapper.selectPrintDestoryList(Convert.toStrArray(ids));
    }

    /**
     * 查询收藏的档案信息
     */
    @Override
    public List<AmsArchivesDTO> selectAmsArchivesByFavorite(String ids, AmsArchivesVO amsArchives) {
        List<AmsArchivesDTO> amsArchivesDTOS = amsArchivesMapper.selectAmsArchivesByFavorite(Convert.toStrArray(ids), amsArchives);
        if (null != amsArchivesDTOS && amsArchivesDTOS.size() > 0) {
            for (AmsArchivesDTO archivesDTO : amsArchivesDTOS) {
                archivesDTO.setShowIamge(true);
            }
        }
        return amsArchivesDTOS;
    }

}
