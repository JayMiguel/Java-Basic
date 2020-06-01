package com.miguel.server.server03.entity;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.*;

public class Request {

    // 协议信息
    private String requestInfo;
    // 请求方式
    private String method;
    // URL
    private String url;
    // 请求参数
    private String paramStr;
    // 参数容器
    private Map<String, List<String>> paramsMap;
    // 换行符
    private final String CRLF = "\r\n";

    public Request(Socket client) throws IOException {
        this(client.getInputStream());
    }

    private Request(InputStream is) {
        try {
            byte[] datas = new byte[1024 * 1024];
            int len = is.read(datas);
            requestInfo = new String(datas, 0, len);
            paramsMap = new HashMap<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
        parseRequestInfo();
    }

    private void parseRequestInfo() {
        System.out.println("------分解------");
        System.out.println("---1.获取请求方式---");
        method = requestInfo.substring(0, requestInfo.indexOf(" ")).toLowerCase();
        System.out.println(method);
        System.out.println("---2.获取URL---");
        int start = requestInfo.indexOf("/") + 1;
        int end = requestInfo.indexOf("HTTP/") - 1;
        url = requestInfo.substring(start, end);
        int queryIdx = url.indexOf("?");
        if (queryIdx >= 0) {
            String[] urlArray = url.split("\\?");
            url = urlArray[0];
            paramStr = urlArray[1];
        }
        System.out.println(url);
        System.out.println(paramStr);
        System.out.println("---3.获取请求数据，需判断请求方式---");
        if (method.equalsIgnoreCase("post")) {
            String datas = requestInfo.substring(requestInfo.lastIndexOf(CRLF)).trim();
            if (paramStr != null) {
                paramStr += "&" + datas;
            } else {
                paramStr = datas;
            }
        }
        paramStr = null == paramStr ? "" : paramStr;
        System.out.println(method + "-->" + url + "-->" + paramStr);
        System.out.println("---4.参数存放到Map中---");
        convertToMap();
    }

    private void convertToMap() {
        String[] paramsArr = paramStr.split("&");
        for (String keyVal : paramsArr) {
            String[] kv = keyVal.split("=");
            kv = Arrays.copyOf(kv, 2);
            String key = kv[0];
            String value = kv[1] == null ? null : decode(kv[1], "utf-8");
            if (!paramsMap.containsKey(key)) {
                paramsMap.put(key, new ArrayList<String>());
            }
            paramsMap.get(key).add(value);
        }
    }

    public String getParameter(String key) {
        String[] values = getValues(key);
        return values == null ? null : values[0];
    }

    public String[] getValues(String key) {
        List<String> values = paramsMap.get(key);
        if (null == values || values.size() < 1) {
            return null;
        }
        return values.toArray(new String[0]);
    }

    private String decode(String value, String enc) {
        try {
            return java.net.URLDecoder.decode(value, enc);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public String getParamStr() {
        return paramStr;
    }
}
