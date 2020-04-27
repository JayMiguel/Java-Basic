package com.miguel.collection;

import java.util.Arrays;

public class MyArrayList<T> {
    private Object[] elementData;
    private int size;

    private static final int DEFAULT_CAPACITY = 10;

    public MyArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int capacity) {
        if (0 < capacity && capacity < DEFAULT_CAPACITY)
            elementData = new Object[DEFAULT_CAPACITY];
        else if (capacity > DEFAULT_CAPACITY)
            elementData = new Object[capacity];
        else
            throw new RuntimeException(capacity + " is an illegal number.");
    }

    public T get(int index) {
        checkElementIndex(index);
        return (T) elementData[index];
    }

    public void set(T element, int index) {
        checkElementIndex(index);
        elementData[index] = element;
    }

    public void add(T element) {
        if (size >= elementData.length * 0.75) {
            resize();
        }
        elementData[size++] = element;
    }

    public T remove(int index) {
        checkElementIndex(index);
        T oldVal = (T) elementData[index];
        int moveCount = size - index - 1;
        System.arraycopy(elementData, index + 1, elementData, index, moveCount);
        elementData[--size] = null;
        return oldVal;
    }

    public boolean remove(T element) {
        if (element == null)
            throw new NullPointerException();
        for (int i = 0; i < size; i++) {
            if (element.equals(elementData[i])) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void resize() {
        elementData = Arrays.copyOf(elementData, elementData.length + (elementData.length >> 1));
    }

    void checkElementIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(elementData[i]);
            if (i != size - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        MyArrayList<String> list = new MyArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("" + i + i);
        }
        System.out.println(list);
        System.out.println(list.get(1));
        list.remove(2);
        System.out.println(list);
        System.out.println(list.size());
    }
}
