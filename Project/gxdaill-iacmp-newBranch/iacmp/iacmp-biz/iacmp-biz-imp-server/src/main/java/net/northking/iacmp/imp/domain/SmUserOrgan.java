package net.northking.iacmp.imp.domain;


import lombok.Data;

import java.util.Date;

/**
 * 用户机构关联表 sm_user_organ
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Data
public class SmUserOrgan {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;
    /**
     * 用户代码
     */
    private String userCode;
    /**
     * 机构代码
     */
    private String organCode;
    /**
     * 开始时间
     */
    private Date stateDate;
    /**
     * 结束时间
     */
    private Date endDate;
    /**
     * 主键
     */
    private Long userOrganId;

}
