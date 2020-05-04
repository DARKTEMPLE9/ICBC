package net.northking.iacmp.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 用户和岗位关联 sys_user_post
 *
 * @author wxy
 */
public class SysUserPost {
    /**
     * 主键ID
     */
    private Long upId;
    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 岗位ID
     */
    private Long postId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getUpId() {
        return this.upId;
    }

    public void setUpId(Long upId) {
        this.upId = upId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("upId", getUpId())
                .append("userId", getUserId())
                .append("postId", getPostId())
                .toString();
    }
}
