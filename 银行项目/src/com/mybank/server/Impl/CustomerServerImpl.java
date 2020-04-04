package com.mybank.server.Impl;

import com.mybank.Dao.Impl.BankCardDaoImpl;
import com.mybank.Dao.Impl.TransactionsDaoImpl;
import com.mybank.entity.BankCard;
import com.mybank.entity.Customer;
import com.mybank.server.CustomerServer;
import com.mybank.server.Transfer;

public class CustomerServerImpl implements CustomerServer, Transfer {
    BankCardDaoImpl bd = new BankCardDaoImpl();
    TransactionsDaoImpl td = new TransactionsDaoImpl();

    /**
     * 登录
     */
    @Override
    public BankCard login(String name, int password) {
        return bd.findByUserNamePwd(name,password);
    }

    /**
     * 挂失
     */
    @Override
    public int updateByloss(String cardId) {
        return bd.loss(cardId);
    }

    /**
     * 修改密码
     */
    @Override
    public int updateByPassword(int password, String cardId) {
        return bd.changeByPassword(password,cardId);
    }

    /**
     * 增加余额
     */
    @Override
    public int add(double money, String cardId) {
        return bd.addMoney(money,cardId);
    }

    /**
     * 减少余额
     * @param money
     * @param cardId
     * @return
     */
    @Override
    public int reduce(double money, String cardId) {
        return bd.reduceMoney(money,cardId);
    }

    /**
     * 添加交易记录
     * @param cardId1
     * @param money
     * @param type
     * @param note
     * @return
     */
    @Override
    public int insertInfo(String cardId1, double money, int type, String note) {
        return td.add(cardId1,money,type,note);
    }
}
