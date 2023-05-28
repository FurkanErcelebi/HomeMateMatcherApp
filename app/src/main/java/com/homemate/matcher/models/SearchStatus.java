package com.homemate.matcher.models;

public enum SearchStatus {

    NOT_IN_SEARCH("not_in_search"),
    IN_SEARCH_OF_MATE("in_search_of_mate"),
    IN_SEARCH_OF_HOME("in_search_of_home");

    private String value;

    public String getValue() {
        return value;
    }

    SearchStatus(String value) {
        this.value = value;
    }

}
