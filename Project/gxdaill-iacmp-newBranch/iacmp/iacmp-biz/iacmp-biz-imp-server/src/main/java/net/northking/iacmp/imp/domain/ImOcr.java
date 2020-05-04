package net.northking.iacmp.imp.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 识别结果表 im_ocr
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Data
public class ImOcr {
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
    @JsonProperty(value = "workitemId")
    private String workItemId;
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
