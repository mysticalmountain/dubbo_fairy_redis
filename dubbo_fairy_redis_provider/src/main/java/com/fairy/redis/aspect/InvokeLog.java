package com.fairy.redis.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 方法调用日志注解
 * @Author andongxu
 * @Create time 16-2-18:下午2:35
 * @Version
 * @Last update time
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface InvokeLog {

}
