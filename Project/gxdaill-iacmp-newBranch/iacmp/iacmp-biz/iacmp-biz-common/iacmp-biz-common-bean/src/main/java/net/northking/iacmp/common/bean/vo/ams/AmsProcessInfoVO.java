package net.northking.iacmp.common.bean.vo.ams;

import lombok.Data;


import java.util.Date;

@Data
public class AmsProcessInfoVO {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long infoId;
    /**
     * 逻辑主键
     */
    private String id;
    /**
     * 审批编号
     */
    private String exaAppId;
    /**
     * 著录编号
     */
    private String brachId;
    /**
     * 档案编号
     */
    private String arcNo;
    /**
     * 档案名称
     */
    private String arcName;
    /**
     * 审批类型
     */
    private String exaAppType;
    /**
     * 申请人号
     */
    private String appOpNo;
    /**
     * 申请人名
     */
    private String appOpName;
    /**
     * 申请人部门号
     */
    private String appOrgNo;
    /**
     * 申请人部门名称
     */
    private String appOrgName;
    /**
     * 申请时间
     */
    private Date appTime;
    /**
     * 申请开始时间
     */
    private Date appTimeStart;
    /**
     * 申请结束时间
     */
    private Date appTimeEnd;
    /**
     * 申请备注
     */
    private String appRemark;
    /**
     * 当前处理人号
     */
    private String nowDispOpNo;
    /**
     * 当前处理人名称
     */
    private String nowDispOpName;
    /**
     * 当前处理人部门号
     */
    private String nowDispOrgNo;
    /**
     * 当前处理人部门名称
     */
    private String nowDispOrgName;
    /**
     * 当前审批意见
     */
    private String nowDispOpnion;
    /**
     * 当前审批时间
     */
    private Date nowDispTime;
    /**
     * 上次处理人号
     */
    private String preDispOpNo;
    /**
     * 上次处理人名称
     */
    private String preDispOpName;
    /**
     * 上次处理人部门号
     */
    private String preDispOrgNo;
    /**
     * 上次处理人部门名称
     */
    private String preDispOrgName;
    /**
     * 上次审批意见
     */
    private String preDispOpnion;
    /**
     * 上次处理时间
     */
    private Date preDispTime;
    /**
     * 审批结束时间
     */
    private Date exaAppEndTime;
    /**
     * 审批状态(10-未提交;20-审判中;30-已审批;)
     */
    private String exaAppStatus;
    /**
     * 审批结果(10-同意;20-不同意;)
     */
    private String exaAppResult;
    /**
     * 是否退回（10-无;20-已退回;）
     */
    private String exaBack;
    /**
     * 流程ID
     */
    private String processId;
    /**
     * 当前流程节点ID
     */
    private String nowProcessId;
    /**
     * 汇总审批状态(1-不为空;0-为空;)
     */
    private String foSearch;
    /**
     * 档案形态（10-电子形态;20-实物形态）
     */
    private String arcFormat;
    /**
     * 代理人名
     */
    private String agentName;
    /**
     * 借阅ID
     */
    private String borrowId;
    /**
     * 是否移交行档室
     */
    private String hasMoveBank;
    /**
     * 借阅开始时间
     */
    private Date borStaTime;
    /**
     * 借阅结束时间
     */
    private Date borEndTime;

}
