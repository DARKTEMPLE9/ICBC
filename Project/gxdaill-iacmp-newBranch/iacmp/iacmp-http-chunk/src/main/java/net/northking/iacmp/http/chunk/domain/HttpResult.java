package net.northking.iacmp.http.chunk.domain;

import net.northking.iacmp.http.chunk.util.HttpStringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.HashMap;


/**
 * 操作消息提醒
 *
 * @author wxy
 */
public class HttpResult extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public static final String CODE_TAG = "code";

    public static final String MSG_TAG = "msg";

    public static final String DATA_TAG = "data";

    /**
     * 状态类型
     */
    public enum Type {
        /**
         * 成功
         */
        SUCCESS(0),
        /**
         * 警告
         */
        WARN(301),
        /**
         * 错误
         */
        ERROR(500);
        private final int value;

        Type(int value) {
            this.value = value;
        }

        public int value() {
            return this.value;
        }
    }

    /**
     * 状态类型
     */
    private Type type;

    /**
     * 状态码
     */
    private int code;

    /**
     * 返回内容
     */
    private String msg;

    /**
     * 数据对象
     */
    private transient Object data;

    /**
     * 初始化一个新创建的 AjaxResult 对象
     *
     * @param type 状态类型
     * @param msg  返回内容
     */
    public HttpResult(Type type, String msg) {
        super.put(CODE_TAG, type.value);
        this.type = type;
        this.code = type.value;
        super.put(MSG_TAG, msg);
        this.msg = msg;
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     *
     * @param type 状态类型
     * @param msg  返回内容
     * @param data 数据对象
     */
    public HttpResult(Type type, String msg, Object data) {
        super.put(CODE_TAG, type.value);
        this.type = type;
        this.code = type.value;
        super.put(MSG_TAG, msg);
        this.msg = msg;
        if (HttpStringUtils.isNotNull(data)) {
            super.put(DATA_TAG, data);
            this.data = data;
        }
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static HttpResult success() {
        return HttpResult.success("操作成功");
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static HttpResult success(Object data) {
        return HttpResult.success("操作成功", data);
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @return 成功消息
     */
    public static HttpResult success(String msg) {
        return HttpResult.success(msg, null);
    }

    /**
     * 返回成功消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static HttpResult success(String msg, Object data) {
        return new HttpResult(Type.SUCCESS, msg, data);
    }

    /**
     * 返回警告消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static HttpResult warn(String msg) {
        return HttpResult.warn(msg, null);
    }

    /**
     * 返回警告消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static HttpResult warn(String msg, Object data) {
        return new HttpResult(Type.WARN, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @return
     */
    public static HttpResult error() {
        return HttpResult.error("操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static HttpResult error(String msg) {
        return HttpResult.error(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static HttpResult error(String msg, Object data) {
        return new HttpResult(Type.ERROR, msg, data);
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("code", getCode())
                .append("msg", getMsg())
                .append("data", getData())
                .toString();
    }
}