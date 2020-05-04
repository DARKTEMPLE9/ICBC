package net.northking.iacmp.common.bean.domain.ams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.annotation.Excel;
import net.northking.iacmp.core.domain.BaseEntity;

import java.util.Date;

/**
 * 档案销毁表 ams_destroy
 *
 * @author wxy
 * @date 2019-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AmsDestroy extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long desId;
    /**
     * id
     */
    private String id;
    /**
     * 销毁人号
     */
    @Excel(name = "销毁人号")
    private String opUserCode;
    /**
     * 销毁人名
     */
    @Excel(name = "销毁人名")
    private String opUserName;
    /**
     * 销毁人机构号
     */
    @Excel(name = "销毁人机构号")
    private String opOrgCode;
    /**
     * 销毁人机构名
     */
    @Excel(name = "销毁人机构名")
    private String opOrgName;
    /**
     * 销毁时间
     */
    @Excel(name = "销毁时间")
    private Date opDate;
    /**
     * 销毁开始时间
     */
//    @Excel(name = "销毁时间")
    private Date opDateStart;
    /**
     * 销毁结束时间
     */
//    @Excel(name = "销毁时间")
    private Date opDateEnd;
    /**
     * 销毁原因
     */
    @Excel(name = "销毁原因")
    private String destroyReason;
    /**
     * 档案数量
     */
    @Excel(name = "档案数量")
    private Integer archivesNum;
    /**
     * 附件
     */
    @Excel(name = "附件")
    private String destroyAttachment;
    /**
     * 销毁档案id
     */
    @Excel(name = "销毁档案id")
    private String amsArcId;

    /**
     * 销毁档案名称
     */
    @Excel(name = "销毁档案名称")
    private String amsArcName;
}
