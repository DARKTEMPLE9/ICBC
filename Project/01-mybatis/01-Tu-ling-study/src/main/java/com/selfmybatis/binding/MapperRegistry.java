package com.selfmybatis.binding;



import java.util.HashMap;
import java.util.Map;

/*
* 配置相关的注册中心
* */
public class MapperRegistry {

    private Map<String, MapperMethod> knownMappers = new HashMap<String, MapperMethod>();

    public void setKnownMappers(Map<String, MapperMethod> knownMappers) {
        this.knownMappers = knownMappers;
    }

    public Map<String, MapperMethod> getKnownMappers() {
        return knownMappers;
    }
}
