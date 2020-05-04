package net.northking.iacmp.common.bean.dto.cms;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.annotation.Excel;
import net.northking.iacmp.annotation.Excels;
import net.northking.iacmp.core.domain.BaseEntity;
import net.northking.iacmp.system.domain.SysDept;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 文件表 cms_fileDTO
 *
 * @author qingyu.yan
 * @date 2019-08-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CmsFileDTO extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 逻辑主键
     */
    private Long id;
    /**
     * 逻辑主键
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
     * 文件批次
     */
    private Long batchId;
    /**
     * 文件顺序
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
    private String Metadata;
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

    /***************************pms_batch*************************/
    /**
     * 主键
     */
    private Long pid;
    /**
     * 登记流水号
     */
    private String regbillglideNo;
    /**
     * 批次编号
     */
    private String operationCode;
    /**
     * 部门编号
     */
    private Long deptId;
    /**
     * 部门对象
     */
    @Excels({
            @Excel(name = "部门名称", targetAttr = "deptName", type = Excel.Type.EXPORT),
            @Excel(name = "部门负责人", targetAttr = "leader", type = Excel.Type.EXPORT)
    })
    private SysDept dept;
    /**
     * 顺序码
     */
    private Long pOrderNum;
    /**
     * 逻辑主键
     */
    private Long pBatchId;
    /**
     * 所属系统
     */
    private Long sysId;
    /**
     * 使用模型
     */
    private String pModelId;
    /**
     * 元数据
     */
    private String pMetadata;

    /**
     * 部门名称
     */
    private String pDeptName;

    /**
     * 承建部门
     */
    private String buildDept;

    /**
     * 预算编号
     */
    private String budgetId;

    /**
     * 项目经理编号
     */
    private String projectManager;

    /**
     * 所属系统编码
     */
    private String sysCode;

    /**
     * 分类编码
     */
    private String billCode;

    /*************************文件影像联合查询*****************/

    /**
     * 主键ID
     */
    private Long unionId;

    /**
     * 逻辑主键ID
     */
    private Long fId;
    /**
     * 类型
     */
    private String fType;
    /**
     * 名称
     */
    private String fName;
    /**
     * 标签
     */
    private String fTrg;
    /**
     * 路径
     */
    private String fPath;

    /**
     * 系统编码
     */
    private String fSysCode;
    /**
     * 分类编码
     */
    private String fBillCode;
    /**
     * 来源
     */
    private String fSource;
    /**
     * 批次
     */
    private Long fBatchId;
    /**
     * 预算
     */
    private String fBudgetId;
    /**
     * 创建时间
     */
    private Date fCreateTime;
    /**
     * 更新时间
     */
    private Date fUpdateTime;

    /**
     * 是否关联项目
     */
    private String fProjectBatch;

}
