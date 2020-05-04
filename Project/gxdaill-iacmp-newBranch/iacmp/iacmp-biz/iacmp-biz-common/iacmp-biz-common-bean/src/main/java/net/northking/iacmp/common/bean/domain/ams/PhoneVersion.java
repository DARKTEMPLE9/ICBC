package net.northking.iacmp.common.bean.domain.ams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.core.domain.BaseEntity;

/**
 * 手机版本表 phone_version
 *
 * @author wxy
 * @date 2019-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PhoneVersion extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;
    /**
     * 版本号
     */
    private String versionNo;
    /**
     * 版本更新地址
     */
    private String url;
    /**
     * 版本更新说明
     */
    private String title;
    /**
     * “1”强制更新，”0“非强制更新
     */
    private String sTATE;
    /**
     * ”1“最新版本，”0“旧版本
     */
    private String fLAG;
    /**
     * "ios"或”android“
     */
    private String phoneType;
    /**
     * 主键
     */
    private Long pversionId;

}
