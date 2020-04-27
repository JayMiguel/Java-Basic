package com.internet.tcp.chat.thread;

import com.internet.tcp.chat.utils.ChatUtils;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Receive implements Runnable {

    private Socket client;
    private DataInputStream dis;
    private boolean isRunning;

    public Receive(Socket client) {
        try {
            this.client = client;
            dis = new DataInputStream(client.getInputStream());
            this.isRunning = true;
        } catch (IOException e) {
            System.out.println("======Receive======");
            release();
        }
    }

    private String receive() {
        String msg = "";
        try {
            msg = dis.readUTF();
        } catch (IOException e) {
            System.out.println("======receive======");
            release();
        }
        return msg;
    }

    private void release() {
        this.isRunning = false;
        ChatUtils.close(dis, client);
    }

    @Override
    public void run() {
        while (isRunning) {
            String msg = receive();
            if (!msg.equals("")) {
                System.out.println(msg);
            }
        }
    }
}
