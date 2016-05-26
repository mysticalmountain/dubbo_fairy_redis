package com.fairy.redis.aspect;

import java.lang.annotation.*;

/**
 * 交易日志注解
 * @author andongxu
 * @date 2016-05-09
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TransLog {

    /**
     * 系统
     * @return
     */
    String system() default "";

    /**
     * 模块
     * @return
     */
    String module() default "";

    /**
     * 交易
     * @return
     */
    String trans() default "";

    /**
     * 日志内容
     * @return
     */
    String value() default "";
}
