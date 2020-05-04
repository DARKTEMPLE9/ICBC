package net.northking.iacmp.ams.web.controller.parammgr;


import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.common.bean.domain.ams.AmsBill;
import net.northking.iacmp.common.bean.domain.ams.AmsBillDept;
import net.northking.iacmp.common.bean.domain.ams.SmSecret;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.domain.Ztree;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.server.service.IAmsBillDeptService;
import net.northking.iacmp.server.service.IAmsBillService;
import net.northking.iacmp.server.service.ISmSecretService;
import net.northking.iacmp.system.domain.SysDept;
import net.northking.iacmp.system.service.ISysDeptService;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 档案类型 信息操作处理
 *
 * @author wxy
 * @date 2019-08-01
 */
@Controller
@RequestMapping("/param/amsBill")
public class AmsBillController extends BaseController {
    private String prefix = "param/amsBill";

    @Autowired
    private IAmsBillService amsBillService;
    @Autowired
    private ISysDeptService deptService;
    @Autowired
    private ISmSecretService smSecretService;
    @Autowired
    private IAmsBillDeptService amsBillDeptService;

    //结果集
    List<AmsBill> retList = null;

    @RequiresPermissions("param:amsBill:view")
    @GetMapping("/index/{id}")
    public String amsBill(@PathVariable String id, ModelMap mmap) {
        mmap.put("path", id);

        if (id.equals("sub")) {
            return prefix + "/amsBillSub";
        } else if (id.equals("gen")) {
            return prefix + "/amsBill";
        }
        return null;
    }

    /**
     * 查询档案类型列表
     */
    @RequiresPermissions("param:amsBill:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AmsBill amsBill) {
        startPage();
        List<AmsBill> list = amsBillService.selectAmsBillList(amsBill);
        return getDataTable(list);
    }

    @PostMapping("/billList")
    @ResponseBody
    public List<AmsBill> billList(HttpServletRequest request) {
        //初始化
        retList = new ArrayList<>();
        //父Id
        String pid = request.getParameter("pid");
        AmsBill amsBill = new AmsBill();
        if (!"".equals(pid) && pid != null) {
            amsBill.setParentId(pid);
            amsBill.setStatus("1");
        }
        List<AmsBill> list = amsBillService.selectAmsBillList(amsBill);
        if (!list.isEmpty()) {
            List<AmsBill> resultList = recursionGenList(list);
            for (AmsBill obj : resultList) {
                list.add(obj);
            }
        }
        return list;
    }

    private List<AmsBill> recursionGenList(List<AmsBill> list) {
        String pid;
        for (int i = 0; i < list.size(); i++) {
            pid = list.get(i).getId();
            AmsBill tmp = new AmsBill();
            tmp.setParentId(pid);
            tmp.setStatus("1");
            List<AmsBill> list2 = amsBillService.selectAmsBillList(tmp);
            if (!list2.isEmpty()) {
                for (AmsBill obj : list2) {
                    retList.add(obj);
                }
                recursionGenList(list2);
            } else {
                continue;
            }
        }
        return retList;
    }

    /**
     * 查询档案子类型列表
     *
     * @param amsBill
     * @return
     */
    @PostMapping("/child/list")
    @ResponseBody
    public TableDataInfo childList(AmsBill amsBill) {
        startPage();
        List<AmsBill> list = amsBillService.queryArcBillParent(amsBill);
        return getDataTable(list);
    }

    /**
     * 导出档案类型列表
     */
    @RequiresPermissions("param:amsBill:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AmsBill amsBill) {
        List<AmsBill> list = amsBillService.selectAmsBillList(amsBill);
        ExcelUtil<AmsBill> util = new ExcelUtil<>(AmsBill.class);
        return util.exportExcel(list, "amsBill");
    }

    /**
     * 新增档案类型
     */
    @GetMapping("/add")
    public String add(ModelMap mmap) {
        String maxId = amsBillService.selectMaxId();
        Long nextId = Long.valueOf(maxId) + 1;
        mmap.put("nextId", nextId.toString());
        return prefix + "/add";
    }

    /**
     * 新增保存档案类型
     */
    @RequiresPermissions("param:amsBill:add")
    @Log(title = "档案类型", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AmsBill amsBill) {
        if (amsBill.getId() == null || amsBill.getId().equals("")) {
            amsBill.setId(amsBill.getCode());
        }
        //设置档案类型状态
        amsBill.setStatus("1");
        amsBill.setParentId("0");
        return toAjax(amsBillService.insertAmsBill(amsBill));
    }

    /**
     * 修改档案类型
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        AmsBill amsBill = amsBillService.selectAmsBillById(id);
        mmap.put("amsBill", amsBill);
        return prefix + "/edit";
    }

    /**
     * 修改保存档案类型
     */
    @RequiresPermissions("param:amsBill:edit")
    @Log(title = "档案类型", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AmsBill amsBill) {
        return toAjax(amsBillService.updateAmsBill(amsBill));
    }

    /**
     * 删除档案类型
     */
    @RequiresPermissions("param:amsBill:remove")
    @Log(title = "档案类型", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        AmsBill amsBill = new AmsBill();
        amsBill.setParentId(ids);

        List<AmsBill> amsBillList = amsBillService.selectAmsBillList(amsBill);

        if (amsBillList.isEmpty()) {
            return toAjax(amsBillService.deleteAmsBillByIds(ids));
        }

        return AjaxResult.error("error");
    }

    /**
     * 选择部门树
     */
    @GetMapping("/selectDeptTree/{deptId}")
    public String selectDeptTree(@PathVariable("deptId") Long deptId, ModelMap mmap) {
        SysDept dept = deptService.selectDeptById(deptId);
        if (dept != null) {
            mmap.put("dept", dept);
            return prefix + "/tree";
        } else {
            mmap.put("deptId", deptId);
            return prefix + "/tree";
        }
    }

    /**
     * 加载部门列表树
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData() {
        return deptService.selectDeptTree(new SysDept());
    }

    /**
     * 加载档案列表树
     */
    @GetMapping("/treeArchives/{pid}")
    @ResponseBody
    public List<Ztree> treeArchives(@PathVariable("pid") String pid) {
        AmsBill amsBill = new AmsBill();
        if (!"".equals(pid) && pid != null) {
            if (!"0".equals(pid)) {
                amsBill.setParentId(pid);
            } else {
                return amsBillService.selectAmsBillTree(amsBill);
            }
        }
        //清空ztree列表
        amsBillService.cleanZtreeList();
        return amsBillService.selectArchiveTree(amsBill);
    }

    /**
     * 查询档案子类型
     *
     * @param request
     * @return
     */
    @PostMapping("/queryChildBill")
    @ResponseBody
    public Map<String, List<AmsBill>> queryChildBill(HttpServletRequest request) {
        String billId = request.getParameter("billId");

        Map resultMap = new HashMap<String, List<AmsBill>>();

        List<AmsBill> childBillList = new ArrayList<>();
        AmsBill amsBill = this.amsBillService.selectAmsBillById(billId);

        childBillList.addAll(this.amsBillService.getChildBill(amsBill));
        resultMap.put("childBillList", childBillList);

        return resultMap;
    }

    /**
     * 新增档案子类型
     */
    @GetMapping("/child/add/{id}/{pname}")
    public String addChild(@PathVariable("id") String id, @PathVariable("pname") String pname, ModelMap mmap) {
        AmsBill amsBill = amsBillService.selectAmsBillById(id);
        mmap.put("amsBill", amsBill);
        mmap.put("pname", pname);

        String maxId = amsBillService.selectMaxId();
        Long nextId = Long.valueOf(maxId) + 1;
        mmap.put("nextId", nextId.toString());

        return prefix + "/addChild";
    }

    /**
     * 新增保存档案子类型
     */
    @RequiresPermissions("param:amsBill:child:add")
    @Log(title = "档案子类型", businessType = BusinessType.INSERT)
    @PostMapping("/child/add")
    @ResponseBody
    public AjaxResult addSaveChild(AmsBill amsBill) {
        //设置档案子类型ID
        amsBill.setParentId(amsBill.getParentId());
        amsBill.setId(amsBill.getCode());
        //设置档案子类型状态
        amsBill.setStatus("1");
        return toAjax(amsBillService.insertAmsBill(amsBill));
    }

    /**
     * 修改档案子类型
     */
    @GetMapping("/child/edit/{id}/{pname}")
    public String editChild(@PathVariable("id") String id, @PathVariable("pname") String pname, ModelMap mmap) {
        AmsBill amsBill = amsBillService.selectAmsBillById(id);
        mmap.put("amsBill", amsBill);
        mmap.put("pname", pname);
        return prefix + "/editChild";
    }

    /**
     * 修改保存档案子类型
     */
    @RequiresPermissions("param:amsBill:child:edit")
    @Log(title = "档案子类型", businessType = BusinessType.UPDATE)
    @PostMapping("/child/edit")
    @ResponseBody
    public AjaxResult editSaveChild(AmsBill amsBill) {
        return toAjax(amsBillService.updateAmsBill(amsBill));
    }

    /**
     * 删除档案子类型
     */
    @RequiresPermissions("param:amsBill:child:remove")
    @Log(title = "档案子类型", businessType = BusinessType.DELETE)
    @PostMapping("/child/remove")
    @ResponseBody
    public AjaxResult removeChild(String ids) {
        AmsBill amsBill = new AmsBill();
        amsBill.setParentId(ids);

        List<AmsBill> amsBillList = amsBillService.selectAmsBillList(amsBill);
        if (amsBillList.isEmpty()) {
            return toAjax(amsBillService.deleteAmsBillByIds(ids));
        }
        return AjaxResult.error("error");
    }

    /**
     * 档案子类型详情
     *
     * @param id
     * @param mmap
     * @return
     */
    @RequiresPermissions("param:amsBill:child:view")
    @GetMapping("/child/detail/{id}")
    public String detailChild(@PathVariable("id") String id, ModelMap mmap) {
        AmsBill amsBill = amsBillService.selectAmsBillById(id);
        mmap.put("amsBill", amsBill);
        return prefix + "/viewChild";
    }

    /**
     * 新增档案子类型
     */
    @GetMapping("/child/add/{id}")
    public String addChild(@PathVariable("id") String id, ModelMap mmap) {
        AmsBill amsBill = amsBillService.selectAmsBillById(id);
        mmap.put("amsBill", amsBill);

        String maxId = amsBillService.selectMaxId();
        Long nextId = Long.valueOf(maxId) + 1;
        mmap.put("nextId", nextId.toString());

        return prefix + "/addChild";
    }

    /**
     * 修改档案子类型
     */
    @GetMapping("/child/edit/{id}")
    public String editChild(@PathVariable("id") String id, ModelMap mmap) {
        AmsBill amsBill = amsBillService.selectAmsBillById(id);
        mmap.put("amsBill", amsBill);

        return prefix + "/editChild";
    }

    /**
     * 限制检索类型
     *
     * @param request
     * @return
     */
    @RequiresPermissions("param:amsBill:child:addSmSecret")
    @PostMapping("/child/addSmSecret")
    @ResponseBody
    public AjaxResult addSmSecret(HttpServletRequest request) {
        String arcBillCodeReq = request.getParameter("arcBillCodeReq");
        String arcBillDeptCodeReq = request.getParameter("arcBillDeptCodeReq");

        String arcBillName = "";
        String arcBillDeptName = "";

        //第一步，确认是否是只有一级类目的形式
        if (null != arcBillCodeReq && !"".equals(arcBillCodeReq) && (arcBillDeptCodeReq == null || arcBillDeptCodeReq.equals(""))) {
            AmsBill amsBill = new AmsBill();
            amsBill.setParentId(arcBillCodeReq);

            //第二步，找出其二级类目的名字和code
            List<AmsBill> listRawAllDeptNameCodeByParentId = amsBillService.selectAmsBillList(amsBill);
            if (listRawAllDeptNameCodeByParentId == null || listRawAllDeptNameCodeByParentId.isEmpty()) {
                return AjaxResult.warn("限制检索类型错误或为空！");
            }
            List<Map<String, String>> listAllDeptNameCodeByParentId = splitRawIntoElement(listRawAllDeptNameCodeByParentId);
            for (int i = 0; i < listAllDeptNameCodeByParentId.size(); i++) {
                String smSecretId = UUID.randomUUID().toString().replace("-", "");
                String code = listAllDeptNameCodeByParentId.get(i).get("code");
                SmSecret smSecret = new SmSecret();
                smSecret.setArcBillDeptCode(code);
                Integer count = smSecretService.selectSmSecretList(smSecret).size();
                //排除已存在的二级类目
                if (count == 0) {
                    if (arcBillCodeReq != null && !arcBillCodeReq.equals("")) {
                        arcBillName = amsBillService.queryForName(arcBillCodeReq);
                    }

                    arcBillDeptName = listAllDeptNameCodeByParentId.get(i).get("name");
                    arcBillDeptCodeReq = listAllDeptNameCodeByParentId.get(i).get("code");

                    SmSecret newObj = new SmSecret();
                    newObj.setId(smSecretId);
                    newObj.setArcBill(arcBillName);
                    newObj.setArcBillCode(arcBillCodeReq);
                    newObj.setArcBillDeptCode(arcBillDeptCodeReq);
                    newObj.setArcBillDept(arcBillDeptName);
                    smSecretService.insertSmSecret(newObj);
                } else {
                    return AjaxResult.warn("已加入此限制检索类型，请选择其他类型继续操作");
                }
            }

        } else {
            String smSecretId = UUID.randomUUID().toString().replace("-", "");
            //查询该二级类目是否在表中存在
            SmSecret obj = new SmSecret();
            obj.setArcBillDeptCode(arcBillDeptCodeReq);
            Integer count = smSecretService.selectSmSecretList(obj).size();

            if (count == 0) {
                if (!"".equals(arcBillCodeReq)) {
                    arcBillName = amsBillService.queryForName(arcBillCodeReq);
                }

                if (arcBillDeptCodeReq != null && !arcBillDeptCodeReq.equals("")) {
                    arcBillDeptName = amsBillService.queryForName(arcBillDeptCodeReq);
                }

                SmSecret smSecret = new SmSecret();
                smSecret.setId(smSecretId);
                smSecret.setArcBill(arcBillName);
                smSecret.setArcBillCode(arcBillCodeReq);
                smSecret.setArcBillDeptCode(arcBillDeptCodeReq);
                smSecret.setArcBillDept(arcBillDeptName);
                smSecretService.insertSmSecret(smSecret);
            } else {
                return AjaxResult.warn("已加入此限制检索类型，请选择其他类型继续操作");
            }
        }

        return AjaxResult.success();
    }

    private List<Map<String, String>> splitRawIntoElement(List<AmsBill> rawReturn) {
        String name = "";
        String code = "";
        List<Map<String, String>> listSplitRawIntoElement = new ArrayList<>();

        for (int i = 0; i < rawReturn.size(); i++) {
            Map<String, String> map = new HashMap<>();
            name = rawReturn.get(i).getName();
            code = rawReturn.get(i).getCode();
            map.put("code", code);
            map.put("name", name);
            listSplitRawIntoElement.add(map);
        }
        return listSplitRawIntoElement;
    }

    /**
     * 查看限制档案类型
     */
    @GetMapping("/toSmSecret")
    public String toSmSecret() {
        return prefix + "/secretWatch";
    }

    /**
     * 查询限制档案类型List
     */
    @RequiresPermissions("param:amsBill:list:smSecret")
    @PostMapping("/list/smSecret")
    @ResponseBody
    public TableDataInfo smSecretList(SmSecret smSecret) {

        List<SmSecret> list = smSecretService.selectSmSecretList(smSecret);
        List<Map<String, String>> rtnList = new ArrayList<Map<String, String>>();
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, String> map = new HashMap<>();
                map.put("id", list.get(i).getId());
                map.put("name", (list.get(i).getArcBillCode() + "-" + list.get(i).getArcBill()));
                map.put("code", (list.get(i).getArcBillDeptCode() + "-" + list.get(i).getArcBillDept()));
                rtnList.add(map);
            }
        }
        return getDataTable(rtnList);
    }

    @PostMapping("/child/delsmSecret")
    @ResponseBody
    public AjaxResult removesmSecret(String ids) {
        return toAjax(smSecretService.deleteSmSecretByIds(ids));
    }

    /**
     * 部门档案类型配置
     *
     * @param request
     * @return
     */
    @PostMapping("/updateForBill")
    @ResponseBody
    public AjaxResult updateForBill(HttpServletRequest request) {
        String organCode = request.getParameter("organCode");
        String billCodes = request.getParameter("billCodes");
        amsBillDeptService.deleteAmsBillDeptBySysId(organCode);
        for (String billCode : billCodes.split(",")) {
            AmsBill amsBill = new AmsBill();
            amsBill.setCode(billCode);
            List<AmsBill> amsList = amsBillService.selectAmsBillList(amsBill);
            if (!amsList.isEmpty()) {
                if (amsList.get(0).getStatus().equals("0")) {
                    AmsBill amsBills = amsList.get(0);
                    amsBills.setStatus("1");
                    amsBillService.updateAmsBill(amsBills);
                }
                AmsBillDept amsBillDept = new AmsBillDept();
                amsBillDept.setId(UUID.randomUUID().toString().replaceAll("-", ""));
                amsBillDept.setCode(amsList.get(0).getCode());
                amsBillDept.setSysId(organCode);
                amsBillDept.setName(amsList.get(0).getName());
                amsBillDeptService.insertAmsBillDept(amsBillDept);
            } else {
                throw new NullPointerException("档案类型不存在");
            }
        }

        return AjaxResult.success("操作成功");
    }

    /**
     * 设计分类模板
     *
     * @return
     */
    @RequiresPermissions("param:amsBill:design")
    @GetMapping("/designAmsBill/{id}")
    public String designAmsBill(@PathVariable("id") String id, ModelMap mmap) {
        AmsBill amsBill = amsBillService.selectAmsBillById(id);
        mmap.put("amsBill", amsBill);
        return prefix + "/designAmsBill";
    }

    /**
     * 添加分类模板字符串
     *
     * @return
     */
    @PostMapping("/designAmsBill")
    @ResponseBody
    public AjaxResult designAmsBillStr(AmsBill amsBill, ModelMap mmap) {
        return toAjax(amsBillService.updateAmsBill(amsBill));
    }

    /**
     * 获取分类模板字符串
     *
     * @return
     */
    @GetMapping("/getAmsBillMould/{id}")
    @ResponseBody
    public Map<String, Object> getAmsBillMould(@PathVariable("id") String id, ModelMap mmap) {
        AmsBill amsBill = amsBillService.selectAmsBillById(id);
        String mould = "";
        Map<String, Object> map = new HashMap<>();
        if (amsBill != null) {
            mould = amsBill.getMouldStr();
            map.put("mould", mould);
        }
        return map;
    }

    @GetMapping("/subBillTree/{id}")
    public String subBillTree(@PathVariable("id") String pid, ModelMap mmap) {
        mmap.put("pid", pid);
        return prefix + "/subBillTree";
    }
}
