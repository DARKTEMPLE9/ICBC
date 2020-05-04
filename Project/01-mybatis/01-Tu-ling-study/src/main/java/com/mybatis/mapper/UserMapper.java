package com.mybatis.mapper;

import com.mybatis.UserBean;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {


    /*
     * mybatis 使用annotation方式
     * @Results 进行关系映射
     * */
    @Results({
            @Result(property = "mengbb", column = "desc")
    })
    @Select(" select id,username,age,phone,`desc` from user where id = #{id}")
    public UserBean selectUser(Integer id);

}
