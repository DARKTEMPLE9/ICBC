package com.icbc.super_shopping.login.loginService;

import com.icbc.super_shopping.entity.UserManage;
import com.icbc.super_shopping.login.loginMapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public UserManage getUserInfo(UserManage userManage) {
        return loginMapper.getUserInfo(userManage);
    }
}
