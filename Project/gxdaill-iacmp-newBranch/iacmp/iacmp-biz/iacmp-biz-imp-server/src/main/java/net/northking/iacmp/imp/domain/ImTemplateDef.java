package net.northking.iacmp.imp.domain;


import lombok.Data;

/**
 * 票据模版定义表 im_template_def
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Data
public class ImTemplateDef {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;
    /**
     * 模版编码
     */
    private String code;
    /**
     * 模版名称
     */
    private String name;
    /**
     * 拼音首字母
     */
    private String pyszm;
    /**
     * 主键
     */
    private Long tdefId;

}
