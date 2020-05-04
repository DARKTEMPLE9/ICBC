package net.northking.iacmp.imp.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 图片批注表 im_annotation
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Data
public class ImAnnotation {
    private static final long serialVersionUID = 1L;

    /**
     * 逻辑主键
     */
    private String id;
    /**
     * 内容
     */
    private String content;
    /**
     * 创建人名字
     */
    private String creatUserName;
    /**
     * 创建人ID
     */
    private String creatUserId;
    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date creatDate;
    /**
     * 坐标X1
     */
    private String x1;
    /**
     * 坐标X2
     */
    private String x2;
    /**
     * 坐标Y1
     */
    private String y1;
    /**
     * 坐标Y2
     */
    private String y2;
    /**
     * 关联图片
     */
    private String imageId;
    /**
     * 保存批注时画布的宽度
     */
    private String imgW;
    /**
     * 保存批注时画布的高度
     */
    private String imgH;
    /**
     * 主键
     */
    private Long annId;

}