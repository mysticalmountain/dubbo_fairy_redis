package com.fairy.redis.dao;

import com.fairy.redis.aspect.InvokeLog;
import com.fairy.redis.jedis.JedisFactory;
import com.fairy.redis.util.ObjectSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

/**
 * Created by andongxu on 16-4-29.
 */
@Component
public class    RedisDao {

    @Autowired
    private JedisFactory jedisFactory;
    @Autowired
    private ObjectSerialize objectSerialize;

    @InvokeLog
    public String save(final int databaseId, final String key, final Serializable value) {
        return jedisFactory.withJedisDo(new JedisFactory.JWork<String>() {
            public String work(Jedis p) {
                p.select(databaseId);
                byte[] bv = objectSerialize.serialize(value);
                return p.set(key.getBytes(), bv);
            }
        });
    }

    @InvokeLog
    public String save(final int databaseId, final String key, final Serializable value, final int expire) {
        return jedisFactory.withJedisDo(new JedisFactory.JWork<String>() {
            public String work(Jedis p) {
                p.select(databaseId);
                byte[] bv = objectSerialize.serialize(value);
                return p.setex(key.getBytes(), expire, bv);
            }
        });
    }

    @InvokeLog
    public String save(final int databaseId, final String key, final Collection<? extends Serializable> value) {
        return jedisFactory.withJedisDo(new JedisFactory.JWork<String>() {
            public String work(Jedis p) {
                p.select(databaseId);
                byte[] bv = objectSerialize.serialize(value);
                return p.set(key.getBytes(), bv);
            }
        });
    }

    @InvokeLog
    public String save(final int databaseId, final String key, final Collection<? extends Serializable> value, final int expire) {
        return jedisFactory.withJedisDo(new JedisFactory.JWork<String>() {
            public String work(Jedis p) {
                p.select(databaseId);
                byte[] bv = objectSerialize.serialize(value);
                return p.setex(key.getBytes(), expire, bv);
            }
        });
    }

    @InvokeLog
    public Object query(final int databaseId, final String key, final Class<? extends Serializable> clazz) {
        return jedisFactory.withJedisDo(new JedisFactory.JWork<Object>() {
            public Object work(Jedis p) {
                p.select(databaseId);
                byte[] bv = p.get(key.getBytes());
                if (bv == null) return null;
                return objectSerialize.deserialize(bv, clazz);
            }
        });
    }

    @InvokeLog
    public Long delete(final int databaseId, final String key) {
        return jedisFactory.withJedisDo(new JedisFactory.JWork<Long>() {
            public Long work(Jedis p) {
                p.select(databaseId);
                return p.del(key.getBytes());
            }
        });
    }

    @InvokeLog
    public Boolean exists(final int databaseId, final String key) {
        return jedisFactory.withJedisDo(new JedisFactory.JWork<Boolean>() {
            public Boolean work(Jedis p) {
                p.select(databaseId);
                return p.exists(key.getBytes());
            }
        });
    }

    @InvokeLog
    public String rename(final int databaseId, final String oldKey, final String newKey) {
        return jedisFactory.withJedisDo(new JedisFactory.JWork<String>() {
            public String work(Jedis p) {
                p.select(databaseId);
                return p.rename(oldKey.getBytes(), newKey.getBytes());
            }
        });
    }

    @InvokeLog
    public Long expire(final int databaseId, final String key, final int second) {
        return jedisFactory.withJedisDo(new JedisFactory.JWork<Long>() {
            public Long work(Jedis p) {
                p.select(databaseId);
                return p.expire(key.getBytes(), second);
            }
        });
    }

    @InvokeLog
    public Set<String> keys(final int databaseId, final String keyRegex) {
        return jedisFactory.withJedisDo(new JedisFactory.JWork<Set<String>>() {
            public Set<String> work(Jedis p) {
                p.select(databaseId);
                return p.keys(keyRegex);
            }
        });
    }
}
