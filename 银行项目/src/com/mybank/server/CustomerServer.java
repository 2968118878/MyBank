package com.mybank.server;

import com.mybank.entity.BankCard;
import com.mybank.entity.Customer;

/**
 * 用户接口
 */
public interface CustomerServer {
    /**
     * 登录
     * @param name
     * @param password
     * @return
     */
    public BankCard login(String name, int password);

    /**
     * 修改密码
     */
    public int updateByPassword(int password,String cardId);

    /**
     * 挂失银行卡
     */
    public int updateByloss(String cardId);

}
