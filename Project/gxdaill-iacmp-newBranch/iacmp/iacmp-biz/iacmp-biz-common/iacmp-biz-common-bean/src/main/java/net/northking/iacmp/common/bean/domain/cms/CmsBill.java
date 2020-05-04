package net.northking.iacmp.common.bean.domain.cms;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.core.domain.BaseEntity;
import net.northking.iacmp.system.domain.SysMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * 分类表 cms_bill
 *
 * @author qingyu.yan
 * @date 2019-08-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CmsBill extends BaseEntity implements Comparable<CmsBill> {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 字符串id
     */
    private String strId;
    /**
     * 类型名称
     */
    private String billName;
    /**
     * 类型编码
     */
    private String billCode;
    /**
     * 父节点
     */
    private Long parentId;
    /**
     * 类型顺序
     */
    private Integer billOrder;
    /**
     * 是否为叶子节点(0-子节点，1-非子节点)
     */
    private String leaf;
    /**
     * 祖级列表
     */
    private String allPath;
    /**
     * 分类状态（0-启用，1-停用）
     */
    private String status;
    /**
     * 分类下的文件数
     */
    private Integer fileNum;
    /**
     * 文件监控数
     */
    private Integer monitorNum;
    /**
     * 分类下的实际文件数
     */
    private Integer actualFileNum;
    /**
     * 模型对象
     */
    private List<CmsModel> models;

    /**
     * 展示方式（1-只显示最新，2-显示历史，3-按日期成对出现）
     */
    private String display;
    /**
     * 是否是文件 1-是 0,NULL-否
     */
    private String isFile;
    /**
     * 文件id
     */
    private Long fileImageId;
    /**
     * 文件名称
     */
    private String fileImageName;

    /**
     * 文件的字符串逻辑主键
     */
    private String stringId;

    /**
     * 文件的billId
     */
    private Long fileImageBillId;

    /**
     * 文件或影像路径
     */
    private String fileImagePath;
    /**
     * 文件或影像类型
     */
    private String fileImageType;

    /**
     * 文件或影像服务器路径
     */
    private String serverUrl;

    /**
     * hadoopType
     */
    private String hadoopType;

    /**
     * 文件标签
     */
    private String trg;

    /**
     * 标签节点（1-是，0-否）
     */
    private String trgNode;

    private Long userId;

    /**
     * 是否人工上传 1-是，0-否
     */
    private String manualUpload;

    /**
     * 分类所具有的按钮
     */
    private List<SysMenu> menus;

    @Override
    public int compareTo(CmsBill o) {
        if (id > o.getId()) {
            return 1;
        } else if (id == o.getId()) {
            return 0;
        } else {
            return -1;
        }
    }
}
