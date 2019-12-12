package com.icbc.super_shopping.login.loginController;

import com.icbc.common.user_power.userMapper.UserInfoMapper;
import com.icbc.super_shopping.entity.UserManage;
import com.icbc.super_shopping.login.loginService.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = {"loginUser"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Integer loginUser(@RequestBody UserManage userManage, HttpServletRequest request) {
        UserManage user = loginService.getUserInfo(userManage);
        if (user != null) {
            return 0;
        }
        return 1;
    }

    /*跳转登录首页*/
    @RequestMapping(value = {"/login"}, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView toLogin() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/system/loginMain");
        return mv;
    }

}
