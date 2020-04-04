package com.mybank.entity;

import java.util.Date;

/**
 * 账单类
 */
public class Transactions {
    private int tsid;       //交易编号
    private String cardid;  //卡号
    private Date tDate;     //交易日期
    private double tMoney;  //交易金额
    private String type;     //交易类型
    private String note;    //备注

    public int getTsid() {
        return tsid;
    }

    public void setTsid(int tsid) {
        this.tsid = tsid;
    }

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid;
    }

    public Date gettDate() {
        return tDate;
    }

    public void settDate(Date tDate) {
        this.tDate = tDate;
    }

    public double gettMoney() {
        return tMoney;
    }

    public void settMoney(double tMoney) {
        this.tMoney = tMoney;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
