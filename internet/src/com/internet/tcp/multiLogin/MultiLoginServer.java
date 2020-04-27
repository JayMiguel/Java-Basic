package com.internet.tcp.multiLogin;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiLoginServer {

    private static int count = 0;

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8888);
        boolean isRunning = true;
        while (isRunning) {
            Socket client = server.accept();
            count++;
            System.out.println(count + "个客户端建立了连接...");
            new Thread(new Channel(client)).start();
        }
        server.close();
    }

    static class Channel implements Runnable {
        private Socket client;
        private DataInputStream dis;
        private DataOutputStream dos;

        public Channel(Socket client) {
            this.client = client;
            try {
                this.dis = new DataInputStream(client.getInputStream());
                this.dos = new DataOutputStream(client.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
                release();
            }
        }

        @Override
        public void run() {
            String name = "";
            String pwd = "";
            String[] datas = receive().split("&");
            for (String data : datas) {
                String[] userInfo = data.split("=");
                if (userInfo[0].equals("name")) {
                    System.out.println("输入的用户名为：" + userInfo[1]);
                    name = userInfo[1];
                } else if (userInfo[0].equals("pwd")) {
                    System.out.println("输入的密码为：" + userInfo[1]);
                    pwd = userInfo[1];
                }
            }
            if (name.equals("miguel") && pwd.equals("123456")) {
                send("登录成功，欢迎" + name);
            } else {
                send("登陆失败，用户名或密码错误！");
            }
            release();
        }

        private String receive() {
            String msg = "";
            try {
                msg = dis.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return msg;
        }

        private void send(String msg) {
            try {
                dos.writeUTF(msg);
                dos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void release() {
            try {
                if (dos != null) {
                    dos.close();
                }
                if (dis != null) {
                    dis.close();
                }
                if (client != null) {
                    client.close();
                    count--;
                    System.out.println("1个客户端断开连接...");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
