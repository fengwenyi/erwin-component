package com.fengwenyi.component.common.request.aspect;

import com.hxsy.component.common.constant.ComponentAnnOrder;
import com.hxsy.component.common.holder.RequestContextHolder;
import com.hxsy.component.common.request.annotation.RequestContext;
import com.hxsy.component.common.util.JacksonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2024-01-10
 */
@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
@Order(ComponentAnnOrder.REQUEST_CONTEXT)
public class RequestContextAspect {

    @Around("@annotation(aop)")
    public Object around(ProceedingJoinPoint joinPoint, RequestContext aop) throws Throwable {

        try {

            Object[] args = joinPoint.getArgs();
            RequestContextHolder.set(JacksonUtils.json(args));

            return joinPoint.proceed();

        } finally {
            RequestContextHolder.clear();
        }
    }

}
