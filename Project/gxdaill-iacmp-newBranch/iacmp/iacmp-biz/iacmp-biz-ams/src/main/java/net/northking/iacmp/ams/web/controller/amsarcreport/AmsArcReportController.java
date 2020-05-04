package net.northking.iacmp.ams.web.controller.amsarcreport;


import lombok.Data;
import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.common.bean.domain.ams.AmsBill;
import net.northking.iacmp.common.bean.vo.ams.AmsReportVO;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.server.service.IAmsBillService;
import net.northking.iacmp.utils.MyExcelUtil;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 档案类型 信息操作处理
 *
 * @author wxy
 * @date 2019-08-05
 */
@Controller
@RequestMapping("/amsArcReportcontroller/amsBill")
@Data
public class AmsArcReportController extends BaseController {
    private static final String ARC_BILL_CODE_SEARCH = "arcBillCodeSearch";
    private static final String ARC_BILL_CODE_SEARCH1 = ARC_BILL_CODE_SEARCH;
    private static final String ARC_BILL_NAME_SEARCH = "arcBillNameSearch";
    private static final String ORG_CODE_SEARCH = "orgCodeSearch";
    private static final String ORG_NAME_SEARCH = "orgNameSearch";
    private static final String FILLING_TIME_LT = "fillingTime_lt";
    private static final String FILLING_TIME_GT = "fillingTime_gt";

    private String prefix = "amsArcReportcontroller/amsBill";

    @Autowired
    private IAmsBillService amsBillService;


    /**
     * 导出Excel数据
     */
    List rows;

    @RequiresPermissions("amsArcReportcontroller:amsBill:view")
    @GetMapping()
    public String amsBill() {
        return prefix + "/amsBill";
    }

    /**
     * 调转到显示部门树页面
     *
     * @return
     */
    @GetMapping("/deptTree")
    public String deptTree() {
        return prefix + "/tree";
    }

    /**
     * 调转到显示档案类型树页面
     *
     * @return
     */
    @GetMapping("/arcBillTree")
    public String arcBillTree() {
        return prefix + "/arcBillTree";
    }

    /**
     * 查询档案类型列表
     */
    @RequiresPermissions("amsArcReportcontroller:amsBill:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AmsBill amsBill) {
        startPage();
        List<AmsBill> list = amsBillService.selectAmsBillList(amsBill);
        return getDataTable(list);
    }

    @RequiresPermissions("amsArcReportcontroller:totalReport:view")
    @GetMapping("/prequeryResultForm")
    public String prequeryResultForm() {
        return prefix + "/totalReport";
    }

    /**
     * 查询档案总量统计
     */
    @PostMapping("/queryResultFormTable")
    @ResponseBody
    public Map queryResultFormTable(AmsReportVO amsReportVO) {
        String fillingTimeGt = amsReportVO.getFillingTimeGt();
        String fillingTimeLt = amsReportVO.getFillingTimeLt();
        String orgNameSearch = amsReportVO.getOrgName();
        String orgCodeSearch = amsReportVO.getOrgCode();
        String arcBillNameSearch = amsReportVO.getArcBill();
        String arcBillCodeSearch = amsReportVO.getArcBillCode();
        List resultList = new LinkedList<>();
        List<String> arcBillCodeSortList = new ArrayList<>();
        Map map = new HashMap();

//        判断并拼listNameCodeOrgan
        List<String> listNameCodeOrgan;
        if (!"".equals(orgCodeSearch) && orgCodeSearch != null) {
            orgCodeSearch = orgCodeSearch.replaceAll(" ", "");
            if (orgNameSearch != null && !"".equals(orgNameSearch)) {
                orgNameSearch = orgNameSearch.replaceAll(" ", "");
            }
            String temp = "[" + orgNameSearch + ", " + orgCodeSearch + "]";

            listNameCodeOrgan = new LinkedList<>();
            listNameCodeOrgan.add(temp);

        } else {
            //查询所有部门的名字和code
            listNameCodeOrgan = amsBillService.queryOrganNameAndCode();
        }


        //查询所有档案类型的名字和code
        List<String> listArcBillandCode;
        if (!"".equals(arcBillCodeSearch) && arcBillCodeSearch != null) {
            arcBillCodeSearch = arcBillCodeSearch.replaceAll(" ", "");
            String temp = "[" + arcBillCodeSearch + ", " + arcBillNameSearch + "]";

            listArcBillandCode = new LinkedList<>();
            listArcBillandCode.add(temp);
        } else {
            listArcBillandCode = amsBillService.queryArcBillAndCode();
        }


        List resultListByOneOrgan;
        //全部部门名称
        List<String> orgNameList = new ArrayList<>();
        List orgCodeList = new ArrayList();//全部部门
        for (int i = 0; i < listNameCodeOrgan.size(); i++) {
            //保存所有档案类型的数组
            String[] organArr = listNameCodeOrgan.get(i).substring(1, listNameCodeOrgan.get(i).length() - 1).split(",");
            organArr[1] = organArr[1].replaceAll(" ", "");
            orgCodeList.add(organArr[1]);
            orgNameList.add(organArr[0]);
        }
        for (int j = 0; j < listArcBillandCode.size(); j++) {
            arcBillCodeSortList.clear();
            resultListByOneOrgan = new ArrayList<>();
            Map<String, Integer> mapOrgArcBillCount = new HashMap<>();
            //查询该档案类型下的全部子目录
            String[] arcBillCodeArr = listArcBillandCode.get(j).substring(1, listArcBillandCode.get(j).length() - 1).split(",");
            arcBillCodeArr[0] = arcBillCodeArr[0].replaceAll(" ", "");
            resultListByOneOrgan.add(arcBillCodeArr[0]);
            resultListByOneOrgan.add(arcBillCodeArr[1]);
            List<String> treeNodeList = amsBillService.allSonTreeNode(arcBillCodeArr[0]);
            //查询该类型下各个部门档案数量（包含子目录档案）
            List<String> orgArcBillCountList = amsBillService.queryNumberArcByOneOrgan(fillingTimeGt, fillingTimeLt, treeNodeList, arcBillCodeArr[0], orgCodeList);
//                mapArcBillCodeToNumber.put(arcBillCodeArr[0],count);
            //各部门档案的各个档案类型默认数量0
            for (int i = 0; i < listNameCodeOrgan.size(); i++) {
                int count = 0;
                //保存所有档案类型的数组
                String[] organArr = listNameCodeOrgan.get(i).substring(1, listNameCodeOrgan.get(i).length() - 1).split(",");
                organArr[0] = organArr[0].replaceAll(" ", "");
                organArr[1] = organArr[1].replaceAll(" ", "");
                mapOrgArcBillCount.put(organArr[1], count);
                //正确的arcBillCode的顺序
                arcBillCodeSortList.add(organArr[1]);
            }
            for (int i = 0; i < orgArcBillCountList.size(); i++) {
                String[] orgArcBillArr = orgArcBillCountList.get(i).substring(1, orgArcBillCountList.get(i).length() - 1).split(",");
                orgArcBillArr[0] = orgArcBillArr[0].replaceAll(" ", "");
                orgArcBillArr[1] = orgArcBillArr[1].replaceAll(" ", "");
                mapOrgArcBillCount.put(orgArcBillArr[1], Integer.parseInt(orgArcBillArr[0]));
            }
            resultListByOneOrgan.add(mapOrgArcBillCount);
            resultList.add(resultListByOneOrgan);
        }

        map.put("listArcBillandCode", listArcBillandCode);
        map.put("arcBillCodeSearch", arcBillCodeSearch);
        map.put("arcBillCodeSortList", arcBillCodeSortList);
        map.put("arcBillNameSearch", arcBillNameSearch);
        map.put("fillingTime_gt", fillingTimeGt);
        map.put("fillingTime_lt", fillingTimeLt);
        map.put("orgCodeSearch", orgCodeSearch);
        map.put("orgNameSearch", orgNameSearch);
        map.put("orgNameList", orgNameList);
        map.put("resultList", resultList);

        return map;
    }


    /**
     * 导出档案类型列表
     */
    @RequiresPermissions("amsArcReportcontroller:amsBill:export")
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
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存档案类型
     */
    @RequiresPermissions("amsArcReportcontroller:amsBill:add")
    @Log(title = "档案类型", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AmsBill amsBill) {
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
    @RequiresPermissions("amsArcReportcontroller:amsBill:edit")
    @Log(title = "档案类型", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AmsBill amsBill) {
        return toAjax(amsBillService.updateAmsBill(amsBill));
    }

    /**
     * 删除档案类型
     */
    @RequiresPermissions("amsArcReportcontroller:amsBill:remove")
    @Log(title = "档案类型", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(amsBillService.deleteAmsBillByIds(ids));
    }


    /**
     * 查询二级档案类型
     */
    @PostMapping("/queryArcBillDept")
    @ResponseBody
    public Map queryArcBillDept(AmsReportVO amsReportVO) {
        String fillingTimeGt = amsReportVO.getFillingTimeGt();
        String fillingTimeLt = amsReportVO.getFillingTimeLt();
        String orgNameSearch = amsReportVO.getOrgName();
        String orgCodeSearch = amsReportVO.getOrgCode();
        String arcBillNameSearch = amsReportVO.getArcBill();
        String arcBillCodeSearch = amsReportVO.getSelectArcCode();
        List resultList = new LinkedList<>();
        List<String> arcBillCodeSortList = new ArrayList<>();
        Map map = new HashMap();//key:部门map（部门名，部门编号） value:档案类型map(【档案类型，类型编码】，总量)
        arcBillCodeSearch = arcBillCodeSearch.replaceAll(" ", "");
//        判断并拼listNameCodeOrgan
        List<String> listNameCodeOrgan;
        if (!"".equals(orgCodeSearch) && orgCodeSearch != null) {
            orgCodeSearch = orgCodeSearch.replaceAll(" ", "");
            if (orgNameSearch != null && !"".equals(orgNameSearch)) {
                orgNameSearch = orgNameSearch.replaceAll(" ", "");
            }
            String temp = "[" + orgNameSearch + ", " + orgCodeSearch + "]";
            listNameCodeOrgan = new LinkedList<>();
            listNameCodeOrgan.add(temp);
        } else {
            //查询所有部门的名字和code
            listNameCodeOrgan = amsBillService.queryOrganNameAndCode();
        }
        //查询当前档案类型下全部二级类型
        List<String> listArcBillandCode = amsBillService.queryArcBillDept(arcBillCodeSearch);
        List resultListByOneOrgan;
        //全部部门名称
        List<String> orgNameList = new ArrayList<>();
        List orgCodeList = new ArrayList();//全部部门
        for (int i = 0; i < listNameCodeOrgan.size(); i++) {
            //保存所有档案类型的数组
            String[] organArr = listNameCodeOrgan.get(i).substring(1, listNameCodeOrgan.get(i).length() - 1).split(",");
            organArr[1] = organArr[1].replaceAll(" ", "");
            orgCodeList.add(organArr[1]);
            orgNameList.add(organArr[0]);
        }
        for (int j = 0; j < listArcBillandCode.size(); j++) {
            Map<String, Integer> mapOrgArcBillCount = new HashMap<>();
            resultListByOneOrgan = new ArrayList();
            String[] arcBillCodeArr = listArcBillandCode.get(j).substring(1, listArcBillandCode.get(j).length() - 1).split(",");
            String arcBillCode = "";
            if (arcBillCodeArr.length > 2) {//有子目录
                arcBillCode = arcBillCodeArr[1];
            } else {
                arcBillCode = arcBillCodeArr[0];
            }
            resultListByOneOrgan.add(arcBillCode);
            resultListByOneOrgan.add(arcBillCodeArr[arcBillCodeArr.length - 1]);
            arcBillCode = arcBillCode.replaceAll(" ", "");
            List<String> treeList = amsBillService.allSonTreeNode(arcBillCode);
            //返回按照档案类型分类的档案数量
            List<String> orgArcBillCountList = amsBillService.queryNumberArcBySecondOrgan(fillingTimeGt, fillingTimeLt, treeList, orgCodeList);
            //各部门档案的各个档案类型默认数量0
            for (int i = 0; i < listNameCodeOrgan.size(); i++) {
                int count = 0;
                //保存所有档案类型的数组
                String[] organArr = listNameCodeOrgan.get(i).substring(1, listNameCodeOrgan.get(i).length() - 1).split(",");
                organArr[0] = organArr[0].replaceAll(" ", "");
                organArr[1] = organArr[1].replaceAll(" ", "");
                mapOrgArcBillCount.put(organArr[1], count);
                //正确的arcBillCode的顺序
                arcBillCodeSortList.add(organArr[1]);
            }
            for (int i = 0; i < orgArcBillCountList.size(); i++) {
                String[] orgArcBillArr = orgArcBillCountList.get(i).substring(1, orgArcBillCountList.get(i).length() - 1).split(",");
                orgArcBillArr[0] = orgArcBillArr[0].replaceAll(" ", "");
                orgArcBillArr[1] = orgArcBillArr[1].replaceAll(" ", "");
                mapOrgArcBillCount.put(orgArcBillArr[1], Integer.parseInt(orgArcBillArr[0]));
            }
            resultListByOneOrgan.add(mapOrgArcBillCount);
            resultList.add(resultListByOneOrgan);
        }

        map.put("listArcBillandCode", listArcBillandCode);
        map.put("arcBillCodeSearch", arcBillCodeSearch);
        map.put("arcBillCodeSortList", arcBillCodeSortList);
        map.put("arcBillNameSearch", arcBillNameSearch);
        map.put("fillingTime_gt", fillingTimeGt);
        map.put("fillingTime_lt", fillingTimeLt);
        map.put("orgCodeSearch", orgCodeSearch);
        map.put("orgNameSearch", orgNameSearch);
        map.put("resultList", resultList);
        map.put("orgNameList", orgNameList);
        return map;
    }

    /**
     * 部门档案统计
     */
    @PostMapping("/countByDept")
    @ResponseBody
    public Map countByDept() {
        return amsBillService.countByDept();
    }

    /**
     * 各类型档案统计
     */
    @PostMapping("/countByArcType")
    @ResponseBody
    public Map countByArcType() {
        List<Map<String, Object>> list = amsBillService.countByArcType();
        Map<String, Object> mmap = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        List codes = new ArrayList();
        for (Map<String, Object> m : list) {
            Collection col = m.values();
            Object[] values = col.toArray();
            if (values.length > 1 && values[1] != null) {
                String key = (String) values[1];
                codes.add(key);
                Object value = values[0];
                map.put(key, value);
            }


        }
        List<String> listArcBillandCode = amsBillService.queryArcBillAndCode();//查询全部档案
        for (int j = 0; j < listArcBillandCode.size(); j++) {
            String[] arcBillCodeArr = listArcBillandCode.get(j).substring(1, listArcBillandCode.get(j).length() - 1).split(",");
            mmap.put(arcBillCodeArr[1], 0);
        }
        for (Object code : codes) {
            for (int j = 0; j < listArcBillandCode.size(); j++) {
                String[] arcBillCodeArr = listArcBillandCode.get(j).substring(1, listArcBillandCode.get(j).length() - 1).split(",");

                if (code.equals(arcBillCodeArr[0])) {
                    mmap.put(arcBillCodeArr[1], map.get(code));
                }

            }
        }


        return mmap;
    }

    /**
     * 导出档案总量统计表
     */
    @PostMapping("/exportExcel")
    @ResponseBody
    public AjaxResult exportExcel(@RequestBody Map<String, Object> rowsList) {
        rows = (List) rowsList.get("rowsList");
        return toAjax(1);
    }

    @RequestMapping("/startExport")
    public void startExport(HttpServletResponse response) {
        if (rows == null) {
            return;
        }
        MyExcelUtil myExcelUtil = new MyExcelUtil();
        String title = "档案总量统计表";
        HSSFWorkbook workbook = myExcelUtil.getHSSFWorkbook(rows, title);
        String fileName = "档案总量统计表.xls";
        myExcelUtil.exportExc(response, workbook, fileName);
    }
}
