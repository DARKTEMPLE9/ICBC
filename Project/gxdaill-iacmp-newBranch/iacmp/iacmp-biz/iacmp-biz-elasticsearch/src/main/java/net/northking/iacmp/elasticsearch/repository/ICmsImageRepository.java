package net.northking.iacmp.elasticsearch.repository;

import net.northking.iacmp.elasticsearch.domain.CmsImageType;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Author：Yanqingyu
 * @ClassName:ICmsImageRepository
 * @Description：TODO
 * @Date：Create in 6:47 PM2019/9/15
 * @Modified by:
 * @Version:1.0
 */
public interface ICmsImageRepository extends ElasticsearchRepository<CmsImageType, Long> {


}
