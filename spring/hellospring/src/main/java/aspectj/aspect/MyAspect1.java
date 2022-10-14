package aspectj.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

@Component("myAspect1")
public class MyAspect1 {
    // 前置
    public void before1(JoinPoint joinPoint) {
        // joinPoint.getTarget(); 获取目标对象
        // joinPoint.getSignature().getName(); 获取目标方法名
        // joinPoint.getArgs(); 获取目标方法参数列表
        // joinPoint.getThis(); 获取代理对象
        System.out.println("=======Before " + joinPoint.getSignature().getName());
    }

    // 后置
    public void afterReturning1(JoinPoint joinPoint) {
        System.out.println("=======AfterReturning " + joinPoint.getSignature().getName());
    }

    // 环绕
    public Object around1(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("=======Around before " + joinPoint.getSignature().getName());
        Object obj = joinPoint.proceed();
        System.out.println("=======Around after " + joinPoint.getSignature().getName());
        return obj;
    }

    // 异常
    public void afterThrowing1(Exception e) {
        System.out.println("=======Exception " + e.toString());
    }

    // 最终
    public void after1() {
        System.out.println("=======After");
    }
}
