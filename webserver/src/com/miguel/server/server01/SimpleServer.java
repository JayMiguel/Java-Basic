package com.miguel.server.server01;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 可以接收客户端请求，读取请求信息
 */
public class SimpleServer {

    private static ServerSocket serverSocket;

    public static void main(String[] args) {
        start();
        receive();
    }

    // 启动服务
    private static void start() {
        try {
            serverSocket = new ServerSocket(8888);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("服务器错误......");
        }
    }

    // 接收请求
    private static void receive() {
        try {
            Socket client = serverSocket.accept();
            InputStream is = client.getInputStream();
            byte[] datas = new byte[1024 * 1024];
            int len = is.read(datas);
            String requestInfo = new String(datas, 0, len);
            System.out.println(requestInfo);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("客户端错误......");
        }
    }

    // 停止服务
    private void stop() {

    }

}
