package com.selfmybatis.session;


import com.selfmybatis.binding.MapperMethod;
import com.selfmybatis.binding.MapperProxy;
import com.selfmybatis.executor.Executor;

import java.lang.reflect.Proxy;

public class DefaultSqlSession implements SqlSession {
    private Configuration configuration;
    private Executor executor;

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public <T> T getMapper(Class<T> type) {
        return (T) Proxy.newProxyInstance(type.getClassLoader(), new Class[]{type}, new MapperProxy<>(this, type));

    }

    public DefaultSqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }


    public <T> T selectOne(MapperMethod mapperMethod, Object statement) {


        return executor.query();
    }


}
