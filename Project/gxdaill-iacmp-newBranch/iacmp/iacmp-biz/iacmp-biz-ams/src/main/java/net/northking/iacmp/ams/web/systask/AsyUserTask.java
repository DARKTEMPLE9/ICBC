package net.northking.iacmp.ams.web.systask;

import net.northking.iacmp.ams.web.controller.system.SysUserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author：Yanqingyu
 * @ClassName:AsyUserTask
 * @Description：定时同步AD域用户
 * @Date：Create in 10:32 AM2019/12/27
 * @Modified by:
 * @Version:1.0
 */
@Component("asyUserTask")
public class AsyUserTask {

    @Autowired
    SysUserController sysUserController;

    public void asyUserTask() {

        sysUserController.dataSyn();
    }
}
