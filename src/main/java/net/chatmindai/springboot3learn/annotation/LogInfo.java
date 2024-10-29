package net.chatmindai.springboot3learn.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * LogInfo 注解
 * 用于标记需要记录日志的方法，并提供方法的描述信息
 *
 * @author jy
 * @date 2024/10/29
 */
@Target(ElementType.METHOD) // Target指定注解的适用范围，指定该注解只能应用于方法(类中的函数)
@Retention(RetentionPolicy.RUNTIME) // 指定该注解在运行时可以通过反射获取
public @interface LogInfo {
    /**
     * 方法描述
     *
     * @return 返回描述该方法功能的字符串
     */
    String value() default "";
    //注解的属性，@LogInfo("这是一个示例方法")
}