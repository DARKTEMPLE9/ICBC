package com.icbc.super_shopping.super_power.controller;

import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/power")
public class SuperPowerController {

    /*
    * 权限首页
    * */
    @RequestMapping(value = "/toPower",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView toPower(){
        ModelAndView modelAndView = new ModelAndView();
        accessedMenus accessedMenus = new accessedMenus();
        accessedMenus.setChilden("1");
        ArrayList<menuObj> menuObjs = new ArrayList<>();
        menuObj menuObject = new menuObj();
        menuObject.setMenuCode("1");
        menuObjs.add(menuObject);
        accessedMenus.setMenuObj(menuObjs);
        modelAndView.addObject("accessedMenus", accessedMenus);
        modelAndView.setViewName("/super-shopping/super-power/power_main.html");
        return modelAndView;
    }

    @Data
    class accessedMenus{

        private String childen;
        private List<menuObj> menuObj;
    }

    @Data
    class menuObj{
        private String menuCode;
    }

}
