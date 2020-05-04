package net.northking.iacmp.cms.adapter;

import com.alibaba.fastjson.JSONObject;

/**
 * 对接系统参数转换接口
 *
 * @Author: wei.chen
 * @Date Created: in 2019/9/16 9:54
 */
public interface RequestExchange {

    JSONObject exchangeRequest(JSONObject reqObj);
}
