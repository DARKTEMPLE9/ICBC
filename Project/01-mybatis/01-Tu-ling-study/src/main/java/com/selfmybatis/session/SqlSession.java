package com.selfmybatis.session;

import com.selfmybatis.binding.MapperMethod;

public interface SqlSession {

    <T> T selectOne(MapperMethod mapperMethod, Object statement);

}
