package net.northking.iacmp.common.bean.domain.ams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.core.domain.BaseEntity;

/**
 * 切片模板表 im_template_def
 *
 * @author wxy
 * @date 2019-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ImTemplateDef extends BaseEntity {
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
    private String pYSZM;
    /**
     * 主键
     */
    private Long tdefId;

}
