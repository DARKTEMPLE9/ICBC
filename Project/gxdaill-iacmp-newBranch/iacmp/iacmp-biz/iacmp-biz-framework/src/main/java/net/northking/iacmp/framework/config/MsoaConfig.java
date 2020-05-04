package net.northking.iacmp.framework.config;

/*import com.baidu.ub.msoa.container.support.bundle.ExtBundleContextPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;*/

import org.springframework.context.annotation.Configuration;

@Configuration
public class MsoaConfig {

   /* @Bean
    @ConditionalOnProperty( name = "loginType", havingValue ="SSO")
    public ExtBundleContextPostProcessor getExtBundleContextPostProcessor(){
        ExtBundleContextPostProcessor extBundleContextPostProcessor = new ExtBundleContextPostProcessor("iacmp-biz-framework");
        extBundleContextPostProcessor.setReloadBundleContext(true);
        return extBundleContextPostProcessor;
    }*/

//    @Bean
//    @ConditionalOnProperty( name = "loginType", havingValue ="ALL")
//    public ExtBundleContextPostProcessor getExtBundleContextPostProcessor2(){
//        ExtBundleContextPostProcessor extBundleContextPostProcessor = new ExtBundleContextPostProcessor("iacmp-biz-framework");
//        extBundleContextPostProcessor.setReloadBundleContext(true);
//        return extBundleContextPostProcessor;
//    }
}
