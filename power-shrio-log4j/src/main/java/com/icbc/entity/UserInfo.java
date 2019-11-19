package com.icbc.entity;

import com.icbc.turnpage.PageModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserInfo extends PageModel implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.id
     *
     * @mbg.generated Sat Jun 08 15:15:08 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.name
     *
     * @mbg.generated Sat Jun 08 15:15:08 CST 2019
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.gender
     *
     * @mbg.generated Sat Jun 08 15:15:08 CST 2019
     */
    private String gender;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.birthday
     *
     * @mbg.generated Sat Jun 08 15:15:08 CST 2019
     */
    private Date birthday;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.idCard
     *
     * @mbg.generated Sat Jun 08 15:15:08 CST 2019
     */
    private String idcard;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.wedlock
     *
     * @mbg.generated Sat Jun 08 15:15:08 CST 2019
     */
    private String wedlock;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.nationId
     *
     * @mbg.generated Sat Jun 08 15:15:08 CST 2019
     */
    private Integer nationid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.nativePlace
     *
     * @mbg.generated Sat Jun 08 15:15:08 CST 2019
     */
    private String nativeplace;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.politicId
     *
     * @mbg.generated Sat Jun 08 15:15:08 CST 2019
     */
    private Integer politicid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.email
     *
     * @mbg.generated Sat Jun 08 15:15:08 CST 2019
     */
    private String email;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.phone
     *
     * @mbg.generated Sat Jun 08 15:15:08 CST 2019
     */
    private String phone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.address
     *
     * @mbg.generated Sat Jun 08 15:15:08 CST 2019
     */
    private String address;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.departmentId
     *
     * @mbg.generated Sat Jun 08 15:15:08 CST 2019
     */
    private Integer departmentid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.jobLevelId
     *
     * @mbg.generated Sat Jun 08 15:15:08 CST 2019
     */
    private Integer joblevelid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.posId
     *
     * @mbg.generated Sat Jun 08 15:15:08 CST 2019
     */
    private Integer posid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.engageForm
     *
     * @mbg.generated Sat Jun 08 15:15:08 CST 2019
     */
    private String engageform;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.tiptopDegree
     *
     * @mbg.generated Sat Jun 08 15:15:08 CST 2019
     */
    private String tiptopdegree;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.specialty
     *
     * @mbg.generated Sat Jun 08 15:15:08 CST 2019
     */
    private String specialty;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.school
     *
     * @mbg.generated Sat Jun 08 15:15:08 CST 2019
     */
    private String school;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.beginDate
     *
     * @mbg.generated Sat Jun 08 15:15:08 CST 2019
     */
    private String begindate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.workState
     *
     * @mbg.generated Sat Jun 08 15:15:08 CST 2019
     */
    private String workstate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.workID
     *
     * @mbg.generated Sat Jun 08 15:15:08 CST 2019
     */
    private String workid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.contractTerm
     *
     * @mbg.generated Sat Jun 08 15:15:08 CST 2019
     */
    private Double contractterm;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.conversionTime
     *
     * @mbg.generated Sat Jun 08 15:15:08 CST 2019
     */
    private String conversiontime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.notWorkDate
     *
     * @mbg.generated Sat Jun 08 15:15:08 CST 2019
     */
    private String notworkdate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.beginContract
     *
     * @mbg.generated Sat Jun 08 15:15:08 CST 2019
     */
    private String begincontract;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.endContract
     *
     * @mbg.generated Sat Jun 08 15:15:08 CST 2019
     */
    private String endcontract;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.workAge
     *
     * @mbg.generated Sat Jun 08 15:15:08 CST 2019
     */
    private Integer workage;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table user_info
     *
     * @mbg.generated Sat Jun 08 15:15:08 CST 2019
     */
    private static final long serialVersionUID = 1L;


}