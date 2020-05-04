package net.northking.iacmp.common.bean.vo.ams;

import lombok.Getter;
import lombok.Setter;
import net.northking.iacmp.core.domain.BaseEntity;

@Getter
@Setter
public class AmsCabinetVO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long cabinetId;
    /**
     * id
     */
    private String id;
    /**
     * 库柜编码
     */
    private String code;
    /**
     * 库柜名称
     */
    private String name;
    /**
     * 库柜状态（0-未满;1-已满;）
     */
    private String status;
    /**
     * 库房名称
     */
    private String depId;
    /**
     * 库房编码
     */
    private String depCode;
    /**
     * 库房名称
     */
    private String depName;
    /**
     * 库房类型
     */
    private String depType;
    /**
     * 责任人
     */
    private String dutyMan;
    /**
     * 档案类型
     */
    private String arcType;
    /**
     * 库房状态（1-正常；0-废弃；）
     */
    private String depStatus;
    /**
     * 所属部门名称
     */
    private String orgName;
}
