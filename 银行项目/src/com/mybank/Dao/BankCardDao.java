package com.mybank.Dao;

import com.mybank.entity.BankCard;

import java.sql.ResultSet;
import java.util.List;

/**
 * 银行卡dao接口
 */
public interface BankCardDao {
    /**
     * 转账   增加余额
     */
    public int addMoney(double money,String cardId);

    /**
     * 转账   减少余额
     */
    public int reduceMoney(double money,String cardId);

    /**
     * 登录查询
     */
    public BankCard findByUserNamePwd(String username, int pwd);

    /**
     * 挂失银行卡
     */
    public int loss(String cardId);

    /**
     * 修改客户密码
     */
    public int changeByPassword(int password,String cardId);

    /**
     * 查询本周开户信息
     */
    public List<BankCard> findByWeek();


    /**
     * 查询银行卡视图
     */
    public void findView();
}
