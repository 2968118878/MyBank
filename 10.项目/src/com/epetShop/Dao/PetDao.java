package com.epetShop.Dao;

import com.epetShop.entity.Master;
import com.epetShop.entity.Pet;

import java.util.List;

/**
 * 宠物Dao接口
 */
public interface PetDao {
    public List<Pet> findByMaster(Master master);       //根据主人id查询宠物

    public List<Pet> findByStore(Pet pet);        //根据商店id查询宠物信息

    public List<Pet> findByState(Pet pet);        //根据宠物是否库存查询宠物信息

    public Pet findByPid(int pid);                   //根据姓名查询宠物

    public List<Pet> findAll();                   //查询所有宠物信息
}
