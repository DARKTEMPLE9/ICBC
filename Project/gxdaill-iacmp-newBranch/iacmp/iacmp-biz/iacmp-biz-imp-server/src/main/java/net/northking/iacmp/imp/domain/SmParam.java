package net.northking.iacmp.imp.domain;

import lombok.Data;

/**
 * 参数配置表 sm_param
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Data
public class SmParam {
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
