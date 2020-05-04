package net.northking.iacmp.imp.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 文件表 ceshi_im_file
 *
 * @author weizhe.fan
 * @date 2019-10-14
 */
@Data
public class ImFile implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private String id;
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 文件状态（0-正常；1-已废弃）
     */
    private String status;
    /**
     * 文件批次
     */
    private String batchId;
    /**
     * 影像流水号
     */
    private String busino;
    /**
     * 分片建哈希值
     */
    private String regionNo;
    /**
     * 文件顺序
     */
    private BigDecimal serialno;
    /**
     * 文件存放路径
     */
    private String filePath;
    /**
     * 文件大小
     */
    private BigDecimal fileSize;
    /**
     * 文件类型
     */
    private String fileType;
    /**
     * 所属单据类型
     */
    private String billId;
    /**
     * 单据类型名称
     */
    private String billName;
    /**
     * 上传人编号
     */
    private String createUser;
    /**
     * 上传人名称
     */
    private String createUserName;
    /**
     * 删除时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private Date deleteTime;
    /**
     * 所属系统
     */
    private String systemFlag;
    /**
     * 文件来源
     */
    private String fileSource;
    /**
     * 系统标识状态
     */
    private Integer sysFlagInt;
    /**
     * 主键
     */
    private Long fileId;

    /**
     * 上传时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date createTime;

    /**
     * 备注
     */
    private String remark;

}
