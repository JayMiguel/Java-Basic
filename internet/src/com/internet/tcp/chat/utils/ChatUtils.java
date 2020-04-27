package com.internet.tcp.chat.utils;

import java.io.Closeable;
import java.io.IOException;

public class ChatUtils {

    public static void close(Closeable... targets) {
        for (Closeable target : targets) {
            if (target != null) {
                try {
                    target.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
