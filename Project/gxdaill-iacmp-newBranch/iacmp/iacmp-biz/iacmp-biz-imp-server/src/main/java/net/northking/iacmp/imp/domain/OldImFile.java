package net.northking.iacmp.imp.domain;

import net.northking.iacmp.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 文件表 im_file
 *
 * @author wei.chen
 * @date 2019-10-22
 */
public class OldImFile extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private String id;
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 文件状态（0-正常；1-已废弃）
     */
    private String status;
    /**
     * 文件批次
     */
    private String batchId;
    /**
     * 文件顺序
     */
    private BigDecimal serialno;
    /**
     * 文件存放路径
     */
    private String filePath;
    /**
     *
     */
    private BigDecimal fileSize;
    /**
     * 文件类型
     */
    private String fileType;
    /**
     * 所属单据类型
     */
    private String billId;
    /**
     * 单据类型名称
     */
    private String billName;
    /**
     * 备注
     */
    private String remark;
    /**
     * 上传人编号
     */
    private String createUser;
    /**
     * 上传人名称
     */
    private String createUserName;
    /**
     * 所属系统（用于分表）
     */
    private String systemFlag;
    /**
     * 文件来源
     */
    private String fileSource;
    /**
     * 系统标识状态
     */
    private Integer sysFlagInt;
    /**
     * 主键
     */
    private Long fileId;
    /**
     *
     */
    private String userCodeId;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setSerialno(BigDecimal serialno) {
        this.serialno = serialno;
    }

    public BigDecimal getSerialno() {
        return serialno;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFileSize(BigDecimal fileSize) {
        this.fileSize = fileSize;
    }

    public BigDecimal getFileSize() {
        return fileSize;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileType() {
        return fileType;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }

    public String getBillName() {
        return billName;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setSystemFlag(String systemFlag) {
        this.systemFlag = systemFlag;
    }

    public String getSystemFlag() {
        return systemFlag;
    }

    public void setFileSource(String fileSource) {
        this.fileSource = fileSource;
    }

    public String getFileSource() {
        return fileSource;
    }

    public void setSysFlagInt(Integer sysFlagInt) {
        this.sysFlagInt = sysFlagInt;
    }

    public Integer getSysFlagInt() {
        return sysFlagInt;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setUserCodeId(String userCodeId) {
        this.userCodeId = userCodeId;
    }

    public String getUserCodeId() {
        return userCodeId;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("fileName", getFileName())
                .append("status", getStatus())
                .append("batchId", getBatchId())
                .append("createTime", getCreateTime())
                .append("serialno", getSerialno())
                .append("filePath", getFilePath())
                .append("fileSize", getFileSize())
                .append("fileType", getFileType())
                .append("billId", getBillId())
                .append("billName", getBillName())
                .append("remark", getRemark())
                .append("createUser", getCreateUser())
                .append("createUserName", getCreateUserName())
                .append("systemFlag", getSystemFlag())
                .append("fileSource", getFileSource())
                .append("sysFlagInt", getSysFlagInt())
                .append("fileId", getFileId())
                .append("userCodeId", getUserCodeId())
                .toString();
    }
}
