package com.internet.tcp.base;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        // 1.创建服务端
        System.out.println("------Server------");
        ServerSocket server = new ServerSocket(8888);
        // 2.等待连接
        Socket client = server.accept();
        System.out.println("已建立一个连接");
        // 3.获取输入流
        DataInputStream dis = new DataInputStream(client.getInputStream());
        // 4.读取数据
        String msg = dis.readUTF();
        // 5.处理数据
        System.out.println(msg);
        // 6.释放资源
        dis.close();
        client.close();
        server.close(); // 一般不关闭
    }
}
