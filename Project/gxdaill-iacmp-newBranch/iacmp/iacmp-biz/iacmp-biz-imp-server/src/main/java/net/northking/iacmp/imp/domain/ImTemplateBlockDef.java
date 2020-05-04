package net.northking.iacmp.imp.domain;


import lombok.Data;

/**
 * 模版碎片定义表 im_template_block_def
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Data
public class ImTemplateBlockDef {
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
     * 所属模版定义
     */
    private String templateId;
    /**
     * 拼音首字母
     */
    private String pyszm;
    /**
     * 主键
     */
    private Long btdefId;


}
