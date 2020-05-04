package net.northking.iacmp.utils;/**
 * @Description:
 * @Author: weizhe.fan
 * @CreateDate: 2019/9/11
 */

import net.northking.iacmp.annotation.LimitKey;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 切面类, 用于限制访问接口次数
 * @Author: weizhe.fan
 * @CreateDate: 2019/9/11
 */
@Component
@Order
@Aspect
public class LimitAspect {

    private Map limitMap = new HashMap();


    private static final Logger log = LoggerFactory.getLogger(LimitAspect.class);

    @Pointcut("@annotation(limitKey)")
    public void limit(LimitKey limitKey) {
    }

    @Around("limit(limitKey)")
    public Object aroundLog(ProceedingJoinPoint joinpoint, LimitKey limitKey) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinpoint.getSignature();
        int frequency = limitKey.frequency();
        String methodName = limitKey.methodName();
        String paramKey = limitKey.paramKey();
        String url = limitKey.url();
        //入参
        String[] parameterNames = methodSignature.getParameterNames();
        Object[] args = joinpoint.getArgs();
        Object obj = null;

        for (int i = 0; i < parameterNames.length; i++) {
            if (parameterNames[i].equals(paramKey)) {
                obj = args[i];
                break;
            }
        }

        System.err.println("args : " + Arrays.toString(args));
        System.err.println("keys : " + Arrays.toString(parameterNames));

        StringBuffer sb = new StringBuffer();
        sb.append(url).append("/_").append(methodName).append("_").append(paramKey).append("_").append(obj).append("_key");
        if (limitMap.get(sb.toString()) == null) {
            limitMap.put(sb.toString(), frequency - 1);
        } else {

            int l = (int) limitMap.get(sb.toString());
            if (l > 0) {
                limitMap.put(sb.toString(), --l);
            } else {
                throw new Exception("系统繁忙，请重试");
            }
        }
        //reids 代替map  redis.set(sb.toString(),frequency,limitKey.timeout());
        System.err.println("剩余次数：" + limitMap.get(sb.toString()) + "----->----->----->自定义key:" + sb.toString());
        return joinpoint.proceed();
    }
}
