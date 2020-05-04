package net.northking.iacmp.common.bean.domain.ams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.core.domain.BaseEntity;

import java.util.Date;

/**
 * 影像缺失记录表 im_image_lost_log
 *
 * @author wxy
 * @date 2019-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ImImageLostLog extends BaseEntity {
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
    private String lostBilltype;
    /**
     * 主键
     */
    private Long lostId;

}
