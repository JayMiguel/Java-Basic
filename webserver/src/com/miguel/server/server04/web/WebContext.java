package com.miguel.server.server04.web;

import com.miguel.server.server04.entity.Mapping;
import com.miguel.server.server04.entity.Entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebContext {

    private List<Entity> entities;
    private List<Mapping> mappings;
    private Map<String, String> entityMap;
    private Map<String, String> mappingMap;

    public WebContext(List<Entity> entities, List<Mapping> mappings) {
        this.entities = entities;
        this.mappings = mappings;
        entityMap = new HashMap<String, String>();
        mappingMap = new HashMap<String, String>();

        for (Entity entity : entities) {
            entityMap.put(entity.getName(), entity.getClz());
        }
        for (Mapping mapping : mappings) {
            for (String pattern : mapping.getPatterns()) {
                mappingMap.put(pattern, mapping.getName());
            }
        }
    }

    public String getClz(String pattern) {
        String name = mappingMap.get(pattern);
        return entityMap.get(name);
    }
}
