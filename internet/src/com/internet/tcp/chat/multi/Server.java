package com.internet.tcp.chat.multi;

import com.internet.tcp.chat.utils.ChatUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        System.out.println("------Server------");
        ServerSocket server = new ServerSocket(8888);
        while (true) {
            Socket client = server.accept();
            System.out.println("一个客户端建立了连接......");
            new Thread(() -> {
                DataInputStream dis = null;
                DataOutputStream dos = null;
                try {
                    dis = new DataInputStream(client.getInputStream());
                    dos = new DataOutputStream(client.getOutputStream());
                } catch (IOException e) {
                    ChatUtils.close(dos,dis,client);
                }
                boolean isRunning = true;
                while (isRunning) {
                    try {
                        String msg = dis.readUTF();
                        dos.writeUTF(msg);
                        dos.flush();
                    } catch (IOException e) {
                        isRunning = false;
                        ChatUtils.close(dos,dis,client);
                    }
                }
                ChatUtils.close(dos,dis,client);
            }).start();
        }
    }
}
