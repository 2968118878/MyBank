package com.mybank.server;

import com.mybank.entity.BankCard;
import com.mybank.entity.Customer;

import java.util.List;

public interface AdminServer {
    /**
     * 登录
     * @param name
     * @param password
     */
    public int Login(String name,int password);

    /**
     * 查询本周开卡用户
     */
    public List<BankCard> Week();


    /**
     * 查询挂失用户
     */
    public List<Customer> Loss();

    /**
     * 查询余额不足用户
     */
    public List<Customer> Money();
}
