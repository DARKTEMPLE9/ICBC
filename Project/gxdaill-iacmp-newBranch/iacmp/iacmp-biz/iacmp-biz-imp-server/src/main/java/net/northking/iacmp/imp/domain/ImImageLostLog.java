package net.northking.iacmp.imp.domain;


import lombok.Data;

import java.util.Date;

/**
 * 影像缺失表 im_image_lost_log
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Data
public class ImImageLostLog {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;
    /**
     * 批次ID
     */
    private String batchId;
    /**
     * 操作人工号
     */
    private String operNo;
    /**
     * 操作时间
     */
    private Date operTime;
    /**
     * 缺失类型
     */
    private String lostBillType;
    /**
     * 主键
     */
    private Long lostId;


}
