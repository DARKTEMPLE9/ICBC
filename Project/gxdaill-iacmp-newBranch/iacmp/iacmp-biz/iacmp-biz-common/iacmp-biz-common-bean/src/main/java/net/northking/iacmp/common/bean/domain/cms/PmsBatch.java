package net.northking.iacmp.common.bean.domain.cms;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.annotation.Excel;
import net.northking.iacmp.annotation.Excels;
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
public class PmsBatch extends BaseEntity {
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
    @Excel(name = "预算编号")
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
    @Excel(name = "科技项目经理", prompt = "登录名")
    private String projectManager;

    /**
     * 项目经理名称
     */
    private String projectManagerName;

    /**
     * 产品经理
     */
    @Excel(name = "项目产品经理", prompt = "登录名")
    private String productManager;

    /**
     * 归属部门
     */
    @Excel(name = "归属部门")
    private String attriDept;

    /**
     * 使用模型
     */
    @Excel(name = "模型选择", prompt = "输入模型编号，并已英文逗号隔开")
    private String modelList;

    /**
     * 承建部门
     */
    @Excel(name = "承建部门")
    private String buildDept;

    /**
     * 系统级别
     */
    @Excel(name = "系统级别", combo = {"N/A", "A", "B", "C", "D", "A+"})
    private String sysLevel;

    /**
     * 系统类型
     */
    @Excel(name = "系统类型", combo = {"运维安全类", "决策支持类", "客户管理类", "产品管理类", "技术组件类", "财务管理类",
            "业务支撑类", "渠道管理类", "大数据类", "基础设施类", "智能应用类", "共享支持类", "其他"})
    private String sysType;

    /**
     * 项目状态
     */
    @Excel(name = "项目状态", readConverterExp = "0=未启动,2=已结项,3=实施中", combo = {"未启动", "已结项", "实施中"})
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
     * 状态集合
     */
    private List<String> statusList;

    /**
     * 是否属于项目
     */
    private String projectBatch;

    /**
     * 用户的辅部门
     */
    private String auxiliaryDept;

    /**
     * 用户的辅部门list
     */
    private Long[] auxiliaryDeptList;


}
