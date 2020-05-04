package com.icbc.common.multi_data_source.synchronization_data.syncController;

import com.icbc.common.system_menu.menuService.MenuService;
import com.icbc.entity.mapper.menu.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class endService implements Runnable {

    @Autowired
    private MenuService menuService;

    public String sss() {
        System.out.println("=========== endService调用成功  =============");
        Thread thread = new Thread();
        thread.setName("开始一个新的线程");
        System.out.println("=========== 开启线程 成功  =============");
        thread.start();
        /*List<TreeNode> treeParent = menuService.getTreeParent();
        for (int i = 0; i < treeParent.size(); i++) {
            System.out.println(treeParent.get(i));
        }*/

        System.out.println("=========== 调用 @Autowired  成功=============");
        return "执行完毕";
    }

    @Override
    public void run() {

    }
}
