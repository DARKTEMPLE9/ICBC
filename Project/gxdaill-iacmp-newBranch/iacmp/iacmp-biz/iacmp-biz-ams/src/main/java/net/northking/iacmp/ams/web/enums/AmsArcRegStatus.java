package net.northking.iacmp.ams.web.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * SmOrganStatus的枚举 Creation Date: 2012-8-10
 * <p>
 * Title: 北京京北方信息技术有限公司代码生成系统
 * </p>
 * <p>
 * Description: 描述机构表批次状态
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company: 北京京北方信息技术有限公司
 * </p>
 *
 * @author 赵龙虎
 * @version 1.0
 * @see
 */
public enum AmsArcRegStatus {
    BAOCUN(10, "已保存"), TIJIAO(20, "已提交"), JIESHOU(30, "已接收"), TUIHUI(40, "已退回"), RECORD(50, "已著录"), DELETE(60, "已删除");

    public static AmsArcRegStatus toTAmsArcRegStatus(Integer value) {
        return valueMap.get(value);
    }

    public static String getNameByKey(Integer value) {
        AmsArcRegStatus status = valueMap.get(value);
        if (status != null) {
            return status.getName();
        }
        return "未知";
    }

    private static Map<Integer, AmsArcRegStatus> valueMap;
    private static Map<String, AmsArcRegStatus> nameMap;

    static {
        valueMap = new HashMap<>();
        nameMap = new HashMap<>();
        for (AmsArcRegStatus status : AmsArcRegStatus.values()) {
            valueMap.put(status.getValue(), status);
            nameMap.put(status.getName(), status);
        }
    }

    private Integer value;
    private String name;

    public boolean isEquals(Integer value) {
        return this.getValue() == value;
    }

    public boolean equalss(Integer value) {
        return this.getValue() == value;
    }

    public boolean equalss(String value) {
        return value != null && this.getValue() == Integer.parseInt(value);
    }

    private AmsArcRegStatus(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.getValue() + "-" + this.getName();
    }

}
