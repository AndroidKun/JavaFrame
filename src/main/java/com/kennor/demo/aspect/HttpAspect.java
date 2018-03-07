package com.kennor.demo.aspect;

import com.kennor.demo.utils.JacksonUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 请求切面
 */
@Aspect
@Component
public class HttpAspect {

    private static final Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    //设置切面
    @Pointcut("execution(* com.kennor.demo.controller..*.*(..))")
    public void aspect() {
    }

    @Before("aspect()")
    public void doBefore(JoinPoint joinPoint) {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        logger.info("Request-------> url: {}, method: {}, uri: {}, params: {},class-method: {}", url, method, uri, queryString, classMethod);
    }

    @After("aspect()")
    public void doAfter() {

    }

    @AfterReturning(returning = "object", pointcut = "aspect()")
    public void doAfterReturning(Object object) {
        logger.info("Response------->" + JacksonUtil.beanToJson(object));
    }
}
