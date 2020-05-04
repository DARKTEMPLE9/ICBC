package net.northking.iacmp.elasticsearch.service.impl;

import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.elasticsearch.domain.CmsFileType;
import net.northking.iacmp.elasticsearch.repository.ICmsFileRepository;
import net.northking.iacmp.elasticsearch.service.ICmsFileEsService;
import net.northking.iacmp.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @Author：Yanqingyu
 * @ClassName:CmsFileEsService
 * @Description：TODO
 * @Date：Create in 3:58 PM2019/9/18
 * @Modified by:
 * @Version:1.0
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class CmsFileEsServiceImpl implements ICmsFileEsService {

    @Autowired
    private ICmsFileRepository cmsFileRepository;

    @Override
    public Long saveCmsFile(CmsFileType cmsFileType) {
        cmsFileType = cmsFileRepository.save(cmsFileType);
        return cmsFileType != null ? cmsFileType.getFileId() : null;
    }

    @Override
    public Page<CmsFileType> selectCmsFilePage(CmsFileType cmsFileType, Pageable pageable) {
        return null;
    }

}
