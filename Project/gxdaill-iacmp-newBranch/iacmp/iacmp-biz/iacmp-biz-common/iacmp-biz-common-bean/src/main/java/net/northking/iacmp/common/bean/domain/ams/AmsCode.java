package net.northking.iacmp.common.bean.domain.ams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.core.domain.BaseEntity;

/**
 * 编号生成表 ams_code
 *
 * @author wxy
 * @date 2019-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AmsCode extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long codeId;
    /**
     * id
     */
    private String id;
    /**
     * 当前人在当前类型节点的最大值
     */
    private Integer maxCode;
    /**
     * 当前用户code
     */
    private String userCode;
    /**
     * 编号类型，著录编号(zlcode)，上交审批编号(sjcode)，电子借阅审批编号(dzjycode)，实物借阅审批编号(swjycode)
     */
    private String type;
    /**
     * 当前时间
     */
    private String nowTime;
    /**
     * 机构代码
     */
    private String orgCode;
    /**
     * 10,30,99
     */
    private Integer period;
    /**
     * 0(总行)；1（部门）
     */
    private String crtOrgFlag;
    /**
     * 档案盒业务部门
     */
    private String opOrgCode;
    /**
     *
     */
    private String arcType;

}
