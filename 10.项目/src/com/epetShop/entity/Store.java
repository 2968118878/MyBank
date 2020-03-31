package com.epetShop.entity;

import java.io.Serializable;

/**
 *
 */
public class Store implements Serializable {
    private static final long serialVersionUID = 1514369897938237462L;  //序列化ID
    private int sid;        //商店编号
    private String sname;   //商店名称
    private int state;      //状态 1.开业 2.关门
    private double balance; //余额
    private String password;//密码

    /**
     * 无参构造
     */
    public Store(){

    }

    /**
     * 带参构造
     */
    public Store(int sid,String sname,int state,double balance,String password){
        this.sid = sid;
        this.sname = sname;
        this.state = state;
        this.balance = balance;
        this.password = password;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
