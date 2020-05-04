package net.northking.iacmp.imp.domain;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 菜单目录表 sm_menu
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Data
public class SmMenu {
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
    /**
     * 主键
     */
    private Long menuId;

}
