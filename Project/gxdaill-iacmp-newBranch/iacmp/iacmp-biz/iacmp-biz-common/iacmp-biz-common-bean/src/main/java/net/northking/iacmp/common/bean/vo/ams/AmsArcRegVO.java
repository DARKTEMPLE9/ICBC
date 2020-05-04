package net.northking.iacmp.common.bean.vo.ams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.annotation.Excel;
import net.northking.iacmp.core.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 档案表 ams_arc_reg
 *
 * @author wxy
 * @date 2019-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AmsArcRegVO extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;
    /**
     * 档案名称
     */
    @Excel(name = "档案名称")
    private String name;
    /**
     * 档案数量
     */
    private Integer regPageNum;
    /**
     * 登记人代码
     */
    private String regOpId;
    /**
     * 登记人名称
     */
    private String regOpName;
    /**
     * 登记人机构代码
     */
    private String regOrgCode;
    /**
     * 登记人机构名称
     */
    private String regOrgName;
    /**
     * 登记时间
     */
    private Date regTime;
    /**
     * 大于等于登记时间
     */
    private Date regTimeGte;
    /**
     * 小于等于登记时间
     */
    private Date regTimeLte;
    /**
     * 登记备注
     */
    @Excel(name = "备注")
    private String regRemark;
    /**
     * 接收档案数量
     */
    private Integer recNum;
    /**
     * 接收人代码
     */
    private String recOpId;
    /**
     * 接收人名称
     */
    private String recOpName;
    /**
     * 接收人机构代码
     */
    private String recOrgCode;
    /**
     * 接收人机构名称
     */
    private String recOrgName;
    /**
     * 接收时间
     */
    private Date recTime;
    /**
     * 大于等于接收时间
     */
    private Date recTimeGte;
    /**
     * 小于等于接收时间
     */
    private Date recTimeLte;
    /**
     * 接收备注
     */
    private String recRemark;
    /**
     * 状态(10-已保存;20-已提交;30-已接收;40-已退回)
     */
    private BigDecimal status;
    /**
     * 修改者姓名
     */
    private String modifier;
    /**
     * 修改时间
     */
    private Date modTime;
    /**
     * 登记份数
     */
    @Excel(name = "份数/件数")
    private Integer regArcNum;
    /**
     * 登记页数
     */
    @Excel(name = "页数")
    private Integer recPageNum;
    /**
     * 档案重复标志
     */
    private String repeatMark;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 登录用户所属部门
     */
    private Long deptId;
    /**
     * 是否移交行档室
     */
    private String hasMoveBank;
    /**
     * 文件编号
     */
    private String arcCode;
    /**
     * 责任人
     */
    private String respOpName;

    /**
     * 形成日期
     */
    @Excel(name = "形成时间(yyyy-mm-dd)")
    private Date arcCreTime;

}
