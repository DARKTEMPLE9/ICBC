package com.selfmybatis.session;

import com.selfmybatis.executor.SimpleExecutor;

/**
 * 在mybatis 中为SqlSessionFactory 接口
 * 底层实现为DefaultSqlSessionFactory
 */
public class SqlSessionFactory {

    public SqlSession openSession(Configuration configuration) {
        return new DefaultSqlSession(configuration,new SimpleExecutor(configuration));
    }
}
