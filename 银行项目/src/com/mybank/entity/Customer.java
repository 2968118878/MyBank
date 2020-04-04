package com.mybank.entity;

/**
 * 客户类
 */
public class Customer {
    private int cid;        //客户id
    private String cName;   //客户姓名
    private String identity;//身份证号
    private String phone;   //联系电话
    private String address; //居住地址

    /**
     * 无参方法
     */
    public Customer(){

    }

    /**
     * 带参方法
     * @param cid
     * @param cName
     * @param identity
     * @param phone
     * @param address
     */
    public Customer(int cid, String cName, String identity, String phone, String address){
        this.cid = cid;
        this.cName = cName;
        this.identity = identity;
        this.phone = phone;
        this.address = address;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
