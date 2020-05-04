package net.northking.iacmp.ams.web.controller.accounting.ledger;

import net.northking.iacmp.common.bean.vo.ams.GasBdAccasoaVO;
import net.northking.iacmp.constant.Constants;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.framework.util.ShiroUtils;
import net.northking.iacmp.server.service.IGasBdAccasoaService;
import net.northking.iacmp.server.service.IGasBdAccasoaService;
import net.northking.iacmp.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 会计科目 信息操作处理
 *
 * @author wei.chen
 * @date 2019-09-23
 */
@Controller
@RequestMapping("/accounting/ledger/gasBdAccasoa")
public class GasBdAccasoaController extends BaseController {
    private String prefix = "accounting/ledger/gasBdAccasoa";

    @Autowired
    private IGasBdAccasoaService gasBdAccasoaService;

    /**
     * 跳转选择会计科目页面
     *
     * @return
     */
    @GetMapping("/selectGasBdAccasoa")
    public String selectUser(ModelMap mmap) {

        return prefix + "/selectGasBdAccasoa";
    }

    /**
     * 查询会计科目列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(GasBdAccasoaVO gasBdAccasoa) {

        long start = System.currentTimeMillis();
        startPage();
        List<GasBdAccasoaVO> list = gasBdAccasoaService.selectGasBdAccasoaList(gasBdAccasoa);
        long end1 = System.currentTimeMillis();
        logger.info("HIVE查询会计科目表耗时{}ms!", (end1 - start));
        String dispname = "";
        for (GasBdAccasoaVO bdAccasoaVO : list) {
            dispname = bdAccasoaVO.getDispname();
            if (dispname.indexOf("\\") != -1) {
                dispname = dispname.substring(0, dispname.indexOf("\\"));
                bdAccasoaVO.setDispname(dispname);
            }
        }
        return getDataTable(list);
    }
}
