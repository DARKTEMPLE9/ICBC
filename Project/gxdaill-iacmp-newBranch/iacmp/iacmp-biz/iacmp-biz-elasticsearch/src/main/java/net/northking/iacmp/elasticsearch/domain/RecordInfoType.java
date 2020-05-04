package net.northking.iacmp.elasticsearch.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

@Document(indexName = "record_info_index", type = "record_info_type")
@Data
@EqualsAndHashCode(callSuper = false)
public class RecordInfoType {
    /**
     * 主键id
     */
    @Id
    private Long id;
    /**
     * 档案著录信息
     */
    private Object recordInfo;
    /**
     * 著录号
     */
    private String batchNo;
    /**
     * 部门编码
     */
    private String orgCode;
    /**
     * 部门名称
     */
    private String orgName;
    /**
     * 著录时间
     */
    private Date crtTime;
    /**
     * 著录人编码
     */
    private String crtNo;
    /**
     * 著录人姓名
     */
    private String crtName;
    /**
     * 修改著录信息时间
     */
    private Date modTime;
    /**
     * 修改人编码
     */
    private String modNo;
    /**
     * 修改人姓名
     */
    private String modName;
    /**
     * 档案当前状态(著录状态)
     */
    private String batchStatus;
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
     * 档案备注
     */
    private String arcRemark;
    /**
     * 移交申请人编码
     */
    private String turnInNo;
    /**
     * 移交申请人姓名
     */
    private String turnInName;
    /**
     * 移交接收人编码
     */
    private String receiveNo;
    /**
     * 移交接收人姓名
     */
    private String receiveName;
    /**
     * 移交申请退回原因
     */
    private String returnReason;
    /**
     * 审批信息编码
     */
    private String apprInfoId;
    /**
     * 责任人
     */
    private String respOpName;
    /**
     * 责任部门
     */
    private String respDepaName;
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
     * 密级
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
    private String etc;
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
    private String medelNo;
    /**
     * 归档日期
     */
    private Date filingTime;
    /**
     * 是否涉及费用
     */
    private String expenseInvolve;
    /**
     * 档案类别
     */
    private String arcBill;
    /**
     * 档案形态
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
    private String filingDepCode;
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
     * 类型(图像分类)
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
    private String receivingOption;
    /**
     * 入盒执行人编码
     */
    private String boxOpCode;
    /**
     * 入盒执行人名称
     */
    private String boxOpName;
    /**
     * 入盒执行部门编码
     */
    private String boxOrgCode;
    /**
     * 入盒执行部门名称
     */
    private String boxOrgName;
    /**
     * 所在盒编号
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
     * 不电子化原因
     */
    private String noElecReason;
    /**
     * 有无介质
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
     * 业务起始日期
     */
    private Date businessStartTime;
    /**
     * 业务到期日期
     */
    private Date businessEndTime;
    /**
     * 移交行档案室接收时间
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
     * 移交登记接收时间
     */
    private Date receiveTime;
    /**
     * 笔数
     */
    private String totalTransactions;
    /**
     * 流水号
     */
    private String busino;
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
     * 档案所属部门
     */
    private String opDepNo;
    /**
     * 档案所属部门名称
     */
    private String opDepName;
    /**
     * 档案管理部门
     */
    private String manaDepNo;
    /**
     * 档案管理部门名称
     */
    private String manaDepName;
    /**
     * 库房编号
     */
    private String depotNo;
    /**
     * 库房类型
     */
    private String depotType;
    /**
     * 库房名称
     */
    private String depotName;
    /**
     * 库柜编号
     */
    private String cabintNo;
    /**
     * 库柜名称
     */
    private String cabintName;
    /**
     * 档案管理员编号
     */
    private String adminNo;
    /**
     * 档案管理员名称
     */
    private String adminName;
    /**
     * 档案出库人编号
     */
    private String depotOutNo;
    /**
     * 档案出库人名称
     */
    private String depotOutName;
    /**
     * 档案借阅人编号
     */
    private String borrowNo;
    /**
     * 档案借阅人名称
     */
    private String borrowName;
    /**
     * 档案状态（借阅）
     */
    private String borrowStatus;
    /**
     * 档案状态
     */
    private String arcStatus;
    /**
     * 库房ID
     */
    private String depotId;
    /**
     * 库柜ID
     */
    private String cabintId;
    /**
     * 保留时间
     */
    private Date persistTime;
    /**
     * 保留期限
     */
    private String persistPeriod;
    /**
     * 是否为保留档案
     */
    private String period;
    /**
     * 档案盒号
     */
    private String boxCode;
    /**
     * 档案盒名称
     */
    private String boxName;
    /**
     * 档案件号
     */
    private String boxNum;
    /**
     * 其他预留数据
     */
    private Object metadata;


}
