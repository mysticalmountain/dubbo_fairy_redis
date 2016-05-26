package com.fairy.redis.service;

import com.fairy.redis.aspect.InvokeLog;
import com.fairy.redis.aspect.TransLog;
import com.fairy.redis.exception.ValidatorException;
import com.fairy.redis.dao.RedisDao;
import com.fairy.redis.util.Channel2Database;
import com.fairy.redis.util.Constants;
import com.fairy.redis.validator.FormatValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * redis数据访问实现
 * Created by andongxu on 16-4-28.
 */
@Service("redisDaoService")
public class RedisDaoServiceImpl implements RedisDaoService {

    @Autowired
    private RedisDao dao;

    @Autowired
    private FormatValidator formatValidator;

    private Logger log = LoggerFactory.getLogger(RedisDaoServiceImpl.class);


    @TransLog(system = Constants.SYSTEM, module = "save", trans = "save", value = "channel=$value.channel,reqId=$value.reqId,key=$value.key,data=$value.data,other=$value")
    public Map<String, Object> save(Map<String, Object> value) {
        try {
            formatValidator.save(value);
            String channel = (String) value.get("channel");
            int database = Channel2Database.getDatabase(channel);
            String key = (String) value.get("key");
            if (dao.exists(database, key.trim())) {                                                                                                //key已经存在
                throw new ValidatorException(Constants.ERROR_KEY_EXISTS, "key已存在");
            }
            Object data = value.get("data");
            String redisCode = "";
            String expire = (String) value.get("expire");
            if (data instanceof Serializable) {
                if (expire != null && expire.trim().length() != 0) {
                    redisCode = dao.save(database, key, (Serializable) data, Integer.valueOf(expire));
                } else {
                    redisCode = dao.save(database, key, (Serializable) data);
                }
            } else if (data instanceof Collection) {
                if (expire != null && expire.trim().length() != 0) {
                    redisCode = dao.save(database, key, (Collection) data, Integer.valueOf(expire));
                } else {
                    redisCode = dao.save(database, key, (Collection) data);
                }
            }
            return this.handlerResult(redisCode, value);
        } catch (Exception e) {
            log.error("save exception:" + e.getMessage(), e);
            return this.handlerException(e, value);
        }
    }

    @TransLog(system = Constants.SYSTEM, module = "query", trans = "query", value = "channel=$value.channel,reqId=$value.reqId,key=$value.key,other=$value")
    public Map<String, Object> query(Map<String, Object> value) {
        try {
            formatValidator.common(value);
            String channel = (String) value.get("channel");
            int database = Channel2Database.getDatabase(channel);
            String key = (String) value.get("key");
            Class c = (Class) value.get("class");
            Object result = dao.query(database, key, c);
            return this.handlerQueryResult(result, value);
        } catch (Exception e) {
            log.error("query exception:" + e.getMessage(), e);
            return this.handlerException(e, value);
        }
    }

    @TransLog(system = Constants.SYSTEM, module = "update", trans = "update", value = "channel=$value.channel,reqId=$value.reqId,key=$value.key,data=$value.data,other=$value")
    public Map<String, Object> update(Map<String, Object> value) {
        try {
            formatValidator.save(value);
            String channel = (String) value.get("channel");
            int database = Channel2Database.getDatabase(channel);
            String key = (String) value.get("key");
            if (!dao.exists(database, key.trim())) {                                                                                                //key不存在
                throw new ValidatorException(Constants.ERROR_KEY_NOT_EXISTS, "key不存在");
            }
            Object data = value.get("data");
            String redisCode = "";
            String expire = (String) value.get("expire");
            if (data instanceof Serializable) {
                if (expire != null && expire.trim().length() != 0) {
                    redisCode = dao.save(database, key, (Serializable) data, Integer.valueOf(expire));
                } else {
                    redisCode = dao.save(database, key, (Serializable) data);
                }
            } else if (data instanceof Collection) {
                if (expire != null && expire.trim().length() != 0) {
                    redisCode = dao.save(database, key, (Collection) data, Integer.valueOf(expire));
                } else {
                    redisCode = dao.save(database, key, (Collection) data);
                }
            }
            return this.handlerResult(redisCode, value);
        } catch (Exception e) {
            log.error("update exception:" + e.getMessage(), e);
            return this.handlerException(e, value);
        }
    }

    @TransLog(system = Constants.SYSTEM, module = "delete", trans = "delete", value = "channel=$value.channel,reqId=$value.reqId,key=$value.key,other=$value")
    public Map<String, Object> delete(Map<String, Object> value) {
        try {
            formatValidator.common(value);
            String channel = (String) value.get("channel");
            int database = Channel2Database.getDatabase(channel);
            String key = (String) value.get("key");
            if (!dao.exists(database, key.trim())) {                                                                                                //key不存在
                throw new ValidatorException(Constants.ERROR_KEY_NOT_EXISTS, "key不存在");
            }
            Long redisCode = dao.delete(database, key.trim());
            return this.handlerResult(Constants.REDIS_OPERATE_SUCCESS, value);
        } catch (Exception e) {
            log.error("delete exception:" + e.getMessage(), e);
            return this.handlerException(e, value);
        }
    }

    @TransLog(system = Constants.SYSTEM, module = "expire", trans = "expire", value = "channel=$value.channel,reqId=$value.reqId,key=$value.key,expire=$value.expire,other=$value")
    public Map<String, Object> expire(Map<String, Object> value) {
        try {
            formatValidator.expire(value);
            String channel = (String) value.get("channel");
            int database = Channel2Database.getDatabase(channel);
            String key = (String) value.get("key");
            if (!dao.exists(database, key.trim())) {                                                                                                //key不存在
                throw new ValidatorException(Constants.ERROR_KEY_NOT_EXISTS, "key不存在");
            }
            String expire = (String) value.get("expire");
            Long redisCode = dao.expire(database, key.trim(), Integer.valueOf(expire));
            return this.handlerResult(Constants.REDIS_OPERATE_SUCCESS, value);
        } catch (Exception e) {
            log.error("expire exception:" + e.getMessage(), e);
            return this.handlerException(e, value);
        }
    }

    @TransLog(system = Constants.SYSTEM, module = "rename", trans = "rename", value = "channel=$value.channel,reqId=$value.reqId,key=$value.key,newKey=$value.newKey,other=$value")
    public Map<String, Object> rename(Map<String, Object> value) {
        try {
            formatValidator.rename(value);
            String channel = (String) value.get("channel");
            int database = Channel2Database.getDatabase(channel);
            String key = (String) value.get("key");
            String newKey = (String) value.get("newKey");
            if (!dao.exists(database, key.trim())) {                                                                                                //key不存在
                throw new ValidatorException(Constants.ERROR_KEY_NOT_EXISTS, "key不存在");
            }
            if (dao.exists(database, newKey.trim())) {
                throw new ValidatorException(Constants.ERROR_KEY_EXISTS, "目标key已存在");
            }
            String redisCode = dao.rename(database, key, newKey);
            return this.handlerResult(redisCode, value);
        } catch (Exception e) {
            log.error("rename exception:" + e.getMessage(), e);
            return this.handlerException(e, value);
        }
    }

    @TransLog(system = Constants.SYSTEM, module = "exists", trans = "exists", value = "channel=$value.channel,reqId=$value.reqId,key=$value.key,other=$value")
    public Map<String, Object> exists(Map<String, Object> value) {
        try {
            formatValidator.common(value);
            String channel = (String) value.get("channel");
            int database = Channel2Database.getDatabase(channel);
            String key = (String) value.get("key");
            boolean flag = dao.exists(database, key.trim());
            Map<String, Object> result = this.handlerResult(Constants.REDIS_OPERATE_SUCCESS, value);
            if (flag) {
                result.put("exists", "1");
            } else {
                result.put("exists", "0");
            }
            return result;
        } catch (Exception e) {
            log.error("exists exception:" + e.getMessage(), e);
            return this.handlerException(e, value);
        }
    }

    @TransLog(system = Constants.SYSTEM, module = "exists", trans = "exists", value = "channel=$value.channel,reqId=$value.reqId,key=$value.key,other=$value")
    public Map<String, Object> keys(Map<String, Object> value) {
        try {
            formatValidator.common(value);
            String channel = (String) value.get("channel");
            int database = Channel2Database.getDatabase(channel);
            String key = (String) value.get("key");
            Set<String> keys = dao.keys(database, key);
            Map<String, Object> result = this.handlerResult(Constants.REDIS_OPERATE_SUCCESS, value);
            result.put("data", keys);
            return result;
        } catch (Exception e) {
            log.error("keys exception:" + e.getMessage(), e);
            return this.handlerException(e, value);
        }
    }

    private Map<String, Object> handlerResult(String redisCode, Map<String, Object> inValue) {
        String errorCode = "";
        String errorMsg = "";
        if (Constants.REDIS_OPERATE_SUCCESS.equals(redisCode)) {
            errorCode = Constants.SUCCESS;
            errorMsg = "成功";
        } else {
            errorCode = Constants.ERROR_UNKNOWN;
            errorMsg = "失败-" + redisCode;
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errorCode", errorCode);
        result.put("errorMsg", errorMsg);
        result.put("versionNo", inValue.get("versionNo") == null ? "" : inValue.get("versionNo"));
        result.put("transId", inValue.get("transId") == null ? "" : inValue.get("transId"));
        result.put("reqId", inValue.get("reqId") == null ? "" : inValue.get("reqId"));
        return result;
    }

    private Map<String, Object> handlerQueryResult(Object data, Map<String, Object> inValue) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errorCode", Constants.SUCCESS);
        result.put("errorMsg", "成功");
        result.put("versionNo", inValue.get("versionNo") == null ? "" : inValue.get("versionNo"));
        result.put("transId", inValue.get("transId") == null ? "" : inValue.get("transId"));
        result.put("reqId", inValue.get("reqId") == null ? "" : inValue.get("reqId"));
        result.put("data", data);
        return result;
    }

    private Map<String, Object> handlerException(Exception e, Map<String, Object> inValue) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("versionNo", inValue.get("versionNo") == null ? "" : inValue.get("versionNo"));
        result.put("transId", inValue.get("transId") == null ? "" : inValue.get("transId"));
        result.put("reqId", inValue.get("reqId") == null ? "" : inValue.get("reqId"));
        if (e instanceof ValidatorException) {
            ValidatorException fe = (ValidatorException) e;
            result.put("errorCode", fe.getErrorCode());
            result.put("errorMsg", fe.getErrorMsg());
        } else {
            result.put("errorCode", Constants.ERROR_UNKNOWN);
            result.put("errorMsg", "未知错误");
        }
        return result;
    }
}
