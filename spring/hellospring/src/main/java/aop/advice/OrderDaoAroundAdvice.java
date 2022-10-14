package aop.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

//环绕增强
@Component("aroundAdvice")
public class OrderDaoAroundAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        // TODO Auto-generated method stub
        System.out.println("环绕增强前***********");
        Object result = methodInvocation.proceed();
        System.out.println("环绕增强后***********");
        return result;
    }
}
