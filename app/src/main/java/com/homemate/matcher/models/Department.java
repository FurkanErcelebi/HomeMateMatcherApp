package com.homemate.matcher.models;

public class Department {

    private String name;
    private String label;
    private Integer code;

    public Department(String name, String label, Integer code) {
        this.name = name;
        this.label = label;
        this.code = code;
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

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
