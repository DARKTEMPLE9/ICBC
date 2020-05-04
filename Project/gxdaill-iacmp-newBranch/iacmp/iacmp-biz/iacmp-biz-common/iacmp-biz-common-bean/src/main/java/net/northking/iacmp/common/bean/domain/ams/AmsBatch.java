package net.northking.iacmp.common.bean.domain.ams;

import lombok.Data;
import lombok.EqualsAndHashCode;

import net.northking.iacmp.annotation.Excel;
import net.northking.iacmp.core.domain.BaseEntity;

import java.util.Date;

/**
 * 档案著录表 ams_batch
 *
 * @author wxy
 * @date 2019-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AmsBatch extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
//    @Excel(name = "主键", width = 50)
    private String id;
    /**
     * 档案名称
     */
    @Excel(name = "题名(必输)")
    private String arcName;
    /**
     * 责任人
     */
    @Excel(name = "保管人(必输)")
    private String respOpName;
    /**
     * 创建人名称
     */

    private String crtName;
    /**
     * 创建人号
     */
//    @Excel(name = "创建人号")
    private String crtNo;
    /**
     * 文件编号
     */
    @Excel(name = "文件编号(必输)")
    private String arcCode;
    /**
     * 形成日期
     */
    @Excel(name = "装订日期(必输)", dateFormat = "yyyy-MM-dd", prompt = "输入日期格式如：2019-08-01")
    private Date arcCreTime;
    /**
     * 创建时间
     */
    private Date crtTime;
    /**
     * 笔数
     */
    @Excel(name = "笔数(必输)")
    private String totalTransactions;
    /**
     * 页数
     */
    @Excel(name = "页数")
    private Integer arcPageNum;
    /**
     * 档案类型
     */
    @Excel(name = "档案类型(必输)", combo = {"会计档案"})
    private String arcBill;
    /**
     * 二级类目
     */
    @Excel(name = "二级类目(必输)", combo = {"柜面凭证", "电子流水", "单位开户资料", "监管报送材料"})
    private String arcBillDept;
    /**
     * 保管期限
     */
    @Excel(name = "保管期限(10年、30年、永久)(必输)", readConverterExp = "10=10,30=30,99=永久", combo = {"10", "30", "永久"})
    private String valPeriod;
    /**
     * 密级 01-绝密、02-机密、03-秘密、04-内部
     */
    @Excel(name = "密级(必输，默认内部公开)", readConverterExp = "01=绝密,02=机密,03=秘密,04=内部公开", combo = {"绝密", "机密", "秘密", "内部公开"})
    private String arcLevel;
    /**
     * 是否涉及费用  0-否 1-是
     */
    @Excel(name = "是否涉及费用", readConverterExp = "0=否,1=是", combo = {"是", "否"})
    private String expenseInvolve;
    /**
     * 档案形态（10-电子形态;20-实物形态;）
     */
    @Excel(name = "是否电子化", readConverterExp = "0=否,1=是", combo = {"是", "否"})
    private String arcFormat1;
    /**
     * 载体形式
     */
    @Excel(name = "载体形式", readConverterExp = "01=纸质档案,02=音像档案,03=电子档案,04=实物档案,99=其他档案", combo = {"纸质档案", "音像档案", "电子档案",
            "实物档案", "其他档案"})
    private String carrier;
    /**
     * 附注
     */
    @Excel(name = "附件")
    private String eTC;
    /**
     * 附件数
     */
    @Excel(name = "附件数")
    private Integer etcNum;
    /**
     * 备注
     */
    @Excel(name = "备注信息")
    private String remark;
    /**
     * 摘要
     */
    @Excel(name = "摘要")
    private String arcAbstract;
    /**
     * 所属年份
     */
//    @Excel(name = "所属年份"dateFormat = "yyyy")
    private String receivingTimeY;
    /**
     * 接收时间
     */
//    @Excel(name = "接收时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date receivingTime;
    /**
     * 档案形态（10-电子形态;20-实物形态;）
     */
//    @Excel(name = "档案形态",readConverterExp="10=电子形态,20=实物形态")
    private String arcFormat;
    /**
     * 注入批次号
     */
//    @Excel(name = "注入批次号")
    private String batchNo;
    /**
     * 部门名称
     */
//    @Excel(name = "部门名称")
    private String orgName;
    /**
     * 部门代码
     */
//    @Excel(name = "部门代码")
    private String orgCode;
    /**
     * 修改时间
     */
//    @Excel(name = "修改时间")
    private Date modTime;
    /**
     * 修改人号
     */
//    @Excel(name = "修改人号")
    private String modNo;
    /**
     * 修改人名称
     */
//    @Excel(name = "修改人名称")
    private String modName;
    /**
     * 档案当前状态（0-已删除;1-已著录;2-申请中;3-待入盒;4-待入库;5-已入库;6-退回）
     */
//    @Excel(name = "当前状态" ,readConverterExp = "0=已删除,1=已著录,2=申请中,3=待入盒,4=待入库,5=已入库,6=退回")
    private String status;
    /**
     * 档案编号
     */
//    @Excel(name = "档案编号")
    private String arcNo;
    /**
     * 档案移交人
     */
//    @Excel(name = "档案移交人")
    private String arcTransfer;

    /**
     * 上交人号
     */
//    @Excel(name = "上交人号")
    private String turnInNo;
    /**
     * 上交人名称
     */
//    @Excel(name = "上交人名称")
    private String turnInName;
    /**
     * 接收人号
     */
//    @Excel(name = "接收人号")
    private String receiveNo;
    /**
     * 接收人名称
     */
//    @Excel(name = "接收人名称")
    private String receiveName;
    /**
     * 退回原因
     */
//    @Excel(name = "退回原因")
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
     * 份数
     */
    private Integer arcNum;


    /**
     * 归档部门
     */
//    @Excel(name = "归档部门")
    private String filingDepa;
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
     * 来源方式
     */
    private String originMode;

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
//    @Excel(name = "接收意见")
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
     * 档案存放地址
     */
    private String storageLocation;
    /**
     * 移交时间
     */
//    @Excel(name = "移交时间")
    private Date turnInTime;
    /**
     * 页面移交开始时间
     */
    private Date turnInTimeStart;
    /**
     * 页面移交结束时间
     */
    private Date turnInTimeEnd;
    /**
     * 接受时间
     */
    private Date receiveTime;
    /**
     * 接收开始时间
     */
    private Date receivingTimeStart;
    /**
     * 接收结束时间
     */
    private Date receivingTimeEnd;
    /**
     * 档案统计记录
     */
    private Integer tjNum;

    /**
     * 是否移交行档室
     */
    private String arcHasMoveBank;

    /**
     * 全局流水号
     */
    private String busiNo;

    /**
     * 姓名
     */
    private String peopleName;
    /**
     * 身份证号
     */
    private String peopleCard;
    /**
     * 批次主键
     */
    private Long batchPk;
    /**
     * 相对方
     */
    private String counterpart;

}
