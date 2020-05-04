package net.northking.iacmp.common.bean.domain.ams;


import lombok.Data;
import net.northking.iacmp.core.domain.BaseEntity;

import java.util.Date;

/**
 * 邮件表 ams_send_email
 *
 * @author wxy
 * @date 2019-10-25
 */
@Data
public class AmsSendEmail extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer emailId;
    /**
     * 发件人
     */
    private String sendUser;
    /**
     * 收件人
     */
    private String reviceUser;
    /**
     * 收件人邮箱
     */
    private String reviceUserEmail;
    /**
     * 邮件发送状态
     */
    private String status;
    /**
     * 发件人邮箱
     */
    private String sendUserEmail;

    /**
     * 审批编号
     */
    private String exaAppId;

    /**
     * 档案名称
     */
    private String arcName;

    /**
     * 邮件发送时间
     */
    private Date sendEmailTime;
}