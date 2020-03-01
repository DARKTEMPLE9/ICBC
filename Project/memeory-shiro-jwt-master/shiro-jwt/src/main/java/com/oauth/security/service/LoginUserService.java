package com.oauth.security.service;

import com.oauth.security.beans.entity.LoginUser;

/**
 * @author likai
 * @Date 2018/10/18
 */
public interface LoginUserService {

  LoginUser findByUserName(String userName);

}
