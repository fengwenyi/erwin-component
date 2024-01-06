package com.fengwenyi.erwin.component.common.aop;

import info.hxgy.component.common.parser.SpElParser;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-12-25
 */
public class BaseAspect {

    public HttpServletRequest getHttpServletRequest(){
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        if (sra != null) {
            return sra.getRequest();
        }
        return null;
    }

    protected String getKey(String key, ProceedingJoinPoint joinPoint) {
        if (!StringUtils.hasText(key)) {
            return null;
        }
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        Parameter[] parameters = method.getParameters();
        String[] parameterNames = new String[parameters.length];
        for (int i = 0; i < parameterNames.length; i++) {
            parameterNames[i] = parameters[i].getName();
        }
        return SpElParser.getKey(key, parameterNames, joinPoint.getArgs());
    }

}
