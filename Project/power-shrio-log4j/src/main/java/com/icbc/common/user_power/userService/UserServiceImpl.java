package com.icbc.common.user_power.userService;

import com.icbc.common.user_power.userMapper.UserInfoMapper;
import com.icbc.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public List<UserInfo> getUserPage() {
        List<UserInfo> userPage = userInfoMapper.getUserPage();
        return userPage;
    }
}
