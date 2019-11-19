package com.icbc.super_shopping.super_power.entity;

import com.icbc.turnpage.PageModel;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class UserManage extends PageModel implements Serializable {
    private String rowId;

    private String userName;

    private String userLoginNumber;

    private String userPassword;

    private Integer userStatus;

    private String userPhone;

    private String userDeptRowId;

    private String userPositionRowId;

    private String userAppRowId;

    private String createUser;

    private Date createDate;

    private String modifyUser;

    private Date modifyDate;

    private static final long serialVersionUID = 1L;


}