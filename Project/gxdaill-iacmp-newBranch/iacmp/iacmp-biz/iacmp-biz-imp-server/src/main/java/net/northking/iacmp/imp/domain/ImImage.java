package net.northking.iacmp.imp.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 影像表 ceshi_im_image
 *
 * @author weizhe.fan
 * @date 2019-10-14
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImImage implements Serializable {
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
    private String batchNo;
    /**
     * 影像流水号
     */
    private String busino;
    /**
     * 分片建哈希值
     */
    private String regionNo;
    /**
     * 影像顺序
     */
    private BigDecimal serialno;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private Date deleteTime;
    /**
     * 所属系统
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
     * 批次号
     */
    private String batchId;

    /**
     * 上传时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date createTime;

    /**
     * 备注
     */
    private String remark;

}
