package com.icbc.super_shopping.system_menu.controller;

import com.icbc.super_shopping.system_menu.entity.UserManage;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/menu")
public class SystemMenuController {


    public String generalMenu(HttpServletRequest request, HttpServletResponse response, Model data) {
        return "";
    }

    public String blank() {
        String retHtml = "system/menu/blank";
        return retHtml;
    }


    /*
    * 跳转权限首页
    * */
    @RequestMapping(value = {"/toMenu0"}, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView toMenu() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/system/menu/main_menu");
        return mv;
    }

    private void setValue(HttpServletRequest request, Model data) {
        try {
            Map<String, String[]> paramsMap = request.getParameterMap();
            for (Map.Entry<String, String[]> entry : paramsMap.entrySet()) {
                data.addAttribute(entry.getKey(), ((String[])entry.getValue())[0]);
            }
        } catch (Exception exception) {}
    }




}
