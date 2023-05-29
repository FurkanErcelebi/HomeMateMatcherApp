package com.homemate.matcher.models;

public class Department {

    private String id;
    private String name;
    private String label;
    private Long code;

    public Department(String id, String name, String label, Long code) {
        this.id = id;
        this.name = name;
        this.label = label;
        this.code = code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }
}
