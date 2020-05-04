package net.northking.iacmp.constant;

/**
 * @Author：chenwei
 * @ClassName:AmsConstants
 * @Description：综合档案系统常量
 * @Date：Create in 2019/11/23
 * @Modified by:
 * @Version:1.0
 */
public class AmsConstants {

    /**
     * filetrans 常量
     */
    /**
     * httpfiletrans服务地址
     */
    public static final String FILETRANSIP = "httpfiletransIp";
    /**
     * httpfiletrans服务端口号
     */
    public static final String FILETRANSPORT = "httpfiletransPort";
    /**
     * httpfiletrans下载连接串
     */
    public static final String FILETRANSPATHDOWN = "httpfiletransPath";
    /**
     * httpfiletrans上传连接串
     */
    public static final String FILETRANSPATHUP = "httpfiletransPathup";
    /**
     * httpfiletrans本地存储地址
     */
    public static final String LOCALFILEPATH = "localFilePath";


    /**
     * window应用服务器存放文件的地址
     */
    public static final String WINDOWS_SERVER_PATH = "windowsServerPath";//"D:/software/alidata/data/hadoop/";

    /**
     * windows httpfiletrans本地存储地址
     */
    public static final String WINDOWS_LOCALFILEPATH = "windowsLocalFilePath"; //"D:/software/alidata/data/hadoop/";

    /**
     * windows应用服务器存放PDF文件的地址
     */
    public static final String WINDOWS_SERVER_PDF_PATH = "windowsServerPdfPath"; //"D:/software/alidata/data/hadoop/pdf";

    /**
     * windows应用服务器存放PDF文件的地址
     */
    public static final String WINDOWS_FILETRANSIP = "windowsHttpfiletransIp"; //"D:/software/alidata/data/hadoop/pdf";

    /**
     * 审批流程是否使用
     */
    public static final String APPROVEONOFF = "approveOnOff";
    /************************魔法值********************************/

    /**
     * 报文KEY 文件列表
     */
    public static final String FILELIST = "fileList";

    /**
     * 报文KEY 全局流水号（项目编号）
     */
    public static final String OPERATIONCODE = "operationCode";

    /**
     * 报文KEY 文件名称
     */
    public static final String FILENAME = "fileName";

    /**
     * 报文KEY 文件路径
     */
    public static final String FILEPATH = "filePath";

    /**
     * 报文KEY 分类名称
     */
    public static final String BILLNAME = "billName";

    /**
     * 报文KEY 接入系统
     */
    public static final String SYSTEMFLAG = "systemFlag";

    /**
     * 报文KEY 返回编码
     */
    public static final String TOTALRESULTCODE = "totalResultCode";

    /**
     * 报文KEY 返回信息
     */
    public static final String TOTALRESULTMSG = "totalResultMsg";
/***********************************snowflake全局唯一标识**********************************/
    /**
     * 机器ID 0~31
     */
    public static final String WORKER_ID = "workerId";
    /**
     * 数据ID 0~31
     */
    public static final String DATACENTER_ID = "datacenterId";
    /**
     * cms端口
     */
    public static final String CMS_PORT = "8098";


}
