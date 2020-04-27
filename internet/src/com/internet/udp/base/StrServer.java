package com.internet.udp.base;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class StrServer {

    public static void main(String[] args) throws IOException {
        System.out.println("服务器启动中.......");
        DatagramSocket server = new DatagramSocket(8888);
        String data = "This is udp program.";
        byte[] datas = data.getBytes();
        DatagramPacket packet = new DatagramPacket(datas, 0, datas.length, new InetSocketAddress("localhost", 8889));
        server.send(packet);
        server.close();

    }
}
