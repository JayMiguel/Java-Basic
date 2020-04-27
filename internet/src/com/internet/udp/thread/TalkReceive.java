package com.internet.udp.thread;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class TalkReceive implements Runnable {

    private DatagramSocket socket;

    public TalkReceive(int port) {
        try {
            this.socket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        while (true) {
            try {
                byte[] content = new byte[1024 * 60];
                DatagramPacket packet = new DatagramPacket(content, 0, content.length);
                socket.receive(packet);
                byte[] datas = packet.getData();
                int len = packet.getLength();
                String msg = new String(datas, 0,len);
                System.out.println(msg);
                if (msg.equalsIgnoreCase("bye"))
                    break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        socket.close();
    }
}
