package net.northking.iacmp.common.bean.domain.ams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.core.domain.BaseEntity;

/**
 * 影像批次类型表 im_batch_im_bill
 *
 * @author wxy
 * @date 2019-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ImBatchImBill extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 逻辑主键
     */
    private String id;
    /**
     * 单据类型外键
     */
    private String imBillId;
    /**
     * 批次外键
     */
    private String imBatchId;
    /**
     * 主键
     */
    private Long billBatchId;

}
