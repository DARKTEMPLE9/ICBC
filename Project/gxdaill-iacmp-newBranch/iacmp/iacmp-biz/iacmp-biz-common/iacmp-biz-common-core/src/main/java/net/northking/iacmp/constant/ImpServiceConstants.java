package net.northking.iacmp.constant;

public class ImpServiceConstants {
    private ImpServiceConstants() {
    }

    /**
     * 交易码
     */
    public static final String TRANCODE = "tranCode";
    /**
     * 业务流水号
     */
    public static final String BUSINO = "busiNo";
    /**
     * 系统编号
     */
    public static final String SYSID = "sysId";
    /**
     * 操作员编号
     */
    public static final String OPERID = "operId";
    /**
     * 操作员编号
     */
    public static final String OPERAID = "operaId";
    /**
     * 文件名称
     */
    public static final String FILENAME = "fileName";
    /**
     * 文件类型
     */
    public static final String FILETYPE = "fileType";
    /**
     * 影像类型
     */
    public static final String BILLTYPE = "billType";
    /**
     * 文件Base64码
     */
    public static final String BASE64 = "base64";
    /**
     * 是否识别标志
     */
    public static final String ISOCR = "isOcr";
    /**
     * 识别模板编号
     */
    public static final String TEMPLATENO = "templateNo";
    /**
     * 机构号
     */
    public static final String BRANCHNO = "branchNo";
    /**
     * 备注信息
     */
    public static final String REMARK = "remark";
    /**
     * 原文件编号
     */
    public static final String SOURCEFILEID = "sourceFileId";
    /**
     * 目标文件Base64码
     */
    public static final String TAEGETFILE = "targetFile";
    /**
     * 用户关联状态
     */
    public static final String STATUS = "status";
    /**
     * 权限
     */
    public static final String PERMISSION = "permission";
    /**
     * 机构编号
     */
    public static final String BRANCHID = "branchId";
    /**
     * 文件列表
     */
    public static final String IMGLIST = "imgList";
    /**
     * 下载本地路径(加一个下载接口，仅仅是测试)
     */
    public static final String SAVEPATH = "savePath";

    /**
     * 交易结果
     */
    public static final String TRADERESULT = "tradeResult";
    /**
     * 交易描述
     */
    public static final String TRADEDESC = "tradeDesc";
    /**
     * 识别结果
     */
    public static final String OCRRESULT = "ocrResult";
    /**
     * 识别信息
     */
    public static final String OCRINFO = "ocrInfo";
    /**
     * 文件编号
     */
    public static final String FILEID = "fileId";
    /**
     * 文件URL
     */
    public static final String FILEURL = "fileUrl";
    /**
     * 业务日期
     */
    public static final String BUSIDATE = "busiDate";
    /**
     * 文件信息列表
     */
    public static final String FILEINFO = "fileInfo";
    /**
     * 文件下载结果
     */
    public static final String DOWNRESULT = "downResult";
    /**
     * 文件Base64码
     */

    public static final String OCRFAIL = "0";
    /**
     * 著录编号
     */
    public static final String BATCHNO = "batchno";
    /**
     * 著录id
     */
    public static final String AMSBATCHID = "amsbatchid";
    /**
     * 批量上传文件集合信息
     */
    public static final String RET = "ret";

    public static final int PORT1001 = 1001;

    public static final int PORT1002 = 1002;

    public static final int PORT1003 = 1003;

    public static final int PORT2001 = 2001;

    public static final int PORT2002 = 2002;

    public static final int PORT2003 = 2003;

    public static final int PORT2004 = 2004;

    public static final int PORT2005 = 2005;

    public static final int PORT2006 = 2006;

    public static final int PORT2007 = 2007;
    //加一个下载接口，仅仅是测试
    public static final int PORT2008 = 2008;

    public static final int PORT3001 = 3001;

    public static final int PORT3002 = 3002;

    public static final String NOTEMP = "0000";
    public static final String UPLOADBUFFERPATH = "uploadbufferPath";
    public static final String FILETRANSPATHUP = "httpfiletransPathup";
    public static final String FILETRANSIP = "httpfiletransIp";
    public static final String FILETRANSRETURNIP = "httpfiletransReturnIp";
    public static final String FILETRANSPORT = "httpfiletransPort";
    public static final String LOCALFILEPATH = "localFilePath";
    public static final String FILETRANSPATH = "httpfiletransPath";
    public static final String FILETRANSSAVEPATH = "httpfiletransSavePath";
    public static final String UNKNOWUSER = "UnknowUser";
    public static final String UNKNOWBILL = "noClassify";
    public static final String MAXFILESIZE = "MaxBase64FileSize";
    public static final String HTTPHEAD = "http://";
    public static final String SWEEPPAGE = "sweepPage";
    public static final String LOOKPAGE = "lookPage";
    public static final String NOSYSTEM = "接入系统不存在";
    public static final String RETRYFORERROR = "请求数据出错，请重试";
    public static final String RETRYFORNOT = "该条记录不存在，请重试";
    public static final String FENBEIERROR = "分配类型失败，请重试";
    public static final String NOBILL = "档案类型不存在";
    public static final String FENUSERERROR = "分配用户失败，请重试";
    public static final String FILELIST = "fileList";
    public static final String CREATEUSER = "createUser";
    public static final int FINISHUPLOAD = 9;

    public static final int NUM1024 = 1024;
    public static final int K64 = 64 * 1024;
    public static final int M1 = 1024 * 1024;

    /**
     * 身份证正面
     */
    public static final String IDCARD = "17";
    /**
     * 身份证反面
     */
    public static final String IDCRADBACK = "20";
    /**
     * 银行卡
     */
    public static final String BANKCARD = "21";

    /**
     * 识别默认轮训时间
     */
    public static final int DEAFULTTIME = 1000;
    /**
     * 身份证银行卡轮训时间
     */
    public static final int CARDTIME = 500;
    /**
     * 识别状态一直是3时的限制次数
     */
    public static final int DEFAULTOCRFAIL = 5;
    /**
     * ocr获取识别任务状态
     */
    public static final int OCRPAUSE = 3;
    /**
     * ocr识别完成状态
     */
    public static final int OCRFINISH = 10;

    public static final String WORKITEMID = "workItemId";

    public static final String LOCALHOST = "localhost";

    // transfilepath
    public static final String TRANSFILEPATH = "transfilepath";

    // tempFileName
    public static final String TEMPFILENAME = "tempFileName";

}
