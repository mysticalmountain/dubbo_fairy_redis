package com.fairy.redis.util;

import com.alibaba.fastjson.JSON;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidParameterException;
import java.util.List;

/**
 * Created by andongxu on 16-4-29.
 */
public class Channel2Database {

    private static String channel2database = "";

    private static List<Channel> channels;


    static {
        loadData();
        parseData();
    }

    private static void loadData() {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("channel2database.json");
        ByteArrayOutputStream out = new ByteArrayOutputStream(256);
        byte[] block = new byte[16];
        try {
            while (true) {
                int i = is.read(block);
                if (i == -1) {
                    break;
                }
                out.write(block, 0, i);
            }
            channel2database += new String(out.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                out.close();
            } catch (IOException e) {
            }
        }
    }

    public static void parseData() {
        channels = JSON.parseArray(channel2database, Channel.class);
    }

    /**
     * 通过渠道代码获取渠道
     * @param code
     * @return
     */
    public static Channel getChannel(String code) {
        for (Channel channel : channels) {
            if (channel.getCode().equals(code)) {
                return channel;
            }
        }
        return null;
    }

    public static int getDatabase(String code) {
        Channel channel = getChannel(code);
        if (channel == null) {
            throw  new InvalidParameterException();
        }
        return channel.getDatabase();
    }

    /**
     * 获取所有渠道
     * @return
     */
    public static List<Channel> getChannels() {
        return channels;
    }


    public static void main(String [] args) {
        System.out.println(channel2database);
//        System.out.println(channels);
    }
}
