package net.northking.iacmp.common.bean.domain.cms;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.core.domain.BaseEntity;

/**
 * 模型与分类关联表 cms_model_bill
 *
 * @author qingyu.yan
 * @date 2019-08-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CmsModelBill extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 模型ID
     */
    private Integer modelId;
    /**
     * 分类ID
     */
    private Integer billId;
    /**
     * 文件数量
     */
    private Integer fileNum;
    /**
     * 主键
     */
    private Long id;
}
