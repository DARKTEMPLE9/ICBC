package net.northking.iacmp.common.bean.domain.ams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.northking.iacmp.core.domain.BaseEntity;

import java.util.Date;

/**
 * 服务表 web_service
 *
 * @author wxy
 * @date 2019-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WebService extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;
    /**
     * 客户端系统标识符
     */
    private String sysId;
    /**
     * 受理号1（车承保时为交强号）
     */
    private String operationcode;
    /**
     * 受理号2（车承保时为商业号）
     */
    private String regbillglideno;
    /**
     * 响应码（第三方上传使用）
     */
    private String orderNum;
    /**
     * 接收报文时间
     */
    private Date operaTime;
    /**
     * 密匙
     */
    private String sysKey;
    /**
     * 方法参数
     */
    private String methodType;
    /**
     * xml报文
     */
    private String content;
    /**
     * 主键
     */
    private Long webId;

}
