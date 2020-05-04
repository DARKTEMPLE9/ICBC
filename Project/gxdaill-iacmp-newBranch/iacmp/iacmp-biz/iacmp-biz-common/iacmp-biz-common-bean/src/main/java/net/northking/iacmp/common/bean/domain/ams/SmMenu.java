package net.northking.iacmp.common.bean.domain.ams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.core.domain.BaseEntity;

import java.math.BigDecimal;

/**
 * 菜单表 sm_menu
 *
 * @author wxy
 * @date 2019-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SmMenu extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 菜单编码
     */
    private String code;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 菜单顺序
     */
    private BigDecimal indexNo;
    /**
     * 菜单URL地址
     */
    private String url;
    /**
     * 逻辑主键
     */
    private String id;
    /**
     * 父菜单id
     */
    private String parentId;
    /**
     * 路径
     */
    private String path;
    /**
     * 包含所有URL
     */
    private String allUrls;

}
