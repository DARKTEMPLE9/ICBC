package com.icbc.common.multi_data_source.synchronization_data.syncController;

import com.icbc.common.system_menu.menuService.MenuService;
import com.icbc.entity.mapper.menu.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StartService implements ApplicationRunner, Runnable {

    @Autowired
    private MenuService menuService;

    @Autowired
    private endService endService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        System.out.println("=========== 项目启动后，初始化 开始 =============");

        String sss = endService.sss();
        System.out.println(sss);

        System.out.println("=========== 项目启动后，调用结束 =============");
    }

    @Override
    public void run() {

    }
}
