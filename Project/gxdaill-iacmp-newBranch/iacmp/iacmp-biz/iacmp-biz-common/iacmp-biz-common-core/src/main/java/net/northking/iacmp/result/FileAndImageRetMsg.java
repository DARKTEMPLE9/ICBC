package net.northking.iacmp.result;

import lombok.Data;

/**
 * @Author：Yanqingyu
 * @ClassName:FileAndImageMsg
 * @Description：内管返回档案接口文件信息
 * @Date：Create in 1:44 PM2019/9/12
 * @Modified by:
 * @Version:1.0
 */
@Data
public class FileAndImageRetMsg {

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件大小
     */
    private String fileSize;

    /**
     * 内管返回文件编号
     */
    private String fileId;

    /**
     * 上传内管是否成功
     */
    private String uploadSuccess;

    /**
     * 文件顺序码
     */
    private String orderNum;

    /**
     * 文件批次号
     */
    private String batchNo;

    /**
     * 文件url
     */
    private String fileUrl;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * Md5值
     */
    private String md5;

    /**
     * hadoopType
     */
    private String hadoopType;

    /**
     * 下载地址
     */
    private String willPath;

}
