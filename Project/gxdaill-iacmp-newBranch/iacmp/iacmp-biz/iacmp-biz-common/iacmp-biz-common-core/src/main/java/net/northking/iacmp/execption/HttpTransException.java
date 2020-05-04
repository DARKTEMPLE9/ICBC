package net.northking.iacmp.execption;

/**
 * 文件传输异常类
 *
 * @Author: wei.chen
 * @Date Created: 2019-08-26
 * @Version: 1.0
 */
public class HttpTransException extends Exception {

    private static final long serialVersionUID = 6029805614974735212L;

    public HttpTransException(String str) {
        super(str);
    }
}
