package com.fairy.redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by andongxu on 16-5-4.
 */
public class ClusterTest {

    @Test
    public void test() {
        Set<String> sentinels = new HashSet<String>();
        sentinels.add("127.0.0.1:26379");
        sentinels.add("127.0.0.1:26380");
        JedisSentinelPool pool = new JedisSentinelPool("mymaster", sentinels);
        Jedis jedis = pool.getResource();
        jedis.set("jedis1", "jedis1");
        pool.returnResource(jedis);
        jedis = pool.getResource();
        jedis.exists("k123");
    }
}
