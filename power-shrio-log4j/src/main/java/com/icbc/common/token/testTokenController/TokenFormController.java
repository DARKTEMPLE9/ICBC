package com.icbc.common.token.testTokenController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("token")
public class TokenFormController {

    @RequestMapping(value = "/toAddForm", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView toAddForm(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("tokenFormTest/addFrom");
        return modelAndView;
    }
}
