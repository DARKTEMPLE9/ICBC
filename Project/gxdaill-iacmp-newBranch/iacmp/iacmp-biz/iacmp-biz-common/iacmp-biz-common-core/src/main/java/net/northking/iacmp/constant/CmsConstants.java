package net.northking.iacmp.constant;

/**
 * @Author：Yanqingyu
 * @ClassName:CmsConstants
 * @Description：内容管理平台常量
 * @Date：Create in 3:11 PM2019/8/27
 * @Modified by:
 * @Version:1.0
 */
public class CmsConstants {
    /**
     * 影像类型状态可用
     */
    public static final String BILL_STATUS_USE = "0";
    /**
     * 影像类型状态禁用
     */
    public static final String BILL_STATUS_FORBIDDEN = "1";
    /**
     * 影像类型为子节点
     */
    public static final String BILL_IS_LEAF = "0";
    /**
     * 影像类型为父节点
     */
    public static final String BILL_IS_PARENT = "1";
    /**
     * 删除返回状态
     */
    public static final int DELETE_STATUS_ERROR = -1;
    /**
     * 删除返回状态(已结项，已废弃不能删除)
     */
    public static final int DELETE_STATUS_FAIL = -2;
    /**
     * 角色分管行领导
     */
    public static final String ROLE_KEY_BRANCH = "branch";
    /**
     * 角色部门负责人
     */
    public static final String ROLE_KEY_DEPTHEAD = "dept-head";
    /**
     * 角色项目经理
     */
    public static final String ROLE_KEY_PM = "pm";
    /**
     * 角色评审人员
     */
    public static final String ROLE_KEY_REVIEW = "review";
    /**
     * OA系统编码
     */
    public static final String SYS_OA_CODE = "OA";
    /**
     * json报文中fileList
     */
    public static final String FILE_LIST_KEY = "fileList";
    /**
     * json报文中路径的分割符
     */
    public static final String JSON_PATH_SEPARATOR = ":";
    /**
     * 应用服务器存放文件的地址
     */
    public static final String SERVER_PATH = "serverPath";//"D:/software/alidata/data/hadoop/";

    /**
     * 应用服务器存放PDF文件的地址
     */
    public static final String SERVER_PDF_PATH = "serverPdfPath";//"D:/software/alidata/data/hadoop/pdf/";

    /**
     * 大数据hdfs存储地址
     */
    public static final String HDFS_PATH = "hdfsPath";

    /**
     * 文件上传10M分界点
     */
    public static final Long TEN_MB = 10 * 1024 * 1024L;
    /**
     * 系统区域
     */
    public static final String SYS_ZONE = "sysZone";

    /**
     * 上传大数据使用Hbase
     */
    public static final String USEHBASE = "0";

    /**
     * 上传大数据使用Hdfs
     */
    public static final String USERHDFS = "1";

    /**
     * 归档标识分隔符
     */
    public static final String TO_ARC_FLAG_SEPARATOR = "@";

    /**
     * 归档标识key和value分隔符
     */
    public static final String KEY_VALUE_SEPARATOR = "=";

    /**
     * 归档值key
     */
    public static final String TO_ARC_KEY = "toArc";

    /**
     * 归档值 是：1
     */
    public static final String TO_ARC_VALUE_YES = "1";

    /**
     * 绑定文件信息时合同类档案类型（信息技术档案）
     */
    public static final String CONSTRACT_ARC_BILL_CODE = "16100";

    /**
     * 绑定文件信息时合同类第二档案类型（合同验收资料档案）
     */
    public static final String CONSTRACT_ARC_BILL_DEPT_CODE = "16103";

    /**
     * filetrans 常量
     */
    /**
     * httpfiletrans服务地址
     */
    public static final String FILETRANSIP = "httpfiletransIp";
    /**
     * 本地filetrans服务地址
     */
    public static final String LOCALTRANSIP = "localHttpfiletransIp";
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
     * 服务器本地IP
     */
    public static final String SERVER_IP = "serverIp";

    /**
     * 服务器本地端口
     */
    public static final String SERVER_PORT = "serverPort";


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

    public static final String SYSCODE = "sysCode";
    public static final String AUTHCODE = "authCode";
    public static final String TRANCODE = "tranCode";
    public static final String BILLTYPE = "billType";
    public static final String FILES = "files";
    public static final String FILELIST = "fileList";
    public static final String BASE64 = "base64";

    public static final String OPERATIONCODE = "operationCode";

    public static final String PROJECTBATCH = "projectBatch";

    public static final String BUILDDEPT = "buildDept";

    public static final String ATTRIDEPT = "attriDept";

    public static final String BUDGETID = "budgetId";

    public static final String PROJECTMANAGER = "projectManager";

    public static final String MODELS = "models";

    public static final String BILLCODE = "billCode";

    public static final String TRG = "trg";

    public static final String UPDATETIMESTART = "updateTimeStart";

    public static final String UPDATETIMEEND = "updateTimeEnd";

    public static final String DESCRIPTION = "description";

    public static final String CREATETIME = "createTime";

    public static final String UPDATETIME = "updateTime";

    public static final String PRODUCTMANAGER = "productManager";

    public static final String PROJECTNAME = "projectName";

    public static final String OPERATOR = "operator";

    public static final String DISPLAY = "display";

    public static final String BILLPARENTCODE = "billParentCode";

    public static final String LEAF = "leaf";

    public static final String COMPLETE = "complete";

    public static final String REGBILLGLIDENO = "regbillglideNo";

    public static final String CREATEUSER = "createUser";

    public static final String DEPTNAME = "deptName";

    public static final String ORDERNUM = "orderNum";

    public static final String FILESYSCODE = "fileSysCode";

    public static final String MODELID = "modelId";

    public static final String FILENAME = "fileName";

    public static final String MD5 = "Md5";

    public static final String REMARK = "remark";

    public static final String FILESOURCE = "fileSource";

    public static final String VERSION = "1.0";

    public static final String TRGSET = "trgSet";

    public static final String TEMPLATEID = "templateId";

    public static final String OCRRESULT = "ocrResult";

    public static final String OCRSTATUS = "ocrStatus";

    public static final String IMGSOURCE = "imgSource";

    public static final String FILEPATH = "filePath";

    public static final String TOTALRESULTCODE = "totalResultCode";

    public static final String TOTALRESULTMSG = "totalResultMsg";

    public static final String PROJECT_STATUS = "status";

    public static final String SYS_LEVEL = "sysLevel";

    public static final String SYS_TYPE = "sysType";

    public static final String HADOOP_TYPE = "hadoopType";
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


    /**
     * AES密钥
     */
    public static final String PUBLIC_KEY = "7Rj4PUc/58SZvC7ZP8AV/Q==";//"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCODtW+O+j7s1UQDNf3hy2vyrRnvdGrrsvuOTfsAvszAcKKuP45QSmqiGMxrtx4hatVKySXGD2kX1pHujJgZRuIamNtkwpx/ihIa29ZvDnbBx9KH2A5+YUZiE22Vf3Vr8pK8S3bzHzg2i7DkcgZiuZyV0MhCEggIJ2VjDYPj/VHyQIDAQAB";
    /**
     * RSA私钥
     */
    public static final String PRIVATE_KEY = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAI4O1b476PuzVRAM1/eHLa/KtGe90auuy+45N+wC+zMBwoq4/jlBKaqIYzGu3HiFq1UrJJcYPaRfWke6MmBlG4hqY22TCnH+KEhrb1m8OdsHH0ofYDn5hRmITbZV/dWvykrxLdvMfODaLsORyBmK5nJXQyEISCAgnZWMNg+P9UfJAgMBAAECgYBAjaM5iBXuUubDnWKCe+4Z5R4nyk0+G/dVsLCl0hD+c71ItpnnwKHDeajByfkr12otEKbW9lAEosPutkDwJuHkY2Y94cDxijm4GYXh07eCo0wBMZ2OTGNGTC05Rx2tUVGH4jrTlKCNvZPbqUgEposRh9OcdxTEbs8heVBXjvFjAQJBAN0K0hu0WqMSnFotQByQur9UTuPcLwW8SqAneksXXOhwG5mMjxh94OaggSELfECldjoFuzkiph98vAQ86XVBumkCQQCkhjuerpx299dHIUksDIuc4Ui0I2FruHyqCcTdEPv7Fbci/617hvplrkckKDHrjZ5HvwqU4VBVkBSkgVZ0b7ZhAkEAtAhWLN5uXBeSZRgp+ZofoCoIvync2q0LkvnUb0iuEVsN/7gt7GLKRM1Bnyp9nyGk84krflOsPkUX1dPOygQo0QJBAJj2IAoZJ2lIZcNBBCnLBCZ4IcMxPcsL/AOPD5PEzbGUb+8/LQiy5qyQip3oLPlmcmkcCfd/P9Q9H6QOh9W7YyECQQChEi6ivdGBj/pf+6wot+hw33MolXKdJ06QTzgohdrs82N7saNT6vIsfIG6a9hQu9oHh5x+k/kODRy8TQqjYX9N";


}
