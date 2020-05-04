package net.northking.iacmp.imp.domain;


import lombok.Data;

/**
 * 影像类型流水关联表 im_batch_im_bill
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Data
public class ImBatchImBill {
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
