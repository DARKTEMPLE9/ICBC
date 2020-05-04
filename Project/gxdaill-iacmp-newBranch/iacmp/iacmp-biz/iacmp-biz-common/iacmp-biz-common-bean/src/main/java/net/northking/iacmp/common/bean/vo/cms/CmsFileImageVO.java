package net.northking.iacmp.common.bean.vo.cms;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.core.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 项目管理结构树页文件或影像VO
 *
 * @author rui.yin
 * @date 2019-09-03
 */
@Data
public class CmsFileImageVO {
    private static final long serialVersionUID = 1L;

    /**
     * 逻辑主键
     */
    private Long id;
    /**
     * 逻辑主键
     */
    private Long fileId;

    /**
     * 字符串逻辑主键
     */
    private String stringId;
    /**
     * 编号
     */
    private String fileImageNo;
    /**
     * id
     */
    private String fileImageId;
    /**
     * 文件名称
     */
    private String fileImageName;
    /**
     * 文件路径
     */
    private String fileImagePath;
    /**
     * 文件名称
     */
    private String voName;
    /**
     * 文件所属分类id
     */
    private Integer billId;
    /**
     * 文件所属分类名称
     */
    private String billName;
    /**
     * 文件批次
     */
    private Long batchId;
    /**
     * 文件存放路径
     */
    private String filePath;
    /**
     * 文件大小
     */
    private BigDecimal fileSize;

    /**
     * 是否为必选类型
     */
    private String type;
    /**
     * 文件预览下载地址
     */
    private String fileUrl;

    /**
     * md5值
     */
    private String md5;

    private Date createTime;
    private Date updateTime;

}
