package com.miguel.server.server04.entity;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;

public class Response {
    private BufferedWriter bw;
    private StringBuilder headInfo;
    private StringBuilder content;
    private int len;
    private final String BLANK = " ";
    private final String CRLF = "\r\n";

    private Response() {
        headInfo = new StringBuilder();
        content = new StringBuilder();
        len = 0;
    }

    public Response(Socket client) {
        this();
        try {
            bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            headInfo = null;
        }
    }

    private void createHeadInfo(int code) {
        // 响应行
        headInfo.append("HTTP/1.1").append(BLANK);
        headInfo.append(code).append(BLANK);
        switch (code) {
            case 200:
                headInfo.append("OK").append(CRLF);
                break;
            case 404:
                headInfo.append("NOT FOUND").append(CRLF);
                break;
            case 505:
                headInfo.append("SERVER ERROR").append(CRLF);
                break;
        }
        // 响应头
        headInfo.append("Date:").append(new Date()).append(CRLF);
        headInfo.append("Server: miguel Server/0.0.1;charset=UTF-8").append(CRLF);
        headInfo.append("Content-type:text/html").append(CRLF);
        headInfo.append("Content-length:").append(len).append(CRLF);
        headInfo.append(CRLF);
    }

    public Response print(String info) {
        content.append(info);
        len += info.getBytes().length;
        return this;
    }

    public Response println(String info) {
        return print(info).print(CRLF);
    }

    public void pushToBrowser(int code) throws IOException {
        if (null == headInfo) {
            code = 505;
        }
        createHeadInfo(code);
        bw.append(headInfo);
        bw.append(content);
        bw.flush();
    }
}
