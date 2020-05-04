package iacmp.biz.common.dao.mapper.ams;


import net.northking.iacmp.common.bean.domain.ams.AmsBox;
import net.northking.iacmp.common.bean.vo.ams.AmsBoxVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 档案盒 数据层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface AmsBoxMapper {
    /**
     * 查询档案盒信息
     *
     * @param id 档案盒ID
     * @return 档案盒信息
     */
    AmsBox selectAmsBoxById(String id);

    /**
     * 查询档案盒列表
     *
     * @param amsBoxVO 档案盒信息
     * @return 档案盒集合
     */
    List<AmsBox> selectAmsBoxList(AmsBoxVO amsBoxVO);

    List<AmsBox> selectAmsBoxes(@Param("amsBox") AmsBoxVO amsBoxVO, @Param("deptList") List<String> deptList);

    /**
     * 新增档案盒
     *
     * @param amsBox 档案盒信息
     * @return 结果
     */
    int insertAmsBox(AmsBox amsBox);

    /**
     * 修改档案盒
     *
     * @param amsBox 档案盒信息
     * @return 结果
     */
    int updateAmsBox(AmsBox amsBox);

    /**
     * 删除档案盒
     *
     * @param id 档案盒ID
     * @return 结果
     */
    int deleteAmsBoxById(String id);

    /**
     * 批量删除档案盒
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteAmsBoxByIds(String[] ids);

    /**
     * @param amsBox
     * @return
     */
    List<AmsBox> selectArchStoreList(@Param("amsBox") AmsBoxVO amsBox, @Param("deptList") List<String> deptList);

    /**
     * @param amsBox
     * @return
     */
    List<AmsBox> selectMoveCabList(@Param("amsBox") AmsBoxVO amsBox, @Param("deptList") List<String> deptList);

    /**
     * 查询档案移库
     *
     * @param amsBox
     * @return
     */
    List<AmsBox> selectArchMoveCab(AmsBox amsBox);

    List<AmsBox> selectAmsBoxListByOpts(@Param("amsBox") AmsBoxVO amsBox, @Param("deptList") List<String> deptList);
}