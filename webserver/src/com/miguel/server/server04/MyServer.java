package com.miguel.server.server04;

import com.miguel.server.server04.dispatcher.Dispatcher;
import com.miguel.server.server04.entity.Request;
import com.miguel.server.server04.entity.Response;
import com.miguel.server.server04.servlet.Servlet;
import com.miguel.server.server04.web.WebApp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 可以接收请求，返回响应信息，并且封装了Request和Response
 */
public class MyServer {

    private static ServerSocket serverSocket;
    private static boolean isRunning;

    public static void main(String[] args) {
        MyServer myServer = new MyServer();
        myServer.start();
    }

    // 启动服务
    private static void start() {
        try {
            serverSocket = new ServerSocket(8888);
            isRunning = true;
            receive();
            stop();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("服务器错误......");
        }
    }

    // 接收请求
    private static void receive() {
        while (isRunning) {
            try {
                // 接收连接
                Socket client = serverSocket.accept();
                System.out.println("一个客户端建立了连接......");
                // 多线程处理
                new Thread(new Dispatcher(client)).start();
            } catch (IOException e) {
                System.out.println("客户端错误......");
                e.printStackTrace();
            }
        }
    }

    // 停止服务
    private static void stop() {
        try {
            isRunning = false;
            serverSocket.close();
            System.out.println("服务器停止......");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
