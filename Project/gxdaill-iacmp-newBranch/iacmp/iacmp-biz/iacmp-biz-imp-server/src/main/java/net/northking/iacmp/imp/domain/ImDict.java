package net.northking.iacmp.imp.domain;

import lombok.Data;

/**
 * 分类字典表 im_dict
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Data
public class ImDict {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 字典编码
     */
    private String dictCode;
    /**
     * 字典名称
     */
    private String dictName;

}
