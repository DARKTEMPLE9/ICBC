package net.northking.iacmp.elasticsearch.repository;

import net.northking.iacmp.elasticsearch.domain.ProjectInfoType;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface IProjectInfoRepository extends ElasticsearchRepository<ProjectInfoType, Long> {
    /**
     * 根据全局流水号查询项目信息
     */
    ProjectInfoType findByTransactionCode(String transactionCode);

    /**
     * 根据全局流水号删除项目信息
     */
    int deleteByTransactionCode(String transactionCode);
}
