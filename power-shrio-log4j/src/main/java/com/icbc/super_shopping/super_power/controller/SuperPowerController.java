package com.icbc.super_shopping.super_power.controller;

import com.icbc.super_shopping.super_power.entity.UserManage;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/power")
public class SuperPowerController {


    /*
    * 用户列表查询
    * */
    @RequestMapping(value = "/getUserPage",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public UserManage getUserPage(UserManage userManage){

        return null;
    }

    /*
    * 权限首页
    * */
    @RequestMapping(value = "/toPower",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView toPower(){
        ModelAndView modelAndView = new ModelAndView();
        accessedMenus accessedMenus = new accessedMenus();
        List<Children> childenList = new ArrayList<Children>();
        Children children = new Children();
        MenuObj menuObj = new MenuObj();
        //menuObj.setMenuCOde("1");
        //menuObj.setMenuName("sadfasdf");
        //children.setMenuObj(menuObj);
        childenList.add(children);
        //accessedMenus.setList(childenList);
        modelAndView.addObject("accessedMenus", accessedMenus);
        modelAndView.setViewName("/super-shopping/super-power/power_main.html");
        return modelAndView;
    }

    @RequestMapping(value = "/welcome",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView welcome() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/super-shopping/super-power/welcome.html");
        return modelAndView;
    }

    @Data
    class accessedMenus{

        private List<Children> list;

    }

    @Data
    class Children{
        private MenuObj menuObj;
    }
    @Data
    class MenuObj{
        private String menuName;
        private String menuCOde;
    }
}
