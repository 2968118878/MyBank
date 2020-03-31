package com.epetShop.Dao;

import com.epetShop.entity.Master;

import java.util.List;

/**
 * 主人Dao接口
 */
public interface MasterDao {
    public Master findByUserPassword(Master master);        //根据用户名和密码查询用户

    public List<Master> findAll();                          //查询所有用户

    public int buy(Master master,int petId);                         //买宠物

    public int downMoney(Master master,int petId);        //减少账户元宝

    public int addMoney(Master master,int petId);           //增加账户元宝
}
