package net.northking.iacmp.imp.domain;

import net.northking.iacmp.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 影像表 im_image
 *
 * @author wei.chen
 * @date 2019-10-22
 */
public class OldImImage extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 影像主键
     */
    private String id;
    /**
     * 影像名称
     */
    private String imageName;
    /**
     * 影像状态（0-正常；1-已废弃）
     */
    private String status;
    /**
     * 批次ID
     */
    private String batchId;
    /**
     * 影像顺序
     */
    private BigDecimal serialno;
    /**
     * ECM系统文件ID
     */
    private String ecmFileId;
    /**
     * 影像宽度
     */
    private String width;
    /**
     * 影像高度
     */
    private String height;
    /**
     * 单据类型
     */
    private String billId;
    /**
     * ECM的压缩图片文件ID
     */
    private String compressFileId;
    /**
     * 影像模版ID
     */
    private String templateId;
    /**
     * 影像大小
     */
    private BigDecimal imageSize;
    /**
     * 影像存放路径
     */
    private String imagePath;
    /**
     * 识别结果
     */
    private String ocrResult;
    /**
     * 识别状态(0-待识别;1-已识别;)
     */
    private String ocrStatus;
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
     * 删除时间
     */
    private Date deleteTime;
    /**
     * 所属系统（用于分区使用）
     */
    private String systemFlag;
    /**
     * 删除人编号
     */
    private String deleteUser;
    /**
     * 删除人姓名
     */
    private String deleteUserName;
    /**
     * 批注数量
     */
    private Integer pzNum;
    /**
     * 影像来源
     */
    private String imageSource;
    /**
     * 系统标识状态
     */
    private Integer sysFlagInt;
    /**
     * 识别类型
     */
    private String ocrType;
    /**
     * 主键
     */
    private Long imageId;
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

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageName() {
        return imageName;
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

    public void setEcmFileId(String ecmFileId) {
        this.ecmFileId = ecmFileId;
    }

    public String getEcmFileId() {
        return ecmFileId;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getWidth() {
        return width;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getHeight() {
        return height;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getBillId() {
        return billId;
    }

    public void setCompressFileId(String compressFileId) {
        this.compressFileId = compressFileId;
    }

    public String getCompressFileId() {
        return compressFileId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setImageSize(BigDecimal imageSize) {
        this.imageSize = imageSize;
    }

    public BigDecimal getImageSize() {
        return imageSize;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setOcrResult(String ocrResult) {
        this.ocrResult = ocrResult;
    }

    public String getOcrResult() {
        return ocrResult;
    }

    public void setOcrStatus(String ocrStatus) {
        this.ocrStatus = ocrStatus;
    }

    public String getOcrStatus() {
        return ocrStatus;
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

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setSystemFlag(String systemFlag) {
        this.systemFlag = systemFlag;
    }

    public String getSystemFlag() {
        return systemFlag;
    }

    public void setDeleteUser(String deleteUser) {
        this.deleteUser = deleteUser;
    }

    public String getDeleteUser() {
        return deleteUser;
    }

    public void setDeleteUserName(String deleteUserName) {
        this.deleteUserName = deleteUserName;
    }

    public String getDeleteUserName() {
        return deleteUserName;
    }

    public void setPzNum(Integer pzNum) {
        this.pzNum = pzNum;
    }

    public Integer getPzNum() {
        return pzNum;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setSysFlagInt(Integer sysFlagInt) {
        this.sysFlagInt = sysFlagInt;
    }

    public Integer getSysFlagInt() {
        return sysFlagInt;
    }

    public void setOcrType(String ocrType) {
        this.ocrType = ocrType;
    }

    public String getOcrType() {
        return ocrType;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public Long getImageId() {
        return imageId;
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
                .append("imageName", getImageName())
                .append("status", getStatus())
                .append("batchId", getBatchId())
                .append("createTime", getCreateTime())
                .append("serialno", getSerialno())
                .append("ecmFileId", getEcmFileId())
                .append("width", getWidth())
                .append("height", getHeight())
                .append("billId", getBillId())
                .append("compressFileId", getCompressFileId())
                .append("templateId", getTemplateId())
                .append("imageSize", getImageSize())
                .append("imagePath", getImagePath())
                .append("ocrResult", getOcrResult())
                .append("ocrStatus", getOcrStatus())
                .append("billName", getBillName())
                .append("remark", getRemark())
                .append("createUser", getCreateUser())
                .append("createUserName", getCreateUserName())
                .append("deleteTime", getDeleteTime())
                .append("systemFlag", getSystemFlag())
                .append("deleteUser", getDeleteUser())
                .append("deleteUserName", getDeleteUserName())
                .append("pzNum", getPzNum())
                .append("imageSource", getImageSource())
                .append("sysFlagInt", getSysFlagInt())
                .append("ocrType", getOcrType())
                .append("imageId", getImageId())
                .append("userCodeId", getUserCodeId())
                .toString();
    }
}
