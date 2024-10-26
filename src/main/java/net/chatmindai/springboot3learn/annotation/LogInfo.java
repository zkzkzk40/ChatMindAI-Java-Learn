package net.chatmindai.springboot3learn.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * LogInfo 注解
 * 用于标记需要记录日志的方法，并提供方法的描述信息
 *
 * @author hpy
 * @date 2024/10/24
 */
@Target(ElementType.METHOD) // 指定该注解只能应用于方法
@Retention(RetentionPolicy.RUNTIME) // 指定该注解在运行时可以通过反射获取
public @interface LogInfo {
    /**
     * 方法描述
     *
     * @return 返回描述该方法功能的字符串
     */
    String value() default "";
}