package com.shop.Interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Interceptor2 implements HandlerInterceptor {

    /**
     * controller执行前调用此方法
     * 其返回值表示是否中断后续操作,返回true表示继续执行，返回false中止执行（包括调用下一个拦截器和控制器类中的方法执行等）。
     * 这里可以加入登录校验、权限拦截等
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("Interceptor2.preHandle……");
        return true;
    }

    /**
     * controller执行后但未返回视图前调用此方法
     * 这里可在返回用户前对模型数据进行加工处理，比如这里加入公用信息以便页面显示
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("Interceptor2.postHandle……");
    }

    /**
     * controller执行后且视图返回后调用此方法(相当于在整个请求完成，即视图渲染结束之后执行。)
     * 这里可得到执行controller时的异常信息
     * 这里可记录操作日志，资源清理等
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("Interceptor2.afterCompletion……");
    }
}
