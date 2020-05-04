package net.northking.iacmp.result;

/**
 * 方法返回码
 *
 * @Description:返回码
 * @Author: weizhe.fan
 * @CreateDate: 2019/9/11
 */
public enum ResultCode {
    /************************文件上传相关返回码**********************************/
    UPLOAD_SUCCESS("0", "上传成功"),
    SUCCESS("0", "成功"),
    SYSTEM_NULL("TDCCMS00002", "此系统尚未接入,请先接入系统再进行上传操作"),
    UPLOAD_NULL("BCECMS00003", "上传文件为空"),
    UPLOAD_FAIL("TIOCMS00004", "上传失败"),
    DOWNLOAD_FAIL("TIOCMS00005", "下载失败"),
    DOWNLOAD_SUCCESS("0", "下载成功"),
    FILE_MISSING("BCECMS00007", "文件丢失损坏，请重新上传"),
    FILE_NOT_EXIT("TDCCMS00008", "文件不存在"),
    FILE_EXIT("0", "文件存在"),
    PROJECT_MISSING("BCECMS00010", "项目编号为空，请重新上传"),
    FILE_NAME_MISSING("BCECMS00011", "文件名为空，请检查报文字段是否完整"),
    BILL_CODE_MISSING("BCECMS00012", "分类为空，请检查报文字段是否完整"),
    FILES_MISSING("0", "文件列表为空"),
    MODELS_MISSING("TDCCMS00014", "模型为空,请检查数据库数据完整性"),
    PROJECT_NULL("TDCCMS00015", "项目不存在"),
    BILL_CODE_NULL("TDCCMS00016", "分类编码不存在"),
    TRAN_CODE_STOP("0", "该交易码已停用"),
    MSG_NULL("BCECMS00017", "报文为空");

    private String code;
    private String msg;

    ResultCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String code() {
        return code;
    }

    public String msg() {
        return msg;
    }

}
