package net.northking.iacmp.ams.web.controller.accounting.ledger;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.northking.iacmp.common.bean.domain.ams.GftEntryDtl;
import net.northking.iacmp.common.bean.domain.ams.ImFile;
import net.northking.iacmp.common.bean.domain.ams.ImImage;
import net.northking.iacmp.common.bean.dto.ams.GftEntryDtlDto;
import net.northking.iacmp.common.bean.feign.ams.CmsFileFeignClient;
import net.northking.iacmp.common.bean.vo.ams.GasBdAccasoaVO;
import net.northking.iacmp.common.bean.vo.ams.GasOrgAccountingbookVO;
import net.northking.iacmp.common.bean.vo.ams.GasOrgOrgsVO;
import net.northking.iacmp.common.bean.vo.ams.GasSmUserVO;
import net.northking.iacmp.constant.AmsConstants;
import net.northking.iacmp.constant.CmsConstants;
import net.northking.iacmp.constant.Constants;
import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;
import net.northking.iacmp.core.page.TableDataInfo;
import net.northking.iacmp.framework.util.SysConfigInitParamsUtils;
import net.northking.iacmp.result.ResultCode;
import net.northking.iacmp.server.service.IAmsGftEntryDtlService;
import net.northking.iacmp.server.service.IGasBdAccasoaService;
import net.northking.iacmp.server.service.IGasOrgAccountingbookService;
import net.northking.iacmp.server.service.IGasOrgOrgsService;
import net.northking.iacmp.server.service.IGasSmUserService;
import net.northking.iacmp.server.service.IImFileService;
import net.northking.iacmp.server.service.IImImageService;
import net.northking.iacmp.utils.StringUtils;
import net.northking.iacmp.utils.http.HttpUtils;
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

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 总账分录明细 信息操作处理
 *
 * @author wxy
 * @date 2019-08-20
 */
@Controller
@RequestMapping("/accounting/ledger/gftEntryDtl")
public class AmsGftEntryDtlController extends BaseController {

    //test
    private String prefix = "accounting/ledger/gftEntryDtl";

    private static final String AMT_DIR_DEBIT = "D";

    private static final String AMT_DIR_CREDIT = "C";


    private static final String UIP_URL = "uipUrl";

    private static final String UIP_GET_UIP_TRAN_CODE = "2002";

    private static final String AMS_SYS_ID = "AMS";

    private static final String UIP_TRAN_CODE_KEY = "tranCode";

    private static final String UIP_SYS_ID_KEY = "sysId";

    private static final String UIP_SYS_BUSI_NO_KEY = "SysBusiNo";

    private static final String UIP_TRADE_RESULT_KEY = "tradeResult";

    private static final String UIP_TRADE_DESC_KEY = "tradeDesc";

    private static final String UIP_TRADE_RESULT_SUCCESS = "00000000000";

    private static final String UIP_FILE_INFO_KEY = "fileInfo";

    private static final int PAGE_RES_CODE_WARN = 301;

    @Autowired
    private IAmsGftEntryDtlService amsGftEntryDtlService;

    @Autowired
    private IGasSmUserService gasSmUserService;

    @Autowired
    private IGasOrgAccountingbookService gasOrgAccountingbookService;

    @Autowired
    private IGasOrgOrgsService gasOrgOrgsService;

    @Autowired
    private IGasBdAccasoaService gasBdAccasoaService;

    @Autowired
    private IImFileService imFileService;

    @Autowired
    private IImImageService imImageService;

    @Resource
    private CmsFileFeignClient cmsFileFeignClient;

    @RequiresPermissions("accounting:ledger:gftEntryDtl:view")
    @GetMapping()
    public String gftEntryDtl(ModelMap mmap) {
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
		List<GasOrgOrgsVO> orgOrgsList =
				gasOrgOrgsService.selectGasOrgOrgsList(new GasOrgOrgsVO());
		long end3 = System.currentTimeMillis();
		logger.info("HIVE查询部门表耗时{}ms!", (end3 - end2));
		List<GasBdAccasoaVO> bdAccasoaList =
				gasBdAccasoaService.selectGasBdAccasoaList(new GasBdAccasoaVO());
		long end4 = System.currentTimeMillis();
		logger.info("HIVE查询会计科目表耗时{}ms!", (end4 - end3));
		String dispname = "";
		for (GasBdAccasoaVO bdAccasoaVO : bdAccasoaList) {
			dispname = bdAccasoaVO.getDispname();
			if (dispname.indexOf("\\") != -1) {
				dispname = dispname.substring(0, dispname.indexOf("\\"));
				bdAccasoaVO.setDispname(dispname);
			}
		}
		mmap.put("smUserList", smUserList);
		mmap.put("bookList", orgAccountingbookList);
		mmap.put("orgOrgsList", orgOrgsList);
		mmap.put("bdAccasoaList", bdAccasoaList);*/
        return prefix + "/amsGftEntryDtl";
    }

    /**
     * 查询总账分录明细列表
     */
    @RequiresPermissions("accounting:ledger:gftEntryDtl:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(GftEntryDtlDto gftEntryDtl) {
        // 检查参数，记账日期起止必须有，
        if (StringUtils.isEmpty(gftEntryDtl.getTallyDtStart()) ||
                StringUtils.isEmpty(gftEntryDtl.getTallyDtEnd())) {
            TableDataInfo rspData = new TableDataInfo();
            rspData.setCode(PAGE_RES_CODE_WARN);
            rspData.setRows(new ArrayList<>());
            rspData.setTotal(0);
            rspData.setMsg("起止记账日期不能为空！");
            return rspData;
        }
        logger.info("开始查询总数量......");
        long start = System.currentTimeMillis();
        int count = amsGftEntryDtlService.selectGftEntryDtlCount(gftEntryDtl);
        long end1 = System.currentTimeMillis();
        logger.info("HIVE查询分录表数量耗时{}ms!", (end1 - start));
        if (count > Constants.ACCOUNTING_COUNT_LIMIT) {
            logger.info("查询总账分录明细列表[{}]结果{}大于{}条，请进一步精确查询！", gftEntryDtl, count, Constants.ACCOUNTING_COUNT_LIMIT);
            TableDataInfo rspData = new TableDataInfo();
            rspData.setCode(PAGE_RES_CODE_WARN);
            rspData.setRows(new ArrayList<>());
            rspData.setTotal(count);
            rspData.setMsg("查询总账分录明细列表结果" + count + "大于" + Constants.ACCOUNTING_COUNT_LIMIT + "条，请进一步精确查询！");
            return rspData;
        }
        startPage();
        List<GftEntryDtl> list = amsGftEntryDtlService.selectGftEntryDtlList(gftEntryDtl);
        long end2 = System.currentTimeMillis();
        logger.info("HIVE查询分录表耗时{}ms!", (end2 - end1));

        return getDataTable(list);
    }


    /**
     * 导出总账分录明细列表
     */
    @RequiresPermissions("accounting:ledger:gftEntryDtl:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(GftEntryDtlDto gftEntryDtl) {
        List<GftEntryDtl> list = amsGftEntryDtlService.selectGftEntryDtlList(gftEntryDtl);
        ExcelUtil<GftEntryDtl> util = new ExcelUtil<>(GftEntryDtl.class);
        return util.exportExcel(list, "gftEntryDtl");
    }

    /**
     * 查看会计档案详细页面
     *
     * @param dealNum
     * @param mmap
     * @return
     */
    @GetMapping("/detail/{dealNum}")
    @RequiresPermissions("accounting:ledger:gftEntryDtl:detail")
    public String detail(GftEntryDtlDto gftEntryDtl, @PathVariable("dealNum") String dealNum, ModelMap mmap) {
        //GftEntryDtlDto gftEntryDtl = new GftEntryDtlDto();
        gftEntryDtl.setDealNum(dealNum);
        gftEntryDtl.setAmtDir(AMT_DIR_DEBIT);
        long start = System.currentTimeMillis();
        List<GftEntryDtl> debList = amsGftEntryDtlService.selectGftEntryDtlList(gftEntryDtl);
        long end1 = System.currentTimeMillis();
        logger.info("HIVE查询借方分录耗时{}ms!", (end1 - start));
        mmap.put("debList", debList);
        gftEntryDtl.setAmtDir(AMT_DIR_CREDIT);
        List<GftEntryDtl> crdList = amsGftEntryDtlService.selectGftEntryDtlList(gftEntryDtl);
        long end2 = System.currentTimeMillis();
        logger.info("HIVE查询贷方分录耗时{}ms!", (end2 - end1));
        mmap.put("crdList", crdList);

        return prefix + "/detail";
    }

    /**
     * 查看会计档案相关文档
     *
     * @param dealNum
     * @return
     */
    @RequiresPermissions("accounting:ledger:gftEntryDtl:detail")
    @PostMapping("/listAms/{dealNum}")
    @ResponseBody
    public AjaxResult listAms(@PathVariable("dealNum") String dealNum) {
        logger.info("listAms starting......");
        JSONArray jsonArray = new JSONArray();
        long start = System.currentTimeMillis();
        List<ImFile> fileList = imFileService.selectImFilesByBusiNo(dealNum);
        long end1 = System.currentTimeMillis();
        logger.info("查询档案系统文件耗时{}ms!", (end1 - start));
        JSONObject jsonObject;
        for (ImFile file : fileList) {
            jsonObject = new JSONObject();
            jsonObject.put(AmsConstants.FILENAME, file.getFileName());
            jsonObject.put(AmsConstants.FILEPATH, getRealPath(file.getFilePath()));
            jsonObject.put(AmsConstants.BILLNAME, file.getBillName());
            jsonObject.put(AmsConstants.SYSTEMFLAG, file.getSystemFlag());
            jsonArray.add(jsonObject);
        }
        List<ImImage> imageList = imImageService.selectImImagesByBusiNo(dealNum);
        long end2 = System.currentTimeMillis();
        logger.info("查询档案系统影像耗时{}ms!", (end2 - end1));
        for (ImImage image : imageList) {
            jsonObject = new JSONObject();
            jsonObject.put(AmsConstants.FILENAME, image.getImageName());
            jsonObject.put(AmsConstants.FILEPATH, getRealPath(image.getImagePath()));
            jsonObject.put(AmsConstants.BILLNAME, image.getBillName());
            jsonObject.put(AmsConstants.SYSTEMFLAG, image.getSystemFlag());
            jsonArray.add(jsonObject);
        }
        jsonObject = new JSONObject();
        jsonObject.put(AmsConstants.OPERATIONCODE, dealNum);
        String result = cmsFileFeignClient.selectCmsFileListByOpts(jsonObject.toJSONString());
        long end3 = System.currentTimeMillis();
        logger.info("查询内容管理平台文件影像耗时{}ms!", (end3 - end2));
        jsonObject = JSONObject.parseObject(result);
        if (ResultCode.SUCCESS.code().equals(jsonObject.getString(AmsConstants.TOTALRESULTCODE))) {
            JSONObject fileObj;
            JSONArray fileArray = jsonObject.getJSONArray(AmsConstants.FILELIST);
            for (int i = 0; i < fileArray.size(); i++) {
                fileObj = fileArray.getJSONObject(i);
                jsonObject = new JSONObject();
                jsonObject.put(AmsConstants.FILENAME, fileObj.getString(CmsConstants.FILENAME));
                jsonObject.put(AmsConstants.FILEPATH, fileObj.getString(CmsConstants.FILEPATH));
                jsonObject.put(AmsConstants.BILLNAME, fileObj.getString(CmsConstants.BILLCODE));
                jsonObject.put(AmsConstants.SYSTEMFLAG, fileObj.getString(CmsConstants.SYSCODE));
                jsonArray.add(jsonObject);
            }
        }
        logger.info("查看附件：{}", jsonArray.toJSONString());

        return AjaxResult.success(jsonArray);
    }

    /**
     * 查看会计档案相关影像
     *
     * @param dealNum
     * @return
     */
    @RequiresPermissions("accounting:ledger:gftEntryDtl:detail")
    @PostMapping("/listUip/{dealNum}")
    @ResponseBody
    public AjaxResult listUip(@PathVariable("dealNum") String dealNum) throws IOException {
        logger.info("listUip starting......");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(UIP_TRAN_CODE_KEY, UIP_GET_UIP_TRAN_CODE);
        jsonObject.put(UIP_SYS_ID_KEY, AMS_SYS_ID);
        jsonObject.put(UIP_SYS_BUSI_NO_KEY, dealNum);
        long start = System.currentTimeMillis();
        String result = HttpUtils.sendHttpJson(SysConfigInitParamsUtils.getConfig(UIP_URL), jsonObject.toJSONString());
        long end1 = System.currentTimeMillis();
        logger.info("调用影像系统2002接口耗时{}ms!", (end1 - start));
        JSONObject resultObj = JSONObject.parseObject(result);
        if (UIP_TRADE_RESULT_SUCCESS.equals(resultObj.getString(UIP_TRADE_RESULT_KEY))) {
            logger.info("影像返回成功！");
        } else {
            logger.warn("影像返回失败：{}[{}]",
                    resultObj.getString(UIP_TRADE_RESULT_KEY),
                    resultObj.getString(UIP_TRADE_DESC_KEY));
        }

        return AjaxResult.success(resultObj.get(UIP_FILE_INFO_KEY));
    }

    /**
     * 获取系统环境的ip
     *
     * @return
     * @author chenwei
     * @date 2019-11-22
     */
    private String getServerIp() {
        String system = System.getProperty("os.name").toLowerCase();
        if (system.indexOf("windows") >= 0) {
            return SysConfigInitParamsUtils.getConfig(AmsConstants.WINDOWS_FILETRANSIP);
        } else {
            return SysConfigInitParamsUtils.getConfig(AmsConstants.FILETRANSIP);
        }
    }

    /**
     * 获取完整路径
     *
     * @return
     * @author chenwei
     * @date 2019-11-22
     */
    private String getRealPath(String filePath) {
        return "http://" + getServerIp() + ":" +
                SysConfigInitParamsUtils.getConfig(AmsConstants.FILETRANSPORT) +
                SysConfigInitParamsUtils.getConfig(AmsConstants.FILETRANSPATHDOWN) +
                filePath;
    }
}
