package net.northking.iacmp.ams.web.controller.accounting.ledger;

import net.northking.iacmp.common.bean.vo.ams.GasOrgAccountingbookVO;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.server.service.IGasOrgAccountingbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 账簿 信息操作处理
 *
 * @author wei.chen
 * @date 2019-09-23
 */
@Controller
@RequestMapping("/accounting/ledger/gasOrgAccountingbook")
public class GasOrgAccountingbookController extends BaseController {
    private String prefix = "accounting/ledger/gasOrgAccountingbook";

    @Autowired
    private IGasOrgAccountingbookService gasOrgAccountingbookService;

    /**
     * 跳转选择账簿页面
     *
     * @return
     */
    @GetMapping("/selectGasOrgAccountingbook")
    public String selectBook(ModelMap mmap) {

        return prefix + "/selectGasOrgAccountingbook";
    }

    /**
     * 跳转选择账簿页面
     *
     * @return
     */
    @GetMapping("/selectGasOrgAccountingbook1")
    public String selectBook1(ModelMap mmap) {

        return prefix + "/selectGasOrgAccountingbook1";
    }

    /**
     * 查询账簿列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(GasOrgAccountingbookVO gasOrgAccountingbook) {

        long start = System.currentTimeMillis();
        startPage();
        List<GasOrgAccountingbookVO> list = gasOrgAccountingbookService.selectGasOrgAccountingbookList(gasOrgAccountingbook);
        long end1 = System.currentTimeMillis();
        logger.info("HIVE查询账簿表耗时{}ms!", (end1 - start));
        return getDataTable(list);
    }
}
