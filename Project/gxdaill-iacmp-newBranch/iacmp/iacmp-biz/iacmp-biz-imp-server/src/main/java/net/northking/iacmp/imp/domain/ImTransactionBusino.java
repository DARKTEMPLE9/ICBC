package net.northking.iacmp.imp.domain;


import lombok.Data;

/**
 * 全局流水影像流水反向索引表 im_transaction_busino
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Data
public class ImTransactionBusino {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;
    /**
     * 全局流水号
     */
    private String transactionNo;
    /**
     * 影像流水号
     */
    private String busino;

}
