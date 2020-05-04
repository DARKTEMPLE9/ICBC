package net.northking.iacmp.common.bean.feign.cms;/**
 * @Description:
 * @Author: weizhe.fan
 * @CreateDate: 2019/9/12
 */

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @Description:
 * @Author: weizhe.fan
 * @CreateDate: 2019/9/12
 */
@Component
public class AmsBatchRecordClientHystrixFallbackFactory implements FallbackFactory<AmsBatchRecordFeignClient> {
    @Override
    public AmsBatchRecordFeignClient create(Throwable throwable) {
        return new AmsBatchRecordFeignClient() {
            @Override
            public void saveFilesProcess(@RequestParam("json") String json) {

            }

            @Override
            public void saveImageProcess(@RequestParam("json") String json) {

            }

            @Override
            public void dealProcess(@RequestBody String json) {

            }
        };
    }
}
