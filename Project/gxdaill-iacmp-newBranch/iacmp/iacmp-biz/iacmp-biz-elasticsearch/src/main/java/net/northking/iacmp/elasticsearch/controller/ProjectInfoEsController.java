package net.northking.iacmp.elasticsearch.controller;

import net.northking.iacmp.elasticsearch.domain.ProjectInfoType;
import net.northking.iacmp.elasticsearch.service.IProjectInfoEsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/es/projectInfoEs")
public class ProjectInfoEsController {
    @Autowired
    private IProjectInfoEsService projectInfoEsService;

    /**
     * 新增项目信息
     */
    @GetMapping("/add")
    @ResponseBody
    public ProjectInfoType insertProjectInfo() {
        for (int i = 1; i <= 50; i++) {
            ProjectInfoType projectInfoType = new ProjectInfoType();
            projectInfoType.setId(Long.valueOf(i));
            projectInfoType.setTransactionCode(Integer.toString(i));
            projectInfoEsService.insertProjectInfo(projectInfoType);
        }
        return null;
    }

    /**
     * 根据全局流水号查询项目信息
     */
    @GetMapping("/findByTransactionCode")
    @ResponseBody
    public ProjectInfoType findByTransactionCode() {
        String transactionCode = "11";
        ProjectInfoType projectInfoType = projectInfoEsService.findByTransactionCode(transactionCode);
        System.out.println(projectInfoType);
        return null;
    }

    /**
     * 更新项目信息
     */
    @GetMapping("/update")
    @ResponseBody
    public ProjectInfoType updateProjectInfo() {
        String transactionCode = "11";
        ProjectInfoType projectInfoType = projectInfoEsService.findByTransactionCode(transactionCode);
        if (projectInfoType != null) {
            projectInfoType.setBudgetId("消费金融事业部");
        }
        ProjectInfoType project = projectInfoEsService.updateProject(projectInfoType);
        System.out.println(project);
        return null;
    }

    /**
     * 根据全局流水号删除项目信息
     */
    @GetMapping("/deleteByTransactionCode")
    @ResponseBody
    public int deleteByTransactionCode() {
        String transactionCode = "11";
        return projectInfoEsService.deleteByTransactionCode(transactionCode);
    }
}
