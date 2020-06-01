package com.miguel.server.xml;

import com.miguel.server.server03.entity.Entity;
import com.miguel.server.server03.entity.Mapping;
import com.miguel.server.server03.web.WebContext;
import com.miguel.server.xml.servlet.Servlet;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.ArrayList;
import java.util.List;

public class XmlParser {

    public static void main(String[] args) throws Exception {
        // 1.获取解析工厂
        SAXParserFactory factory = SAXParserFactory.newInstance();
        // 2.构建解析器
        SAXParser parser = factory.newSAXParser();
        // 3.构建处理器
        // 4.加载处理器
        WebHandler handler = new WebHandler();
        // 5.解析
        parser.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream("com/miguel/server/server03/web/web.xml"), handler);
        // 6.处理数据
        WebContext context = new WebContext(handler.getEntities(), handler.getMappings());  // 读取上下文
        String className = context.getClz("/login");   // 获取类路径
        Class clazz = Class.forName(className);  // 获取类对象
        Servlet servlet = (Servlet) clazz.getConstructor().newInstance();  // 反射获取构造器，实例化
        System.out.println(servlet);  // 业务
        servlet.service(); // 业务

    }

    private static class WebHandler extends DefaultHandler {
        private Entity entity;
        private Mapping mapping;
        private List<Entity> entities = new ArrayList<Entity>();
        private List<Mapping> mappings = new ArrayList<Mapping>();
        private boolean isMapping = false;
        private String tag;

        public WebHandler() {
        }

        public List<Entity> getEntities() {
            return entities;
        }

        public List<Mapping> getMappings() {
            return mappings;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (null != qName) {
                tag = qName;
            }
            if ("servlet".equals(tag)) {
                entity = new Entity();
                isMapping = false;
            } else if ("servlet-mapping".equals(tag)) {
                mapping = new Mapping();
                isMapping = true;
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            if (length > 0) {
                String content = new String(ch, start, length);
                if (isMapping) {
                    if ("servlet-name".equals(tag)) {
                        mapping.setName(content);
                    }
                    if ("url-pattern".equals(tag)) {
                        mapping.addPattern(content);
                    }
                } else {
                    if ("servlet-name".equals(tag)) {
                        entity.setName(content);
                    }
                    if ("servlet-class".equals(tag)) {
                        entity.setClz(content);
                    }
                }
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if ("servlet".equals(qName)) {
                entities.add(entity);
            } else if ("servlet-mapping".equals(qName)) {
                mappings.add(mapping);
            }
            tag = null;
        }
    }

}
