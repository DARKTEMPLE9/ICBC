package net.northking.iacmp.common.bean.domain.cms;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.annotation.Excel;
import net.northking.iacmp.annotation.Excels;
import net.northking.iacmp.core.domain.BaseEntity;
import net.northking.iacmp.system.domain.SysDept;

import java.util.Date;
import java.util.List;

/**
 * 模型表 cms_model
 *
 * @author qingyu.yan
 * @date 2019-08-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CmsModel extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Excel(name = "模型编号")
    private Long id;
    /**
     * 模型名称
     */
    @Excel(name = "模型名称")
    private String modelName;
    /**
     * 模型编码
     */
    @Excel(name = "模型编码")
    private String modelCode;
    /**
     * 部门ID
     */
    private Long deptId;
    /**
     * 备注
     */
    @Excel(name = "备注")
    private String remark;
    /**
     * 创建时间
     */
    @Excel(name = "创建时间")
    private Date createTime;
    /**
     * 部门对象
     */
    @Excels({
            @Excel(name = "所属部门", targetAttr = "deptName", type = Excel.Type.EXPORT),
    })
    private SysDept dept;

    /**
     * 分类ids
     */
    private Long[] bills;


    private List<CmsBill> billList;
    /**
     * 文件数量
     */
    private Integer fileNum;
    /**
     * 关联分类
     */
//    @Excels({
//            @Excel(name = "分类编号", targetAttr = "billCode", type = Excel.Type.EXPORT),
//            @Excel(name = "分类名称", targetAttr = "billName", type = Excel.Type.EXPORT),
//    })
    private CmsBill cmsBill;

    /**
     * 模型状态
     */
    private String status;


}
