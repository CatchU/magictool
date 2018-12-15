package com.magic.lang;

import org.junit.Test;

/**
 * 缓存测试类
 */
public class SimpleCacheTest {

    private SimpleCache<Integer,Integer> simpleCache = new SimpleCache<Integer, Integer>();

    @Test
    public void test(){
        System.out.println(simpleCache.put(1,10));
        System.out.println(simpleCache.get(1));
        System.out.println(simpleCache.remove(1));
        //simpleCache.clear();
        System.out.println(simpleCache.get(1));
    }

}
