package com.fairy.redis.jedis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.clients.jedis.*;
import redis.clients.util.Pool;

import java.util.HashSet;
import java.util.Set;

/**
 * Jedis链接工厂
 *
 * @author andongxu
 * @Create time 16-2-14:下午4:29
 */
@Component
public class JedisFactory {

    protected static Logger m_logger = Logger.getLogger(JedisFactory.class.getName());

    @Autowired
    public JedisConfig jedisConfig;

    /**
     * 连接池
     */
    protected static Pool pool;

    /**
     * 同步锁
     */
    private final static Object objSync = new Object();


    private JedisFactory() {
        super();
    }

    /**
     * 获取Redis链接
     *
     * @return Jedis Object
     */
    public Jedis getRes() {
        return maybeInitAndGet();
    }

    protected void createAndConnectPool() {
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMinIdle(jedisConfig.getPoolMinIdle());
        poolConfig.setMaxIdle(jedisConfig.getPoolMinIdle());
        poolConfig.setMaxTotal(jedisConfig.getPoolMaxActive());
        if ("true".equals(jedisConfig.getIsCluster())) {
            Set<String> sentinels = new HashSet<String>();
            String[] sentines = jedisConfig.getSentinesHost().split(",");
            for (String sentine : sentines) {
                sentinels.add(sentine);
            }
            pool = new JedisSentinelPool("mymaster", sentinels, poolConfig);
        } else {
            pool = new JedisPool(poolConfig, jedisConfig.getMasterHost(), jedisConfig.getMasterPort());
        }
    }

    public Jedis maybeInitAndGet() {
        if (pool == null) {
            synchronized (objSync) {
                createAndConnectPool();
            }
        }
        // 获取可用的链接
        Jedis j = getWorkingResource();

        if (j != null) {
            return j;
        }

        synchronized (objSync) {
            m_logger.info("超过50%的链接失效，重新初始化连接池");
            pool = null;

            for (int i = 0; i < jedisConfig.getReconnectRetryCount(); i++) {
                shutdownPool();
                createAndConnectPool();

                if (pool != null) {
                    Jedis jd = getWorkingResource();

                    if (jd != null) {
                        return jd;
                    }
                }
                // 等待获取链接
                if (i < jedisConfig.getReconnectRetryCount() - 1) {
                    try {
                        Thread.sleep(jedisConfig.getReconnectRetryWaittime());
                    } catch (Exception e) {
                    }
                }
            }
        }
        return null;
    }

    protected void shutdownPool() {
        if (pool == null) {
            return;
        }

        pool.destroy();
        pool = null;
    }

    /**
     * 获取可用的链接，如果没有返回null
     *
     * @return Jedis Object
     */
    protected Jedis getWorkingResource() {
        for (int i = 0; i < jedisConfig.getFailedResourceBeforeReconnect(); i++) {
            Jedis j = (Jedis) pool.getResource();

            if (j.isConnected()) {
                return j;
            } else {
                pool.returnBrokenResource(j);
            }
        }

        return null;
    }

    /**
     * 返回Jedis链接给连接池
     *
     * @param jedis
     */
    public static void returnRes(Jedis jedis) {
        if (pool != null) {
            pool.returnResource(jedis);
        }
    }

    /**
     * 使用Jedis链接做事
     *
     * @param work 工作的实现类
     * @param <T>  工作的返回值
     * @return 工作的返回值
     */
    public <T> T withJedisDo(JWork<T> work) {
        // catch exception and gracefully fall back.
        try {
            Jedis j = getRes();
            T ret = work.work(j);
            returnRes(j);

            return ret;
        } catch (Exception e) {
            return null;
        }
    }

    public interface Work<Return, Param> {
        public Return work(Param p) throws IllegalAccessException, InstantiationException;
    }

    public interface JWork<Return> extends Work<Return, Jedis> {

    }
}