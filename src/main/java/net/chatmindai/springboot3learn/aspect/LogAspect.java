package net.chatmindai.springboot3learn.aspect;

import lombok.extern.slf4j.Slf4j;
import net.chatmindai.springboot3learn.annotation.LogInfo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;

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
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        LogInfo logInfo = signature.getMethod().getAnnotation(LogInfo.class);

        // 如果方法没有 @LogInfo 注解，直接执行方法并返回结果
        if (logInfo == null) {
            return joinPoint.proceed();
        }

        String methodDescription = logInfo.value();
        String methodName = signature.getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        Object[] args = joinPoint.getArgs();

        log.info("执行方法: {}.{} - {}", className, methodName, methodDescription);
        log.info("入参: {}", Arrays.toString(args));

        Object result = joinPoint.proceed();

        log.info("方法返回: {}.{} - {}", className, methodName, methodDescription);
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
