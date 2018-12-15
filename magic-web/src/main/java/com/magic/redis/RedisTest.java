package com.magic.redis;

import redis.clients.jedis.Jedis;

/**
 * @author junzhongliu
 * @date 2018/12/12 20:54
 */
public class RedisTest {
    public static void main(String[] args) {
        Jedis jedis = JedisUtils.getJedis();
    }
}
