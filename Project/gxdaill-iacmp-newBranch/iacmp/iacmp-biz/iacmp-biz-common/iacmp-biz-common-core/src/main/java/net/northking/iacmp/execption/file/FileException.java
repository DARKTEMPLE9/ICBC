package net.northking.iacmp.execption.file;

import net.northking.iacmp.execption.base.BaseException;

/**
 * 文件信息异常类
 *
 * @author wxy
 */
public class FileException extends BaseException {
    private static final long serialVersionUID = 1L;

    public FileException(String code, Object[] args) {
        super("file", code, args, null);
    }

}
