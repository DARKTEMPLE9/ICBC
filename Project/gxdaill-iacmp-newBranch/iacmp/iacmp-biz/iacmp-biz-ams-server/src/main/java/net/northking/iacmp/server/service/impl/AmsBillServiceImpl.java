package net.northking.iacmp.server.service.impl;

import iacmp.biz.common.dao.mapper.ams.AmsBillMapper;
import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.common.bean.domain.ams.AmsBill;
import net.northking.iacmp.constant.Constants;
import net.northking.iacmp.core.domain.Ztree;
import net.northking.iacmp.core.text.Convert;
import net.northking.iacmp.enums.DataSourceType;
import net.northking.iacmp.server.service.IAmsBillService;
import net.northking.iacmp.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 档案类型 服务层实现
 *
 * @author wxy
 * @date 2019-08-01
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class AmsBillServiceImpl implements IAmsBillService {
    @Autowired
    private AmsBillMapper amsBillMapper;

    List<Ztree> ztreeList = new ArrayList<Ztree>();
    List<String> treeList = new ArrayList<>();

    /**
     * 查询档案类型信息
     *
     * @param id 档案类型ID
     * @return 档案类型信息
     */
    @Override
    public AmsBill selectAmsBillById(String id) {
        return amsBillMapper.selectAmsBillById(id);
    }

    /**
     * 查询档案类型列表
     *
     * @param amsBill 档案类型信息
     * @return 档案类型集合
     */
    @Override
    public List<AmsBill> selectAmsBillList(AmsBill amsBill) {
        List<AmsBill> amsBills = amsBillMapper.selectAmsBillList(amsBill);
        for (AmsBill bill : amsBills) {
            AmsBill amsBill1 = amsBillMapper.selectAmsBillById(bill.getParentId());
            if ("0".equals(bill.getParentId())) {
                bill.setParentName("-");
            } else {
                bill.setParentName(amsBill1.getName());
            }
        }
        return amsBills;
    }

    /**
     * 查询oragnization的name和code
     */
    @Override
    public List<String> queryOrganNameAndCode() {

        List<String> listNameCodeOrgan = new ArrayList<>();
        List<Map<String, Object>> listNameCode = amsBillMapper.queryOrganNameAndCode();
        List<Object[]> obj = new ArrayList<Object[]>();
        for (Map<String, Object> map : listNameCode) {
            System.out.println(map.values());
            Collection values = map.values();
            List list = new ArrayList(values);
            obj.add(list.toArray());
        }
        for (Object[] message : obj) {
            listNameCodeOrgan.add(Arrays.toString(message));
        }
        return listNameCodeOrgan;
    }

    //查询档案类型的名字和code
    @Override
    public List<String> queryArcBillAndCode() {
        List<String> listArcBillNameCode = new ArrayList<>();
        List<Map<String, Object>> listArcBillName = amsBillMapper.queryArcBillAndCode();
        List<Object[]> obj = new ArrayList<Object[]>();
        for (Map<String, Object> map : listArcBillName) {
            System.out.println(map.values());
            Collection values = map.values();
            List list = new ArrayList(values);
            obj.add(list.toArray());
        }
        for (Object[] message : obj) {
            listArcBillNameCode.add(Arrays.toString(message));
        }
        return listArcBillNameCode;
    }

    /**
     * 按部门查询一级录目
     *
     * @param organCode
     * @param fillingTimeGt
     * @param fillingTimeLt
     * @param arcBillCode
     * @return
     */
    @Override
    public List<String> queryNumberArcByOneOrgan(String fillingTimeGt, String fillingTimeLt, List<String> treeList, String arcBillCode, List orgCodeList) {
        //对于searchYear进行去空操作,String arcBillCode
//		organCode = organCode.replaceAll(" ", "");
        List<String> listArcBillCodeAndNumber = new ArrayList<>();
        //System.out.println(fillingTime_gt);
        //起始时间与结束时间都存在
        List<Map<String, Object>> listBillCodeAndNumber = amsBillMapper.queryNumberArcByOneOrgan(fillingTimeGt, fillingTimeLt, treeList, arcBillCode, orgCodeList);
        List<Object[]> obj = new ArrayList<Object[]>();
        //总量
//		int count=Integer.valueOf(listBillCodeAndNumber.get(0).get("number").toString());
//		List<Object[]> obj = new ArrayList<Object[]>();
        for (Map<String, Object> map : listBillCodeAndNumber) {
            System.out.println(map.values());
            Collection values = map.values();
            List list = new ArrayList(values);
            obj.add(list.toArray());
        }
        for (Object[] message : obj) {
            listArcBillCodeAndNumber.add(Arrays.toString(message));
        }
        return listArcBillCodeAndNumber;
    }

    /**
     * 查询档案移交统计表部门一级目录
     *
     * @param organCode
     * @param fillingTimeGt
     * @param fillingTimeLt
     * @param arcBillCode
     * @return
     */
    public List<String> queryNumberArcByOneOrganTrans(String fillingTimeGt, String fillingTimeLt, List<String> treeList, String arcBillCode, List<String> orgCodeList) {

        List<String> listArcBillCodeAndNumber = new ArrayList<>();
        //System.out.println(fillingTime_gt);
        //起始时间与结束时间都存在
        List<Map<String, Object>> listBillCodeAndNumber = amsBillMapper.queryNumberArcByOneOrganTrans(fillingTimeGt, fillingTimeLt, treeList, arcBillCode, orgCodeList);
        List<Object[]> obj = new ArrayList<Object[]>();
        for (Map<String, Object> map : listBillCodeAndNumber) {
            System.out.println(map.values());
            Collection values = map.values();
            List list = new ArrayList(values);
            obj.add(list.toArray());
        }
        for (Object[] message : obj) {
            listArcBillCodeAndNumber.add(Arrays.toString(message));
        }
//		int count =Integer.parseInt(listBillCodeAndNumber.get(0).get("number").toString());
        return listArcBillCodeAndNumber;
    }


    /**
     * 新增档案类型
     *
     * @param amsBill 档案类型信息
     * @return 结果
     */
    @Override
    public int insertAmsBill(AmsBill amsBill) {
        return amsBillMapper.insertAmsBill(amsBill);
    }

    /**
     * 修改档案类型
     *
     * @param amsBill 档案类型信息
     * @return 结果
     */
    @Override
    public int updateAmsBill(AmsBill amsBill) {
        return amsBillMapper.updateAmsBill(amsBill);
    }

    /**
     * 删除档案类型对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAmsBillByIds(String ids) {
        return amsBillMapper.deleteAmsBillByIds(Convert.toStrArray(ids));
    }

    /**
     * 查询档案类型树
     *
     * @param amsBill 档案类型信息
     * @return 结果
     */
    @Override
    public List<Ztree> selectArchiveTree(AmsBill amsBill) {

        AmsBill parentNode = new AmsBill();
        parentNode.setId(amsBill.getParentId());
        List<AmsBill> pList = this.selectAmsBillList(parentNode);
        if (pList != null && pList.size() > 0) {
            if (pList.get(0).getParentId().equals("0")) {
                Ztree ztree = new Ztree();
                ztree.setId(Long.valueOf(pList.get(0).getId()));
                ztree.setpId(Long.valueOf(pList.get(0).getParentId()));
                ztree.setName(pList.get(0).getName());
                ztree.setTitle(pList.get(0).getName());
                ztreeList.add(ztree);
            }
        }

        List<AmsBill> amsBillList = amsBillMapper.selectAmsBillALL(amsBill);

        for (int i = 0; i < amsBillList.size(); i++) {

            if (Constants.AMS_BILL_NORMAL.equals(amsBillList.get(i).getStatus())) {
                Ztree ztree = new Ztree();
                ztree.setId(Long.valueOf(amsBillList.get(i).getId()));
                ztree.setpId(Long.valueOf(amsBillList.get(i).getParentId()));
                ztree.setName(amsBillList.get(i).getCode() + "-" + amsBillList.get(i).getName());
                ztree.setTitle(amsBillList.get(i).getName());
                ztreeList.add(ztree);
            }

            AmsBill children = new AmsBill();
            children.setParentId(amsBillList.get(i).getId());

            List<AmsBill> list = this.selectAmsBillList(children);
            if (list != null && list.size() > 0) {
                for (AmsBill obj : list) {
                    this.selectArchiveTree(obj);
                }
            }
        }

        return ztreeList;
    }

    public void cleanZtreeList() {
        ztreeList.clear();
    }

    /**
     * 查询子类档案类型
     *
     * @param amsBill
     * @return
     */
    @Override
    public List<AmsBill> getChildBill(AmsBill amsBill) {
        return amsBillMapper.getChildBill(amsBill);
    }

    /**
     * 对象转部门树
     *
     * @param amsBillList 部门列表
     * @param roleList    角色已存在菜单列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<AmsBill> amsBillList, List<String> roleList) {

        List<Ztree> ztrees = new ArrayList<Ztree>();
        boolean isCheck = StringUtils.isNotNull(roleList);
        for (AmsBill amsBill : amsBillList) {
            Ztree ztree = new Ztree();
            ztree.setId(new Long(amsBill.getId()));
            if (amsBill.getParentId().equals("null") || amsBill.getParentId().equals("")) {
                ztree.setpId(new Long(0));
            } else {
                ztree.setpId(new Long(amsBill.getParentId()));
            }
            ztree.setName(amsBill.getName());
            ztree.setTitle(amsBill.getName());
            if (isCheck) {
                ztree.setChecked(roleList.contains(amsBill.getId() + amsBill.getName()));
            }
            ztrees.add(ztree);
        }
        return ztrees;
    }

    /**
     * 查询档案类型树
     *
     * @param amsBill
     * @return
     */
    @Override
    public List<Ztree> selectAmsBillTree(AmsBill amsBill) {

        List<AmsBill> amsBillList = this.selectAmsBillList(amsBill);
        List<Ztree> ztrees = this.initZtree(amsBillList);

        return ztrees;
    }

    /**
     * 查询档案类型树(只查询一级类目)
     *
     * @param amsBill
     * @return
     */
    @Override
    public List<Ztree> selectAmsBillTreeOneLevel(AmsBill amsBill) {

        List<AmsBill> amsBillList = amsBillMapper.selectAmsBillListOneLevel(amsBill);
        List<Ztree> ztrees = this.initZtree(amsBillList);

        return ztrees;
    }

    /**
     * 对象转部门树
     *
     * @param amsBillList 档案类型列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<AmsBill> amsBillList) {

        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (AmsBill amsBill : amsBillList) {
            if (Constants.AMS_BILL_NORMAL.equals(amsBill.getStatus())) {
                Ztree ztree = new Ztree();
                ztree.setId(Long.valueOf(amsBill.getId()));
                ztree.setpId(Long.valueOf(amsBill.getParentId()));
                ztree.setName(amsBill.getName());
                ztree.setTitle(amsBill.getName());
                ztree.setParentName(amsBill.getParentName());
                ztrees.add(ztree);
            }
        }
        return ztrees;
    }

    /**
     * 查询档案类型名称
     *
     * @param code
     * @return
     */
    @Override
    public String queryForName(String code) {
        return amsBillMapper.queryForName(code);
    }

    /**
     * 查询档案子类型父节点
     *
     * @param amsBill
     * @return
     */
    @Override
    public List<AmsBill> queryArcBillParent(AmsBill amsBill) {
        return amsBillMapper.queryArcBillParent(amsBill);
    }

    /**
     * 根据id模糊查询父档案类型
     *
     * @param billId
     * @return
     */
    @Override
    public AmsBill selectAmsBillLikeById(String billId) {
        return amsBillMapper.selectAmsBillLikeById(billId);
    }

    /**
     * 查询最大Id值
     *
     * @return
     */
    @Override
    public String selectMaxId() {
        return amsBillMapper.selectMaxId();
    }

    /**
     * 查询实物类型树
     *
     * @param amsBill
     * @return
     */
    @Override
    public List<Ztree> selectAmsBillMatterType(AmsBill amsBill) {
        List<AmsBill> amsBillList = amsBillMapper.selectAmsBillMatterType(amsBill);
        List<Ztree> ztrees = this.initZtree(amsBillList);

        return ztrees;
    }


    //根据一级档案类型查询下属二级类型
    @Override
    public List<String> queryArcBillDept(String parentId) {
        List<String> listArcBillNameCode = new ArrayList<>();
        List<Map<String, Object>> listArcBillName = amsBillMapper.queryArcBillDept(parentId);
        List<String> noLeafArcBillDept = amsBillMapper.selectNoLeafSecondLevel(parentId);
        List<Object[]> obj = new ArrayList<Object[]>();
        for (Map<String, Object> map : listArcBillName) {
            System.out.println(map.values());
            String code = map.get("value").toString();
            if (noLeafArcBillDept.size() > 0) {
                for (int i = 0; i < noLeafArcBillDept.size(); i++) {
                    if (code.equals(noLeafArcBillDept.get(i))) {//该节点是父节点
                        map.put("isParent", "isParent");
                        break;
                    }
                }
            }
            Collection values = map.values();
            List list = new ArrayList(values);
            obj.add(list.toArray());
        }
        for (Object[] message : obj) {
            listArcBillNameCode.add(Arrays.toString(message));
        }
        return listArcBillNameCode;
    }

    /**
     * 按部门查询二级录目（总量统计）
     *
     * @param organCode
     * @param fillingTimeGt
     * @param fillingTimeLt
     * @param arcBillCode
     * @return
     */
    @Override
    public List<String> queryNumberArcBySecondOrgan(String fillingTimeGt, String fillingTimeLt, List<String> treeList, List<String> orgCodeList) {

        //对于searchYear进行去空操作

//		organCode = organCode.replaceAll(" ", "");
        List<String> listArcBillCodeAndNumber = new ArrayList<>();
        //起始时间与结束时间都存在
        List<Map<String, Object>> listBillCodeAndNumber = amsBillMapper.queryNumberArcBySecondOrgan(fillingTimeGt, fillingTimeLt, treeList, orgCodeList);
        List<Object[]> obj = new ArrayList<Object[]>();
        for (Map<String, Object> map : listBillCodeAndNumber) {
            System.out.println(map.values());
            Collection values = map.values();
            List list = new ArrayList(values);
            obj.add(list.toArray());
        }
        for (Object[] message : obj) {
            listArcBillCodeAndNumber.add(Arrays.toString(message));
        }
        return listArcBillCodeAndNumber;
//		int count=Integer.parseInt(listBillCodeAndNumber.get(0).get("number").toString());
//		return count;
    }


    /**
     * 按部门查询二级录目（移交统计）
     *
     * @param organCode
     * @param fillingTimeGt
     * @param fillingTimeLt
     * @param arcBillCode
     * @return
     */
    @Override
    public List<String> queryNumberArcBySecondOrganTrans(String fillingTimeGt, String fillingTimeLt, List<String> treeList, List<String> orgCodeList) {

        List<String> listArcBillCodeAndNumber = new ArrayList<>();
        //起始时间与结束时间都存在
        List<Map<String, Object>> listBillCodeAndNumber = amsBillMapper.queryNumberArcBySecondOrganTrans(fillingTimeGt, fillingTimeLt, treeList, orgCodeList);
        List<Object[]> obj = new ArrayList<Object[]>();
        for (Map<String, Object> map : listBillCodeAndNumber) {
            System.out.println(map.values());
            Collection values = map.values();
            List list = new ArrayList(values);
            obj.add(list.toArray());
        }
        for (Object[] message : obj) {
            listArcBillCodeAndNumber.add(Arrays.toString(message));
        }
//		int count=Integer.parseInt(listBillCodeAndNumber.get(0).get("number").toString());
        return listArcBillCodeAndNumber;
    }

    /**
     * 按部门查询二级录目（移交统计）
     *
     * @param organCode
     * @param fillingTimeGt
     * @param fillingTimeLt
     * @param arcBillCode
     * @return
     */
    @Override
    public List<String> queryNumberArcBySecondOrganBorrow(String fillingTimeGt, String fillingTimeLt, List<String> treeList, List<String> orgCodeList) {
        List<String> listArcBillCodeAndNumber = new ArrayList<>();
        //起始时间与结束时间都存在
        List<Map<String, Object>> listBillCodeAndNumber = amsBillMapper.queryBorTypeBySecondOrgan(fillingTimeGt, fillingTimeLt, treeList, orgCodeList);
        List<Object[]> obj = new ArrayList<Object[]>();
        for (Map<String, Object> map : listBillCodeAndNumber) {
            System.out.println(map.values());
            Collection values = map.values();
            List list = new ArrayList(values);
            obj.add(list.toArray());
        }
        for (Object[] message : obj) {
            listArcBillCodeAndNumber.add(Arrays.toString(message));
        }
        return listArcBillCodeAndNumber;
    }

    /**
     * 部门档案统计
     */
    @Override
    public Map<String, Object> countByDept() {
        List<Map<String, Object>> list = amsBillMapper.countByDept();
        Map<String, Object> map = new HashMap<>();
        for (Map<String, Object> m : list) {
            Collection col = m.values();
            Object[] values = col.toArray();
            Object key = values[0];
//			String key=(String)values[0];
            Object value = values[1];
            map.put(key.toString(), value);
        }

        return map;
    }

    /**
     * 各类型档案统计
     */
    @Override
    public List<Map<String, Object>> countByArcType() {
        List<Map<String, Object>> list = amsBillMapper.countByArcType();
//		Map<String,Object> map = new HashMap<>();
//		for(Map<String,Object> m:list){
//			Collection col=m.values();
//			Object[] values=col.toArray();
//			String key=(String)values[1];
//			Object value=values[0];
//			map.put(key,value);
//		}
        return list;
    }

    /**
     * 根据父档案类型查询二级类目
     */
    @Override
    public List<Ztree> treeDataSecondLevel(String parentId) {
        List<AmsBill> list = amsBillMapper.treeDataSecondLevel(parentId);
        List<Ztree> ztrees = this.initZtree(list);
        return ztrees;
    }

    /**
     * 根据当前档案类型id查询全部子节点
     */
    @Override
    public List<String> allSonTreeNode(String parentId) {
        List<String> list = amsBillMapper.selectAllSonArcBillCode(parentId);
        list.add(parentId);
        return list;
    }

}
