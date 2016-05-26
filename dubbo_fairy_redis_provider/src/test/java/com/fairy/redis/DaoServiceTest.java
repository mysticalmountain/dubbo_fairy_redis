package com.fairy.redis;

import com.fairy.redis.service.RedisDaoService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by andongxu on 16-5-3.
 */
public class DaoServiceTest extends SpringConfigLoader {

    @Autowired
    private RedisDaoService dao;

    @Test
    public void save() {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("channel", "WX");
        dataMap.put("transTime", "20150504 000000");
        dataMap.put("transCode", "123");
        dataMap.put("transId", "123456789123456789");
        dataMap.put("reqId", "324324324");
        dataMap.put("key", "k123");
        dataMap.put("data", "v123");

        dao.save(dataMap);
    }

    @Test
    public void saveExpire() {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("channel", "WX");
        dataMap.put("transTime", "20150504 000000");
        dataMap.put("transCode", "123");
        dataMap.put("transId", "123456789123456789");
        dataMap.put("reqId", "324324324");
        dataMap.put("key", "k125");
        dataMap.put("data", "v125");
        dataMap.put("expire", "20");

        dao.save(dataMap);
    }

    @Test
    public void delete() {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("channel", "WX");
        dataMap.put("transTime", "20150504 000000");
        dataMap.put("transCode", "123");
        dataMap.put("transId", "123456789123456789");
        dataMap.put("reqId", "324324324");
        dataMap.put("key", "k123");

        dao.delete(dataMap);
    }

    @Test
    public void update() {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("channel", "WX");
        dataMap.put("transTime", "20150504 000000");
        dataMap.put("transCode", "123");
        dataMap.put("transId", "123456789123456789");
        dataMap.put("reqId", "324324324");
        dataMap.put("key", "k123");
        dataMap.put("data", "v123");

        dao.update(dataMap);
    }

    @Test
    public void query() {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("channel", "WX");
        dataMap.put("transTime", "20150504 000000");
        dataMap.put("transCode", "123");
        dataMap.put("transId", "123456789123456789");
        dataMap.put("reqId", "324324324");
        dataMap.put("key", "k123");
        dataMap.put("class", String.class);

        dao.query(dataMap);
    }


    @Test
    public void expire() {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("channel", "WX");
        dataMap.put("transTime", "20150504 000000");
        dataMap.put("transCode", "123");
        dataMap.put("transId", "123456789123456789");
        dataMap.put("reqId", "324324324");
        dataMap.put("key", "k123");
        dataMap.put("expire", "1000");

        dao.expire(dataMap);
    }

    @Test
    public void rename() {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("channel", "WX");
        dataMap.put("transTime", "20150504 000000");
        dataMap.put("transCode", "123");
        dataMap.put("transId", "123456789123456789");
        dataMap.put("reqId", "324324324");
        dataMap.put("key", "k124");
        dataMap.put("newKey", "000");

        dao.rename(dataMap);
    }

    @Test
    public void exists() {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("channel", "WX");
        dataMap.put("transTime", "20150504 000000");
        dataMap.put("transCode", "123");
        dataMap.put("transId", "123456789123456789");
        dataMap.put("reqId", "324324324");
        dataMap.put("key", "k124");

        dao.exists(dataMap);
    }

    @Test
    public void keys() {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("channel", "WX");
        dataMap.put("transTime", "20150504 000000");
        dataMap.put("transCode", "123");
        dataMap.put("transId", "123456789123456789");
        dataMap.put("reqId", "324324324");
        dataMap.put("key", "a*");
        Map res = dao.keys(dataMap);
        if (res != null) {
            Set<String> keys = (Set<String>) res.get("data");
            Iterator<String> ik = keys.iterator();
            while (ik.hasNext()) {
                System.out.println("*************" + ik.next());
            }
        }
    }
}
