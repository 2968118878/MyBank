package com.epetShop.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Bill implements Serializable {
    private static final long serialVersionUID = 3201756928692421808L;      //序列化ID
    private Timestamp bDate;            //交易时间
    private double bMoney;              //交易金额
    private String type;                //交易类型
    private String storeName;           //商店名称
    private String petName;             //宠物昵称
    private String masterName;          //主人名称

    /**
     * 无参构造
     */
    public Bill(){

    }

    /**
     * 带参构造
     */
    public Bill(Timestamp bDate,double bMoney,String type,String storeName,String petName,String masterName){
        this.bDate = bDate;
        this.bMoney = bMoney;
        this.type = type;
        this.storeName = storeName;
        this.petName = petName;
        this.masterName = masterName;
    }

    public Timestamp getbDate() {
        return bDate;
    }

    public void setbDate(Timestamp bDate) {
        this.bDate = bDate;
    }

    public double getbMoney() {
        return bMoney;
    }

    public void setbMoney(double bMoney) {
        this.bMoney = bMoney;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }
}
