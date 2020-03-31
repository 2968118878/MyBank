package com.epetShop.Bo;

import com.epetShop.entity.Pet;
import com.epetShop.entity.Store;

import java.util.List;

/**
 * 商店接口
 */
public interface StoreService {
    public List<Pet> getPetInStock(int storeId);        //查询宠物库存

    public List<Pet> getPetBreed(int storeId);          //根据商店id查询宠物种类

    public void charge(Pet pet);                        //培育宠物

    public Store login();                               //登录
}
