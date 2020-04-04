package com.mybank.server;

import com.mybank.entity.BankCard;

import java.sql.ResultSet;

/**
 * 登录
 */
public interface FindView {
    /**
     * 查看银行卡视图
     * @return
     */
    public void findBankCardView();

    /**
     * 查看用户视图
     */
    public void findCustomerView();

    /**
     * 查看账单视图
     */
    public void findTransactionsView();
}
