package com.miguel.collection;

import java.util.HashMap;

public class MyHashSet<K> {

    private HashMap<K, Object> map;
    private static final Object PRESENT = new Object();

    public MyHashSet() {
        map = new HashMap<>();
    }

    public boolean add(K key) {
        return map.put(key, PRESENT) == null;
    }

    public int size() {
        return map.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (K key : map.keySet()) {
            sb.append(key + ",");
        }
        sb.setCharAt(sb.length() - 1, '}');
        return sb.toString();
    }

    public static void main(String[] args) {
        MyHashSet<Integer> mySet = new MyHashSet<>();
        mySet.add(10);
        mySet.add(20);
        mySet.add(30);
        mySet.add(10);
        System.out.println(mySet);
        System.out.println("size: " + mySet.size());
    }
}
