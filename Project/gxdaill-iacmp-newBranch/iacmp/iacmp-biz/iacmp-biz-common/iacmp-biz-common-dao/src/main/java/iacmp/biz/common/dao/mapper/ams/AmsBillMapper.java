package iacmp.biz.common.dao.mapper.ams;


import net.northking.iacmp.common.bean.domain.ams.AmsBill;
import net.northking.iacmp.core.domain.Ztree;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 档案类型 数据层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface AmsBillMapper {
    /**
     * 查询档案类型信息
     *
     * @param id 档案类型ID
     * @return 档案类型信息
     */
    AmsBill selectAmsBillById(String id);

    /**
     * 查询档案类型列表
     *
     * @param amsBill 档案类型信息
     * @return 档案类型集合
     */
    List<AmsBill> selectAmsBillList(AmsBill amsBill);

    /**
     * 查询organname和code
     */
    List<Map<String, Object>> queryOrganNameAndCode();

    /**
     * 查询档案类型的名字和code
     *
     * @return
     */
    List<Map<String, Object>> queryArcBillAndCode();

    /**
     * 按部门查询一级目录
     */
    List<Map<String, Object>> queryNumberArcByOneOrgan(@Param("fillingTimeGt") String fillingTimeGt, @Param("fillingTimeLt") String fillingTimeLt, @Param("treeList") List<String> treeList, @Param("arcBillCode") String arcBillCode, @Param("orgCodeList") List orgCodeList);

    /**
     * 查询档案移交统计一级目录
     */
    List<Map<String, Object>> queryNumberArcByOneOrganTrans(@Param("fillingTimeGt") String fillingTimeGt, @Param("fillingTimeLt") String fillingTimeLt, @Param("treeList") List<String> treeList, @Param("arcBillCode") String arcBillCode, @Param("orgCodeList") List<String> orgCodeList);

    /**
     * 查询档案统计利用统计
     */
    List<Map<String, Object>> queryBorTypeByOneOrgan(@Param("fillingTimeGt") String fillingTimeGt, @Param("fillingTimeLt") String fillingTimeLt, @Param("arcBillCode") String arcBillCode, @Param("treeList") List<String> treeList, @Param("orgCodeList") List<String> orgCodeList);

    /**
     * 新增档案类型
     *
     * @param amsBill 档案类型信息
     * @return 结果
     */
    int insertAmsBill(AmsBill amsBill);

    /**
     * 修改档案类型
     *
     * @param amsBill 档案类型信息
     * @return 结果
     */
    int updateAmsBill(AmsBill amsBill);

    /**
     * 删除档案类型
     *
     * @param id 档案类型ID
     * @return 结果
     */
    int deleteAmsBillById(String id);

    /**
     * 批量删除档案类型
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteAmsBillByIds(String[] ids);

    /**
     * 查询档案类型全列表
     *
     * @param amsBill 档案类型信息
     * @return 档案类型集合
     */
    List<AmsBill> selectAmsBillALL(AmsBill amsBill);

    /**
     * 查询子类档案类型
     *
     * @param amsBill
     * @return
     */
    List<AmsBill> getChildBill(AmsBill amsBill);

    /**
     * 查询档案类型名称
     *
     * @param code
     * @return
     */
    String queryForName(String code);

    /**
     * 查询档案子类型父节点
     *
     * @param amsBill
     * @return
     */
    List<AmsBill> queryArcBillParent(AmsBill amsBill);

    /**
     * 查询档案类型树(只查询一级类目)
     *
     * @param amsBill
     * @return
     */
    List<AmsBill> selectAmsBillListOneLevel(AmsBill amsBill);

    /**
     * 根据id模糊查询父档案类型
     *
     * @param billId
     * @return
     */
    AmsBill selectAmsBillLikeById(String billId);

    /**
     * 查询最大Id值
     *
     * @return
     */
    String selectMaxId();

    List<AmsBill> selectAmsBillMatterType(AmsBill amsBill);

    /**
     * 根据一级档案类型查询下属二级目录
     */
    List<Map<String, Object>> queryArcBillDept(@Param("parentId") String parentId);

    /**
     * 按部门查询二级目录下数量（总量统计）
     */
    List<Map<String, Object>> queryNumberArcBySecondOrgan(@Param("fillingTimeGt") String fillingTimeGt, @Param("fillingTimeLt") String fillingTimeLt, @Param("treeList") List<String> treeList, @Param("orgCodeList") List<String> orgCodeList);

    /**
     * 按部门查询二级目录下数量（移交统计）
     */
    List<Map<String, Object>> queryNumberArcBySecondOrganTrans(@Param("fillingTimeGt") String fillingTimeGt, @Param("fillingTimeLt") String fillingTimeLt, @Param("treeList") List<String> treeList, @Param("orgCodeList") List<String> orgCodeList);


    /**
     * 按部门查询二级目录下数量（利用统计）
     */
    List<Map<String, Object>> queryBorTypeBySecondOrgan(@Param("fillingTimeGt") String fillingTimeGt, @Param("fillingTimeLt") String fillingTimeLt, @Param("treeList") List<String> treList, @Param("orgCodeList") List<String> orgCodeList);

    /**
     * 部门档案统计
     */
    List<Map<String, Object>> countByDept();

    /**
     * 各类型档案统计
     */
    List<Map<String, Object>> countByArcType();

    /**
     * 根据父档案类型查询二级类目
     */
    List<AmsBill> treeDataSecondLevel(@Param("parentId") String parentId);

    /**
     * 根据档案类型查询下属非叶子节点的二级目录
     */
    List<String> selectNoLeafSecondLevel(@Param("parentId") String parentId);

    /**
     * 查询目录下全部子目录
     */
    List<String> selectAllSonArcBillCode(@Param("parentId") String parentId);

}