package net.northking.iacmp.common.bean.domain.cms;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.core.domain.BaseEntity;

import java.util.Date;

/**
 * 临时分类权限表 cms_user_bill_tmp
 *
 * @author qingyu.yan
 * @date 2019-08-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CmsUserBillTmp extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 分类ID
     */
    private Integer billId;
    /**
     * 权限类型 R读 W写
     */
    private String authType;
    /**
     * 权限起始
     */
    private Date authStart;
    /**
     * 权限终止
     */
    private Date authEnd;
    /**
     * 主键
     */
    private Long id;

}
