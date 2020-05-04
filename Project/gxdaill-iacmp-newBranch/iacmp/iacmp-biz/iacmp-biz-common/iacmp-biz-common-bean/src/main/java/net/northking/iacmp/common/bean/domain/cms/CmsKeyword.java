package net.northking.iacmp.common.bean.domain.cms;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.core.domain.BaseEntity;

/**
 * 标签表 cms_keyword
 *
 * @author qingyu.yan
 * @date 2019-08-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CmsKeyword extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 标签名称
     */
    private String name;
    /**
     * 所属系统
     */
    private String sysId;
    /**
     * 所属部门
     */
    private Long deptId;
    /**
     * 使用次数
     */
    private Integer nUMBER;
    /**
     * 关键字
     */
    private String keyword;
    /**
     * 标签编号
     */
    private String trgCode;

}
