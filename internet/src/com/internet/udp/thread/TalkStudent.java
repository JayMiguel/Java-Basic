package com.internet.udp.thread;

public class TalkStudent {

    public static void main(String[] args) {
        new Thread(new TalkReceive(8888)).start();
        new Thread(new TalkSend(8889, 8890, "localhost")).start();
    }

}
