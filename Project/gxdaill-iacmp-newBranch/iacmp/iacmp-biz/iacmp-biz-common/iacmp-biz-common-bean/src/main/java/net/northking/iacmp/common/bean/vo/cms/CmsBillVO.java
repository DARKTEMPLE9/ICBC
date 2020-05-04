package net.northking.iacmp.common.bean.vo.cms;


import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.common.bean.domain.cms.CmsBill;
import net.northking.iacmp.core.domain.BaseEntity;

import java.util.List;

/**
 * 项目管理结构树页文件或影像VO
 *
 * @author rui.yin
 * @date 2019-09-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CmsBillVO extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;
    /**
     * 类型名称
     */
    private String billName;
    /**
     * 类型编码
     */
    private String billCode;
    /**
     * 父节点
     */
    private Long parentId;
    /**
     * 类型顺序
     */
    private Integer billOrder;
    /**
     * 是否为叶子节点(0-子节点，1-非子节点)
     */
    private String leaf;
    /**
     * 祖级列表
     */
    private String allPath;
    /**
     * 分类状态（0-启用，1-停用）
     */
    private String status;
    /**
     * 分类下的文件数
     */
    private Integer fileNum;
    /**
     * 实际文件数
     */
    private Integer actualFileNum;
    /**
     * 完成度
     */
    private String completion;
    /**
     * 所属部门名称
     */
    private String deptName;
    /**
     * 监控结果
     */
    private String monitor;
    /**
     * 文件路径
     */
    private String srcUrl;
    /**
     * 所属阶段
     */
    private String stage;
    /**
     * 所属项目id
     */
    private String pmsId;
    /**
     * 所属模型id
     */
    private String modelId;
    /**
     * 子节点
     */
    private List<CmsBill> cmsBills;

}
