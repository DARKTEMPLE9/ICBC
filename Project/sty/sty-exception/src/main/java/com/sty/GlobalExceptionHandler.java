package com.sty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 请求方式异常处理
     * @param e
     * @return
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public String hanleException(HttpRequestMethodNotSupportedException e) {
        log.error(e.getMessage(),e );
        return "不支持'"+e.getMethod()+"'请求方式";
    }

    /**
     * 系统异常
     * @param e
     * @return
     */
    @ExceptionHandler({Exception.class})
    public String handleException(Exception e) {
        log.error(e.getMessage(),e);
        return "服务器运行异常，请联系管理员";
    }
    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public String notFount(RuntimeException e) {
        log.error("运行时异常:", e);
        return "运行时异常:" + e.getMessage();
    }

    /**
     * 业务异常
     */
    @ExceptionHandler({BusinessException.class})
    public Object businessException(HttpServletRequest request,BusinessException e) {
        log.error(e.getMessage(), e);
        //判断请求是否为axaj
        /*if () {
        }*/
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("errorMessage", e.getMessage());
        modelAndView.setViewName("error/business");
        return modelAndView;
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler({BindException.class})
    public String validateBindException(org.springframework.validation.BindException e) {
        log.error(e.getMessage(), e);
        String message = e.getAllErrors().get(0).getDefaultMessage();
        return message;
    }


}
