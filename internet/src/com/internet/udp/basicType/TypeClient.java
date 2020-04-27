package com.internet.udp.basicType;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class TypeClient {

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
        DataInputStream dis = new DataInputStream(new BufferedInputStream(new ByteArrayInputStream(datas)));
        int num = dis.readInt();
        char ch = dis.readChar();
        boolean boo = dis.readBoolean();
        String utf = dis.readUTF();
        System.out.println(utf + "--->" + num);
        client.close();
    }
}
