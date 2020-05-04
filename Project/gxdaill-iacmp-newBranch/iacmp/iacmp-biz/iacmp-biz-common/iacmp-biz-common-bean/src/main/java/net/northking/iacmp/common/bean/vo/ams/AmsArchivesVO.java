package net.northking.iacmp.common.bean.vo.ams;

import lombok.Getter;
import lombok.Setter;
import net.northking.iacmp.core.domain.BaseEntity;


import java.util.Date;
import java.util.List;

/**
 * 档案登记表 ams_archives
 *
 * @author wxy
 * @date 2019-08-01
 */
@Getter
@Setter
public class AmsArchivesVO extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;
    /**
     * 档案编号
     */
    private String arcNo;
    /**
     * 题名
     */
    private String name;
    /**
     * 档案业务部门号
     */
    private String opDepNo;
    /**
     * 档案业务部门名(所属部门)
     */
    private String opDepName;
    /**
     * 档案管理部门号
     */
    private String manaDepNo;
    /**
     * 档案管理部门名
     */
    private String manaDepName;
    /**
     * 库房编号
     */
    private String depotNo;
    /**
     * 库房名称
     */
    private String depotName;
    /**
     * 库房类型
     */
    private String depotType;
    /**
     * 库柜编号
     */
    private String cabintNo;
    /**
     * 库柜名称
     */
    private String cabintName;
    /**
     * 档案形态
     */
    private String arcType;
    /**
     * 责任部门
     */
    private String respDepaName;
    /**
     * 责任者
     */
    private String respOpName;
    /**
     * 份数
     */
    private Integer arcNum;
    /**
     * 页数z
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
     * 归档日期
     */
    private Date filingTime;
    /**
     * 归档单位
     */
    private String filingDepa;
    /**
     * 归档单位名称
     */
    private String filingDeptName;
    /**
     * 档案管理员号
     */
    private String adminNo;
    /**
     * 档案管理员名
     */
    private String adminName;
    /**
     * 档案出库人号
     */
    private String depotOutNo;
    /**
     * 档案出库人名
     */
    private String depotOutName;
    /**
     * 档案借阅人号
     */
    private String borrowNo;
    /**
     * 档案借阅人名
     */
    private String borrowName;
    /**
     * 档案状态
     */
    private String status;
    /**
     * 著入Id
     */
    private String batchId;
    /**
     * 著入编号
     */
    private String batchNo;
    /**
     * 归档部门名称
     */
    private String filingDepaName;
    /**
     * 库房id
     */
    private String depotId;
    /**
     * 库柜id
     */
    private String cabintId;
    /**
     * 档案类型名称
     */
    private String arcBillName;
    /**
     * 档案类型编码
     */
    private String arcBillCode;
    /**
     * 保管期限
     */
    private String valPeriod;
    /**
     * 来源方式
     */
    private String originMode;
    /**
     * 是否涉及费用  0-否 1-是
     */
    private String expenseInvolve;
    /**
     * 图像分类
     */
    private String bill;
    /**
     * 业务类型
     */
    private String serviceType;
    /**
     * 载体形式
     */
    private String carrier;
    /**
     * 实物分类
     */
    private String practType;
    /**
     *
     */
    private String filePath;
    /**
     * 查看著录路径
     */
    private String viewPath;
    /**
     * 保留时间
     */
    private Date persistTime;
    /**
     * 保留期限
     */
    private String persistPeriod;
    /**
     * 是否为保留档案 1-是，0-不是
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
    private Integer boxNum;
    /**
     * batch表的文件编号
     */
    private String fileNo;
    /**
     * 档案存放地址
     */
    private String storageLocation;
    /**
     * 移交时间
     */
    private Date turnInTime;
    /**
     * 接收时间
     */
    private Date receiveTime;
    /**
     * 部门档案类型
     */
    private String arcBillDept;
    /**
     * 部门档案类型编号
     */
    private String arcBillDeptCode;

    private List<String> tids;

    /**
     * 接收日期(开始)
     */
    private Date receivingTimeStart;

    /**
     * 接收(结束)
     */
    private Date receivingTimeEnd;

    /**
     * 形成日期(开始)
     */
    private Date arcCreTimeStart;

    /**
     * 形成日期(结束)
     */
    private Date arcCreTimeEnd;

    /**
     * 归档日期(开始)
     */
    private Date filingTimeStart;

    /**
     * 归档日期(结束)
     */
    private Date filingTimeEnd;
    /**
     * 是否移交行档案室
     */
    private String hasMoveBank;
}
