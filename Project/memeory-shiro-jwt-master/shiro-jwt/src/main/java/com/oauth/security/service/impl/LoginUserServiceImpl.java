package com.oauth.security.service.impl;

import com.oauth.security.beans.entity.LoginUser;
import com.oauth.security.constants.OAuthConstant.Permissions;
import com.oauth.security.constants.OAuthConstant.Roles;
import com.oauth.security.service.LoginUserService;
import java.util.LinkedHashSet;
import java.util.Set;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author likai
 * @Date 2018/10/18
 */
@Service
public class LoginUserServiceImpl implements LoginUserService {

  @Override
  public LoginUser findByUserName(String userName) {
    //写死验证业务
    if (!StringUtils.isEmpty(userName) && ("lk".equals(userName))) {
      LoginUser loginUser = new LoginUser();
      loginUser.setUserName(userName);
      loginUser.setPassword("123456");
      Set<String> roles = new LinkedHashSet<>();
      Set<String> permissions = new LinkedHashSet<>();
      roles.add(Roles.ADMIN);
      permissions.add(Permissions.INSERT);
      loginUser.setRoles(roles);
      loginUser.setPermissions(permissions);
      return loginUser;
    }
    return null;
  }
}
