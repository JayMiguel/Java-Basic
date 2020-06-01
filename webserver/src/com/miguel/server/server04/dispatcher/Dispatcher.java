package com.miguel.server.server04.dispatcher;

import com.miguel.server.server04.entity.Request;
import com.miguel.server.server04.entity.Response;
import com.miguel.server.server04.servlet.Servlet;
import com.miguel.server.server04.web.WebApp;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Dispatcher implements Runnable {
    private static Socket client;
    private static Request request;
    private static Response response;

    public Dispatcher(Socket client) {
        try {
            this.client = client;
            // 封装请求
            this.request = new Request(client);
            // 封装响应
            this.response = new Response(client);
        } catch (IOException e) {
            e.printStackTrace();
            this.release();
        }
    }

    @Override
    public void run() {
        try {
            if (null == request.getUrl() || request.getUrl().equals("")) {
                InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("index.html");
                response.print(new String(is.readAllBytes()));
                response.pushToBrowser(200);
                is.close();
                return;
            }
            // 4.调用业务层
            Servlet servlet = WebApp.getServletByUrl(request.getUrl());
            if (null != servlet) {
                servlet.service(request, response);
                // 5.返回响应
                response.pushToBrowser(200);
            } else {
                InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("error.html");
                response.print(new String(is.readAllBytes()));
                response.pushToBrowser(404);
                is.close();
            }
        } catch (IOException e) {
            try {
                response.pushToBrowser(500);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        release();
    }

    public void release() {
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
