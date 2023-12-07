package com.nowcoder.community.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

//@Component
//@Aspect
public class AlphaAspect {

    @Pointcut("execution(* com.nowcoder.community.service.*.*(..))")
    public void pointcut() {

    }

    @Before("pointcut()")
    public void before() {
        System.out.println("alpha before...");
    }

    @After("pointcut()")
    public void after() {
        System.out.println("alpha after...");
    }

    @AfterReturning("pointcut()")
    public void afterReturning() {
        System.out.println("alpha afterReturning...");
    }

    @AfterThrowing("pointcut()")
    public void afterThrowing() {
        System.out.println("alpha afterThrowing...");
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("alpha around before...");
        Object obj = joinPoint.proceed();
        System.out.println("alpha around after...");
        return obj;
    }
}
