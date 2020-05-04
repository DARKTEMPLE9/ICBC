package net.northking.iacmp.server.service;

import net.northking.iacmp.common.bean.domain.ams.AmsBox;
import net.northking.iacmp.common.bean.vo.ams.AmsBoxVO;

import java.util.List;

/**
 * 档案盒 服务层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface IAmsBoxService {
    /**
     * 查询档案盒信息
     *
     * @param id 档案盒ID
     * @return 档案盒信息
     */
    public AmsBox selectAmsBoxById(String id);

    /**
     * 查询档案盒列表
     *
     * @param amsBox 档案盒信息
     * @return 档案盒集合
     */
    public List<AmsBox> selectAmsBoxList(AmsBoxVO amsBox);

    public List<AmsBox> selectAmsBoxList(AmsBoxVO amsBox, List<String> deptList);

    /**
     * 新增档案盒
     *
     * @param amsBox 档案盒信息
     * @return 结果
     */
    public int insertAmsBox(AmsBox amsBox);

    /**
     * 修改档案盒
     *
     * @param amsBox 档案盒信息
     * @return 结果
     */
    public int updateAmsBox(AmsBox amsBox);

    /**
     * 删除档案盒信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAmsBoxByIds(String ids);

    List<AmsBox> selectArchStoreList(AmsBoxVO amsBox, List<String> deptList);

    List<AmsBox> selectMoveCabList(AmsBoxVO amsBox, List<String> deptList);

    /**
     * 查询档案移库
     *
     * @param amsBox
     * @return
     */
    public List<AmsBox> selectArchMoveCab(AmsBox amsBox);

    List<AmsBox> selectAmsBoxListByOpts(AmsBoxVO amsBox, List<String> deptList);
}
