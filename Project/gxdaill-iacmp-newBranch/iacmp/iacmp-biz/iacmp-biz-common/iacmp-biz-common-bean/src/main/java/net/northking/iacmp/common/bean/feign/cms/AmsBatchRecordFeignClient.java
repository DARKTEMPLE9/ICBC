package net.northking.iacmp.common.bean.feign.cms;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @Description:
 * @Author: weizhe.fan
 * @CreateDate: 2019/9/12
 */
@FeignClient(name = "iacmp-biz-ams", configuration = FeignClientsConfiguration.class,
        fallbackFactory = AmsBatchRecordClientHystrixFallbackFactory.class, path = "${providerContext}/outInterface/amsBatchRecord")
public interface AmsBatchRecordFeignClient {

    /**
     * 保存文件信息到im_file表
     */
    @PostMapping("/saveFiles")
    @ResponseBody
    public void saveFilesProcess(@RequestParam("json") String json);

    /**
     * 保存图片信息到im_image表
     */
    @PostMapping("/saveImage")
    @ResponseBody
    public void saveImageProcess(@RequestParam("json") String json);

    /**
     * 接收并处理报文
     */
    @PostMapping("/record")
    @ResponseBody
    public void dealProcess(@RequestParam("json") String json);
}
