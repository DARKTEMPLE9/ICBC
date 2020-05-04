package net.northking.iacmp.cms.adapter;

import com.alibaba.fastjson.JSONObject;
import net.northking.iacmp.constant.CmsConstants;

/**
 * 接入系统参数转换适配器
 *
 * @Author: wei.chen
 * @Date Created: in 2019/9/16 10:40
 */
public class RequestExchangeAdapter {

    RequestExchange requestExchange;

    public RequestExchangeAdapter(String sysCode) {
        if (CmsConstants.SYS_OA_CODE.equals(sysCode)) {
            requestExchange = new OARequestExchange(sysCode);
        } else {
            requestExchange = new DefaultRequestExchange(sysCode);
        }
    }

    public JSONObject exchangeRequest(JSONObject reqObj) {
        return requestExchange.exchangeRequest(reqObj);
    }
}
