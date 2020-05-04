package net.northking.iacmp.ams.web.controller.accounting.ledger;

import net.northking.iacmp.common.bean.vo.ams.GasSmUserVO;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.server.service.IGasSmUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 用户 信息操作处理
 *
 * @author wei.chen
 * @date 2019-09-23
 */
@Controller
@RequestMapping("/accounting/ledger/gasSmUser")
public class GasSmUserController extends BaseController {
    private String prefix = "accounting/ledger/gasSmUser";

    @Autowired
    private IGasSmUserService gasSmUserService;

    /**
     * 跳转选择用户页面
     *
     * @return
     */
    @GetMapping("/selectGasSmUser")
    public String selectUser(ModelMap mmap) {

        return prefix + "/selectGasSmUser";
    }

    /**
     * 跳转选择用户页面
     *
     * @return
     */
    @GetMapping("/selectGasSmUser1")
    public String selectUser1(ModelMap mmap) {

        return prefix + "/selectGasSmUser1";
    }

    /**
     * 查询用户列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(GasSmUserVO gasSmUser) {

        long start = System.currentTimeMillis();
        startPage();
        List<GasSmUserVO> list = gasSmUserService.selectGasSmUserList(gasSmUser);
        long end1 = System.currentTimeMillis();
        logger.info("HIVE查询用户表耗时{}ms!", (end1 - start));
        return getDataTable(list);
    }
}
