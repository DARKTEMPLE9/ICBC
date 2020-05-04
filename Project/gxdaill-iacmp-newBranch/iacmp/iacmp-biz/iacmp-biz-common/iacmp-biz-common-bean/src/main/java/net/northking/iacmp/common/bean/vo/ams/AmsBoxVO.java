package net.northking.iacmp.common.bean.vo.ams;

import lombok.Getter;
import lombok.Setter;
import net.northking.iacmp.core.domain.BaseEntity;


import java.util.Date;
import java.util.List;

/**
 * 档案盒表 ams_box
 *
 * @author wxy
 * @date 2019-08-01
 */
@Getter
@Setter
public class AmsBoxVO extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long boxPk;
    /**
     * id
     */
    private String id;
    /**
     * 档案盒代码
     */
    private String code;
    /**
     * 档案盒名称
     */
    private String name;
    /**
     * 所属机构
     */
    private String orgCode;
    /**
     * 所属机构名称
     */
    private String orgName;
    /**
     * 状态（00-未使用;10-已使用未满；20-已满）
     */
    private String status;
    /**
     * 所属年份
     */
    private String boxYear;
    /**
     * 保管期限
     */
    private String valPeriod;
    /**
     * 归档部门CODE
     */
    private String opOrgCode;
    /**
     * 归档部门名称
     */
    private String opOrgName;
    /**
     * 档案类型
     */
    private String arcType;
    /**
     * 备注
     */
    private String remark;
    /**
     * 库房id
     */
    private String cabId;
    /**
     * 库房name
     */
    private String cabName;
    /**
     * 库柜id
     */
    private String depId;
    /**
     * 库柜name
     */
    private String depName;
    /**
     * 一级类目编号
     */
    private String arcTypeCode;
    /**
     * 二级类目编号
     */
    private String childTypeCode;
    /**
     * 二级类目名称
     */
    private String childType;
    /**
     * 档案盒创建时间
     */
    private Date crtTime;
    /**
     * 盒入库时间
     */
    private Date incabTime;

    private List<String> boxIds;

    /**
     * 入库日期(开始)
     */
    private Date incabTimeStart;

    /**
     * 入库日期(结束)
     */
    private Date incabTimeEnd;

    /**
     * 档案库类型(部门/总行)
     */
    private String depType;
}
