package aspectj.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//指定当前对象为切面对象
@Aspect
@Order(2)
@Component("myAspect3")
public class MyAspect3 {
    @Pointcut("execution(* aspectj.service.*.*(..))")
    public void pointcut3() {
    }

    // 前置
    @Before("pointcut3()")
    public void before3(JoinPoint joinpoint) {
        System.out.println("====Before3:" + joinpoint.getSignature().getName());
    }

    // 返回后
    @AfterReturning("pointcut3()")
    public void afterReturning(JoinPoint joinPoint) {
        System.out.println("====AfterReturning3:" + joinPoint.getSignature().getName());
    }

    // 环绕
    @Around("pointcut3()")
    public Object around3(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("====AroundBefore3:" + joinPoint.getSignature().getName());
        Object result = joinPoint.proceed();
        System.out.println("====AroundAfter3:" + joinPoint.getSignature().getName());
        return result;
    }

    // 最终
    @After("pointcut3()")
    public void after3() {
        System.out.println("====After3");
    }

    // 异常
    // throwing的值e要和afterThrowing3方法参数名一致
    @AfterThrowing(value = "pointcut3()", throwing = "e")
    public void afterThrowing3(Exception e) {
        System.out.println("====AfterThrowing3:Exception," + e.getMessage());
    }
}
