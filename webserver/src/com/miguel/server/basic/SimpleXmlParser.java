package com.miguel.server.basic;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class SimpleXmlParser {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        //1.获取解析工厂
        SAXParserFactory factory = SAXParserFactory.newInstance();
        //2.获取解析器
        SAXParser parser = factory.newSAXParser();
        //3.构建处理器
        //4.加载处理器
        PersonHandler handler = new PersonHandler();
        //4.解析
        parser.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream("com/miguel/server/basic/person.xml"),handler);

    }

    private static class PersonHandler extends DefaultHandler {
        @Override
        public void startDocument() throws SAXException {
            System.out.println("------解析文档开始------");
        }

        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            System.out.println(qName + "--->解析开始");
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String content = new String(ch, start, length);
            if (content.length() > 0) {
                System.out.println("内容为：" + content);
            } else {
                System.out.println("内容为：");
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            System.out.println(qName + "--->解析结束");
        }

        @Override
        public void endDocument() throws SAXException {
            System.out.println("------解析文档结束------");
        }
    }
}
