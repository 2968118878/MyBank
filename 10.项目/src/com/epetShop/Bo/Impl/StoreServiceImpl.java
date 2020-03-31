package com.epetShop.Bo.Impl;

import com.epetShop.Bo.StoreService;
import com.epetShop.Dao.impl.MasterDaoImpl;
import com.epetShop.Dao.impl.StoreDaoImpl;
import com.epetShop.entity.Master;
import com.epetShop.entity.Pet;
import com.epetShop.entity.Store;
import org.apache.commons.lang3.StringEscapeUtils;

import java.util.List;
import java.util.Scanner;

public class StoreServiceImpl implements StoreService {
    Store store = null;
    Scanner in = new Scanner(System.in);
    @Override
    public Store login() {
        System.out.print("请先登录，请您输入宠物店的名字：");
        String name = in.next();
        System.out.print("请您输入宠物店的密码：");
        String pwd = in.next();
        store = new Store();
        StoreDaoImpl sd = new StoreDaoImpl();
        name = StringEscapeUtils.escapeJava(name);
        pwd = StringEscapeUtils.escapeJava(pwd);
        try {
            store.setSname(name);
            store.setPassword(pwd);
            store = sd.findByNamePassword(store);
            if(store!=null){
                System.out.println("--------恭喜您成功登录--------");
                System.out.println("--------宠物店的基本信息--------");
                System.out.println("名字："+store.getSname());
                System.out.println("余额："+store.getBalance());
            }else {
                return null;
            }
        }catch (NullPointerException e){
            System.out.println("登陆失败！");
        }
        return store;
    }

    @Override
    public List<Pet> getPetBreed(int storeId) {
        return null;
    }

    @Override
    public List<Pet> getPetInStock(int storeId) {
        return null;
    }

    @Override
    public void charge(Pet pet) {

    }
}
