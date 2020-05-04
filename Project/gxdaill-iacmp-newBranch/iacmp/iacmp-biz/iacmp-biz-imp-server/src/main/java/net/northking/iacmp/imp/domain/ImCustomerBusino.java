package net.northking.iacmp.imp.domain;

import lombok.Data;

/**
 * 客户影像流水反向索引表 im_customer_busino
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Data
public class ImCustomerBusino {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 客户号
     */
    private String userCode;
    /**
     * 影像流水号
     */
    private String busino;

}
