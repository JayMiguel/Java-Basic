package com.miguel.collection;

import java.util.*;

public class IterCollections {

    public static void main(String[] args) {
        iterList();
        iterSet();
        iterMap();
    }

    public static void iterList() {
        List<String> list = new ArrayList<>();
        list.add("aa");
        list.add("bb");
        list.add("cc");
        for (Iterator<String> iter = list.iterator(); iter.hasNext(); ) {
            System.out.print(iter.next() + ",");
        }
        System.out.println();
    }

    public static void iterSet() {
        Set<String> set = new HashSet<>();
        set.add("rr");
        set.add("ee");
        set.add("cc");
        for (Iterator<String> iter = set.iterator(); iter.hasNext();) {
            System.out.print(iter.next() + ",");
        }
        System.out.println();
    }

    public static void iterMap() {
        Map<String, Integer> map = new HashMap<>();
        map.put("aa", 10);
        map.put("bb", 20);
        map.put("cc", 30);
        for (Iterator<Map.Entry<String,Integer>> iter = map.entrySet().iterator(); iter.hasNext();) {
            Map.Entry<String, Integer> entry = iter.next();
            System.out.print(entry.getKey() + ":" + entry.getValue() + ",");
        }
        System.out.println();
    }
}
