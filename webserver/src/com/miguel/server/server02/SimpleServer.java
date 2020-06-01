package com.miguel.server.server02;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * 可接收客户端请求，返回响应信息。
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
            // 1.接收连接
            Socket client = serverSocket.accept();
            // 2.获取输入流
            InputStream is = client.getInputStream();
            // 3.数据容器
            byte[] datas = new byte[1024 * 1024];
            // 4.读取数据
            int len = is.read(datas);
            // 5.处理数据
            String requestInfo = new String(datas, 0, len);
            System.out.println(requestInfo);

            // 6.构建响应内容
            StringBuilder content = new StringBuilder();
            content.append("<html>");
            content.append("<head>");
            content.append("<title>");
            content.append("服务器响应成功");
            content.append("</title>");
            content.append("</head>");
            content.append("<body>");
            content.append("响应回来了......");
            content.append("<body>");
            content.append("</html>");
            // 内容长度
            int size = content.toString().getBytes().length;
            // 常用字符
            String blank = " ";
            String CRLF = "\r\n";

            // 7.响应
            StringBuilder responseInfo = new StringBuilder();
            // 响应行 HTTP/1.1 200 OK
            responseInfo.append("HTTP/1.1").append(blank);
            responseInfo.append(200).append(blank);
            responseInfo.append("OK").append(CRLF);
            // 响应头
            responseInfo.append("Date:").append(new Date()).append(CRLF);
            responseInfo.append("Server: miguel Server/0.0.1;charset=UTF-8").append(CRLF);
            responseInfo.append("Content-type:text/html").append(CRLF);
            responseInfo.append("Content-length:").append(size).append(CRLF);
            responseInfo.append(CRLF);
            // 接上响应内容
            responseInfo.append(content.toString());
            // 创建输出流
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            bw.write(responseInfo.toString());
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("客户端错误......");
        }
    }

    // 停止服务
    private void stop() {

    }

}
