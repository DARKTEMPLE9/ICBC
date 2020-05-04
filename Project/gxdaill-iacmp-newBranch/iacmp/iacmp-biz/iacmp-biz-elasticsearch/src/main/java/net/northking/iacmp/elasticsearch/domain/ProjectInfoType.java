package net.northking.iacmp.elasticsearch.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

@Document(indexName = "project_info_index", type = "project_info_type")
@Data
@EqualsAndHashCode(callSuper = false)
public class ProjectInfoType {
    /**
     * 主键id
     */
    @Id
    private Long id;
    /**
     * 项目信息
     */
    private Object projectInfo;
    /**
     * 全局流水号
     */
    private String transactionCode;
    /**
     * 承建部门
     */
    private String buildDept;
    /**
     * 预算编号
     */
    private String budgetId;
    /**
     * 项目编号
     */
    private String operationCode;
    /**
     * 归属部门
     */
    private String attriDept;
    /**
     * 项目经理
     */
    private String projectManager;
    /**
     * 产品经理
     */
    private String productManager;
    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 创建时间
     */
    private Date projectCreateTime;
    /**
     * 其他预留数据
     */
    private Object metadata;
}
