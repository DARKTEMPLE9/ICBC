package net.northking.iacmp.ams.web.controller.amsarcreport;


import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.common.bean.domain.ams.AmsBatch;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.enums.BusinessType;
import net.northking.iacmp.server.service.IAmsBatchService;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.regex.Pattern;

/**
 * 档案著录 信息操作处理
 *
 * @author wxy
 * @date 2019-08-02
 */
@Controller
@RequestMapping("/amsArcReportcontroller/amsBatch")
@Data
@EqualsAndHashCode(callSuper = true)
public class AmsBatchController extends BaseController {
    private String prefix = "amsArcReportcontroller/amsBatch";
    @Autowired
    private IAmsBatchService amsBatchService;
    private List deptList;


    @RequiresPermissions("amsArcReportcontroller:amsBatch:view")
    @GetMapping()
    public String amsBatch() {
        return prefix + "/amsBatch";
    }

    /**
     * 查询档案著录列表
     */
    @RequiresPermissions("amsArcReportcontroller:amsBatch:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AmsBatch amsBatch) {
        startPage();
        List<AmsBatch> list = amsBatchService.selectAmsBatchList(amsBatch);
        return getDataTable(list);
    }

    @RequiresPermissions("amsArcReportcontroller:retrievalReport:view")
    @GetMapping("/prequeryArcBillByDept")
    public String prequeryResultForm() {
        return prefix + "/retrievalReport";
    }

    /**
     * 查询档案统计
     */
    @PostMapping("/queryArcBillByDept")
    @ResponseBody
    public Map queryArcBillByDept() {

        Map map = new HashMap();
        List<List<String>> returnList = amsBatchService.queryResultByDeptName();
        List<String> newReturnList = new ArrayList();
        for (int i = 0; i < returnList.size(); i++) {
            for (int j = 0; j < returnList.get(i).size(); j++) {
                String queryOutput = returnList.get(i).get(j);
                String subQueryOutput = queryOutput.substring(1, queryOutput.length() - 1);
                newReturnList.add(subQueryOutput);

            }
        }
        List<String> deptShowList = new LinkedList();
        List<List<String>> recordList = new LinkedList();
        List<List<String>> recordValueList = new LinkedList();

        for (String str : newReturnList) {
            String[] returnListArr = str.split(",");
            List<String> strList = new LinkedList();
            List<String> valueList = new LinkedList();
            for (int i = 1; i < returnListArr.length; i++) {
                returnListArr[i] = returnListArr[i].replaceAll(" +", "");
                if (Pattern.matches("^\\d+$", returnListArr[i])) {
                    valueList.add(returnListArr[i]);
                } else {
                    strList.add(returnListArr[i]);
                }
            }
            deptShowList.add(returnListArr[0]);
            recordList.add(strList);
            recordValueList.add(valueList);

        }

        List<String> allDept;
        allDept = amsBatchService.queryAllDept();
        map.put("allDept", allDept);
        map.put("deptShowList", deptShowList);
        map.put("recordList", recordList);
        map.put("recordValueList", recordValueList);
        return map;


    }

    /**
     * 导出档案著录列表
     */
    @RequiresPermissions("amsArcReportcontroller:amsBatch:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AmsBatch amsBatch) {
        List<AmsBatch> list = amsBatchService.selectAmsBatchList(amsBatch);
        ExcelUtil<AmsBatch> util = new ExcelUtil<>(AmsBatch.class);
        return util.exportExcel(list, "amsBatch");
    }

    /**
     * 新增档案著录
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存档案著录
     */
    @RequiresPermissions("amsArcReportcontroller:amsBatch:add")
    @Log(title = "档案著录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AmsBatch amsBatch) {
        return toAjax(amsBatchService.insertAmsBatch(amsBatch));
    }

    /**
     * 修改档案著录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        AmsBatch amsBatch = amsBatchService.selectAmsBatchById(id);
        mmap.put("amsBatch", amsBatch);
        return prefix + "/edit";
    }

    /**
     * 修改保存档案著录
     */
    @RequiresPermissions("amsArcReportcontroller:amsBatch:edit")
    @Log(title = "档案著录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AmsBatch amsBatch) {
        return toAjax(amsBatchService.updateAmsBatch(amsBatch));
    }

    /**
     * 删除档案著录
     */
    @RequiresPermissions("amsArcReportcontroller:amsBatch:remove")
    @Log(title = "档案著录", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(amsBatchService.deleteAmsBatchByIds(ids));
    }

}
