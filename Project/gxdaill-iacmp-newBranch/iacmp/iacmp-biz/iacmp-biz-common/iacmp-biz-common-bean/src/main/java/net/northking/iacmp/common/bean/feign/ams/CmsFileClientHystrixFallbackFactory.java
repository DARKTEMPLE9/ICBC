package net.northking.iacmp.common.bean.feign.ams;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @Description:
 * @Author: chenwei
 * @CreateDate: 2019/11/23
 */
@Component
public class CmsFileClientHystrixFallbackFactory implements FallbackFactory<CmsFileFeignClient> {
    @Override
    public CmsFileFeignClient create(Throwable throwable) {
        return new CmsFileFeignClient() {
            /**
             * 根据条件查询文件和影像信息
             *
             * @param json
             * @return
             */
            @Override
            public String selectCmsFileListByOpts(String json) {
                return null;
            }
        };
    }
}
