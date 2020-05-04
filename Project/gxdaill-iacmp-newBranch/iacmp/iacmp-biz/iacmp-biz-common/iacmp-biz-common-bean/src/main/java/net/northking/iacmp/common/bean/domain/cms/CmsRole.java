package net.northking.iacmp.common.bean.domain.cms;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.annotation.Excel;
import net.northking.iacmp.core.domain.BaseEntity;

import java.util.Date;

/**
 * 分类角色表 cms_role
 *
 * @author qingyu.yan
 * @date 2019-08-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CmsRole extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 角色名称
     */
    @Excel(name = "角色名称")
    private String roleName;
    /**
     * 角色权限字符串
     */
    @Excel(name = "权限字符")
    private String roleKey;
    /**
     * 显示顺序
     */
    @Excel(name = "显示顺序")
    private Integer roleSort;
    /**
     * 数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）
     */
    private String dataScope;
    /**
     * 角色状态（0正常 1停用）
     */
    @Excel(name = "角色状态")
    private String status;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;
    /**
     * 主键
     */
    private Long id;
    /**
     * 分类列表
     */
    private Long[] billIds;
    /**
     * 项目组
     */
    private Long[] projectIds;

    @Excel(name = "创建时间")
    private Date createTime;
}
