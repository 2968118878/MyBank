package com.epetShop.entity;

import java.io.Serializable;

public class Pet implements Serializable {
    private static final long serialVersionUID = -1829770366682352443L;     //序列化ID
    private int pid;            //宠物id
    private String name;         //宠物昵称
    private int age;             //宠物年龄
    private int health;          //健康值
    private int love;            //爱心值
    private double price;         //价格
    private String storeName;     //商店名
    private String masterName;    //主人名称
    private String type;          //宠物类型
    private int state;            //库存宠物或新培育宠物

    /**
     * 无参构造
     */
    public Pet(){

    }

    /**
     * 带参构造
     */
    public Pet(int pid,String name,int age,int health,int love,double price,String storeName,String masterName,String type,int state){
        this.pid = pid;
        this.name = name;
        this.age = age;
        this.health = health;
        this.love = love;
        this.price = price;
        this.storeName = storeName;
        this.masterName = masterName;
        this.type = type;
        this.state = state;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getLove() {
        return love;
    }

    public void setLove(int love) {
        this.love = love;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
