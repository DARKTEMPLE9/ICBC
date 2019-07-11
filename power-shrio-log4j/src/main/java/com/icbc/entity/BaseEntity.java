package com.icbc.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @项目名称：project-common
 * @类名称：BaseEntity
 * @类描述：所有实体类的父类。可将公共的属性所有类序列化集中在此类中
 * @创建时间：2016年9月5日 上午11:37:02
 * @version 1.0.0
 */

@Data
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer page = 1;
    private Integer rows = 10;

}
