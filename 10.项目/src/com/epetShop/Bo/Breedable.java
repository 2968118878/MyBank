package com.epetShop.Bo;

import com.epetShop.entity.Pet;

/**
 * 
 */
public interface Breedable {
    public Pet breed(String petType);       //根据宠物类型返回宠物
}
