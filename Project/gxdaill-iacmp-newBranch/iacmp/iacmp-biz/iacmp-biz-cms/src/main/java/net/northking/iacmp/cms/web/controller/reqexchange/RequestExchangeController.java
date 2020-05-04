package net.northking.iacmp.cms.web.controller.reqexchange;


import com.alibaba.fastjson.JSON;

import net.northking.iacmp.cms.adapter.RequestExchangeAdapter;


import net.northking.iacmp.core.controller.BaseController;
import net.northking.iacmp.core.domain.AjaxResult;


import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 对接系统参数转换
 *
 * @author wei.chen
 * @date 2019-09-16
 */
@Controller
@RequestMapping("/cms/reqExchange")
public class RequestExchangeController extends BaseController {

    /**
     * OA请求参数转换
     */
    //@RequiresPermissions("cms:cmsSystem:remove")
    @GetMapping("/exchange/{sysCode}")
    @ResponseBody
    public AjaxResult exchangeRequest(@RequestBody String json,
                                      @PathVariable("sysCode") String sysCode) {
        RequestExchangeAdapter adapter = new RequestExchangeAdapter(sysCode);
        return AjaxResult.success(adapter.exchangeRequest(JSON.parseObject(json)));
    }

}
