package com.selfmybatis.session;


import java.io.IOException;

public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build (Configuration configuration) throws IOException {
        configuration.loadConfigurations();
        return new SqlSessionFactory();
    }

}
