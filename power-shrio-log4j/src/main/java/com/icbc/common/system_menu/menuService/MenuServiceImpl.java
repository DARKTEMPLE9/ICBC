package com.icbc.common.system_menu.menuService;

import com.icbc.common.system_menu.menuMapper.MenuMapper;
import com.icbc.entity.mapper.menu.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService{


    @Autowired
    private MenuMapper menuMapper;




    @Override
    public List<TreeNode> getTree() {
        List<TreeNode> list = menuMapper.getTreeList();
        return list;
    }
    /*
    * 获取父节点
    * */
    @Override
    public List<TreeNode> getTreeParent() {
        int i = 0;
        List<TreeNode> treeNodeList =  menuMapper.getTreeParent(i);
        return treeNodeList;
    }

    /*
     * 获取当前父节点下的子节点数据
     * */
    @Override
    public List<TreeNode> getTreeChild(String id) {
        //获取父节点信息
        TreeNode  treeNode = menuMapper.getParent(id);
        //查询子节点信息  父节点id当作子节点pid
        List<TreeNode> treeNodeList = menuMapper.getChildList(id);
        treeNode.setChildNodes(treeNodeList);
        return treeNodeList;
    }
}
