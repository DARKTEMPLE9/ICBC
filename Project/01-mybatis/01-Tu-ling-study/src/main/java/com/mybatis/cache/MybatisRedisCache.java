package com.mybatis.cache;

import org.apache.ibatis.cache.Cache;

import java.util.concurrent.locks.ReadWriteLock;

/**
 * mybatis 二级缓存 使用redis 实现cache
 * 需要做序列化处理
 * 不建议使用
 */
public class MybatisRedisCache implements Cache {


    /*此处注入redis
    private Jedis redisClient = createRedis();
    */

    @Override
    public String getId() {
        return null;
    }

    @Override
    public void putObject(Object key, Object value) {
        //redisClient.set(SerializeUtil.serialize(key.toString(),SerializeUtil.serialize(value)))
    }

    @Override
    public Object getObject(Object key) {
        //获取时进行反序列化
        return null;
    }

    @Override
    public Object removeObject(Object key) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return null;
    }
}
