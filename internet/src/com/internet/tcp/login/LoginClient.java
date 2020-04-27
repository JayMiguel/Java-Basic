package com.internet.tcp.login;

import java.io.*;
import java.net.Socket;

public class LoginClient {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Socket socket = new Socket("localhost", 8888);
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        while (true) {
            boolean flag;
            System.out.println("请输入用户名：");
            String name = reader.readLine();
            System.out.println("请输入密码：");
            String pwd = reader.readLine();
            dos.writeUTF("name=" + name + "&pwd=" + pwd);
            dos.flush();
            String msg = dis.readUTF();
            System.out.println(msg);
            flag = dis.readBoolean();
            if (flag) {
                dos.close();
                dis.close();
                break;
            }
        }
        reader.close();
    }
}
