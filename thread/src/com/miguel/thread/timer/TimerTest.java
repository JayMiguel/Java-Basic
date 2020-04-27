package com.miguel.thread.timer;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {

    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new MyTask(), 5000L, 200L);
    }
}

class MyTask extends TimerTask {

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("闹钟响了......");
        }
    }

}
