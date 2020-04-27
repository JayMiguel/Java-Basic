package com.internet.tcp.chat.thread;

import com.internet.tcp.chat.utils.ChatUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Send implements Runnable {

    private Socket client;
    private BufferedReader console;
    private boolean isRunning;
    private DataOutputStream dos;

    public Send(Socket client, String name) {
        try {
            this.client = client;
            console = new BufferedReader(new InputStreamReader(System.in));
            dos = new DataOutputStream(client.getOutputStream());
            this.isRunning = true;
            send(name);
        } catch (IOException e) {
            System.out.println("======Send======");
            release();
        }
    }

    private String getMsgFromConsole() {
        try {
            return console.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private void send(String msg) {
        try {
            dos.writeUTF(msg);
            dos.flush();
        } catch (IOException e) {
            System.out.println("======send======");
            release();
        }

    }

    private void release() {
        this.isRunning = false;
        ChatUtils.close(dos, client);
    }

    @Override
    public void run() {
        while (isRunning) {
            String msg = getMsgFromConsole();
            if (!msg.equals("")) {
                send(msg);
            }
        }
    }


}
