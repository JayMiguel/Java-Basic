package com.internet.udp.thread;

public class TalkTeacher {

    public static void main(String[] args) {
        new Thread(new TalkReceive(8890)).start();
        new Thread(new TalkSend(8891, 8888, "localhost")).start();
    }
}
