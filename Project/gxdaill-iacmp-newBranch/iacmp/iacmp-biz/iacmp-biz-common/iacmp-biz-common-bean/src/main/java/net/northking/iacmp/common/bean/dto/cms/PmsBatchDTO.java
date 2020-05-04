package net.northking.iacmp.common.bean.dto.cms;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.annotation.Excel;
import net.northking.iacmp.annotation.Excels;
import net.northking.iacmp.common.bean.domain.cms.CmsRole;
import net.northking.iacmp.core.domain.BaseEntity;
import net.northking.iacmp.system.domain.SysDept;
import net.northking.iacmp.system.domain.SysRole;

import java.util.List;

/**
 * 影像批次表 pms_batch
 *
 * @author qingyu.yan
 * @date 2019-08-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PmsBatchDTO extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 登记流水号
     */
    private String regbillglideNo;
    /**
     * 项目名称
     */
    @Excel(name = "项目名称（必输项）")
    private String projectName;
    /**
     * 批次编号
     */
    @Excel(name = "项目编号（必输项）")
    private String operationCode;
    /**
     * 预算编号
     */
    @Excel(name = "预算编号（必输项）")
    private String budgetId;
    /**
     * 部门编号
     */
    private Long deptId;
    /**
     * 顺序码
     */
    private Long orderNum;
    /**
     * 逻辑主键
     */
    private Long batchId;
    /**
     * 所属系统
     */
    private Integer sysId;
    /**
     * 模型名称集合
     */
    private String modelListName;
    /**
     * 元数据
     */
    private String metadata;
    /**
     * 模型编码
     */
    private String modelCode;


    /**
     * 部门名称
     */
    private String deptName;


    /**
     * 承建部门名称
     */
    private String buildDeptName;

    /**
     * 归属部门名称
     */
    private String attriDeptName;


    /**
     * 项目经理编号
     */
    @Excel(name = "科技项目经理（必输项）")
    private String projectManager;

    /**
     * 项目经理名称
     */
    private String projectManagerName;

    /**
     * 产品经理
     */
    @Excel(name = "项目产品经理（必输项）")
    private String productManager;

    /**
     * 归属部门
     */
    @Excel(name = "归属部门（必输项）")
    private String attriDept;

    /**
     * 使用模型
     */
    @Excel(name = "模型选择（必输项）")
    private String modelList;

    /**
     * 承建部门
     */
    @Excel(name = "承建部门（必输项）")
    private String buildDept;

    /**
     * 系统级别
     */
    @Excel(name = "系统级别（必输项）")
    private String sysLevel;

    /**
     * 系统级别
     */
    @Excel(name = "系统类型（必输项）")
    private String sysType;

    /**
     * 项目状态
     */
    @Excel(name = "项目状态（必输项）", readConverterExp = "0=未启动,1=已废弃,2=已结项,3=实施中")
    private String status;
    /**
     * 产品经理名称
     */
    private String productManagerName;


    /**
     * 所属系统编码
     */
    private String sysCode;

    /**
     * 权限集合
     */
    private Long[] roles;

    /**
     * 系统角色集合
     */
    private List<SysRole> sysRoles;

    /**
     * 系统角色集合所涉及的部门
     */
    private List<Long> deptIds;

    /**
     * 数据角色集合
     */
    private List<CmsRole> dataRoles;

    /**
     * 数据权限
     */
    private String dataScope;

    /**
     * 数据权限部门
     */
    private String dataScopeDept;
    /**
     * 部门对象
     */
    @Excels({
            @Excel(name = "部门名称", targetAttr = "deptName", type = Excel.Type.EXPORT),
            @Excel(name = "部门负责人", targetAttr = "leader", type = Excel.Type.EXPORT)
    })
    private SysDept dept;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 分类
     */
    private String cmsBillCode;

}
