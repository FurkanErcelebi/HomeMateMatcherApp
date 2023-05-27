package com.homemate.matcher.models;

public class User {

    public final String id;
    public final String content;
    public final String details;
    private  String firstName;
    private  String lastName;
    private  SearchStatus searchStatus;
    private  String department;
    private  Double distanceToCampus;
    private  Long hostingTime;
    private  Boolean isVerified;

    public User(String id, String content, String details){
        this.id = id;
        this.content = content;
        this.details = details;
    }

    public User(String firstName, String lastName, SearchStatus searchStatus
                , String department, Double distanceToCampus, Long hostingTime
            , Boolean isVerified, String content, String details, String id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.searchStatus = searchStatus;
        this.department = department;
        this.distanceToCampus = distanceToCampus;
        this.hostingTime = hostingTime;
        this.isVerified = isVerified;
        this.id = id;
        this.content = content;
        this.details = details;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public SearchStatus getSearchStatus() {
        return searchStatus;
    }

    public void setSearchStatus(SearchStatus searchStatus) {
        this.searchStatus = searchStatus;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Double getDistanceToCampus() {
        return distanceToCampus;
    }

    public void setDistanceToCampus(Double distanceToCampus) {
        this.distanceToCampus = distanceToCampus;
    }

    public Long getHostingTime() {
        return hostingTime;
    }

    public void setHostingTime(Long hostingTime) {
        this.hostingTime = hostingTime;
    }

    public Boolean getVerified() {
        return isVerified;
    }

    public void setVerified(Boolean verified) {
        isVerified = verified;
    }
}
