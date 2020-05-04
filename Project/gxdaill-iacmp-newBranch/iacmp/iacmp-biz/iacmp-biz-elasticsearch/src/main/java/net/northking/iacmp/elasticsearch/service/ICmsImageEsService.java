package net.northking.iacmp.elasticsearch.service;

import net.northking.iacmp.elasticsearch.domain.CmsImageType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Author：Yanqingyu
 * @InterfaceName:CmsImageEsService
 * @Description：TODO
 * @Date：Create in 6:50 PM2019/9/15
 * @Modified by:
 * @Version:1.0
 */
public interface ICmsImageEsService {

    /**
     * 保存批次信息
     *
     * @param cmsImageType
     * @return
     */
    Long saveCmsImage(CmsImageType cmsImageType);

    /**
     * 通过元数据分页查询批次信息
     *
     * @param cmsImageType
     * @param pageable
     * @return
     */
    Page<CmsImageType> selectCmsImagePage(CmsImageType cmsImageType, Pageable pageable);

}
