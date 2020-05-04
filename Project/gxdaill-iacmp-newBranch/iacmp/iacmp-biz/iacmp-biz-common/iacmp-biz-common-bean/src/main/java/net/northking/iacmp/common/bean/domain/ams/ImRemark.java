package net.northking.iacmp.common.bean.domain.ams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.core.domain.BaseEntity;

import java.util.Date;

/**
 * 影像备注表 im_remark
 *
 * @author wxy
 * @date 2019-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ImRemark extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 逻辑主键
     */
    private String id;
    /**
     * 内容
     */
    private String content;
    /**
     * 创建人ID
     */
    private String creatUserId;
    /**
     * 创建时间
     */
    private Date creatDate;
    /**
     * 创建人姓名
     */
    private String creatUserName;
    /**
     * 批次主键
     */
    private String batchId;
    /**
     * 主键
     */
    private Long remarkId;

}
