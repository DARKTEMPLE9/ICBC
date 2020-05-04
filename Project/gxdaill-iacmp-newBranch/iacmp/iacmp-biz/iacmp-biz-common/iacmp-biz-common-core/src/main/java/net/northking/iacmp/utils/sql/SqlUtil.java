package net.northking.iacmp.utils.sql;

import net.northking.iacmp.utils.StringUtils;

/**
 * sql操作工具类
 *
 * @author wxy
 */
public class SqlUtil {
    private SqlUtil() {
        throw new IllegalStateException("SqlUtil class");
    }

    /**
     * 仅支持字母、数字、下划线、空格、逗号（支持多个字段排序）
     */
    public static final String SQL_PATTERN = "[a-zA-Z0-9_\\ \\,]+";

    /**
     * 检查字符，防止注入绕过
     */
    public static String escapeOrderBySql(String value) {
        if (StringUtils.isNotEmpty(value) && !isValidOrderBySql(value)) {
            return StringUtils.EMPTY;
        }
        return value;
    }

    /**
     * 验证 order by 语法是否符合规范
     */
    public static boolean isValidOrderBySql(String value) {
        return value.matches(SQL_PATTERN);
    }
}