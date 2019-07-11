package com.icbc.common.system_menu.menuService;

import com.icbc.entity.mapper.menu.TreeNode;

import java.util.List;

public interface MenuService {

    List<TreeNode> getTree();
}
