package com.icbc.super_shopping.login.loginMapper;

import com.icbc.super_shopping.entity.UserManage;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {

    UserManage getUserInfo(UserManage userManage);
}
