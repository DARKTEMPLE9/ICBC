package net.northking.iacmp.imp.domain;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 部门机构表 sm_organ
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Data
public class SmOrgan {
    private static final long serialVersionUID = 1L;

    /**
     * 逻辑主键
     */
    private String id;
    /**
     * 机构代码
     */
    private String code;
    /**
     * 机构名称
     */
    private String name;
    /**
     * 父机构
     */
    private String parentId;
    /**
     * 机构路径
     */
    private String path;
    /**
     * 机构状态（0-正常;1-停用）
     */
    private BigDecimal status;
    /**
     * 修改人
     */
    private String modifier;
    /**
     * 创建人
     */
    private String creator;
    /**
     * 管理机构号
     */
    private String ssoOrgCode;
    /**
     * 主键
     */
    private Long organId;

}
