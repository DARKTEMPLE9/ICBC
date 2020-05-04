package net.northking.iacmp.common.bean.domain.ams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.core.domain.BaseEntity;

import java.util.Date;

/**
 * 专题库表 ams_collection
 *
 * @author wxy
 * @date 2019-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AmsCollection extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer colId;
    /**
     * 档案名/专题库名
     */
    private String name;
    /**
     * 档案ID/专题库ID
     */
    private String archivesId;
    /**
     * 收藏该档案的人/创建专题库的人
     */
    private String searcher;
    /**
     * 0: 个人收藏  1：专题库  2：已加入专题库的档案
     */
    private Integer fLAG;
    /**
     * 专题库创建时间
     */
    private Date crtTime;
    /**
     * 该专题库的主要作用
     */
    private String remark;
    /**
     * 0: 移除  1: 正常使用
     */
    private Integer status;
    /**
     * 移除时间
     */
    private Date delTime;
    /**
     * 档案类别(一级类目)
     */
    private String arcBill;
    /**
     * 部门档案类型(二级类目）
     */
    private String arcBillDept;
    /**
     * 所属年份
     */
    private String boxYear;
    /**
     * 保管期限
     */
    private String valPeroid;
    /**
     * 档案所属专题库id
     */
    private String specArch;
    /**
     * 档案业务部门名(所属部门)
     */
    private String opDepName;

}
