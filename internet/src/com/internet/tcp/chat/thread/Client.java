package com.internet.tcp.chat.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {
        System.out.println("------Client------");
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("请输入用户名: ");
        String name = console.readLine();
        Socket client = new Socket("localhost", 8888);
        new Thread(new Send(client, name)).start();
        new Thread(new Receive(client)).start();
    }
}
