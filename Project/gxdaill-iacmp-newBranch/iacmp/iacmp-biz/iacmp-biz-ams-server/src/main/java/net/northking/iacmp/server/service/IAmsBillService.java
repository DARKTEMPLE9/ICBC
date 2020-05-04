package net.northking.iacmp.server.service;

import net.northking.iacmp.common.bean.domain.ams.AmsBill;
import net.northking.iacmp.core.domain.Ztree;

import java.util.List;
import java.util.Map;

/**
 * 档案类型 服务层
 *
 * @author wxy
 * @date 2019-08-01
 */
public interface IAmsBillService {
    /**
     * 查询档案类型信息
     *
     * @param id 档案类型ID
     * @return 档案类型信息
     */
    public AmsBill selectAmsBillById(String id);

    /**
     * 查询档案类型列表
     *
     * @param amsBill 档案类型信息
     * @return 档案类型集合
     */
    public List<AmsBill> selectAmsBillList(AmsBill amsBill);

    /**
     * 查询oragnization的name和code
     */
    public List<String> queryOrganNameAndCode();

    /**
     * 查询档案类型的名字和code
     *
     * @return
     */
    public List<String> queryArcBillAndCode();

    /**
     * 按部门查询一级目录
     */
    public List<String> queryNumberArcByOneOrgan(String fillingTimeGt, String fillingTimeLt, List<String> treeList, String arcBillCode, List orgCodeList);

    /**
     * 查询档案移交统计表部门一级目录
     *
     * @param organCode
     * @param fillingTimeGt
     * @param fillingTimeLt
     * @param arcBillCode
     * @return
     */
    public List<String> queryNumberArcByOneOrganTrans(String fillingTimeGt, String fillingTimeLt, List<String> treeList, String arcBillCode, List<String> orgCodeList);

    /**
     * 新增档案类型
     *
     * @param amsBill 档案类型信息
     * @return 结果
     */
    public int insertAmsBill(AmsBill amsBill);

    /**
     * 修改档案类型
     *
     * @param amsBill 档案类型信息
     * @return 结果
     */
    public int updateAmsBill(AmsBill amsBill);

    /**
     * 删除档案类型信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAmsBillByIds(String ids);

    /**
     * 查询档案类型树
     *
     * @param amsBill 档案类型信息
     * @return 结果
     */
    public List<Ztree> selectArchiveTree(AmsBill amsBill);

    /**
     * 清空ztree列表
     */
    public void cleanZtreeList();

    /**
     * 查询子类档案类型
     *
     * @param amsBill
     * @return
     */
    public List<AmsBill> getChildBill(AmsBill amsBill);

    /**
     * 加载档案类型树
     *
     * @param amsBill
     * @return
     */
    public List<Ztree> selectAmsBillTree(AmsBill amsBill);

    List<Ztree> selectAmsBillTreeOneLevel(AmsBill amsBill);

    /**
     * 查询档案类型名称
     *
     * @param code
     * @return
     */
    public String queryForName(String code);

    /**
     * 查询档案子类型父节点
     *
     * @param amsBill
     * @return
     */
    public List<AmsBill> queryArcBillParent(AmsBill amsBill);

    /**
     * 根据id模糊查询父档案类型
     *
     * @param substring
     * @return
     */
    AmsBill selectAmsBillLikeById(String substring);

    /**
     * 查询最大Id值
     *
     * @return
     */
    String selectMaxId();

    List<Ztree> selectAmsBillMatterType(AmsBill amsBill);

    /**
     * 根据一级档案类型查询下属二级类型
     */
    List<String> queryArcBillDept(String parentId);

    /**
     * 按部门查询二级目录(总量统计)
     */
    public List<String> queryNumberArcBySecondOrgan(String fillingTimeGt, String fillingTimeLt, List<String> treeList, List<String> orgCodeList);

    /**
     * 按部门查询二级目录(移交统计)
     */
    public List<String> queryNumberArcBySecondOrganTrans(String fillingTimeGt, String fillingTimeLt, List<String> treeList, List<String> orgCodeList);

    /**
     * 按部门查询二级目录(利用统计)
     */
    public List<String> queryNumberArcBySecondOrganBorrow(String fillingTimeGt, String fillingTimeLt, List<String> treeList, List<String> orgCodeList);

    /**
     * 部门档案统计
     */
    public Map<String, Object> countByDept();

    /**
     * 各类型档案统计
     */
    public List<Map<String, Object>> countByArcType();

    /**
     * 根据父档案类型查询二级类目
     */
    List<Ztree> treeDataSecondLevel(String parentId);

    /**
     * 根据当前档案类型id查询全部子节点
     */
    List<String> allSonTreeNode(String parentId);
}
