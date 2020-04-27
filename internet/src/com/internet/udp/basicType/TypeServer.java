package com.internet.udp.basicType;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class TypeServer {

    public static void main(String[] args) throws IOException {
        System.out.println("服务器启动中.......");
        DatagramSocket server = new DatagramSocket(8888);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(baos));
        dos.writeInt(10);
        dos.writeChar('A');
        dos.writeBoolean(false);
        dos.writeUTF("UTF");
        dos.flush();
        byte[] datas = baos.toByteArray();
        DatagramPacket packet = new DatagramPacket(datas, 0, datas.length, new InetSocketAddress("localhost", 8889));
        server.send(packet);
        server.close();
    }
}
