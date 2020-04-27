package com.internet.tcp.chat.multi;

import com.internet.tcp.chat.utils.ChatUtils;

import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {
        System.out.println("------Client------");
        Socket client = new Socket("localhost", 8888);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        DataInputStream dis = new DataInputStream(client.getInputStream());
        boolean isRunning = true;
        while (isRunning) {
            String msg = reader.readLine();
            dos.writeUTF(msg);
            dos.flush();
            msg = dis.readUTF();
            System.out.println(msg);
        }
        ChatUtils.close(dos,dis,client);
    }
}
