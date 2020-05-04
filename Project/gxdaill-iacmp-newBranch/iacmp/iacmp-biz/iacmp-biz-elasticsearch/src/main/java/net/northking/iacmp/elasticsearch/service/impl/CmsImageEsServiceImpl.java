package net.northking.iacmp.elasticsearch.service.impl;

import net.northking.iacmp.elasticsearch.domain.CmsImageType;
import net.northking.iacmp.elasticsearch.repository.ICmsImageRepository;
import net.northking.iacmp.elasticsearch.service.ICmsImageEsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Author：Yanqingyu
 * @ClassName:CmsImageEsService
 * @Description：TODO
 * @Date：Create in 3:59 PM2019/9/18
 * @Modified by:
 * @Version:1.0
 */
public class CmsImageEsServiceImpl implements ICmsImageEsService {

    @Autowired
    private ICmsImageRepository cmsImageRepository;

    @Override
    public Long saveCmsImage(CmsImageType cmsImageType) {
        cmsImageType = cmsImageRepository.save(cmsImageType);

        return cmsImageType != null ? cmsImageType.getImageId() : null;
    }

    @Override
    public Page<CmsImageType> selectCmsImagePage(CmsImageType cmsImageType, Pageable pageable) {
        return null;
    }

}
