package com.mycompany.model;

public class Shopkeeper_registration {
    //access modifier needs to be updated when error occurs

    protected String fullName;
    protected String mobile;
    protected String email;
    protected String pwd;

    public Shopkeeper_registration(String fullName, String mobile, String email, String pwd) {
        //System.out.println("Inside constructor of Shopkeeper_registration class......");
        this.fullName = fullName;
        this.mobile = mobile;
        this.email = email;
        this.pwd = pwd;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getFullName() {
        return fullName;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getPwd() {
        return pwd;
    }

}
