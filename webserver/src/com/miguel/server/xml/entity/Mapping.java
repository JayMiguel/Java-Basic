package com.miguel.server.xml.entity;

import java.util.HashSet;
import java.util.Set;

public class Mapping {

    private String name;
    private Set<String> patterns = new HashSet<String>();

    public Mapping() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getPatterns() {
        return patterns;
    }

    public void setPatterns(Set<String> patterns) {
        this.patterns = patterns;
    }

    public void addPattern(String pattern) {
        patterns.add(pattern);
    }

    @Override
    public String toString() {
        return "Mapping{" +
                "name='" + name + '\'' +
                ", patterns=" + patterns +
                '}';
    }
}
