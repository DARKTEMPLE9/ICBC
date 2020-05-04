package net.northking.iacmp.constant;

/**
 * 通用常量信息
 *
 * @author wxy
 */
public class Constants {

    /**
     * 通用成功标识
     */
    public static final String SUCCESS = "0";

    /**
     * 通用失败标识
     */
    public static final String FAIL = "1";

    /**
     * 登录成功
     */
    public static final String LOGIN_SUCCESS = "Success";

    /**
     * 注销
     */
    public static final String LOGOUT = "Logout";

    /**
     * 登录失败
     */
    public static final String LOGIN_FAIL = "Error";

    /**
     * 自动去除表前缀
     */
    public static final String AUTO_REOMVE_PRE = "true";

    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "pageNum";

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize";

    /**
     * 排序列
     */
    public static final String ORDER_BY_COLUMN = "orderByColumn";

    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    public static final String IS_ASC = "isAsc";

    /**
     * 著录编号
     */
    public static final String ID_RECORD = "zlcode";
    /**
     * 档案盒编号
     */
    public static final String ID_ARCH_BOX = "dahcode";
    /**
     * 上交审批编号
     */
    public static final String ID_SUBMIT_APPROVE = "sjcode";
    /**
     * 电子借阅审批编号
     */
    public static final String ID_ELE_APPROVE = "dzjycode";
    /**
     * 实物借阅审批编号
     */
    public static final String ID_THING_APPROVE = "swjycode";
    /**
     * 上交审批代码
     */
    public static final String CODE_SUBMIT_APPROVE = "H";
    /**
     * 电子借阅审批代码
     */
    public static final String CODE_ELE_APPROVE = "E";
    /**
     * 实物借阅审批代码
     */
    public static final String CODE_THING_APPROVE = "P";
    /**
     * 年月日
     */
    public static final String DATE_TYPE_EIGHT = "yyyyMMdd";
    /**
     * 年
     */
    public static final String DATE_TYPE_FOUR = "yyyy";
    /**
     * 已删除
     */
    public static final String ALREADY_DELETE = "0";
    /**
     * 已著录
     */
    public static final String ALREADR_RECORD = "1";
    /**
     * 申请中
     */
    public static final String APPLYING = "2";
    /**
     * 待入盒
     */
    public static final String WAIT_SUBMIT = "3";
    /**
     * 待入库
     */
    public static final String WAIT_PUT_STORAGE = "4";
    /**
     * 已入库
     */
    public static final String ALREADY_PUT_STORAGE = "5";
    /**
     * 已退回
     */
    public static final String SEND_BACK = "6";
    /**
     * 已出库
     */
    public static final String ALREADY_EXIT_STROAGE = "7";
    /**
     * 已废弃
     */
    public static final String ALREADY_DISCARD = "8";
    /**
     * 待废弃
     */
    public static final String WAIT_DISCARD = "9";
    /**
     * 线
     */
    public static final String LINE = "-";
    /**
     * 可用盒
     */
    public static final String AVAILABLE_BOX = "00";
    /**
     * 可用未满盒
     */
    public static final String NOTMAX_BOX = "10";
    /**
     * 停用盒
     */
    public static final String BLOCK_UP_BOX = "20";
    /**
     * 审批通过
     */
    public static final String APPROVE_PASS = "2";
    /**
     * 审批拒绝
     */
    public static final String APPROVE_REFUSE = "3";
    /**
     * 待入盒
     */
    public static final String WAIT_PUT_BOX = "3";
    /**
     * 档案类型正常状态
     */
    public static final String AMS_BILL_NORMAL = "1";
    /**
     * 档案类型废弃状态
     */
    public static final String AMS_BILL_DISCARD = "0";
    /**
     * 申请利用电子借阅类型
     */
    public static final String AMS_APPLY_ELE = "10";
    /**
     * 申请利用实物借阅类型
     */
    public static final String AMS_APPLY_ENTITY = "20";

    /**
     * 未满盒入库状态
     */
    public static final String NOT_FULL_INTOCAB = "5";

    /**
     * 已满盒入库状态
     */
    public static final String FULL_INTOCAB = "6";

    /**
     * 已满盒入库状态
     */
    public static final String MATTER_ARCBILL = "12100";

    /**
     * 影像状态--正常
     */
    public static final Integer NORMAL_IMAGE = 0;

    /**
     * 影像状态--已废弃
     */
    public static final Integer ABANDON_IMAGE = 1;

    /**
     * 正在上传
     */
    public static final Integer UPLOAD_ING = 0;
    /**
     * 上传完成
     */
    public static final Integer UPLOAD_OVER = 1;

    /**
     * 上传失败
     */
    public static final Integer UPLOAD_FAIL = 2;

    /*****************************交易码*********************************/

    /**
     * 档案上传交易码
     */
    public static final String AMS_UPLOAD = "ams_2001";

    /**
     * 内管上传交易码(批量文件)
     */
    public static final String CMS_FILES_UPLOAD = "cms_3001";

    /**
     * 内管上传交易码(BASE64S)
     */
    public static final String CMS_BASE64S_UPLOAD = "cms_4001";

    /**
     * 后台文件查询接口
     */
    public static final String CMS_FILES_QUERY = "cms_3002";

    /**
     * 项目信息同步
     */
    public static final String PRO_INFO_SYN = "cms_3005";

    /**
     * 文件信息绑定
     */
    public static final String FILE_INFO_BIND = "cms_3006";

    /**
     * 拉取项目分类
     */
    public static final String PRO_BILLS_QUERY = "cms_3007";

    /**
     * 获取项目整体信息
     */
    public static final String PRO_INFO_TOTAL = "cms_3008";

    /**
     * 获取下载授权Token
     */
    public static final String GET_TOKEN = "cms_8001";

    /**
     * 后台下载接口（安全认证）
     */
    public static final String DOWNLOAD = "cms_8002";

    /**
     * 后台预览接口（安全认证）
     */
    public static final String PREVIEW = "cms_8003";
    /*********************************************************************/
    /**
     * 使用适配器
     */
    public static final String USE_ADAPTER = "1";

    /**
     * 不使用适配器
     */
    public static final String UNUSE_ADAPTER = "0";

    /**
     * 叶子结点
     */
    public static final String IS_LEAF = "0";

    /**
     * 非叶子结点
     */
    public static final String NOT_LEAF = "1";

    /**
     * 根节点
     */
    public static final Integer ROOT = 0;

    /**
     * 特殊分类
     */
    public static final String SPECIALBILL = "3";

    /**
     * 系统参数
     */
    public static final String CMS_UPLOAD_SYSCODE = "AMS";

    /**
     * 系统参数key
     */
    public static final String CMS_UPLOAD_SYSCODE_KEY = "sysKey";

    /**
     * ams端口
     */
    public static final String AMS_PORT = "8088";

    /**
     * httpfiletrans端口
     */
    public static final String HTTP_FILETRANS_PORT = "8080";

    /**
     * 会计档案总数限制
     */
    public static final int ACCOUNTING_COUNT_LIMIT = 100;

    /**
     * 会计档案总数限制
     */
    public static final int PAGE_RES_CODE_WARN = 100;

    /*************************编码格式*****************************/

    public static final String UTF8 = "UTF-8";

    public static final String GBK = "GBK";

    public static final String GB2312 = "GB2312";

    public static final String ISO88591 = "ISO8859-1";

    /**
     * sso默认密码
     */
    public static final String SSO_PW = "sso令牌验证通过";

    /*****************************filetrans 常量*****************************/
    /**
     * Base64文件限制
     */
    public static final String MAX_BASE64_FILE_SIZE = "MaxBase64FileSize";
    /**
     * 返回报文IP
     */
    public static final String HTTP_FILETRANS_RETIP = "httpfiletransReturnIp";
    /**
     * httpfiletrans服务端口号
     */
    public static final String HTTP_FILETRANS_PORTS = "httpfiletransPort";
    /**
     * httpfiletrans下载连接串
     */
    public static final String HTTP_FILETRANS_PATH = "httpfiletransPath";
    /**
     * 图片缓存位置
     */
    public static final String UPLOAD_BUFFER_PATH = "uploadbufferPath";
    /**
     * httpfiletransPathup
     */
    public static final String HTTP_FILETRANS_PATHUP = "httpfiletransPathup";
    /**
     * windows文件本地存储地址
     */
    public static final String WIN_LOCAL_FILE_PATH = "windowsLocalFilePath";
    /**
     * HDFS存储地址
     */
    public static final String HDFS_PATH = "hdfsPath";
    /**
     * 全宗号 可配置参数
     */
    public static final String RECORD_GROUP_NUMBER = "recordGroupNumber";
}
