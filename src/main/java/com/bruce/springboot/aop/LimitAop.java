package com.bruce.springboot.aop;

import com.bruce.springboot.annotation.LimitRequest;
import com.chinacareer.ymtd.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@Slf4j
public class LimitAop {
    @Before("@annotation(com.bruce.springboot.annotation.LimitRequest)")
    public void before(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            // 从获取RequestAttributes中获取HttpServletRequest的信息
            HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
            if (request != null) {
                // 反爬策略
                // 1、UA+Referer+Cookie校验
                String ua = request.getHeader("user-agent");
                checkUA(ua);

                String referer = request.getHeader("referer");
                checkReferer(referer);

                // 2、ip限制
                String ip = request.getRemoteAddr();
                log.info("==> 请求者的IP：" + request.getRemoteAddr());

                log.info("==> 请求者的Cookie:{}", (Object) request.getCookies());
                log.info("==> 请求者的Path:{}", request.getPathInfo());
                log.info("==> 请求者的Query:{}", request.getQueryString());
                log.info("==> 请求头的UA:{}", request.getHeader("user-agent"));
                log.info("==> 请求头的Referer:{}", request.getHeader("referer"));
            }
        }

        // 拿limit的注解
        LimitRequest limit = method.getAnnotation(LimitRequest.class);
        System.out.println(limit);
    }

    /**
     * 校验UA
     *
     * @param ua User-Agent
     */
    private void checkUA(String ua) {
        if (ua == null) {
            log.error("检测爬虫:Request Header中无UA");
            throw new BizException("系统繁忙");
        }
        if (!ua.contains("Mozilla")) {
            log.error("检测爬虫:Request Header中UA非网景,UA:{}", ua);
            throw new BizException("系统繁忙");
        }
    }

    /**
     * 校验referer
     *
     * @param referer referer
     */
    private void checkReferer(String referer) {
        if (referer == null) {
            log.error("检测爬虫:Request Header中无referer");
            throw new BizException("系统繁忙");
        }
        if (!referer.contains("yimaitongdao")) {
            log.error("检测爬虫:Request Header中referer没有来自yimaitongdao");
            throw new BizException("系统繁忙");
        }
    }

    /**
     * 校验cookie
     *
     * @param cookies cookies
     */
    private void checkCookie(Cookie[] cookies) {
        if (cookies == null) {
            log.error("检测爬虫:Request Header中无cookie");
            throw new BizException("系统繁忙");
        }

        Arrays.stream(cookies).forEach(cookie -> {
            System.out.println(cookie.getName());
            System.out.println(cookie.getValue());
        });
    }

    private void checkIP(String ip) {
        // 间隔时间 -- 同一ip，相邻两次请求时间差不得小于500ms
    }
}
