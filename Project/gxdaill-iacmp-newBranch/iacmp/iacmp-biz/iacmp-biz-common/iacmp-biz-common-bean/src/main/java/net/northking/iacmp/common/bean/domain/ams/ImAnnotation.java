package net.northking.iacmp.common.bean.domain.ams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.core.domain.BaseEntity;

import java.util.Date;

/**
 * 影像注释表 im_annotation
 *
 * @author wxy
 * @date 2019-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ImAnnotation extends BaseEntity {
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
