package com.internet.tcp.chat.thread;

import com.internet.tcp.chat.utils.ChatUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {

    private static CopyOnWriteArrayList<Channel> all = new CopyOnWriteArrayList<Channel>();

    public static void main(String[] args) throws IOException {
        System.out.println("------Server------");
        ServerSocket server = new ServerSocket(8888);
        while (true) {
            Socket client = server.accept();
            System.out.println("一个客户端建立了连接......");
            Channel c = new Channel(client);
            all.add(c);
            new Thread(c).start();
        }
    }

    static class Channel implements Runnable {
        private Socket client;
        private DataInputStream dis;
        private DataOutputStream dos;
        private boolean isRunning;
        private String name;

        public Channel(Socket client) {
            try {
                this.client = client;
                this.dis = new DataInputStream(client.getInputStream());
                this.dos = new DataOutputStream(client.getOutputStream());
                this.isRunning = true;
                this.name = receive();
                this.send("欢迎你的到来！");
                this.sendOthers(this.name + "进入聊天室。", true);
            } catch (IOException e) {
                release();
            }
        }

        public String receive() {
            String msg = "";
            try {
                msg = dis.readUTF();
            } catch (IOException e) {
                release();
            }
            return msg;
        }

        public void send(String msg) {
            try {
                dos.writeUTF(msg);
                dos.flush();
            } catch (IOException e) {
                release();
            }
        }

        public void sendOthers(String msg, boolean isSys) {
            boolean isPrivate = msg.startsWith("@");
            if (isPrivate) {
                int idx = msg.indexOf(":");
                String target = msg.substring(1, idx);
                msg = msg.substring(idx);
                for (Channel other : all) {
                    if (other.name.equals(target)) {
                        other.send(this.name + "悄悄对你说：" + msg);
                        break;
                    }
                }
            } else {
                for (Channel other : all) {
                    if (other == this) {
                        continue;
                    }
                    if (!isSys) {
                        other.send(this.name + ": " + msg);
                    } else {
                        other.send(msg);
                    }
                }
            }
        }

        public void release() {
            this.isRunning = false;
            ChatUtils.close(client, dis, dos);
            all.remove(this);
            sendOthers(this.name + "离开聊天室。", true);
        }

        @Override
        public void run() {
            while (isRunning) {
                String msg = receive();
                if (!msg.equalsIgnoreCase("")) {
                    sendOthers(msg, false);
                }
            }
        }
    }

}
