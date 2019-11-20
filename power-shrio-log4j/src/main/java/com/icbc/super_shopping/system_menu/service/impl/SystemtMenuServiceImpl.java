package com.icbc.super_shopping.system_menu.service.impl;

import com.icbc.super_shopping.system_menu.entity.MenuInf;
import com.icbc.super_shopping.system_menu.entity.MenuTree;
import com.icbc.super_shopping.system_menu.mapper.SystemMenuMapper;
import com.icbc.super_shopping.system_menu.service.SystemtMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

public class SystemtMenuServiceImpl implements SystemtMenuService {

    @Autowired
    private SystemMenuMapper systemMenuMapper;

    public Map<String, Object> getMenuTree() {
        List<MenuInf> menus = this.systemMenuMapper.getAllMenus();
        if (menus == null || menus.size() == 0) {
            return null;
        }
        Map<String, Object> menuTree = buildMenuTree(menus);
        return menuTree;
    }








    public List<MenuTree> getAllMenus() {
        List<MenuTree> menus = this.systemMenuMapper.getAllMenus2();
        if (null != menus && !menus.isEmpty()) {
            return menus;
        }
        return null;
    }







    public Map<String, Object> getAllAccessedMenu() {
        List<MenuInf> menus = this.systemMenuMapper.getAllMenus();
        Map<String, Object> menuTree = buildMenuTree(menus);
        return menuTree;
    }







    private Map<String, Object> buildMenuTree(List<MenuInf> menus) {
        Map<String, Object> root = new HashMap<>();
        root.put("menuId", "0");
        root.put("children", new ArrayList());
        for (MenuInf oneMenu : menus) {
            addTo(oneMenu, root);
        }
        return root;
    }








    private void addTo(MenuInf menu, Map<String, Object> menuTree) {
        List<Map<String, Object>> children = (List<Map<String, Object>>)menuTree.get("children");
        String treeMenuId = (String)menuTree.get("menuId");
        MenuInf menuObj = (MenuInf)menuTree.get("menuObj");
        int lvl = (menu.getMenulevel() == null) ? 1 : menu.getMenulevel().shortValue();
        int lvlObj = (menuObj == null || menuObj.getMenulevel() == null) ? 1 : menuObj.getMenulevel().shortValue();


        if (StringUtils.equals(menu.getParentCode(), treeMenuId) || (treeMenuId
                .equals("0") && menu.getMenulevel().shortValue() == 1)) {

            Map<String, Object> node = new HashMap<>();
            node.put("menuId", menu.getMenuCode());
            node.put("menuObj", menu);
            node.put("children", new ArrayList());

            int len = children.size();
            for (int i = 0; i < children.size(); i++) {
                if (((MenuInf)((Map)children.get(i)).get("menuObj")).getMenuOrder().longValue() > ((MenuInf)node.get("menuObj")).getMenuOrder().longValue()) {
                    children.add(i, node);

                    break;
                }
            }
            if (len == children.size()) children.add(node);
        } else if ((treeMenuId

                .equals("0") && lvl > 1) || (lvl > lvlObj &&

                StringUtils.equals(menu.getMenuCode().substring(0, lvlObj * 3), menuObj.getMenuCode()))) {



            for (int i = 0; i < children.size(); i++) {
                Map<String, Object> subTree = children.get(i);
                MenuInf subMenuObj = (MenuInf)subTree.get("menuObj");
                if (StringUtils.equals(menu.getMenuCode().substring(0, subMenuObj.getMenulevel().shortValue() * 3), subMenuObj.getMenuCode())) {
                    addTo(menu, subTree);
                    break;
                }
            }
        }
    }






    public Map<String, Object> getAllAccessedMenu(String currentUserId) { return null; }





    public String generalMenu(HttpServletRequest request, HttpServletResponse response, Model data) throws IOException { return null; }
}
