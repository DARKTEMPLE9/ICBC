package net.northking.iacmp.ams.web.controller.accounting.ledger;

import net.northking.iacmp.common.bean.vo.ams.GasOrgAccountingbookVO;
import net.northking.iacmp.common.bean.vo.ams.GasSmUserVO;
import net.northking.iacmp.common.bean.vo.ams.GlDetailVO;
import net.northking.iacmp.common.bean.vo.ams.GlVoucherVO;
import net.northking.iacmp.constant.Constants;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.server.service.IGasOrgAccountingbookService;
import net.northking.iacmp.server.service.IGasSmUserService;
import net.northking.iacmp.server.service.IGlVoucherService;
import net.northking.iacmp.utils.HiveCacheUtil;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 凭证 信息操作处理
 *
 * @author wei.chen
 * @date 2019-09-23
 */
@Controller
@RequestMapping("/accounting/ledger/glVoucher")
public class GlVoucherController extends BaseController {
    private String prefix = "accounting/ledger/glVoucher";

    @Autowired
    private IGlVoucherService glVoucherService;

    @Autowired
    private IGasSmUserService gasSmUserService;

    @Autowired
    private IGasOrgAccountingbookService gasOrgAccountingbookService;

    private static final int PAGE_RES_CODE_WARN = 301;

    @RequiresPermissions("accounting:ledger:glVoucher:view")
    @GetMapping()
    public String glVoucher(ModelMap mmap) {
		/*long start = System.currentTimeMillis();
		List<GasSmUserVO> smUserList =
				gasSmUserService.selectGasSmUserList(new GasSmUserVO());
		long end1 = System.currentTimeMillis();
		logger.info("HIVE查询用户表耗时{}ms!", (end1 - start));
		List<GasOrgAccountingbookVO> orgAccountingbookList =
				gasOrgAccountingbookService.selectGasOrgAccountingbookList(
						new GasOrgAccountingbookVO());
		long end2 = System.currentTimeMillis();
		logger.info("HIVE查询账簿表耗时{}ms!", (end2 - end1));
		mmap.put("smUserList", smUserList);
		mmap.put("bookList", orgAccountingbookList);*/

        return prefix + "/glVoucher";
    }

    /**
     * 查询凭证列表
     */
    @RequiresPermissions("accounting:ledger:glVoucher:list")
    @PostMapping("/list")
    @ResponseBody
    public AjaxResult list(GlVoucherVO glVoucher) {
        startPage();
        ExecutorService es = Executors.newSingleThreadExecutor();
        String key = UUID.randomUUID().toString().replaceAll("-", "");
        es.submit(new Runnable() {
            @Override
            public void run() {
                logger.info("开始查询总数量......");
                long start = System.currentTimeMillis();
                int count = glVoucherService.selectGlVoucherCount(glVoucher);
                long end1 = System.currentTimeMillis();
                logger.info("HIVE查询凭证表数量耗时{}ms!", (end1 - start));
                if (count > Constants.ACCOUNTING_COUNT_LIMIT) {
                    logger.info("查询凭证列表[{}]结果{}大于{}条，请进一步精确查询！", glVoucher, count, Constants.ACCOUNTING_COUNT_LIMIT);
                    TableDataInfo rspData = new TableDataInfo();
                    rspData.setCode(PAGE_RES_CODE_WARN);
                    rspData.setRows(new ArrayList<>());
                    rspData.setTotal(count);
                    rspData.setMsg("查询总账分录明细列表结果" + count + "大于" + Constants.ACCOUNTING_COUNT_LIMIT + "条，请进一步精确查询！");
                    HiveCacheUtil.putCache(key, rspData);
                }
                List<GlVoucherVO> list = glVoucherService.selectGlVoucherList(glVoucher);
                long end2 = System.currentTimeMillis();
                logger.info("HIVE查询凭证表耗时{}ms!", (end2 - end1));
                HiveCacheUtil.putCache(key, getDataTable(list));
            }
        });
        return toAjax(true, key);
    }

    /**
     * 查看会计档案详细页面
     *
     * @param pkVoucher
     * @param mmap
     * @return
     */
    @GetMapping("/detail/{pkVoucher}")
    @RequiresPermissions("accounting:ledger:gftEntryDtl:detail")
    public String detail(@PathVariable("pkVoucher") String pkVoucher, ModelMap mmap) {
        long start = System.currentTimeMillis();
        List<GlVoucherVO> glVoucherList = glVoucherService.selectGlVoucherById(pkVoucher);
        long end1 = System.currentTimeMillis();
        logger.info("HIVE查询凭证表耗时{}ms!", (end1 - start));
        mmap.put("glVoucher", glVoucherList.get(0));
        List<GlDetailVO> glDetailList = glVoucherService.selectGlDetailList(pkVoucher);
        long end2 = System.currentTimeMillis();
        logger.info("HIVE查询凭证明细表耗时{}ms!", (end2 - end1));
        mmap.put("glDetailList", glDetailList);

        return prefix + "/glVoucherDetail";
    }


    /**
     * 导出凭证列表
     */
    @RequiresPermissions("cms:glVoucher:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(GlVoucherVO glVoucher) {
        List<GlVoucherVO> list = glVoucherService.selectGlVoucherList(glVoucher);
        ExcelUtil<GlVoucherVO> util = new ExcelUtil<>(GlVoucherVO.class);
        return util.exportExcel(list, "glVoucher");
    }

    /**
     * 定时查询异步hive是否返回
     */
    @RequestMapping("/queryHive/{key}")
    @ResponseBody
    public AjaxResult queryHive(@PathVariable("key") String key) {
        if (HiveCacheUtil.containsKey(key)) {
            return toAjax(true, HiveCacheUtil.getCache(key));
        } else {
            return toAjax(false);
        }
    }
}
