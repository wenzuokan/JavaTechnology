package com.atguigu.controller.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Drew
 * @create 2023-03
 *
 * 拦截器的三个方法：
 * preHandle():在控制器方法执行之前执行，其返回值表示对控制器方法的拦截（false）或放行（true）
 * postHandle():在控制器方法执行之后执行
 * afterCompletion:在控制器方法执行之后，且渲染视图完毕之后执行
 *
 * 若拦截器中有某个拦截器的preHandle()返回了false
 * 拦截器的preHandle()返回false和她之前的拦截器的preHandle()都会执行
 * 所有的拦截器的postHandle()都不执行
 * 拦截器的preHandle()返回false之前的拦截器的afterCompletion()会执行
 */
@Component
public class FirstInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("FirstInterceptor-->preHandle");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("FirstInterceptor-->postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("FirstInterceptor-->afterCompletion");
    }

}
