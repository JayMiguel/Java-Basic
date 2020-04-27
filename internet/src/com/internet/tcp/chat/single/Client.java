package com.internet.tcp.chat.single;

import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {
        System.out.println("------Client------");
        // 1.创建客户端
        Socket client = new Socket("localhost", 8888);
        // 2.创建键盘输入流
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // 3.获取输出流
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        // 4.获取输入流
        DataInputStream dis = new DataInputStream(client.getInputStream());
        boolean isRunning = true;
        while (isRunning) {
            // 4.读取数据
            String msg = reader.readLine();
            // 5.发送数据
            dos.writeUTF(msg);
            dos.flush();
            // 6.接收数据
            msg = dis.readUTF();
            System.out.println(msg);
        }
        // 7.释放资源
        dos.close();
        dis.close();
        client.close();
    }
}
