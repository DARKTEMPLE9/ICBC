package net.northking.iacmp.common.bean.domain.cms;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.core.domain.BaseEntity;

import java.util.Date;
import java.math.BigDecimal;

/**
 * 影像表 cms_image
 *
 * @author qingyu.yan
 * @date 2019-08-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CmsImage extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 逻辑主键
     */
    private Long imageId;
    /**
     * 影像编号
     */
    private String imageNo;
    /**
     * 影像名称
     */
    private String imageName;
    /**
     * uuid生成的名称
     */
    private String randomName;
    /**
     * 影像状态（0-正常；1-已废弃）
     */
    private String status;
    /**
     * 批次ID
     */
    private Long batchId;
    /**
     * 影像顺序
     */
    private Long orderNum;
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
    private Integer billId;
    /**
     * 所属单据名称
     */
    private String billName;
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
     * 备注
     */
    private String remark;
    /**
     * 上传人编号
     */
    private Long createUser;
    /**
     * 删除时间
     */
    private Date deleteTime;
    /**
     * 删除人编号
     */
    private Long deleteUser;
    /**
     * 批注数量
     */
    private Integer pzNum;
    /**
     * 影像来源
     */
    private String imageSource;

    /**
     * 标签
     */
    private String trg;
    /**
     * 识别类型
     */
    private String ocrType;
    /**
     * 版本
     */
    private String version;
    /**
     * 元数据
     */
    private String metadata;
    /**
     * 所属系统
     */
    private String imageSysCode;
    /**
     * 所属部门
     */
    private String deptName;

    /**
     * 模型ID
     */
    private String modelId;

    /**
     * 文件MD5值
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
