package net.northking.iacmp.common.bean.domain.cms;

import net.northking.iacmp.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 接入系统参数映射表 cms_req_mapping
 *
 * @author wei.chen
 * @date 2019-09-16
 */
public class CmsReqMapping extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 接入系统
     */
    private String sysCode;
    /**
     * 本地字段路径
     */
    private String localPath;
    /**
     * 接入参数路径
     */
    private String remotePath;
    /**
     * 流程名称
     */
    private String processName;
    /**
     * 流程参数名
     */
    private String proParamName;
    /**
     * 流程参数值
     */
    private String proParamValue;
    /**
     * 节点名称
     */
    private String nodeName;
    /**
     * 节点参数名
     */
    private String nodeParamName;
    /**
     * 节点参数值
     */
    private String nodeParamValue;
    /**
     * 状态（0-启用，1-停用）
     */
    private String status;
    /**
     * 备注
     */
    private String remark;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setRemotePath(String remotePath) {
        this.remotePath = remotePath;
    }

    public String getRemotePath() {
        return remotePath;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProParamName(String proParamName) {
        this.proParamName = proParamName;
    }

    public String getProParamName() {
        return proParamName;
    }

    public void setProParamValue(String proParamValue) {
        this.proParamValue = proParamValue;
    }

    public String getProParamValue() {
        return proParamValue;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeParamName(String nodeParamName) {
        this.nodeParamName = nodeParamName;
    }

    public String getNodeParamName() {
        return nodeParamName;
    }

    public void setNodeParamValue(String nodeParamValue) {
        this.nodeParamValue = nodeParamValue;
    }

    public String getNodeParamValue() {
        return nodeParamValue;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("sysCode", getSysCode())
                .append("localPath", getLocalPath())
                .append("remotePath", getRemotePath())
                .append("processName", getProcessName())
                .append("proParamName", getProParamName())
                .append("proParamValue", getProParamValue())
                .append("nodeName", getNodeName())
                .append("nodeParamName", getNodeParamName())
                .append("nodeParamValue", getNodeParamValue())
                .append("status", getStatus())
                .append("remark", getRemark())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
