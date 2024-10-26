package net.chatmindai.springboot3learn.AOP;

import net.chatmindai.springboot3learn.annotation.LogInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.Arrays;

public class AOP {
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
}