package com.icbc.super_shopping.system_menu.service;


import com.icbc.super_shopping.entity.MenuTree;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface SystemtMenuService {
    Map<String, Object> getMenuTree();

    List<MenuTree> getAllMenus();

    String generalMenu(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, Model paramModel) throws IOException;


}
