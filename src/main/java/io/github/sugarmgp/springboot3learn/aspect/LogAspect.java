package io.github.sugarmgp.springboot3learn.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LogAspect {

    // 定义切入点，匹配所有控制器类中的所有方法
    @Pointcut("execution(* io.github.sugarmgp.springboot3learn.controller..*.*(..))")
    public void controllerPointcut() {
    }

    // 环绕通知，在方法执行前后都进行处理
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
}