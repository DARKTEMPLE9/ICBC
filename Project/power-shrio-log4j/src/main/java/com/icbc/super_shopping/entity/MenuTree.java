package com.icbc.super_shopping.entity;

import lombok.Data;

import java.util.List;

@Data
public class MenuTree {

    private String id;
    private String label;
    private String parentCode;
    private Short menulevel;
    private Long menuOrder;
    private Byte menuStatus;
    private List<String> menuChecked;
    private List<MenuTree> children;
}
