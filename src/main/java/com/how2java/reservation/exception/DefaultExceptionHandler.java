package com.how2java.reservation.exception;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;
//用于后台管理的shiro的异常处理
@ControllerAdvice
public class DefaultExceptionHandler {
	@ExceptionHandler({ UnauthorizedException.class })
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ModelAndView processUnauthenticatedException(NativeWebRequest request, UnauthorizedException e) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("ex", e);
		mv.setViewName("unauthorized");
		return mv;
	}
}
