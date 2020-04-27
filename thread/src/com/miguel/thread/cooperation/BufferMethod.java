package com.miguel.thread.cooperation;

public class BufferMethod {

    public static void main(String[] args) {
        Container container = new Container();
        new Productor(container).start();
        new Consumer(container).start();
    }

}

class Productor extends Thread {

    Container container;

    public Productor(Container container) {
        this.container = container;
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            Hamburger hamburger = new Hamburger(i);
            try {
                container.push(hamburger);
                System.out.println("生产者-->生产了第" + i + "个馒头");
                this.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class Consumer extends Thread {

    Container container;

    public Consumer(Container container) {
        this.container = container;
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                container.pop();
                System.out.println("消费者-->消费了第" + i + "个馒头");
                this.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Container {
    Hamburger[] hamburgers = new Hamburger[10];
    int count = 0;

    public synchronized void push(Hamburger hamburger) throws InterruptedException {
        if (count == hamburgers.length) {
            this.wait();
        }
        hamburgers[count] = hamburger;
        count++;
        notifyAll();
    }

    public synchronized Hamburger pop() throws InterruptedException {
        if (count == 0) {
            this.wait();
        }
        count--;
        Hamburger hamburger = hamburgers[count];
        notifyAll();
        return hamburger;
    }
}

class Hamburger {
    int id;

    public Hamburger(int id) {
        this.id = id;
    }
}

