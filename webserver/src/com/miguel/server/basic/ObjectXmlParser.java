package com.miguel.server.basic;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ObjectXmlParser {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        //1.获取解析工厂
        SAXParserFactory factory = SAXParserFactory.newInstance();
        //2.获取解析器
        SAXParser parser = factory.newSAXParser();
        //3.构建处理器
        //4.加载处理器
        PersonHandler handler = new PersonHandler();
        //5.解析
        parser.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream("com/miguel/server/basic/person.xml"), handler);
        //6.获取数据
        List<Person> persons = handler.getPersons();
        for (Person person : persons) {
            System.out.println(person);
        }
    }

    private static class PersonHandler extends DefaultHandler {

        private List<Person> persons;
        private Person person;
        private String tag; //存储当前操作的标签名

        public List<Person> getPersons() {
            return persons;
        }

        @Override
        public void startDocument() throws SAXException {
            persons = new ArrayList<Person>();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (null != qName) {
                tag = qName;
            }
            if ("person".equals(tag)) {
                person = new Person();
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String content = new String(ch, start, length).trim();
            if ("name".equals(tag)) {
                person.setName(content);
            }
            if ("age".equals(tag)) {
                person.setAge(Integer.valueOf(content));
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if ("person".equals(qName)) {
                persons.add(person);
            }
            tag = null;
        }

        @Override
        public void endDocument() throws SAXException {
        }
    }
}
