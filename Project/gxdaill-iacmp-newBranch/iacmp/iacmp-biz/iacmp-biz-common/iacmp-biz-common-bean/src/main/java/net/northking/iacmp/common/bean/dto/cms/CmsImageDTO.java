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
 * 影像表 cms_imageDTO
 *
 * @author qingyu.yan
 * @date 2019-08-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CmsImageDTO extends BaseEntity {
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
    private Long billId;
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
     * 模型编码
     */
    private String modelCode;

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

}
