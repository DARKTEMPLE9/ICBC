package com.icbc.common.token.testTokenController;

import com.icbc.common.token.Token;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("token")
public class TokenFormController {

    /*
    * 跳转至新增页面测试token生成及其作用产生
    * */
    @RequestMapping(value = "/toAddForm", method = {RequestMethod.GET, RequestMethod.POST})
    @Token(save = true)
    public ModelAndView toAddForm(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("tokenFormTest/addFrom");
        request.getSession().getAttribute("token");
        return modelAndView;
    }



}
