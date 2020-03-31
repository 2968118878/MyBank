package com.epetShop.Bo;

import com.epetShop.entity.Pet;

/**
 * 卖出接口
 */
public interface Sellable {
    public void sell(Pet pet);      //卖出宠物
}
