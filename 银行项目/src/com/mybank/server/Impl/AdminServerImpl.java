package com.mybank.server.Impl;

import com.mybank.Dao.Impl.BankCardDaoImpl;
import com.mybank.Dao.Impl.CustomerDaoImpl;
import com.mybank.Dao.Impl.TransactionsDaoImpl;
import com.mybank.entity.BankCard;
import com.mybank.entity.Customer;
import com.mybank.server.AdminServer;
import com.mybank.server.FindView;

import java.sql.ResultSet;
import java.util.List;

public class AdminServerImpl implements AdminServer, FindView {
    BankCardDaoImpl bd = new BankCardDaoImpl();
    CustomerDaoImpl cd = new CustomerDaoImpl();
    TransactionsDaoImpl td = new TransactionsDaoImpl();

    /**
     * 登录
     * @param name
     * @param password
     * @return
     */
    @Override
    public int Login(String name, int password) {
        int result = 0;
        if(name == "黄芃杰"&&password == 1234){
            result = 1;
        }
        return result;
    }

    /**
     * 查询本周办卡用户
     * @return
     */
    @Override
    public List<BankCard> Week() {
        return bd.findByWeek();
    }



    /**
     * 查询挂失用户
     * @return
     */
    @Override
    public List<Customer> Loss() {
        return cd.findByLoss();
    }

    /**
     * 查询余额不足用户
     */
    @Override
    public List<Customer> Money() {
        return cd.findByMoney();
    }

    /**
     * 查看银行卡视图
     */
    @Override
    public void findBankCardView() {
        bd.findView();
    }

    /**
     * 查看用户视图
     */
    @Override
    public void findCustomerView() {
        cd.findView();
    }

    /**
     * 查看账单视图
     */
    @Override
    public void findTransactionsView() {
        td.findView();
    }
}
