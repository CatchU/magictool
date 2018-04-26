package com.magic.cache;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRULinkedHashMap<K,V> extends LinkedHashMap<K,V> {

    private final int CACHE_SIZE;

    public LRULinkedHashMap(int initialCapacity) {
        super((int)Math.ceil(initialCapacity/0.75), 0.75f, true);
        CACHE_SIZE = initialCapacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size()>CACHE_SIZE;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<K,V> m: entrySet ()){
            sb.append(String.format("%s:%s",m.getKey(),m.getValue()));
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        LRULinkedHashMap<Integer,String> lru = new LRULinkedHashMap<Integer,String>(5);
        lru.put(1, "11");
        lru.put(2, "11");
        lru.put(3, "11");
        lru.put(4, "11");
        lru.put(5, "11");
        System.out.println(lru.toString());
        lru.put(6, "66");
        lru.get(2);
        lru.put(7, "77");
        lru.get(4);
        System.out.println(lru.toString());
        System.out.println();
    }
}
