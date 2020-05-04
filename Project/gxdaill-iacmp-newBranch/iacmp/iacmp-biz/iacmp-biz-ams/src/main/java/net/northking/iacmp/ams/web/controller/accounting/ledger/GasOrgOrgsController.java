package net.northking.iacmp.ams.web.controller.accounting.ledger;

import net.northking.iacmp.common.bean.vo.ams.GasOrgOrgsVO;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.server.service.IGasOrgOrgsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 部门 信息操作处理
 *
 * @author wei.chen
 * @date 2019-09-23
 */
@Controller
@RequestMapping("/accounting/ledger/gasOrgOrgs")
public class GasOrgOrgsController extends BaseController {
    private String prefix = "accounting/ledger/gasOrgOrgs";

    @Autowired
    private IGasOrgOrgsService gasOrgOrgsService;

    /**
     * 跳转选择部门页面
     *
     * @return
     */
    @GetMapping("/selectGasOrgOrgs")
    public String selectOrg(ModelMap mmap) {

        return prefix + "/selectGasOrgOrgs";
    }

    /**
     * 查询部门列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(GasOrgOrgsVO gasOrgOrgs) {

        long start = System.currentTimeMillis();
        startPage();
        List<GasOrgOrgsVO> list = gasOrgOrgsService.selectGasOrgOrgsList(gasOrgOrgs);
        long end1 = System.currentTimeMillis();
        logger.info("HIVE查询部门表耗时{}ms!", (end1 - start));
        return getDataTable(list);
    }
}
