package com.internet.tcp.login;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class LoginServer {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8888);
        Socket client = server.accept();
        DataInputStream dis = new DataInputStream(client.getInputStream());
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        while (true) {
            boolean flag = false;
            String msg = dis.readUTF();
            String name = "";
            String pwd = "";
            String[] datas = msg.split("&");
            for (String data : datas) {
                String[] userInfo = data.split("=");
                if (userInfo[0].equals("name")) {
                    System.out.println("输入的用户名为：" + userInfo[1]);
                    name = userInfo[1];
                } else if (userInfo[0].equals("pwd")) {
                    System.out.println("输入的密码为：" + userInfo[0]);
                    pwd = userInfo[1];
                }
            }
            if (name.equals("miguel") && pwd.equals("123456")) {
                flag = true;
                dos.writeUTF("登录成功，欢迎" + name);
            } else {
                dos.writeUTF("登陆失败，用户名或密码错误！");
            }
            dos.writeBoolean(flag);
            dos.flush();
            if (flag) {
                break;
            }
        }
        dis.close();
    }
}
