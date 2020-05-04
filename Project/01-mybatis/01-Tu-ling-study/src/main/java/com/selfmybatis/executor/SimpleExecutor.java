package com.selfmybatis.executor;


import com.selfmybatis.session.Configuration;

/*
 * 执行器
 * */
public class SimpleExecutor implements Executor {

    private Configuration configuration;

    public SimpleExecutor(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T query() {
        System.out.println("执行器执行成功");
        return null;
    }
}
