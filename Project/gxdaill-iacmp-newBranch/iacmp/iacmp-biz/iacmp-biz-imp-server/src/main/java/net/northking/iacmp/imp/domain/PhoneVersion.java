package net.northking.iacmp.imp.domain;

import lombok.Data;

/**
 * 移动端版本表 phone_version
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Data
public class PhoneVersion {
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
    private String state;
    /**
     * ”1“最新版本，”0“旧版本
     */
    private String flag;
    /**
     * "ios"或”android“
     */
    private String phoneType;
    /**
     * 主键
     */
    private Long pVersionId;

}
