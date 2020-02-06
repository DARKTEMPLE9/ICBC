package com.mybatis.mapper;

import com.mybatis.UserBean;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    /*
    * mybatis 使用annotation方式
    *
    * */
    @Select(" select * from user where id = #{id}")
    public UserBean selectUser(Integer id);


}
