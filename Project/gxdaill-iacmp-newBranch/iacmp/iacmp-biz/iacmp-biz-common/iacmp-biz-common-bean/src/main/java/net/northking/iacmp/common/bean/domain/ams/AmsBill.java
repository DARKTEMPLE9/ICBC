package net.northking.iacmp.common.bean.domain.ams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.core.domain.BaseEntity;

/**
 * 档案类型表 ams_bill
 *
 * @author wxy
 * @date 2019-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AmsBill extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;
    /**
     * 档案类型名称
     */
    private String name;
    /**
     * 档案类型编码
     */
    private String code;
    /**
     * 档案父类型
     */
    private String parentId;

    /**
     * 档案父类型名称
     */
    private String parentName;
    /**
     * 新增档案路径
     */
    private String addPath;
    /**
     * 修改档案路径
     */
    private String updatePath;
    /**
     * 查看档案路径
     */
    private String viewPath;
    /**
     * 状态
     */
    private String status;
    /**
     * 模板代码
     */
    private String mould;

    /**
     * 模板字符串
     */
    private String mouldStr;
    /**
     * 类型主键
     */
    private Long billId;
}
