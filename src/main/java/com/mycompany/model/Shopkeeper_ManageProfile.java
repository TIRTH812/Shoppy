package com.mycompany.model;

public class Shopkeeper_ManageProfile {

    protected String fullName;
    protected String email;
    protected String address;
    protected String landmark;
    protected String country;
    protected String state;
    protected String city;
    protected String pincode;
    protected String shopName;
    protected String shopType;

    public Shopkeeper_ManageProfile(String fullName, String email, String address, String landmark, String country, String state, String city, String pincode, String shopName, String shopType) {
        this.fullName = fullName;
        this.email = email;
        this.address = address;
        this.landmark = landmark;
        this.country = country;
        this.state = state;
        this.city = city;
        this.pincode = pincode;
        this.shopName = shopName;
        this.shopType = shopType;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public void setShopType(String shopType) {
        this.shopType = shopType;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getLandmark() {
        return landmark;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getPincode() {
        return pincode;
    }

    public String getShopName() {
        return shopName;
    }

    public String getShopType() {
        return shopType;
    }
}
