package net.northking.iacmp.elasticsearch.repository;

import net.northking.iacmp.elasticsearch.domain.CmsBatchType;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Author：Yanqingyu
 * @ClassName:CmsBatchSearchRepository
 * @Description：ES操作类
 * @Date：Create in 6:14 PM2019/9/15
 * @Modified by:
 * @Version:1.0
 */
public interface ICmsBatchRepository extends ElasticsearchRepository<CmsBatchType, Long> {


}
