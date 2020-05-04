package net.northking.iacmp.elasticsearch.service.impl;

import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.elasticsearch.domain.CmsBatchType;
import net.northking.iacmp.elasticsearch.repository.ICmsBatchRepository;
import net.northking.iacmp.elasticsearch.service.ICmsBatchEsService;
import net.northking.iacmp.enums.DataSourceType;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author：Yanqingyu
 * @ClassName:PmsBatchEsServiceimpl
 * @Description：TODO
 * @Date：Create in 6:43 PM2019/9/15
 * @Modified by:
 * @Version:1.0
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class CmsBatchEsServiceimpl implements ICmsBatchEsService {


    @Resource
    private ICmsBatchRepository cmsBatchRepository;

    /**
     * 保存批次信息
     *
     * @param cmsBatchType
     * @return
     */
    @Override
    public Long saveCmsBatch(CmsBatchType cmsBatchType) {

        cmsBatchType = cmsBatchRepository.save(cmsBatchType);
        return cmsBatchType != null ? cmsBatchType.getBatchId() : null;
    }

    /**
     * 通过元数据分页查询批次信息
     *
     * @param cmsBatchType
     * @param pageable
     * @return
     */
    @Override
    public Page<CmsBatchType> selectCmsBatchPage(CmsBatchType cmsBatchType, org.springframework.data.domain.Pageable pageable) {
        return null;
    }

}
