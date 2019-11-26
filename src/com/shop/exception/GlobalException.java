package com.shop.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常
 */
public class GlobalException implements HandlerExceptionResolver {
  /*  @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        String errorMsg = "喔，有问题 ( TvT )";
        ModelAndView mv = new ModelAndView();
        mv.addObject("errorMsg",errorMsg);
        mv.setViewName("error");

        return mv;
    }*/
  @Override
  public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
      String errorMsg = "喔，有问题 ( TvT )";
      //需要判断我们自定义异常是不是Exception子类 MyException extends Exception
      if (e instanceof MyException)
      {
         errorMsg =((MyException) e).getErrormsg();
      }

      ModelAndView mv = new ModelAndView();
      mv.addObject("errorMsg",errorMsg);
      mv.setViewName("error");

      return mv;
  }
}
