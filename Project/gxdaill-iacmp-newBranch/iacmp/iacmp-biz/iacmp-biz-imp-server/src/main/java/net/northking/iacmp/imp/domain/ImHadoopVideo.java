package net.northking.iacmp.imp.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 大数据视频表 im_hadoop_video
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImHadoopVideo {
    private static final long serialVersionUID = 1L;

    /**
     * 逻辑主键
     */
    private String id;
    /**
     * 视频名称
     */
    private String videoName;
    /**
     * 视频状态（0-正常；1-已废弃）
     */
    private String status;
    /**
     * 视频类别（0-双录视频；1-安防视频）
     */
    private String videoBill;
    /**
     * 视频批次
     */
    private String batchId;
    /**
     * 视频顺序
     */
    private BigDecimal serialNo;
    /**
     * 视频存放路径
     */
    private String videoPath;
    /**
     * 视频大小
     */
    private BigDecimal videoSize;
    /**
     * 视频类型
     */
    private String videoType;
    /**
     * 备注
     */
    private String remark;
    /**
     * 上传人编号
     */
    private String createUser;
    /**
     * 上传人名称
     */
    private String createUserName;
    /**
     * 所属系统（用于分表）
     */
    private String systemFlag;
    /**
     * 视频来源
     */
    private String videoSource;
    /**
     * 影像标识状态
     */
    private Integer sysFlagInt;
    /**
     * 主键
     */
    private Long videoId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private Date createTime;


}
