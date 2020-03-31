package com.epetShop.Dao;

import com.epetShop.entity.Bill;

import java.util.List;

/**
 * 账单接口
 */
public interface BillDao {
    public List<Bill> findByMaster(Bill bill);         //根据主人编号查询账单

    public List<Bill> findByStore(Bill bill);          //根据商店编号查询账单

    public int addInfo(int pid,int mid);                      //增加账单记录
}
