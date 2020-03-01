package com.oauth.security.controller;

import com.oauth.security.beans.entity.LoginUser;
import com.oauth.security.common.RestResponse;
import com.oauth.security.common.RestResponse.Builder;
import com.oauth.security.constants.OAuthConstant.Code;
import com.oauth.security.service.LoginUserService;
import com.oauth.security.utils.JwtUtils;
import javax.validation.constraints.NotNull;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author likai
 * @Date 2018/10/18
 */
@RestController
public class LoginController {

  @Autowired
  private LoginUserService loginUserService;


  @RequestMapping("/login")
  public RestResponse<String> login(@NotNull LoginUser loginUser) {
    String username = loginUser.getUserName();
    String password = loginUser.getPassword();
    if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
      RestResponse.Builder builder = new Builder();
      builder.setMsg("账号和密码不能为空!");
      builder.setCode(Code.BUSINESS_ERROR);
      return builder.build();
    }
    LoginUser user = loginUserService.findByUserName(username);
    if (null == user || !password.equals(user.getPassword())) {
      throw new UnknownAccountException("用户名和密码错误");
    }
    String msg = "登录成功,请妥善保管您的token,有效期5分钟!";
    return RestResponse.OK(msg, JwtUtils.createToken(username, password));
  }

}
