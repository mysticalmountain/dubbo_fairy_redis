package com.fairy.redis.util;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * 对象序列化
 *
 * @Author andongxu
 * @Create time 16-2-15:上午10:28
 * @Version
 * @Last update time
 */
@Component
public class ObjectSerialize {

    private static Logger logger = Logger.getLogger(ObjectSerialize.class.getName());

    /**
     * 序列化
     * @param obj 被序列化对象
     * @return 字节数组
     */
    public byte[] serialize(Object obj) {
        ByteArrayOutputStream bos = null;
        ObjectOutputStream os = null;
        byte[] rv = null;
        try {
            bos = new ByteArrayOutputStream();
            os = new ObjectOutputStream(bos);
            os.writeObject(obj);
//            os.writeObject(null);
            os.close();
            bos.close();
            return bos.toByteArray();
        } catch (Exception e) {
            logger.error("对象序列化异常", e);
            return null;
        } finally {
            try {
                os.close();
                bos.close();
            } catch (IOException e) {
            }
        }
    }

    /**
     * 反序列化
     * @param value 被反序列化数组
     * @param clazz 序列化后对象所属class
     * @return 对象
     */
    public Object deserialize(byte[] value, Class clazz) {
        ByteArrayInputStream bis = null;
        ObjectInputStream is = null;
        try {
            bis = new ByteArrayInputStream(value);
            is = new ObjectInputStream(bis);
            Object o = Class.forName(clazz.getName()).newInstance();
            o = is.readObject();
            return o;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("对象反序列化异常", e);
            return null;
        } finally {
            try {
                is.close();
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
