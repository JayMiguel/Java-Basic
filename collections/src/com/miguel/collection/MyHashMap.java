package com.miguel.collection;

import java.util.Arrays;

public class MyHashMap<K, V> {
    private Node<K, V>[] table;
    private int size;
    private static final int DEFAULT_CAPACITY = 16;

    public MyHashMap() {
        table = new Node[DEFAULT_CAPACITY];
    }

    public void put(K key, V value) {
        resize();
        int hash = hash(key);
        Node<K, V> newNode = new Node<>();
        newNode.hash = hash;
        newNode.key = key;
        newNode.value = value;
        newNode.next = null;

        Node<K, V> temp = table[hash];
        Node<K, V> iterLast = null;
        if (temp == null) {
            table[hash] = newNode;
        } else {
            while (temp != null) {
                if (temp.key.equals(key)) {
                    temp.value = value;
                    return;
                } else {
                    iterLast = temp;
                    temp = temp.next;
                }
            }
            iterLast.next = newNode;
        }
        size++;
    }

    public V get(K key) {
        int hash = hash(key);
        Node<K, V> temp = table[hash];
        if (temp != null) {
            while (!temp.key.equals(key)) {
                temp = temp.next;
            }
        }
        return temp.value;
    }

    public V remove(K key) {
        int hash = hash(key);
        Node<K, V> target = table[hash];
        Node<K, V> temp;
        if (target.key.equals(key)) {
            table[hash] = target.next;
        } else {
            do {
                temp = target;
                target = target.next;
            } while ((!target.key.equals(key)));
            temp.next = target.next;
        }
        size--;
        return target.value;
    }

    private int hash(K key) {
        return key.hashCode() & (table.length - 1);
    }

    private void resize() {
        if (size >= table.length * 0.75) {
            Node[] newTable = new Node[table.length + (table.length>>>1)];
            for (int i = 0; i < table.length; i++) {
                Node temp = table[i];
                newTable[i] = temp;
                Node newTemp = newTable[i];
                while (temp != null && temp.next != null) {
                    temp = temp.next;
                    newTemp.next = temp;
                    newTemp = newTemp.next;
                }
            }
            table = newTable;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (Node node : table) {
            while (node != null) {
                sb.append(node.key + ":" + node.value + ",");
                node = node.next;
            }
        }
        sb.setCharAt(sb.length()-1,'}');
        return sb.toString();
    }

    private static class Node<K, V> {
        private int hash;
        private K key;
        private V value;
        private Node<K, V> next;

        @Override
        public String toString() {
            return "{" +
                    key +
                    '=' + value +
                    '}';
        }
    }

    public static void main(String[] args) {
        MyHashMap<Integer, String> map = new MyHashMap<>();

        for (int i = 10; i < 200; i += 10) {
            map.put(i, "test");
            System.out.println(map.size);
        }
        System.out.println(map);

    }
}

