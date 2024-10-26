package net.chatmindai.springboot3learn.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 日志切面类
 * 用于记录控制器方法的入参和出参
 *
 * @author zk
 * @date 2024/10/05
 */
@Aspect // 声明这是一个切面
@Component // 将该类声明为Spring组件，使其能被自动扫描和注册
@Slf4j // Lombok注解，自动生成日志对象
public class LogAspect {

    // 定义切入点，匹配所有控制器类中的所有方法
    @Pointcut("execution(* net.chatmindai.springboot3learn.controller..*.*(..))")
    public void controllerPointcut() {}

    /**
     * 环绕通知，在方法执行前后都进行处理
     */
    @Around("controllerPointcut()")
    public Object logAroundController(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        Object[] args = joinPoint.getArgs();

        // 记录方法调用信息和入参
        log.info("调用控制器方法: {}.{}", className, methodName);
        log.info("入参: {}", Arrays.toString(args));

        // 执行原方法
        Object result = joinPoint.proceed();

        // 记录出参
        log.info("出参: {}", result);

        return result;
    }

    /**
     * 前置通知，在方法执行前进行处理
     */
    @Before("controllerPointcut()")
    public void logBeforeController(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        Object[] args = joinPoint.getArgs();

        // 记录方法调用信息和入参
        log.info("Before调用控制器方法: {}.{}", className, methodName);
        log.info("Before入参: {}", Arrays.toString(args));
    }

    /**
     * 返回通知，在方法正常返回后进行处理
     */
    @AfterReturning(pointcut = "controllerPointcut()", returning = "result")
    public void logAfterController(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();

        // 记录方法执行完毕信息和出参
        log.info("AfterReturning控制器方法执行完毕: {}.{}", className, methodName);
        log.info("AfterReturning出参: {}", result);
    }
}
