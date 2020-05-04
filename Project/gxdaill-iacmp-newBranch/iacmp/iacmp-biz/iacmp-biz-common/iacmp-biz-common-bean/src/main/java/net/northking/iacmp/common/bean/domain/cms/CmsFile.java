package net.northking.iacmp.common.bean.domain.cms;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.core.domain.BaseEntity;

import java.math.BigDecimal;

/**
 * 文件表 cms_file
 *
 * @author qingyu.yan
 * @date 2019-08-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CmsFile extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 逻辑主键
     */
    private Long id;
    /**
     * 文件逻辑主键
     */
    private Long fileId;
    /**
     * 文档编号
     */
    private String fileNo;
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * uuid生成的名称
     */
    private String randomName;
    /**
     * 文件状态（0-正常；1-已废弃）
     */
    private String status;
    /**
     * 批次ID
     */
    private Long batchId;
    /**
     * 显示顺序
     */
    private Long orderNum;
    /**
     * 文件存放路径
     */
    private String filePath;
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
    private Integer billId;
    /**
     * 所属单据名称
     */
    private String billName;
    /**
     * 备注
     */
    private String remark;
    /**
     * 上传人编号
     */
    private Long createUser;
    /**
     * 文件来源
     */
    private String fileSource;
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
    private String fileSysCode;

    /**
     * 标签
     */
    private String trg;
    /**
     * 所属部门
     */
    private String deptName;

    /**
     * 模型ID
     */
    private String modelId;

    /**
     * 输入的最小size
     */
    private BigDecimal minFileSize;

    /**
     * 输入的最大size
     */
    private BigDecimal maxFileSize;

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
