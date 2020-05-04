package com.selfmybatis.binding;

import com.selfmybatis.session.DefaultSqlSession;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MapperProxy<T> implements InvocationHandler, Serializable {

    private final DefaultSqlSession defaultSqlSession;

    private final Class<T> mapperInterface;

    public MapperProxy(DefaultSqlSession defaultSqlSession, Class<T> mapperInterface) {
        this.defaultSqlSession = defaultSqlSession;
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MapperMethod mapperMethod = defaultSqlSession.getConfiguration().getMapperRegistry().getKnownMappers().get(method.
                getDeclaringClass().getName() + "." + method.getName());
        if (mapperMethod != null) {
            return defaultSqlSession.selectOne(mapperMethod, String.valueOf(args[0]));
        }
        return method.invoke(proxy, args);
    }
}
