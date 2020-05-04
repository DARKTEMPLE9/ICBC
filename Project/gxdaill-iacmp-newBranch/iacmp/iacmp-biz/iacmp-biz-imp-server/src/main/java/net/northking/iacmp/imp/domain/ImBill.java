package net.northking.iacmp.imp.domain;


import lombok.Data;

import java.math.BigDecimal;

/**
 * 影像分类表 im_bill
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Data
public class ImBill {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;
    /**
     * 类型名称
     */
    private String name;
    /**
     * 类型变那么
     */
    private String code;
    /**
     * 关联系统
     */
    private String sysId;
    /**
     * 父节点
     */
    private String parentId;
    /**
     * 类型顺序
     */
    private BigDecimal billOrder;
    /**
     * 系统编码
     */
    private String sysCode;
    /**
     * 是否为缺失影像
     */
    private String isLostType;
    /**
     * 是否为叶子节点
     */
    private String isLeaf;
    /**
     * 所属产品
     */
    private String product;
    /**
     * 主键
     */
    private Long billId;
    /**
     * 字典编码
     */
    private String dictCode;

}
