package net.northking.iacmp.imp.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 异步影像表 im_image_cache
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Data
public class ImImageCache {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 影像名称
     */
    private String imageName;
    /**
     * 影像状态（0-正常；1-已废弃;2-待上传；3-正在上传；4-没有文件；5-上传错误；6-上传成功）
     */
    private String imageStatus;
    /**
     * 批次ID
     */
    private String batchId;
    /**
     * 影像顺序
     */
    private BigDecimal serialino;
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
    private String operationCode;
    /**
     * 识别状态(0-待识别;1-已识别;)
     */
    private String fileType;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private Date deleteTime;
    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private Date createTime;
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
     * 影像ID
     */
    private String imageId;
    /**
     * 客户号
     */
    private String userCode;
    /**
     * 主机IP
     */
    private String ip;

}
