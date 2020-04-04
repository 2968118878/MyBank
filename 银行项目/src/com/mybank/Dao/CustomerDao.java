package com.mybank.Dao;


import com.mybank.entity.Customer;

import java.sql.ResultSet;
import java.util.List;

public interface CustomerDao {
    /**
     * 查询所有用户
     */
    public List<Customer> findAll();

    /**
     * 查询挂失用户
     */
    public List<Customer> findByLoss();

    /**
     * 查询余额不足用户
     */
    public List<Customer> findByMoney();

    /**
     * 查询用户视图
     */
    public void findView();
}
