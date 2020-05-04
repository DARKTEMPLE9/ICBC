package net.northking.iacmp.common.bean.domain.ams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.core.domain.BaseEntity;

import java.math.BigDecimal;

/**
 * 文件类型表 im_bill
 *
 * @author wxy
 * @date 2019-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ImBill extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 逻辑主键
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
    private String billOrder;
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
    private String pRODUCT;
    /**
     * 主键
     */
    private Long billId;

}
