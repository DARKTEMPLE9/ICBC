package net.northking.iacmp.ams.web.controller.wfagent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.northking.iacmp.annotation.Log;
import net.northking.iacmp.common.bean.domain.ams.AmsAgent;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.framework.util.ShiroUtils;
import net.northking.iacmp.server.service.IAmsAgentService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import net.northking.iacmp.server.service.IWfAgentService;
import net.northking.iacmp.common.bean.domain.ams.WfAgent;
import net.northking.iacmp.enums.BusinessType;

@Controller
@RequestMapping("/wfAgent")
public class WfAgentController extends BaseController {
    private String prefix = "wfAgent";

    @Autowired
    private IAmsAgentService amsAgentService;

    @RequiresPermissions("wfAgent:view")
    @GetMapping()
    public String wfAgent() {
        return prefix + "/view";
    }

    /**
     * 查询流程代理列表
     */
//    @RequiresPermissions("wfAgent:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AmsAgent wfAgent) {
        List<AmsAgent> list = amsAgentService.selectAmsAgentList(wfAgent);
        //已结束或代理中，修改状态
        for (AmsAgent amsAgent : list) {
            //代理开始日期
            Date startDate = amsAgent.getAgentStartDate();
            //代理结束日期
            Date endDate = amsAgent.getAgentEndDate();
            //代理权限在当日24点[即次日0点]结束
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(endDate);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            //当前日期
            Date now = new Date();
            if (now.getTime() >= startDate.getTime()) {
                String status = amsAgent.getAgentStatus();//代理状态
                //开始代理
                if (now.getTime() < calendar.getTimeInMillis()) {//代理中
                    if (status != null) {
                        if (!"1".equals(status)) {//根据时间判断已开始代理中，但是状态未更改
                            amsAgent.setAgentStatus("1");
                            amsAgentService.updateAmsAgent(amsAgent);
                        }
                    }
                } else {//代理结束
                    if (status != null) {
                        if (!"0".equals(status)) {//根据时间判断代理已结束，但是状态未更改
                            amsAgent.setAgentStatus("0");
                            amsAgentService.updateAmsAgent(amsAgent);
                        }
                    }
                }
            }
        }
        startPage();
        List<AmsAgent> resultList = amsAgentService.selectAmsAgentList(wfAgent);//展示列表
        return getDataTable(resultList);
    }

    /**
     * 新增流程代理
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存流程代理
     */
//    @RequiresPermissions("wfAgent:add")
    @Log(title = "流程代理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AmsAgent wfAgent) {
        //代理开始时间
        Date startDate = wfAgent.getAgentStartDate();
        //代理结束日期
        Date endDate = wfAgent.getAgentEndDate();
        //代理权限在当日24点[即次日0点]结束
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endDate);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        //当前日期
        Date now = new Date();
        if (now.getTime() < startDate.getTime()) {//当前时间小于代理开始时间（代理未开始)
            wfAgent.setAgentStatus("2");
        }
        return toAjax(amsAgentService.insertAmsAgent(wfAgent));
    }

    /**
     * 编辑流程代理
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable String id, ModelMap mmap) {
        AmsAgent wfAgent = amsAgentService.selectAmsAgentById(id);
        mmap.put("wfAgent", wfAgent);
        return prefix + "/edit";
    }

    /**
     * 编辑保存流程代理
     */
//    @RequiresPermissions("wfAgent:edit")
    @Log(title = "流程代理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult update(AmsAgent wfAgent) {
        return toAjax(amsAgentService.updateAmsAgent(wfAgent));
    }

    /**
     * 删除流程代理
     */
//    @RequiresPermissions("wfAgent:remove")
    @Log(title = "流程代理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(amsAgentService.deleteAmsAgentByIds(ids));
    }

    /**
     * 跳转选择用户页面
     *
     * @return
     */
    @GetMapping("/selectUser")
    public String selectUser(ModelMap mmap) {
        mmap.put("userId", ShiroUtils.getUserId());
        return prefix + "/selectUser";
    }

    /**
     * 详细页面
     *
     * @param id
     * @param mmap
     * @return
     */
    @GetMapping("/detail/{id}")
    public String agentDetail(@PathVariable("id") String id, ModelMap mmap) {
        AmsAgent amsAgent = amsAgentService.selectAmsAgentById(id);
        mmap.put("amsAgent", amsAgent);
        return prefix + "/detail";
    }

    /**
     * 流程代理重复添加操作校验
     *
     * @param wfAgent
     * @return
     */
    @PostMapping("/validate")
    @ResponseBody
    public AjaxResult validateAmsAgent(AmsAgent wfAgent) {
        List<AmsAgent> list = amsAgentService.selectAmsAgentList(wfAgent);
        Integer num = list.size();
        return AjaxResult.success("查询结果", num);
    }
}
