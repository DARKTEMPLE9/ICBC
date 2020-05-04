package net.northking.iacmp.common.bean.domain.ams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.core.domain.BaseEntity;

import java.math.BigDecimal;

/**
 * 上传文件表 im_file
 *
 * @author wxy
 * @date 2019-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ImFile extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 逻辑主键
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
    private String serialNo;
    /**
     * 文件存放路径
     */
    private String filePath;

    /**
     * 文件存放的hdfs路径
     */
    private String fileServerUrl;
    /**
     * 文件大小
     */
    private BigDecimal fileSize;
    /**
     * 文件类型
     */
    private String fileType;
    /**
     * 所属单据类型
     */
    private String fileBillId;
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
     * 内管存储文件编号
     */
    private Long fileRealId;

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
