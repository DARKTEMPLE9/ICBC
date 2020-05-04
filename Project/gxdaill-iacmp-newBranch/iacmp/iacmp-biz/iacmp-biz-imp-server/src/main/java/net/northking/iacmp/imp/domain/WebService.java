package net.northking.iacmp.imp.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 报文表 web_service
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Data
public class WebService {
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
    private String operationCode;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date operaTime;
    /**
     *
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
