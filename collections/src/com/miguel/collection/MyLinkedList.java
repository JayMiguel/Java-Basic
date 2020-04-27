package com.miguel.collection;

import java.util.NoSuchElementException;

public class MyLinkedList<E> {
    private Node<E> first;
    private Node<E> last;
    private int size;

    public int size() {
        return size;
    }

    public E get(int index) {
        checkElementIndex(index);
        if (index < (size >> 1)) {
            Node<E> item = first;
            for (int i = 0; i < index; i++) {
                item = item.next;
            }
            return item.element;
        } else {
            Node<E> item = last;
            for (int i = size - 1; i > index; i--) {
                item = item.previous;
            }
            return item.element;
        }
    }

    Node<E> node(int index) {
        checkElementIndex(index);
        if (index < (size >> 1)) {
            Node<E> item = first;
            for (int i = 0; i < index; i++) {
                item = item.next;
            }
            return item;
        } else {
            Node<E> item = last;
            for (int i = size - 1; i > index; i--) {
                item = item.previous;
            }
            return item;
        }
    }

    public void add(E element) {
        Node<E> node = new Node<E>(element);
        if (first == null) {
            this.first = node;
            this.last = node;
        } else {
            node.previous = last;
            last.next = node;
            last = node;
        }
        size++;
    }

    public void add(int index, E element) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index must be in [0, " + size + "]");
        if (index == 0) {
            addFirst(element);
        } else if (index == size) {
            addLast(element);
        } else {
            Node<E> newNode = new Node<E>(element);
            Node<E> temp = node(index);
            Node<E> tempPrev = temp.previous;

            newNode.previous = tempPrev;
            newNode.next = temp;
            tempPrev.next = newNode;
            temp.previous = newNode;
            size++;
        }

    }

    public void addFirst(E element) {
        Node<E> newNode = new Node<>(element);
        Node<E> temp = first;
        newNode.next = temp;
        temp.previous = newNode;
        first = newNode;
        size++;
    }

    public void addLast(E element) {
        Node<E> newNode = new Node<>(element);
        Node<E> temp = last;
        newNode.previous = temp;
        temp.next = newNode;
        last = newNode;
        size++;
    }

    public E removeFirst() {
        if (first == null)
            throw new NoSuchElementException();
        return unlink(first);
    }

    public E removeLast() {
        if (last == null)
            throw new NoSuchElementException();
        return unlink(last);
    }

    public boolean remove(E element) {
        if (element == null) {
            for (Node<E> item = first; item != null; item = item.next) {
                if (item.element == null) {
                    unlink(item);
                    return true;
                }
            }
        } else {
            for (Node<E> item = first; item != null; item = item.next) {
                if (element.equals(item.element)) {
                    unlink(item);
                    return true;
                }
            }
        }
        return false;
    }

    void checkElementIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    E unlink(Node<E> item) {
        Node<E> previous = item.previous;
        E element = item.element;
        Node<E> next = item.next;
        if (item == first) {
            if (next == null) {
                last = null;
            } else {
                next.previous = null;
                first = next;
            }
        } else if (item == last) {
            previous.next = null;
            last = previous;
        } else {
            previous.next = next;
            next.previous = previous;
        }
        item.previous = null;
        item.element = null;
        item.next = null;
        size--;
        return element;
    }

    @Override
    public String toString() {
        Node temp = first;
        StringBuilder sb = new StringBuilder("[");
        while (temp != null) {
            sb.append(temp.element);
            if (temp != last) {
                sb.append(",");
            }
            temp = temp.next;
        }
        sb.append("]");
        return sb.toString();
    }

    private static class Node<E> {
        private Node<E> previous;
        private E element;
        private Node<E> next;

        Node(E element) {
            this.element = element;
        }
    }

    public static void main(String[] args) {
        MyLinkedList<String> list = new MyLinkedList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        System.out.println("add 3 times : " + list);
        System.out.println("size = " + list.size());
        System.out.println("******************************");
        System.out.println("get : " + list.get(1));
        System.out.println("size = " + list.size());
        System.out.println("******************************");
        System.out.println("remove : " + list.remove("c"));
        System.out.println(list);
        System.out.println("size = " + list.size());
        System.out.println("******************************");
        System.out.println("removeFirst : " + list.removeFirst());
        System.out.println("size = " + list.size());
        System.out.println("******************************");
        list.add(0, "d");
        System.out.println("add d : " + list);
        System.out.println("size = " + list.size());
        System.out.println("******************************");
        System.out.println("removeLast : " + list.removeLast());
        System.out.println("size = " + list.size());
        System.out.println("******************************");
        list.addLast("c");
        System.out.println("add c : " + list);
        System.out.println("size = " + list.size());
    }
}
