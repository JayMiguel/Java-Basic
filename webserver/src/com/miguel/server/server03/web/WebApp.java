package com.miguel.server.server03.web;

import com.miguel.server.server03.servlet.Servlet;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class WebApp {

    private static WebContext context;

    static {
        // 1.获取解析工厂
        SAXParserFactory factory = SAXParserFactory.newInstance();
        // 2.构建解析器
        SAXParser parser = null;
        try {
            parser = factory.newSAXParser();
            // 3.构建处理器
            // 4.加载处理器
            WebHandler handler = new WebHandler();
            // 5.解析
            parser.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream("com/miguel/server/server03/web/web.xml"), handler);
            // 6.获取数据
            context = new WebContext(handler.getEntities(), handler.getMappings());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Servlet getServletByUrl(String url) {
        String className = context.getClz("/" + url);
        Class clz = null;
        try {
            clz = Class.forName(className);
            Servlet servlet = (Servlet) clz.getConstructor().newInstance();
            return servlet;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
