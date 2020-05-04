package net.northking.iacmp.common.bean.feign.ams;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @Description: 调用CmsFile接口
 * @Author: chenwei
 * @CreateDate: 2019/11/23
 */
@FeignClient(name = "iacmp-biz-cms", configuration = FeignClientsConfiguration.class,
        fallbackFactory = CmsFileClientHystrixFallbackFactory.class, path = "${providerContext}/cms/cmsFile")
public interface CmsFileFeignClient {

    /**
     * 根据条件查询文件和影像信息
     *
     * @param json
     * @return
     */
    @RequestMapping("/listByOpts")
    @ResponseBody
    String selectCmsFileListByOpts(@RequestParam("json") String json);

}
