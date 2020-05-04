package net.northking.iacmp.common.bean.vo.cms;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import net.northking.iacmp.annotation.Excel;
import net.northking.iacmp.annotation.Excels;
import net.northking.iacmp.common.bean.domain.cms.CmsBill;
import net.northking.iacmp.common.bean.domain.cms.CmsRole;
import net.northking.iacmp.core.domain.BaseEntity;
import net.northking.iacmp.system.domain.SysDept;
import net.northking.iacmp.system.domain.SysMenu;
import net.northking.iacmp.system.domain.SysRole;

import java.util.List;

/**
 * 影像批次表 pms_batch
 *
 * @author qingyu.yan
 * @date 2019-08-26
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class PmsBatchVO extends BaseEntity implements Comparable<PmsBatchVO> {
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

    /**
     * 项目分类列表
     */
    private List<CmsBill> cmsBillList;

    /**
     * 项目权限列表
     */
    private List<SysMenu> sysMenuList;
    /**
     * 核对是否有文件
     */
    private boolean checkFiles;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;                  //比较对象和当前元素相同，返回true
        if (obj == null)
            return false;                //比较对象为空，返回false
        if (!(obj instanceof PmsBatchVO))
            return false;                //比较对象类型和当前元素不同，返回false
        PmsBatchVO other = (PmsBatchVO) obj;
        if (operationCode == null) {
            if (other.operationCode != null)
                return false;     //当前元素operationCode为空，而比较对象operationCode不为空，返回false
        } else if (!operationCode.equals(other.operationCode))
            return false;                //当前元素operationCode和比较对象operationCode不相同，返回false

        return true;                         //排除以上因素后，该函数才返回true
    }


    @Override
    public int compareTo(PmsBatchVO o) {

        if (batchId > o.getBatchId()) {
            return 1;
        } else if (batchId == o.getBatchId()) {

            return 0;
        } else {
            return -1;
        }
    }
}
