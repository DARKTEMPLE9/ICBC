package net.northking.iacmp.common.bean.domain.ams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.core.domain.BaseEntity;

import java.util.Date;

/**
 * 视频上传日志表 im_hadoop_upload_log
 *
 * @author wxy
 * @date 2019-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ImHadoopUploadLog extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 逻辑主键
     */
    private String id;
    /**
     * 影像ID
     */
    private String imageId;
    /**
     * 影像路径
     */
    private String imagePath;
    /**
     * 用户管理标识
     */
    private Integer uploadFlag;
    /**
     * 上传时间
     */
    private Date uploadTime;
    /**
     * 用户管理标识
     */
    private Date uploadOvertime;
    /**
     * 文件数量
     */
    private Integer failCount;
    /**
     * 系统标识
     */
    private String systemFlag;
    /**
     * 文件来源
     */
    private String failReason;
    /**
     * 文件大小
     */
    private Integer fileSize;
    /**
     * 文件类型
     */
    private Integer fileType;
    /**
     * 影像名称
     */
    private String imageName;
    /**
     * 文件是否为小
     */
    private Integer isSmall;
    /**
     * 主键
     */
    private Long hlogId;

}
