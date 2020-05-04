package net.northking.iacmp.imp.domain;


import lombok.Data;

/**
 * 切片表 im_block
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Data
public class ImBlock {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;
    /**
     * 切片编码
     */
    private String code;
    /**
     * 切片名称
     */
    private String name;
    /**
     * 所属切片类型
     */
    private String templateBlockId;
    /**
     * ECM系统文件ID
     */
    private String ecmFileId;
    /**
     * 所属影像Id
     */
    private String imageId;
    /**
     * 识别结果
     */
    private String ocrValue;
    /**
     * 识别结果
     */
    private String status;
    /**
     * 识别结果
     */
    private String inputValue;
    /**
     * 主键
     */
    private Long blockId;

}
