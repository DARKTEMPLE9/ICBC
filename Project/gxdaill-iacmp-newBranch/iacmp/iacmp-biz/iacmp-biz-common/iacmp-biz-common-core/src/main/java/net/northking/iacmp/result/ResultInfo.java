package net.northking.iacmp.result;

import java.io.Serializable;

/**
 * @Title: 结果信息类
 * @Author: weizhe.fan
 * @CreateDate: 2019/09/11
 * @Version:0.1
 */
public class ResultInfo<T> implements Serializable {
    private String resultCode;
    private String resultMsg;
    private String fileUrl;
    private String md5;
    private String hadoopType;
    private String willPath;
    private Long fileId;
    private Long orderNum;
    private transient T resultData;

    public ResultInfo(ResultCode success, ResultInfo<T> resultInfo) {

    }

    @Override
    public String toString() {
        return "ResultInfo{" +
                "resultCode='" + resultCode + '\'' +
                ", resultMsg='" + resultMsg + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                ", md5='" + md5 + '\'' +
                ", hadoopType='" + hadoopType + '\'' +
                ", willPath='" + willPath + '\'' +
                ", fileId=" + fileId +
                ", orderNum=" + orderNum +
                ", resultData=" + resultData +
                '}';
    }

    public ResultInfo() {
        this(ResultCode.UPLOAD_SUCCESS, "success");
    }


    public ResultInfo(String resultCode, String resultMsg, T data) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.resultData = data;
    }

    public ResultInfo(ResultCode resultCode, String message, T data) {
        this.resultCode = resultCode.code();
        this.resultMsg = message;
        this.resultData = data;
    }

    public ResultInfo(String resultCode, String message, String fileUrl, Long fileId) {
        this.resultCode = resultCode;
        this.resultMsg = message;
        this.fileUrl = fileUrl;
        this.fileId = fileId;
    }

    public ResultInfo(ResultCode resultCode, String message) {
        this.resultCode = resultCode.code();
        this.resultMsg = message;
    }

    public ResultInfo(String resultCode, String message) {
        this.resultCode = resultCode;
        this.resultMsg = message;
    }

    public ResultInfo(ResultCode resultCode) {
        this.resultCode = resultCode.code();
        this.resultMsg = resultCode.msg();
    }


    public ResultInfo(ResultCode resultCode, T data) {
        this.resultCode = resultCode.code();
        this.resultMsg = resultCode.msg();
        this.resultData = data;
    }


    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public T getResultData() {
        return resultData;
    }

    public void setResultData(T resultData) {
        this.resultData = resultData;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileUrl() {
        return fileUrl;
    }


    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public Long getFileId() {
        return fileId;
    }

    public Long getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Long orderNum) {
        this.orderNum = orderNum;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getHadoopType() {
        return hadoopType;
    }

    public void setHadoopType(String hadoopType) {
        this.hadoopType = hadoopType;
    }

    public String getWillPath() {
        return willPath;
    }

    public void setWillPath(String willPath) {
        this.willPath = willPath;
    }
}
