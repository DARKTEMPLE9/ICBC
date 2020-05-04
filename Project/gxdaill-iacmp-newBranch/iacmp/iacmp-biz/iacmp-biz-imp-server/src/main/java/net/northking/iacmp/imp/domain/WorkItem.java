package net.northking.iacmp.imp.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;

import java.util.Date;

/**
 * 识别任务表 work_item
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Data
public class WorkItem {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;
    /**
     * 业务编号
     */
    private String code;
    /**
     * 工作项名称
     */
    private String name;
    /**
     * 状态（0-初始；1-就绪；3-已获取；4-已分配；10-已完成）
     */
    private BigDecimal status;
    /**
     * 工作项描述
     */
    private String description;
    /**
     * 工作项类型(OCR-OCR；人工版面分类-人工版面分类；切块-切块； 录入-录入；录入一级岗-录入一级岗；录入二级岗-录入二级岗；录入仲裁-录入仲裁；质检-质检；问题件-问题件；电话处理-电话处理；人工审核-人工审核；)
     */
    private String type;
    /**
     * 录入角色（0-非中文；1-中文；2-录入一级岗；3-录入二级岗）
     */
    private String role;
    /**
     * 任务操作员
     */
    private String operator;
    /**
     * 完成时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private Date finishTime;
    /**
     * 获取时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private Date acquireTime;
    /**
     * 排除操作员1
     */
    private String exOperator1;
    /**
     * 排除操作员2
     */
    private String exOperator2;
    /**
     * 扫描批次
     */
    private String scanBatchId;
    /**
     * 业务批次
     */
    private String batchId;
    /**
     * 原始影像
     */
    private String imageId;
    /**
     * 优先级（大数优先）
     */
    private BigDecimal priority;
    /**
     * 业务日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date bizDate;
    /**
     * 中文工作量
     */
    private BigDecimal chWorkload;
    /**
     * 英文工作量
     */
    private BigDecimal enWorkload;
    /**
     * 字段个数
     */
    private BigDecimal fieldNum;
    /**
     * 指定操作员
     */
    private String validOper;
    /**
     * 非空字段数
     */
    private BigDecimal validFieldNum;
    /**
     * 所属项目
     */
    private String projectId;
    /**
     * 所属单据
     */
    private String billId;
    /**
     * 处理时间
     */
    private BigDecimal processTime;
    /**
     * 批次创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date batchCtime;
    /**
     * 切块影像
     */
    private String imageBlockId;
    /**
     * 排除环节
     */
    private String exWorkItemId;
    /**
     * 第几次录入（1或2等）
     */
    private BigDecimal inputNo;
    /**
     * 月份
     */
    private BigDecimal month;
    /**
     * 年份
     */
    private BigDecimal year;
    /**
     * 日期
     */
    private BigDecimal day;
    /**
     * 操作员姓名
     */
    private String operatorName;
    /**
     * 队列名
     */
    private String queueName;
    /**
     * 获取时间
     */
    private BigDecimal obtainedTime;
    /**
     * 获取时间戳
     */
    private String updateAss;
    /**
     * 主键
     */
    private Long workId;
    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private Date createTime;
}
