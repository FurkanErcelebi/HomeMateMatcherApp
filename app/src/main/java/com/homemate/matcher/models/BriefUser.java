package com.homemate.matcher.models;

public class BriefUser {

    private String id;
    private String firstName;
    private String lastName;
    private SearchStatus status;

    public BriefUser(String id, String firstName, String lastName, SearchStatus status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public SearchStatus getStatus() {
        return status;
    }
}
