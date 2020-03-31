package com.epetShop.Dao;

import com.epetShop.entity.Master;
import com.epetShop.entity.Store;

import java.util.List;

/**
 * 商店接口
 */
public interface StoreDao {
    public Store findByNamePassword(Store store);           //根据账号密码查询宠物商店

    public List<Store> findAll();                           //查询所有商店

    public int sell(Store store, int petId);                        //卖宠物

    public int downMoney(double price,String name);        //减少账户元宝

    public int addMoney(double price,String name);           //增加账户元宝

    public Store findByName(String name);                   //根据商店名查询宠物商店
}
