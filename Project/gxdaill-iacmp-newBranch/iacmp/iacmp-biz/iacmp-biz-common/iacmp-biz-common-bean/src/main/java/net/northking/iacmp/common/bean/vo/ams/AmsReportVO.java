package net.northking.iacmp.common.bean.vo.ams;

import lombok.Getter;
import lombok.Setter;
import net.northking.iacmp.core.domain.BaseEntity;

import java.util.Date;

/**
 * 导出统计报表
 */
@Getter
@Setter
public class AmsReportVO extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 开始时间
     */
    private String fillingTimeGt;
    /**
     * 结束时间
     */
    private String fillingTimeLt;
    /**
     * 部门名称
     */
    private String orgName;
    /**
     * 部门编码
     */
    private String orgCode;

    /**
     * 档案类型名称
     */
    private String arcBill;
    /**
     * 档案类型编码
     */
    private String arcBillCode;
    /**
     * 点击查看的档案类型
     */
    private String selectArcCode;
}
