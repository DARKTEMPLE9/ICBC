package net.northking.iacmp.imp.domain;


import lombok.Data;

/**
 * 异常流水表 im_busino
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Data
public class ImBusino {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 文件名异常流水号
     */
    private String busino;

}
