package net.northking.iacmp.result;/**
 * @Description:
 * @Author: weizhe.fan
 * @CreateDate: 2019/9/12
 */

import org.apache.poi.ss.formula.functions.T;

import java.io.Serializable;

/**
 * 返回结果集
 *
 * @Description:返回结果集
 * @Author: weizhe.fan
 * @CreateDate: 2019/9/12
 */
public class TotalResult<T> implements Serializable {

    private String totalResultCode;
    private String totalResultMsg;
    private transient T totalResultData;

    public TotalResult() {

    }

    public TotalResult(ResultCode resultCode, T data) {
        this.totalResultCode = resultCode.code();
        this.totalResultMsg = resultCode.msg();
        this.totalResultData = data;
    }

    public TotalResult(ResultCode resultCode) {
        this.totalResultCode = resultCode.code();
        this.totalResultMsg = resultCode.msg();
    }

    public String getTotalResultCode() {
        return totalResultCode;
    }

    public void setTotalResultCode(String totalResultCode) {
        this.totalResultCode = totalResultCode;
    }

    public String getTotalResultMsg() {
        return totalResultMsg;
    }

    public void setTotalResultMsg(String totalResultMsg) {
        this.totalResultMsg = totalResultMsg;
    }


    public T getTotalResultData() {
        return totalResultData;
    }

    public void setTotalResultData(T totalResultData) {
        this.totalResultData = totalResultData;
    }
}
