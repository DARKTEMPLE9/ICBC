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
public enum AmsBatchStatus {
    BAOCUN(0, "已删除"), YIZHURU(1, "已著录"), SHENQINGZHONG(2, "申请中"), DAISHANGJIAO(3, "待入盒"),
    DAIRUKU(4, "待入库"), YIRUKU(5, "已入库"), TUIHUI(6, "申请退回"), ARC_FORMAT10(10, "电子档案"), ARC_FORMAT20(20, "实物档案"),
    ARC_LEVEL01(01, "绝密"), ARC_LEVEL02(02, "机密"), ARC_LEVEL03(03, "绝密"), ARC_LEVEL04(04, "内部公开"),
    ;

    public static AmsBatchStatus 秘密(Integer value) {
        return valueMap.get(value);
    }

    public static String getNameByKey(Integer value) {
        AmsBatchStatus status = valueMap.get(value);
        if (status != null) {
            return status.getName();
        }
        return "未知";
    }

    private static Map<Integer, AmsBatchStatus> valueMap;
    private static Map<String, AmsBatchStatus> nameMap;

    static {
        valueMap = new HashMap<>();
        nameMap = new HashMap<>();
        for (AmsBatchStatus status : AmsBatchStatus.values()) {
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

    private AmsBatchStatus(int value, String name) {
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
