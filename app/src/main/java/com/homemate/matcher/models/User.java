package com.homemate.matcher.models;

public class User {

    public String id;
    public String content;
    public String details;
    private  String firstName;
    private  String lastName;
    private  String email;
    private  String schoolEmail;
    private  String phoneNumber;
    private  SearchStatus searchStatus;
    private  Department department;
    private  Long distanceToCampus;
    private  Long hostingTime;
    private  Boolean isVerified;

    public User(String id, String content, String details){
        this.id = id;
        this.content = content;
        this.details = details;
    }

    public User(Department department, String lastName, SearchStatus searchStatus
                , String firstName, Long distanceToCampus, Long hostingTime
            , Boolean isVerified, String email, String schoolEmail, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.searchStatus = searchStatus;
        this.department = department;
        this.distanceToCampus = distanceToCampus;
        this.hostingTime = hostingTime;
        this.isVerified = isVerified;
        this.email = email;
        this.schoolEmail = schoolEmail;
        this.phoneNumber = phoneNumber;
    }

    public User(String firstName, String lastName, SearchStatus searchStatus
                , Department department, Long distanceToCampus, Long hostingTime
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Long getDistanceToCampus() {
        return distanceToCampus;
    }

    public void setDistanceToCampus(Long distanceToCampus) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSchoolEmail() {
        return schoolEmail;
    }

    public void setSchoolEmail(String schoolEmail) {
        this.schoolEmail = schoolEmail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
