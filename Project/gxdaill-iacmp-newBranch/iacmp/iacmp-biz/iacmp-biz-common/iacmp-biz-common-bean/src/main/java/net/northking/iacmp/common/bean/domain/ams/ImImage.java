package net.northking.iacmp.common.bean.domain.ams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.core.domain.BaseEntity;

import java.util.Date;
import java.math.BigDecimal;

/**
 * 上传影像表 im_image
 *
 * @author wxy
 * @date 2019-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ImImage extends BaseEntity {
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
    private String serialNo;
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
    private String ImageBillId;
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
     * 影像存放的服务器路径
     */
    private String imageServerUrl;
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
     * 内管存储文件编号
     */
    private Long imageRealId;

    /**
     * MD5值
     */
    private String md5;

    /**
     * Hadoop存取类型
     */
    private String hadoopType;

    /**
     * 系统区域
     */
    private String sysZone;

}
