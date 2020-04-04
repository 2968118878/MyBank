package com.mybank.entity;

import java.util.Date;

/**
 * 银行卡实体类
 */
public class BankCard {
    private String cName;      //客户名称
    private String cardId;     //银行卡号
    private int password;      //客户密码
    private String currency;   //币种
    private String dType;           //存款类型
    private Date openDate;     //开户日期
    private double openMoney;  //开户金额
    private double money;      //余额
    private String loss;          //是否挂失

    /**
     * 无参方法
     */
    public BankCard(){

    }

    /**
     * 带参方法
     * @return
     */
    public BankCard(String cName, String cardId, int password, String currency, String dType, Date openDate, double openMoney, double money, String loss){
        this.cName = cName;
        this.cardId = cardId;
        this.password = password;
        this.currency = currency;
        this.dType = dType;
        this.openDate = openDate;
        this.openMoney = openMoney;
        this.money = money;
        this.loss = loss;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getdType() {
        return dType;
    }

    public void setdType(String dType) {
        this.dType = dType;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public double getOpenMoney() {
        return openMoney;
    }

    public void setOpenMoney(double openMoney) {
        this.openMoney = openMoney;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getLoss() {
        return loss;
    }

    public void setLoss(String loss) {
        this.loss = loss;
    }
}
