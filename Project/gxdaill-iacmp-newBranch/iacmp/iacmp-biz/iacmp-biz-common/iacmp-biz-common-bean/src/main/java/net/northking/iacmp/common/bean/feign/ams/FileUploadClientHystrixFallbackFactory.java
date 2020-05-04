package net.northking.iacmp.common.bean.feign.ams;

import feign.hystrix.FallbackFactory;
import net.northking.iacmp.execption.file.InvalidExtensionException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author：Yanqingyu
 * @ClassName:FileUploadClientHystrixFallbackFactory
 * @Description：文件上传熔断器
 * @Date：Create in 4:30 PM2019/9/6
 * @Modified by:
 * @Version:1.0
 */
@Component
public class FileUploadClientHystrixFallbackFactory implements FallbackFactory<FileUploadFeignClient> {

    @Override
    public FileUploadFeignClient create(Throwable throwable) {
        return new FileUploadFeignClient() {

            @Override
            public String uploadFiles(String data, MultipartFile[] multipartFile) throws InvalidExtensionException {
                return null;
            }

            @Override
            public String uploadBase64(String json) throws InvalidExtensionException {
                return null;
            }

        };
    }
}
