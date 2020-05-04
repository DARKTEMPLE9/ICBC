package net.northking.iacmp.common.bean.vo.ams;

import lombok.Getter;
import lombok.Setter;
import net.northking.iacmp.core.domain.BaseEntity;

import java.util.Date;

/**
 * 档案著录表 ams_batch
 *
 * @author wxy
 * @date 2019-08-01
 */
@Getter
@Setter
public class AmsBatchVO extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;
    /**
     * 注入批次号
     */
    private String batchNo;
    /**
     * 部门代码
     */
    private String orgCode;
    /**
     * 部门名称
     */
    private String orgName;
    /**
     * 创建时间
     */
    private Date crtTime;
    /**
     * 创建时间年份
     */
    private String crtTimeY;
    /**
     * 创建人号
     */
    private String crtNo;
    /**
     * 创建人名称
     */
    private String crtName;
    /**
     * 修改时间
     */
    private Date modTime;
    /**
     * 修改人号
     */
    private String modNo;
    /**
     * 修改人名称
     */
    private String modName;
    /**
     * 档案当前状态（0-已删除;1-已著录;2-申请中;3-待入盒;4-待入库;5-已入库;6-退回）
     */
    private String status;
    /**
     * 档案编号
     */
    private String arcNo;
    /**
     * 档案名称
     */
    private String arcName;
    /**
     * 档案移交人
     */
    private String arcTransfer;
    /**
     * 备注
     */
    private String remark;
    /**
     * 上交人号
     */
    private String turnInNo;
    /**
     * 上交人名称
     */
    private String turnInName;
    /**
     * 接收人号
     */
    private String receiveNo;
    /**
     * 接收人名称
     */
    private String receiveName;
    /**
     * 退回原因
     */
    private String returnReason;
    /**
     * 审批信息ID
     */
    private String apprInfoId;
    /**
     * 责任部门
     */
    private String respDepaName;
    /**
     * 责任人
     */
    private String respOpName;
    /**
     * 文件编号
     */
    private String arcCode;
    /**
     * 份数
     */
    private Integer arcNum;
    /**
     * 页数
     */
    private Integer arcPageNum;
    /**
     * 密级 01-绝密、02-机密、03-秘密、04-内部
     */
    private String arcLevel;
    /**
     * 形成日期
     */
    private Date arcCreTime;
    /**
     * 归档部门
     */
    private String filingDepa;
    /**
     * 保管期限
     */
    private String valPeriod;
    /**
     * 主题词
     */
    private String themeSpeech;
    /**
     * 开本
     */
    private String cutting;
    /**
     * 语种
     */
    private String language;
    /**
     * 载体形式
     */
    private String carrier;
    /**
     * 并列题名
     */
    private String otherName;
    /**
     * 转发文号
     */
    private String forwardNo;
    /**
     * 转发文件名
     */
    private String forwardName;
    /**
     * 收文编号
     */
    private String receiptNo;
    /**
     * 附注
     */
    private String eTC;
    /**
     * 附件数
     */
    private Integer etcNum;
    /**
     * 实物分类
     */
    private String practType;
    /**
     * 实物描述
     */
    private String practDesc;
    /**
     * 版本
     */
    private String edition;
    /**
     * A4数
     */
    private Integer afourNum;
    /**
     * 文本
     */
    private String arcText;
    /**
     * 来源单位或个人
     */
    private String originType;
    /**
     * 存放地点
     */
    private String saveAddress;
    /**
     * 媒体类别
     */
    private String medelType;
    /**
     * 媒体编号
     */
    private String medelCode;
    /**
     * 归档日期
     */
    private Date filingTime;
    /**
     * 是否涉及费用  0-否 1-是
     */
    private String expenseInvolve;
    /**
     * 档案类别
     */
    private String arcBill;
    /**
     * 档案形态（10-电子形态;20-实物形态;）
     */
    private String arcFormat;
    /**
     * 来源方式
     */
    private String originMode;
    /**
     * 摘要
     */
    private String arcAbstract;
    /**
     * 责任部门编号
     */
    private String respDepaCode;
    /**
     * 责任者编号
     */
    private String respOpCode;
    /**
     * 档案类型编号
     */
    private String arcBillCode;
    /**
     * 归档单位编号
     */
    private String filingDepaCode;
    /**
     * 部门档案类型
     */
    private String arcBillDept;
    /**
     * 部门档案类型编号
     */
    private String arcBillDeptCode;
    /**
     * 授予单位
     */
    private String conferIns;
    /**
     * 接收单位
     */
    private String receiveIns;
    /**
     * 照片号
     */
    private String photoNo;
    /**
     * 底片号
     */
    private String negativesNo;
    /**
     * 摄影者
     */
    private String photographer;
    /**
     * 参见号
     */
    private String indexMark;
    /**
     * 类型
     */
    private String bill;
    /**
     * 描述
     */
    private String arcDesc;
    /**
     * 版权
     */
    private String arcCopyright;
    /**
     * 格式
     */
    private String arcLayout;
    /**
     * 业务类型
     */
    private String serviceType;
    /**
     * 关键字
     */
    private String keyWord;
    /**
     * 接收意见
     */
    private String receivingOpinion;
    /**
     * 入盒执行人Code
     */
    private String boxOpCode;
    /**
     * 入盒执行人Name
     */
    private String boxOpName;
    /**
     * 入盒执行部门Code
     */
    private String boxOrgCode;
    /**
     * 入盒执行部门Name
     */
    private String boxOrgName;
    /**
     * 所在盒ID
     */
    private String boxId;
    /**
     * 著录修改页面
     */
    private String updatePath;
    /**
     * 著录查看页面
     */
    private String viewPath;
    /**
     * 费用金额
     */
    private String expense;
    /**
     * 不电子化的原因
     */
    private String reason;
    /**
     * 有无介质  1-有，0-无
     */
    private String isNoMedium;
    /**
     * 文字说明
     */
    private String caption;
    /**
     * 时长
     */
    private String duration;
    /**
     * 业务起始日
     */
    private Date businessStartTime;
    /**
     * 业务到期日
     */
    private Date businessEndTime;
    /**
     * 接收时间
     */
    private Date receivingTime;
    /**
     * 档案存放地址
     */
    private String storageLocation;
    /**
     * 移交时间
     */
    private Date turnInTime;
    /**
     * 接受时间
     */
    private Date receiveTime;

    /**
     * 形成日期(开始)
     */
    private Date arcCreTimeStart;

    /**
     * 形成日期(结束)
     */
    private Date arcCreTimeEnd;

    /**
     * 著录日期(开始)
     */
    private Date crtTimeStart;

    /**
     * 著录日期(结束)
     */
    private Date crtTimeEnd;

    /**
     * 接收日期(开始)
     */
    private Date receivingTimeStart;

    /**
     * 接收(结束)
     */
    private Date receivingTimeEnd;

    /**
     * 笔数
     */
    private String totalTransactions;

    /**
     * 是否移交行档室
     */
    private String arcHasMoveBank;

    /**
     * 姓名
     */
    private String peopleName;
    /**
     * 身份证号
     */
    private String peopleCard;
    /**
     * 大于等于接收时间
     */
    private Date recTimeGte;
    /**
     * 小于等于接收时间
     */
    private Date recTimeLte;
    /**
     * 大于等于登记时间
     */
    private Date regTimeGte;
    /**
     * 小于等于登记时间
     */
    private Date regTimeLte;
}
