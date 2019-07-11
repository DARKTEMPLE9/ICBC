package com.icbc.entity;

import com.icbc.entity.BaseEntity;

import java.io.Serializable;

public class ViewLogin extends BaseEntity implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column view_login.name
     *
     * @mbg.generated Sat Jun 08 15:15:08 CST 2019
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column view_login.age
     *
     * @mbg.generated Sat Jun 08 15:15:08 CST 2019
     */
    private String age;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table view_login
     *
     * @mbg.generated Sat Jun 08 15:15:08 CST 2019
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column view_login.name
     *
     * @return the value of view_login.name
     *
     * @mbg.generated Sat Jun 08 15:15:08 CST 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column view_login.name
     *
     * @param name the value for view_login.name
     *
     * @mbg.generated Sat Jun 08 15:15:08 CST 2019
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column view_login.age
     *
     * @return the value of view_login.age
     *
     * @mbg.generated Sat Jun 08 15:15:08 CST 2019
     */
    public String getAge() {
        return age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column view_login.age
     *
     * @param age the value for view_login.age
     *
     * @mbg.generated Sat Jun 08 15:15:08 CST 2019
     */
    public void setAge(String age) {
        this.age = age;
    }
}