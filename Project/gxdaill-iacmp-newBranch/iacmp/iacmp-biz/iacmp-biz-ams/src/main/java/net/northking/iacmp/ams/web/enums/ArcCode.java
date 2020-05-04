package net.northking.iacmp.ams.web.enums;

/**
 * @author renjizhang
 */
public enum ArcCode {

    ARCH_ARCHIVIST("A01", "档案登记员"),
    ARCH_ASSISTANT("A02", "档案协管员"),
    ARCH_MANAGER("A03", "档案管理员"),
    ARCH_INTERVIEWER("A04", "档案查询员"),
    ARCH_DEPT_HEAD("A05", "部门领导"),
    ARCH_SPECIFIC_LEADER("A06", "档案分管领导"),
    ARCH_SYSTEM_MANAGER("A99", "系统管理员"),

    ARCH_HAND_IN_PROCESS("950171a75fb3d9b9015fb3e10eab000f", "档案上交流程"),
    ARCH_BORROW_PROCESS("950171a75fb3d9b9015fb3e16f380012", "档案借阅流程"),

    ARCH_OBJECT_HAND_IN("10", "实物档案上交"),
    ARCH_ELE_CONSULT("20", "电子档案调阅"),
    ARCH_OBJECT_CONSULT("30", "实物档案调阅"),


    ARCH_RECORDING("1", "已著录"),
    ARCH_APPLYING("2", "申请中"),
    ARCH_WAIT_PUT_BOX("3", "待入盒"),
    ARCH_WAIT_PUT_STORAGE("4", "待入库"),
    ARCH_ALREADY_PUT_STORAGE("5", "已入库"),
    ARCH_SEND_BACK("6", "已退回"),
    ARCH_ALREADY_EXIT_STORAGE("7", "已出库"),
    ARCH_ALREADY_DISCARD("8", "已废弃"),
    ARCH_WAIT_DISCARD("9", "待废弃"),

    UNTOUCHED("10", "未提交"),
    PROCESSING("20", "审批中"),
    PROCESSED("30", "已审批"),

    NOTSEARCH("0", "N"),
    FORSEARCH("1", "+1"),

    RECORD_CODE("zlcode", "著录编号"),
    HAND_IN_PROCESS_CODE("sjcode", "上交审批编号"),
    ELE_BORROW_PROCESS_CODE("dzjycode", "电子借阅审批编号"),
    OBJECT_BORROW_PROCESS_CODE("swjycode", "实物借阅审批编号"),

    START("runner", "runner"),
    END("end", "end");

    private String code;
    private String name;

    private ArcCode(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }


}
