package net.northking.iacmp.imp.vo;


import lombok.Data;

import java.util.Date;

/**
 * 注解表 im_remark
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Data
public class ImRemarkVO {
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
    /**
     * 排序字段
     */
    private String order;

}
