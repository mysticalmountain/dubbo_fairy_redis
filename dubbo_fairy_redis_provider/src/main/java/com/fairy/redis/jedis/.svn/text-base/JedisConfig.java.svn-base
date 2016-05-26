package com.fairy.redis.jedis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Jedis 配置
 *
 * @Author andongxu
 * @Create time 16-2-14:下午4:29
 * @Version
 * @Last update time
 */
@Component
public class JedisConfig {

    /**
     * 是否采用集群
     */
    @Value("${redis_is_cluster:fase}")
    private String isCluster;
    /**
     * 非集群模式master host
     */
    @Value("${redis_master_host:127.0.0.1}")
    private String masterHost;
    /**
     * 非集群模式master port
     */
    @Value("${redis_master_port:6379}")
    private int masterPort;
    /**
     * 集群模式sentinels host
     */
    @Value("${redis_sentinels_host:}")
    private String sentinesHost;


    /**
     * Jedis连接池激活的最大链接数
     */
    @Value("${pool_max_active:32}")
    private int poolMaxActive = 32;

    /**
     * Jedis连接池允许的最小空闲连接数
     */
    @Value("${pool_min_idle:24}")
    private int poolMinIdle = 24;

    /**
     * 在放弃或者重新初始化链接池之前，获取链接的次数
     */
    private int failedResourceBeforeReconnect = poolMaxActive / 2 + 1;

    /**
     * 重新链接重试次数
     */
    private int reconnectRetryCount = 48;

    /**
     * 每次重新链接等待时间（毫秒）
     */
    private int reconnectRetryWaittime = 5000;


    public String getIsCluster() {
        return isCluster;
    }

    public void setIsCluster(String isCluster) {
        this.isCluster = isCluster;
    }

    public String getMasterHost() {
        return masterHost;
    }

    public void setMasterHost(String masterHost) {
        this.masterHost = masterHost;
    }

    public int getMasterPort() {
        return masterPort;
    }

    public void setMasterPort(int masterPort) {
        this.masterPort = masterPort;
    }

    public String getSentinesHost() {
        return sentinesHost;
    }

    public void setSentinesHost(String sentinesHost) {
        this.sentinesHost = sentinesHost;
    }

    public int getPoolMaxActive() {
        return poolMaxActive;
    }

    public void setPoolMaxActive(int poolMaxActive) {
        this.poolMaxActive = poolMaxActive;
    }

    public int getPoolMinIdle() {
        return poolMinIdle;
    }

    public void setPoolMinIdle(int poolMinIdle) {
        this.poolMinIdle = poolMinIdle;
    }

    public int getFailedResourceBeforeReconnect() {
        return failedResourceBeforeReconnect;
    }

    public void setFailedResourceBeforeReconnect(int failedResourceBeforeReconnect) {
        this.failedResourceBeforeReconnect = failedResourceBeforeReconnect;
    }

    public int getReconnectRetryCount() {
        return reconnectRetryCount;
    }

    public void setReconnectRetryCount(int reconnectRetryCount) {
        this.reconnectRetryCount = reconnectRetryCount;
    }

    public int getReconnectRetryWaittime() {
        return reconnectRetryWaittime;
    }

    public void setReconnectRetryWaittime(int reconnectRetryWaittime) {
        this.reconnectRetryWaittime = reconnectRetryWaittime;
    }
}
