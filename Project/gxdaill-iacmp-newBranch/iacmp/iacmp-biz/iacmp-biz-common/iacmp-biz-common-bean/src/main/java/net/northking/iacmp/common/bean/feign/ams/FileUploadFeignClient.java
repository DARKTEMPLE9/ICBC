package net.northking.iacmp.common.bean.feign.ams;

import feign.Logger;
import feign.codec.Encoder;
import net.northking.iacmp.encoder.FeignSpringFormEncoder;
import net.northking.iacmp.execption.file.InvalidExtensionException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author：Yanqingyu
 * @InterfaceName:FileUploadFeignClient
 * @Description：调用内容管理平台文件上传接口
 * @Date：Create in 3:44 PM2019/9/6
 * @Modified by:
 * @Version:1.0
 */
@FeignClient(name = "iacmp-biz-cms", configuration = FileUploadFeignClient.MultipartSupportConfig.class, fallbackFactory = FileUploadClientHystrixFallbackFactory.class, path = "${providerContext}/file")
public interface FileUploadFeignClient {

    /**
     * 上传(影像或文件)  最大20M
     */
    @PostMapping(value = "/upload", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    String uploadFiles(@RequestParam("json") String json, @RequestPart(value = "files", required = false) MultipartFile[] multipartFile) throws InvalidExtensionException;

    /**
     * 上传 base64方式 最大20M
     */
    @PostMapping("/uploadBase64")
    @ResponseBody
    String uploadBase64(@RequestParam("json") String json) throws InvalidExtensionException;

    class MultipartSupportConfig {
        @Autowired
        private ObjectFactory<HttpMessageConverters> messageConverters;

        @Bean
        @Primary
        @Scope("prototype")
        public Encoder feignFormEncoder() {
            return new FeignSpringFormEncoder();
        }

        @Bean
        public feign.Logger.Level multipartLoggerLevel() {
            return Logger.Level.FULL;
        }
    }
}
