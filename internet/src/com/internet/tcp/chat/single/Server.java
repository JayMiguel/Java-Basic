package com.internet.tcp.chat.single;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        System.out.println("------Server------");
        //1.创建服务端
        ServerSocket server = new ServerSocket(8888);
        //2.接收客户端连接
        Socket client = server.accept();
        //3.获取输入流
        DataInputStream dis = new DataInputStream(client.getInputStream());
        //4.创建输出流
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        boolean isRunning = true;
        while (isRunning) { // 循环，达到多次收发消息
            //5.读取消息
            String msg = dis.readUTF();
            //6.转发消息
            dos.writeUTF(msg);
            dos.flush();
        }

        //7.释放资源
        dos.close();
        dis.close();
        client.close();
    }
}
