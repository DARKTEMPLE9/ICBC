package net.northking.iacmp.common.bean.domain.ams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.core.domain.BaseEntity;

/**
 * 影像识别表 im_ocr
 *
 * @author wxy
 * @date 2019-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ImOcr extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 逻辑主键
     */
    private String id;
    /**
     * 影像id
     */
    private String imageId;
    /**
     * 识别id
     */
    private String workitemId;
    /**
     * 识别结果
     */
    private String ocrResult;
    /**
     * 识别信息
     */
    private String ocrInfo;
    /**
     * 主键
     */
    private Long ocrId;

}
