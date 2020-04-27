package com.internet.tcp.file;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer {

    public static void main(String[] args) throws IOException {
        System.out.println("======服务端启动======");
        ServerSocket server = new ServerSocket(8888);
        System.out.println("等待连接......");
        Socket client = server.accept();
        System.out.println("一个客户端建立连接......");
        InputStream is = new BufferedInputStream(client.getInputStream());
        System.out.println("正在接收文件......");
        OutputStream os = new BufferedOutputStream(new FileOutputStream("src/com/internet/tcp/temp/temp.png"));
        byte[] datas = new byte[1024];
        int len = -1;
        System.out.println("正在保存文件......");
        while ((len = is.read(datas)) != -1) {
            os.write(datas, 0, len);
        }
        os.flush();
        os.close();
        is.close();
        System.out.println("文件保存完毕！");
        server.close();
        System.out.println("======服务端关闭======");
    }
}
