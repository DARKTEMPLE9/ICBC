package net.northking.iacmp.annotation;

import java.lang.annotation.*;

/**
 * @Description:限制接口访问次数
 * @Author: weizhe.fan
 * @CreateDate: 2019/9/11
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LimitKey {

    //方法名称
    String methodName() default "";

    //访问次数
    int frequency() default 10;

    //业务key
    String paramKey() default "";

    //方法请求地址
    String url() default "";

    //过期时间
    long timeout() default 300;
}
