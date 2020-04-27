package com.internet.udp.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class TalkSend implements Runnable {

    private DatagramSocket socket;
    private DatagramPacket packet;
    private int toPort;
    private String toIP;
    private BufferedReader reader;

    public TalkSend(int port, int toPort, String toIP) {
        try {
            this.toPort = toPort;
            this.toIP = toIP;
            this.socket = new DatagramSocket(port);
            this.reader = new BufferedReader(new InputStreamReader(System.in));
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                String msg = reader.readLine();
                byte[] content = msg.getBytes();
                this.packet = new DatagramPacket(content, 0, content.length, new InetSocketAddress(toIP, toPort));
                socket.send(packet);
                if (msg.equalsIgnoreCase("bye"))
                    break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        socket.close();
    }
}
