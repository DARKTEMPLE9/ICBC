package net.northking.iacmp.elasticsearch.service;

import net.northking.iacmp.elasticsearch.domain.ProjectInfoType;

public interface IProjectInfoEsService {
    /**
     * 新增项目信息
     */
    ProjectInfoType insertProjectInfo(ProjectInfoType projectInfoType);

    /**
     * 根据全局流水号查询项目信息
     */
    ProjectInfoType findByTransactionCode(String transactionCode);

    /**
     * 更新项目信息
     */
    ProjectInfoType updateProject(ProjectInfoType projectInfoType);

    /**
     * 根据全局流水号删除项目信息
     */
    int deleteByTransactionCode(String transactionCode);
}
