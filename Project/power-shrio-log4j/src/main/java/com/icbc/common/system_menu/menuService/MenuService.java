package com.icbc.common.system_menu.menuService;

import com.icbc.entity.mapper.menu.TreeNode;

import java.util.List;

public interface MenuService {

    List<TreeNode> getTree();

     /*
     * 获取全部父节点
     * */
    List<TreeNode> getTreeParent();

    /*
     * 获取当前父节点下的子节点数据
     * */
    List<TreeNode> getTreeChild(String id);
}
