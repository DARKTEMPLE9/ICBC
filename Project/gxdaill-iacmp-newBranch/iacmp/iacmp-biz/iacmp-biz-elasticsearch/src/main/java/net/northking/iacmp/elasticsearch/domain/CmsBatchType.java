package net.northking.iacmp.elasticsearch.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

/**
 * @Author：Yanqingyu
 * @ClassName:PmsBatchType
 * @Description：内管批次索引
 * @Date：Create in 7:13 PM2019/9/17
 * @Modified by:
 * @Version:1.0
 */
@Document(indexName = "cms_batch_index", type = "cms_batch_type")
@Data
@EqualsAndHashCode(callSuper = false)
public class CmsBatchType {
    /**
     * 主键id
     */
    @Id
    private Long id;

    /**
     * 批次编号
     */
    private Long batchId;

    /**
     * 批次创建时间
     */
    private Date batchCreateTime;

    /**
     * 所属系统
     */
    private String sysCode;

    /**
     * 来源系统
     */
    private String sysSource;

    /**
     * 批次创建人
     */
    private String batchCreateBy;

    /**
     * 所属模型
     */
    private Object model;

    /**
     * 动态元数据
     */
    private Object metaData;

    /**
     * 部门名称
     */
    private String deptId;
}
