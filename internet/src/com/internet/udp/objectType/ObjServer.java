package com.internet.udp.objectType;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class ObjServer {

    public static void main(String[] args) throws IOException {
        System.out.println("服务器启动中.......");
        DatagramSocket server = new DatagramSocket(8888);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(baos));
        Employee employee = new Employee("马云", "20000");
        oos.writeObject(employee);
        oos.flush();
        byte[] datas = baos.toByteArray();
        DatagramPacket packet = new DatagramPacket(datas, 0, datas.length, new InetSocketAddress("localhost", 8889));
        server.send(packet);
        server.close();
    }
}
