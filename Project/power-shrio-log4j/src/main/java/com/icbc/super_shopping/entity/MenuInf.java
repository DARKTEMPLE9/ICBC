package com.icbc.super_shopping.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;
import javax.persistence.Table;

@Table(name = "SJS_PRM_MENU_INF")
@Data
public class MenuInf {

    @Id
    @Column(name = "PK_MENU_INF")
    private Integer pkMenuInf;

    @Column(name = "MENU_CODE")
    private String menuCode;

    @Column(name = "MENU_NAME")
    private String menuName;

    @Column(name = "PARENT_CODE")
    private String parentCode;

    @Column(name = "MENULEVEL")
    private Short menulevel;

    @Column(name = "MENU_ORDER")
    private Long menuOrder;

    @Column(name = "MENU_STATUS")
    private Byte menuStatus;

    @Column(name = "URL")
    private String url;

    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Column(name = "UPDATE_TIME")
    private Date updateTime;
}
