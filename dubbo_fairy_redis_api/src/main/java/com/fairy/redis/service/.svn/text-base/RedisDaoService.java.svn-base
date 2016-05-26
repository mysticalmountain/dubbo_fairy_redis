package com.fairy.redis.service;

import java.util.Map;

/**
 * Redis 数据访问接口
 * <p/>
 * Created by andongxu on 16-4-28.
 *
 * @version 1.0
 */
public interface RedisDaoService {

    /**
     * 保存数据到redis缓存
     *
     * @param value
     * @return
     */
    public Map<String, Object> save(Map<String, Object> value);

    /**
     * 从redis缓存查询数据
     *
     * @param value
     * @return
     */
    public Map<String, Object> query(Map<String, Object> value);

    /**
     * 更新redis缓存中已存在的数据
     *
     * @param value
     * @return
     */
    public Map<String, Object> update(Map<String, Object> value);

    /**
     * 删除redis缓存中已存在的数据
     *
     * @param value
     * @return
     */
    public Map<String, Object> delete(Map<String, Object> value);

    /**
     * 设置redis 中 key 过期时间
     *
     * @param value
     * @return
     */
    public Map<String, Object> expire(Map<String, Object> value);

    /**
     * 重命名 redis 中key
     *
     * @param value
     * @return
     */
    public Map<String, Object> rename(Map<String, Object> value);

    /**
     * 判断redis中key是否存在
     *
     * @param value
     * @return
     */
    public Map<String, Object> exists(Map<String, Object> value);

    /**
     * 获取redis中匹配的key
     *
     * @param value
     * @return
     */
    public Map<String, Object> keys(Map<String, Object> value);
}
