package com.mybank.Dao;

import com.mybank.entity.Transactions;

import java.sql.ResultSet;
import java.util.List;

public interface TransactionsDao {

    /**
     * 插入交易记录
     */
    public int add(String cardId1,double money,int type,String note);

    /**
     * 查询交易视图
     */
    public void findView();
}
