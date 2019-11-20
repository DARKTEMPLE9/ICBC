package com.icbc.super_shopping.system_menu.mapper;

import com.icbc.super_shopping.system_menu.entity.MenuInf;
import com.icbc.super_shopping.system_menu.entity.MenuTree;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SystemMenuMapper {

    List<MenuInf> getAllMenus();

    List<MenuTree> getAllMenus2();

    List<MenuInf> getMenus(List<String> paramList);

}
