package net.northking.iacmp.common.bean.domain.ams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.core.domain.BaseEntity;

import java.util.Date;

/**
 * 用户机构表 sm_user_organ
 *
 * @author wxy
 * @date 2019-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SmUserOrgan extends BaseEntity {
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
