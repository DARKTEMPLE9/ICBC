package com.icbc.common.system_menu.menuService;

import com.icbc.common.system_menu.menuMapper.MenuMapper;
import com.icbc.entity.menu.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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
}
