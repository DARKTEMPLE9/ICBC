package com.selfmybatis.binding;



/**
 * 把我们解析的sql（UserMapper）加载到类中
 */
public class MapperMethod {

    private String sql;

    private Class type;

    /*生成get` set 方法 及构造函数*/
    public MapperMethod() {

    }

    public MapperMethod(String sql, Class type) {
        this.sql = sql;
        this.type = type;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }
}
