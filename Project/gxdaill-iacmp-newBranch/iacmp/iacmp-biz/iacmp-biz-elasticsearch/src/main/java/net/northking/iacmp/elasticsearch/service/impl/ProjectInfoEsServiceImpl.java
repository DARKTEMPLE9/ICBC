package net.northking.iacmp.elasticsearch.service.impl;

import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.elasticsearch.domain.ProjectInfoType;
import net.northking.iacmp.elasticsearch.repository.IProjectInfoRepository;
import net.northking.iacmp.elasticsearch.service.IProjectInfoEsService;
import net.northking.iacmp.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@DataSource(value = DataSourceType.SLAVE)
public class ProjectInfoEsServiceImpl implements IProjectInfoEsService {
    @Autowired
    private IProjectInfoRepository projectInfoRepository;

    /**
     * 新增项目信息
     */
    @Override
    public ProjectInfoType insertProjectInfo(ProjectInfoType projectInfoType) {
        return projectInfoRepository.save(projectInfoType);
    }

    /**
     * 根据全局流水号查询项目信息
     */
    @Override
    public ProjectInfoType findByTransactionCode(String transactionCode) {
        return projectInfoRepository.findByTransactionCode(transactionCode);
    }

    /**
     * 更新项目信息
     */
    @Override
    public ProjectInfoType updateProject(ProjectInfoType projectInfoType) {
        return projectInfoRepository.save(projectInfoType);
    }

    /**
     * 根据全局流水号删除项目信息
     */
    @Override
    public int deleteByTransactionCode(String transactionCode) {
        return projectInfoRepository.deleteByTransactionCode(transactionCode);
    }
}
