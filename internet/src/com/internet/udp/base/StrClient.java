package com.internet.udp.base;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class StrClient {

    public static void main(String[] args) throws IOException {
        System.out.println("客户端启动中......");
        // 创建客户端，指定端口
        DatagramSocket client = new DatagramSocket(8889);
        // 创建容器，存放数据
        byte[] content = new byte[1024 * 60];
        // 创建数据包
        DatagramPacket packet = new DatagramPacket(content, 0, content.length);
        // 接收数据，存放到数据包
        client.receive(packet);
        // 处理数据
        byte[] datas = packet.getData();
        int len = packet.getLength();
        System.out.println(new String(datas, 0, len));
        client.close();
    }
}
