package com.icbc.common.user_power.userController;

import com.icbc.common.user_power.userService.UserService;
import com.icbc.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getUserPage",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public List<UserInfo> getUserPage(){
        return userService.getUserPage();
    }
}
