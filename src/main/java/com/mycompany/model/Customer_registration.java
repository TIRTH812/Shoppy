package com.mycompany.model;

public class Customer_registration {

    protected String mobile;
    protected String address;
    protected String landmark;
    protected String city;
    protected String state;
    protected String country;
    protected String pincode;
    protected String fullname;
    protected String email;
    protected String pwd;

    public Customer_registration(String mobile, String address, String landmark, String city, String state, String country, String pincode, String fullname, String email, String pwd) {
        this.mobile = mobile;
        this.address = address;
        this.landmark = landmark;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pincode = pincode;
        this.fullname = fullname;
        this.email = email;
        this.pwd = pwd;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getMobile() {
        return mobile;
    }

    public String getAddress() {
        return address;
    }

    public String getLandmark() {
        return landmark;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getPincode() {
        return pincode;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public String getPwd() {
        return pwd;
    }

}
