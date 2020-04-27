package com.internet.tcp.multiLogin;

import java.io.*;
import java.net.Socket;

public class MultiLoginClient {

    public static void main(String[] args) throws IOException {
        System.out.println("------Client------");

        // 建立连接
        Socket client = new Socket("localhost", 8888);
        // 发送数据
        new Send(client).send();
        // 接收数据
        new Receive(client).receive();
        // 关闭连接
        client.close();
    }

    static class Send {
        private Socket client;
        private DataOutputStream dos;
        private BufferedReader console;

        public Send(Socket client) {
            try {
                this.console = new BufferedReader(new InputStreamReader(System.in));
                this.client = client;
                this.dos = new DataOutputStream(client.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public String init() {
            String msg = "";
            try {
                System.out.println("请输入用户名：");
                String name = console.readLine();
                System.out.println("请输入密码：");
                String pwd = console.readLine();
                msg = "name=" + name + "&pwd=" + pwd;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return msg;
        }

        public void send() {
            try {
                String msg = init();
                dos.writeUTF(msg);
                dos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class Receive {
        private Socket client;
        private DataInputStream dis;

        public Receive(Socket client) {
            this.client = client;
            try {
                this.dis = new DataInputStream(client.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void receive() {
            String msg = null;
            try {
                msg = dis.readUTF();
                System.out.println(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
