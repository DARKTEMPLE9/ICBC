package net.northking.iacmp.elasticsearch.service;

import net.northking.iacmp.elasticsearch.domain.CmsFileType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Author：Yanqingyu
 * @InterfaceName:CmsFileEsService
 * @Description：TODO
 * @Date：Create in 6:50 PM2019/9/15
 * @Modified by:
 * @Version:1.0
 */
public interface ICmsFileEsService {


    /**
     * 保存批次信息
     *
     * @param cmsFileType
     * @return
     */
    Long saveCmsFile(CmsFileType cmsFileType);

    /**
     * 通过元数据分页查询批次信息
     *
     * @param cmsFileType
     * @param pageable
     * @return
     */
    Page<CmsFileType> selectCmsFilePage(CmsFileType cmsFileType, Pageable pageable);
}
