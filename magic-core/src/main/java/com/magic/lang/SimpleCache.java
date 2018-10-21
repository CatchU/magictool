package com.magic.lang;

import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 简单缓存，基于java的WeakHashMap实现
 */
public class SimpleCache<K,V> {

    /** 池 */
    private final Map<K,V> cache = new WeakHashMap<>();

    private final ReentrantReadWriteLock cacheLock = new ReentrantReadWriteLock();
    private final ReentrantReadWriteLock.ReadLock readLock = cacheLock.readLock();
    private final ReentrantReadWriteLock.WriteLock writeLock = cacheLock.writeLock();

    /**
     * 从池中取值
     * @param key 键
     * @return 值
     */
    public V get(K key){
        readLock.lock();
        V value;
        try {
            value = cache.get(key);
        } finally {
            readLock.unlock();
        }
        return value;
    }

    /**
     * 向池中放值
     * @param key 键
     * @param value 值
     * @return 值
     */
    public V put(K key,V value){
        writeLock.lock();
        try {
            cache.put(key,value);
        } finally {
            writeLock.unlock();
        }
        return value;
    }

    /**
     * 移除单个值
     * @param key 键
     * @return
     */
    public V remove(K key){
        writeLock.lock();
        try {
            return cache.remove(key);
        } finally {
            writeLock.unlock();
        }
    }

    public void clear(){
        writeLock.lock();
        try {
            cache.clear();
        } finally {
            writeLock.unlock();
        }
    }
}
