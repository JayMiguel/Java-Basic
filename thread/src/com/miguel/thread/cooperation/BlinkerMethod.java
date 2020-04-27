package com.miguel.thread.cooperation;

public class BlinkerMethod {

    public static void main(String[] args) {
        Tv tv = new Tv();
        new Actor(tv).start();
        new Watcher(tv).start();
    }

}

class Tv {

    String program;
    boolean flag;

    public Tv() {
    }

    public synchronized void play(String program) {
        try {
            if (flag) {
                this.wait();
            }
            System.out.println("在表演: " + program);
            this.program = program;
            notifyAll();
            this.flag = !this.flag;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void watch() {
        try {
            if (!flag) {
                this.wait();
            }
            System.out.println("在观看：" + program);
            notifyAll();
            this.flag = !this.flag;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Actor extends Thread{

    Tv tv;

    public Actor(Tv tv) {
        this.tv = tv;
    }

    public void run() {
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                this.tv.play("奇葩说");
            } else {
                this.tv.play("王牌对王牌");
            }
        }
    }
}

class Watcher extends Thread{

    Tv tv;

    public Watcher(Tv tv) {
        this.tv = tv;
    }

    public void run() {
        for (int i = 0; i < 20; i++) {
           this.tv.watch();
        }
    }
}
