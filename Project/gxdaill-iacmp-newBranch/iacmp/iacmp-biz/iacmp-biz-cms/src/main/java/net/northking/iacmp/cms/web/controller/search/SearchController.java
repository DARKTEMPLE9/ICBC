package net.northking.iacmp.cms.web.controller.search;/**
 * @Description:
 * @Author: weizhe.fan
 * @CreateDate: 2019/8/26
 */


import net.northking.iacmp.cms.service.ICmsFileService;
import net.northking.iacmp.cms.service.ICmsImageService;
import net.northking.iacmp.common.bean.domain.cms.CmsImage;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Description:检索
 * @Author: weizhe.fan
 * @CreateDate: 2019/8/26
 */
@Controller
@RequestMapping("/search")
public class SearchController extends BaseController {
    @Autowired
    private ICmsFileService iCmsFileService;

    @Autowired
    private ICmsImageService iCmsImageService;

    /**
     * 检索MYSQL  影像名称，上传日期等等等
     */
    public TableDataInfo queryMysqlImage(CmsImage cmsImage) {
        //根据条件模糊查询出文档列表
        List<CmsImage> cmsImageList = iCmsImageService.selectCmsImageList(cmsImage);
        return getDataTable(cmsImageList);
    }


    /**
     * 检索ES  文档内容
     */

}
