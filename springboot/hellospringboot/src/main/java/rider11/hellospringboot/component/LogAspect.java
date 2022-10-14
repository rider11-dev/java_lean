package rider11.hellospringboot.component;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import rider11.hellospringboot.bean.RequestLog;
import rider11.hellospringboot.service.LogService;
import rider11.hellospringboot.utils.Constants;

@Slf4j
@Aspect
@Component
public class LogAspect {
    private LogService logService;

    public LogAspect(LogService logService) {
        this.logService = logService;
    }

    @Pointcut("execution(* rider11.hellospringboot.controller.*.*(..))")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes();
            assert attributes != null;
            //TODO:requestid、traceid生成规则
            attributes.setAttribute(Constants.RequestId, UUID.randomUUID().toString(), 0);
            attributes.setAttribute(Constants.TraceId, UUID.randomUUID().toString(), 0);
            HttpServletRequest request = attributes.getRequest();
            log.debug("请求before:{}", request.getRequestURI());
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            // 获取请求参数
            String[] argNames = signature.getParameterNames();
            Object[] args = joinPoint.getArgs();
            // log.debug("请求路径:{},请求方式:{},请求参数:{},IP:{}", request.getRequestURI(),
            // request.getMethod(),getRequestParam(argNames, args),
            // request.getRemoteAddr());
            RequestLog log = new RequestLog();
            log.setUrl(request.getRequestURI());
            log.setMethod(request.getMethod());
            log.setParam(getRequestParam(argNames, args));
            log.setIp(request.getRemoteAddr());

            // logService.saveLog(log);//同步
            logService.saveLogAsync(log);// 异步
        } catch (Exception ex) {
            log.error("记录请求日志错误", ex);
        }
    }

    @After("pointcut()")
    public void after() {
        log.debug("请求after:{}", getRequestUri());
    }

    @AfterReturning("pointcut()")
    public void afterReturning(JoinPoint joinPoint) {
        log.debug("请求afterReturning:{}", getRequestUri());

    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.debug("请求around-before:{}", getRequestUri());
        Object result = joinPoint.proceed();
        log.debug("请求around-after:{}", getRequestUri());
        return result;
    }

    private String getRequestUri() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        assert attributes != null;
        return attributes.getRequest().getRequestURI();
    }

    /**
     * 
     * @param argNames
     * @param args
     * @return 返回json字符串
     * @throws JsonProcessingException
     */
    private String getRequestParam(String[] argNames, Object[] args) throws JsonProcessingException {
        HashMap<String, Object> params = new HashMap<>(argNames.length);
        if (argNames.length > 0 && args.length > 0) {
            for (int i = 0; i < argNames.length; i++) {
                params.put(argNames[i], args[i]);
            }
        }
        params.put("__log_time", new Date());
        return new ObjectMapper().writeValueAsString(params);
    }
}
