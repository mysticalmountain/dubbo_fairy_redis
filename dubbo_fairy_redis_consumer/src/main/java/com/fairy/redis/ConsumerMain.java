package com.fairy.redis;

import com.fairy.redis.service.RedisDaoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by andongxu on 16-5-6.
 */
public class ConsumerMain {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring.xml", "dubbo-consumer.xml"});
        context.start();
        RedisDaoService redisDaoService = (RedisDaoService) context.getBean("redisDaoService");

        System.out.println("==================" + redisDaoService);

        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("channel", "WX");
        dataMap.put("transTime", "20150504 000000");
        dataMap.put("transCode", "123");
        dataMap.put("transId", "123456789123456789");
        dataMap.put("reqId", "324324324");
        dataMap.put("key", "k129");
        dataMap.put("data", "v129");

        Map res = redisDaoService.save(dataMap);
        System.out.println("====================" + res);
    }
}
