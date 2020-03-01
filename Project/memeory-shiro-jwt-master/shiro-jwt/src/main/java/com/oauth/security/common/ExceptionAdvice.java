package com.oauth.security.common;

import com.oauth.security.beans.entity.LoginUser;
import com.oauth.security.common.RestResponse.Builder;
import com.oauth.security.constants.OAuthConstant.Code;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author likai
 * @Date 2018/10/18
 */
@RestControllerAdvice
public class ExceptionAdvice {

  @ExceptionHandler({UnauthenticatedException.class,AuthenticationException.class})
  public RestResponse<String> unauthenticatedException() {
    RestResponse.Builder builder = new Builder();
    builder.setCode(Code.UNAUTHENTICATED);
    builder.setMsg("对不起,您还未登录!");
    return builder.build();
  }

  @ExceptionHandler(UnauthorizedException.class)
  @ResponseBody
  public RestResponse<String> unauthorizedException() {
    RestResponse.Builder builder = new Builder();
    builder.setCode(Code.UNAUTHORIZED);
    builder.setMsg("对不起,您没权限操作!");
    return builder.build();
  }

  @ExceptionHandler(UnknownAccountException.class)
  @ResponseBody
  public RestResponse<String> unknownAccountException() {
    RestResponse.Builder builder = new Builder();
    builder.setCode(Code.BUSINESS_ERROR);
    builder.setMsg("登陆失败,用户名或密码错误!");
    return builder.build();
  }
}
