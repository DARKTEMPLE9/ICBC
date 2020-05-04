package net.northking.iacmp.elasticsearch.service;

import net.northking.iacmp.elasticsearch.domain.CmsBatchType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


/**
 * @Author：Yanqingyu
 * @ClassName:PmsBatchSearchService
 * @Description：
 * @Date：Create in 6:19 PM2019/9/15
 * @Modified by:
 * @Version:1.0
 */
@Service
public interface ICmsBatchEsService {

    /**
     * 保存批次信息
     *
     * @param cmsBatchType
     * @return
     */
    Long saveCmsBatch(CmsBatchType cmsBatchType);

    /**
     * 通过元数据分页查询批次信息
     *
     * @param cmsBatchType
     * @param pageable
     * @return
     */
    Page<CmsBatchType> selectCmsBatchPage(CmsBatchType cmsBatchType, Pageable pageable);

}
