package com.miguel.server.server03;

import com.miguel.server.server03.entity.Request;
import com.miguel.server.server03.entity.Response;
import com.miguel.server.server03.servlet.Servlet;
import com.miguel.server.server03.web.WebApp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 可以接收请求，返回响应信息，并且封装了Request和Response
 */
public class MyServer {

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
            // 1.接收连接
            Socket client = serverSocket.accept();
            System.out.println("一个客户端建立了连接......");
            // 2.获取请求协议
            Request request = new Request(client);
            // 3.获取响应协议
            Response response = new Response(client);
            // 4.调用业务层
            Servlet servlet = WebApp.getServletByUrl(request.getUrl());
            if (null != servlet) {
                servlet.service(request, response);
                // 5.返回响应
                response.pushToBrowser(200);
            } else {
                response.pushToBrowser(404);
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("客户端错误......");
        }
    }

    // 停止服务
    private void stop() {

    }

}
