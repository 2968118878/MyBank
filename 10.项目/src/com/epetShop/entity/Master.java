package com.epetShop.entity;

import java.io.Serializable;

public class Master implements Serializable {
    private static final long serialVersionUID = -7785549689194496495L; //序列化ID
    private int mid;             //主人id
    private String userName;     //用户名
    private String password;     //密码
    private double money;        //元宝数

    /**
     * 无参构造
     */
    public Master(){

    }

    /**
     * 带参构造
     */
    public Master(int mid,String userName,String password,double money){
        this.mid = mid;
        this.userName = userName;
        this.password = password;
        this.money = money;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
