package net.northking.iacmp.elasticsearch.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

/**
 * @Author：Yanqingyu
 * @ClassName:CmsImageType
 * @Description：内管影像索引
 * @Date：Create in 7:14 PM2019/9/17
 * @Modified by:
 * @Version:1.0
 */
@Document(indexName = "cms_image_index", type = "cms_image_type")
@Data
@EqualsAndHashCode(callSuper = false)
public class CmsImageType {

    /**
     * 主键
     */
    @Id
    private Long id;
    /**
     * 影像文件编号
     */
    private Long imageId;

    /**
     * 影像名称
     */
    private String imageName;

    /**
     * 影像上传时间
     */
    private Date imageUploadTime;

    /**
     * 所属系统
     */
    private String sysCode;

    /**
     * 影像上传人
     */
    private String imageUploadBy;

    /**
     * 绑定批次
     */
    private String batchId;

    /**
     * 分类编码
     */
    private String billCode;

    /**
     * 动态元数据
     */
    private Object metaData;

    /**
     * 影像大小
     */
    private Double imageSize;

    /**
     * 影像备注信息
     */
    private String remark;

    /**
     * 源系统
     */
    private String imageSource;

    /**
     * 部门编码
     */
    private String deptId;
}
