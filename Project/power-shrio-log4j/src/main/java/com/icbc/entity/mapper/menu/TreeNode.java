package com.icbc.entity.mapper.menu;

import lombok.Data;

import java.util.List;

@Data
public class TreeNode {

    private Integer id ;

    private String name;

    private Integer pid;

    private String url;

    private Integer orderSort;

    private List<TreeNode> childNodes;


}
