package com.internet.tcp.base;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {
        // 1.创建客户端，须指定地址和端口
        System.out.println("------Client------");
        Socket client = new Socket("localhost", 8888);
        // 2.创建输出流
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        // 3.输出数据
        dos.writeUTF("Hello");
        dos.flush();
        // 4.释放资源
        dos.close();
        client.close();
    }
}
