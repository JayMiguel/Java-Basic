package com.internet.tcp.file;

import java.io.*;
import java.net.Socket;

public class FileClient {

    public static void main(String[] args) throws IOException {
        System.out.println("======客户端启动======");
        Socket client = new Socket("localhost", 8888);
        System.out.println("已连接服务器......");
        InputStream is = new BufferedInputStream(new FileInputStream("src/com/internet/tcp/temp/kid.jpg"));
        System.out.println("正在读取文件......");
        OutputStream os = new BufferedOutputStream(client.getOutputStream());
        byte[] datas = new byte[1024];
        int len = -1;
        System.out.println("正在传输文件......");
        while ((len = is.read(datas)) != -1) {
            os.write(datas, 0 ,len);
        }
        os.flush();
        os.close();
        is.close();
        System.out.println("文件传输完毕！");
        client.close();
        System.out.println("======连接已断开======");
    }
}
