package com.fairy.redis.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 方法调用日志切面
 * @Author andongxu
 * @Create time 16-2-18:下午2:38
 * @Version
 * @Last update time
 */
@Aspect
@Component
public class InvokeAspect {

    @Around("@annotation(com.fairy.redis.aspect.InvokeLog), argNames = joinPoint")
    public Object advice(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = extractMethod(joinPoint);
        Object[] args = joinPoint.getArgs();
        String param = "";
        for (Object arg : args) {
            param += arg + ",";
        }
        long begin = System.currentTimeMillis();
        Logger log = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        log.info("method(" + method.getName() + ")--->" + param);
        Object obj = null;
        try {
            obj = joinPoint.proceed(args);
            return obj;
        } finally {
            long end = System.currentTimeMillis();
            log.info("method(" + method.getName() + ")<---"  + String.format("%dms", end - begin) + "\t" + obj);
        }
    }

    private Method extractMethod(ProceedingJoinPoint joinPoint) throws NoSuchMethodException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Class<?> targetClass = joinPoint.getTarget().getClass();
        if (Modifier.isPublic(signature.getMethod().getModifiers())) {
            return targetClass.getMethod(signature.getName(), signature.getParameterTypes());
        } else {
            return ReflectionUtils.findMethod(targetClass, signature.getName(), signature.getParameterTypes());
        }
    }

}
