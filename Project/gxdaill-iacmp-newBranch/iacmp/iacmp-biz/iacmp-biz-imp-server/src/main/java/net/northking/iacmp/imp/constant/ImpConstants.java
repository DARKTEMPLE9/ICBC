package net.northking.iacmp.imp.constant;

/**
 * @Author: wei.chen
 * @Date Created: in 2019/10/19 13:22
 */
public class ImpConstants {

    /**
     * 数据迁移阶段开关
     */
    public static final String DATA_MIG_STAGE = "dataMigrationStage";
    /**
     * 数据迁移阶段--老库为主
     */
    public static final String DATA_MIG_STAGE_OLD_FIRST = "1";
    /**
     * 数据迁移阶段--新库为主
     */
    public static final String DATA_MIG_STAGE_NEW_FIRST = "2";
    /**
     * 数据迁移阶段--只有新库
     */
    public static final String DATA_MIG_STAGE_NEW = "3";

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
     * 影像列表
     */
    public static final String IMGLIST = "imgList";
    /**
     * 文件列表
     */
    public static final String FILELIST = "fileList";
    /**
     * 文件路径
     */
    public static final String FILEPATH = "filePath";
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
    //客户号
    public static final String USERCODE = "userCode";
    //客户姓名
    public static final String USERNAME = "userName";
    //客户证件号码
    public static final String USERIDCARD = "idCard";
    //证件类型
    public static final String CARDTYPE = "cardType";
    //原业务流水号 TRANSACTIONNO SysBusiNo
    public static final String TRANSACTIONNO = "transactionNo";
    public static final String SYSBUSINO = "SysBusiNo";
    public static final String IDTYPE = "idType";
    // 客户ID
    public static final String USERID = "userId";
    // userCodeId
    public static final String USERCODEID = "userCodeId";
    // transfilepath
    public static final String TRANSFILEPATH = "transfilepath";
    // imageId
    public static final String IMAGEID = "imageId";
    // id
    public static final String ID = "id";
    // createUserName
    public static final String CREATEUSERNAME = "createUserName";
    // deleteUser
    public static final String DELETEUSER = "deleteUser";
    // deleteUserName
    public static final String DELETEUSERNAME = "deleteUserName";
    // serialno
    public static final String SERIALNO = "serialno";
    // deleteTime
    public static final String DELETETIME = "deleteTime";
    // workId
    public static final String WORKID = "workId";
    // batchId
    public static final String BATCHID = "batchId";

    // 影像状态正常
    public static final String STATUS_NORMAL = "0";

    // 影像状态已废弃
    public static final String STATUS_ABANDONED = "1";
}
