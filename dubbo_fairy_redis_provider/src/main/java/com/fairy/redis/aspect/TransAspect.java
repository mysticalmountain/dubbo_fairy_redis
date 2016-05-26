package com.fairy.redis.aspect;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import java.io.StringWriter;
import java.lang.reflect.Method;

/**
 * 交易日志切面
 * @author andongxu
 * @date 2016-05-09
 */
@Aspect
@Component
public class TransAspect {
    private Log logger = LogFactory.getLog(TransAspect.class);

    private static LocalVariableTableParameterNameDiscoverer parameterNameDiscovere = new LocalVariableTableParameterNameDiscoverer();


    @Around("@annotation(com.fairy.redis.aspect.TransLog), argNames = joinPoint")
    public Object saveLog(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = getMethod(joinPoint);
        TransLog transLog = method.getAnnotation(TransLog.class);
        String system = transLog.system();
        String module = transLog.module();
        String trans = transLog.trans();
        String description = executeTemplateVelocity(transLog.value(), joinPoint);
        logger.info("system=" + system + ",module=" + module + ",trans=" + trans + "--->value=" + description);
        Object obj = null;
        try {
            obj = joinPoint.proceed();
            return obj;
        } finally {
            long end = System.currentTimeMillis();
            logger.info("system=" + system + ",module=" + module + ",trans=" + trans + "<---" + obj);
        }
    }


    private String executeTemplateVelocity(String template, ProceedingJoinPoint joinPoint) {
        Method method = getMethod(joinPoint);
        String[] parameterNames = parameterNameDiscovere.getParameterNames(method);
        Object[] args = joinPoint.getArgs();
        Velocity.init();
        VelocityContext context = new VelocityContext();
        StringWriter outString = new StringWriter();
        for (int i = 0; i < parameterNames.length; i++) {
            context.put(parameterNames[i], args[i]);
        }
        Velocity.evaluate(context, outString, "mystring", template);
        return outString.toString();
    }

    private Method getMethod(ProceedingJoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Method[] methods = joinPoint.getTarget().getClass().getMethods();
        Method resultMethod = null;
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                resultMethod = method;
                break;
            }
        }
        return resultMethod;
    }
}
