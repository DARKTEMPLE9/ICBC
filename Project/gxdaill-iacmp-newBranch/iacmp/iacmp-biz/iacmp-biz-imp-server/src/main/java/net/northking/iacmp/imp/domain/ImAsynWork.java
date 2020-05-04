package net.northking.iacmp.imp.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 异步任务表 im_asyn_work
 *
 * @author weizhe.fan
 * @date 2019-10-29
 */
public class ImAsynWork {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 逻辑主键
     */
    private String workId;
    /**
     * 客户端系统标识符
     */
    private String sysId;
    /**
     * 影像流水号
     */
    private String busino;
    /**
     * 交易码
     */
    private String tranCode;
    /**
     * 参数集
     */
    private String paramMap;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public String getWorkId() {
        return workId;
    }

    public void setSysId(String sysId) {
        this.sysId = sysId;
    }

    public String getSysId() {
        return sysId;
    }

    public void setBusino(String busino) {
        this.busino = busino;
    }

    public String getBusino() {
        return busino;
    }

    public void setTranCode(String tranCode) {
        this.tranCode = tranCode;
    }

    public String getTranCode() {
        return tranCode;
    }

    public void setParamMap(String paramMap) {
        this.paramMap = paramMap;
    }

    public String getParamMap() {
        return paramMap;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("workId", getWorkId())
                .append("sysId", getSysId())
                .append("busino", getBusino())
                .append("tranCode", getTranCode())
                .append("paramMap", getParamMap())
                .toString();
    }
}
