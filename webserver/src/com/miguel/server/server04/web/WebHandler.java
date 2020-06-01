package com.miguel.server.server04.web;

import com.miguel.server.server04.entity.Entity;
import com.miguel.server.server04.entity.Mapping;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class WebHandler extends DefaultHandler {
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

