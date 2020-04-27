package com.internet.udp.objectType;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ObjClient {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
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
        ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new ByteArrayInputStream(datas)));
        Object obj = ois.readObject();
        if (obj instanceof Employee) {
            Employee employee = (Employee) obj;
            System.out.println(employee);
        }
        client.close();
    }
}
