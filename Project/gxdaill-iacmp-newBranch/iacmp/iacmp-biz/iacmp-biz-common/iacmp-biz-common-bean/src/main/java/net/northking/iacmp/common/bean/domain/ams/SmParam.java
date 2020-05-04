package net.northking.iacmp.common.bean.domain.ams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.core.domain.BaseEntity;

/**
 * 参数表 sm_param
 *
 * @author wxy
 * @date 2019-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SmParam extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;
    /**
     * 参数名称
     */
    private String name;
    /**
     * 参数值
     */
    private String value;
    /**
     * 参数编码
     */
    private String code;
    /**
     * 主键
     */
    private Long paramId;

}
