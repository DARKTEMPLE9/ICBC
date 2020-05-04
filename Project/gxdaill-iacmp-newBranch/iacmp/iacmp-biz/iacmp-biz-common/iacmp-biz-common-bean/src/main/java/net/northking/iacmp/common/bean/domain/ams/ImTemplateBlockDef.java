package net.northking.iacmp.common.bean.domain.ams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.core.domain.BaseEntity;

/**
 * 切片模板表 im_template_block_def
 *
 * @author wxy
 * @date 2019-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ImTemplateBlockDef extends BaseEntity {
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
    private String pYSZM;
    /**
     * 主键
     */
    private Long btdefId;

}
