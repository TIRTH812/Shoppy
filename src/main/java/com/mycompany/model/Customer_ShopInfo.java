package com.mycompany.model;

public class Customer_ShopInfo 
{
    protected int shopID;
    protected String cutomerID;
    protected String status;

    public Customer_ShopInfo(int shopID, String cutomerID, String status) 
    {
        this.shopID = shopID;
        this.cutomerID = cutomerID;
        this.status = status;
    }

    public void setShopID(int shopID) {
        this.shopID = shopID;
    }

    public void setCutomerID(String cutomerID) {
        this.cutomerID = cutomerID;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getShopID() {
        return shopID;
    }

    public String getCutomerID() {
        return cutomerID;
    }

    public String getStatus() {
        return status;
    }
}