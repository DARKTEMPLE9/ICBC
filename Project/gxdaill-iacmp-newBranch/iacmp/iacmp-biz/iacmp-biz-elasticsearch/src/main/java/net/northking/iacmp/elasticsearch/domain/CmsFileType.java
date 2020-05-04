package net.northking.iacmp.elasticsearch.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;
import java.util.Map;

/**
 * @Author：Yanqingyu
 * @ClassName:CmsFileType
 * @Description：内管文件索引
 * @Date：Create in 7:13 PM2019/9/17
 * @Modified by:
 * @Version:1.0
 */
@Document(indexName = "cms_file_index", type = "cms_file_type")
@Data
@EqualsAndHashCode(callSuper = false)
public class CmsFileType {

    /**
     * 主键
     */
    @Id
    private Long id;
    /**
     * 文件编号
     */
    private Long fileId;

    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 文件上传时间
     */
    private Date fileUploadTime;

    /**
     * 所属系统
     */
    private String sysCode;

    /**
     * 文件上传人
     */
    private String fileUploadBy;

    /**
     * 绑定批次
     */
    private Long batchId;

    /**
     * 分类编码
     */
    private String billCode;

    /**
     * 动态元数据
     */
    private Map<String, Object> metaData;

    /**
     * 文件大小
     */
    private Double fileSize;

    /**
     * 文件备注信息
     */
    private String remark;

    /**
     * 源系统
     */
    private String fileSource;

    /**
     * 部门编码
     */
    private String deptId;
}
