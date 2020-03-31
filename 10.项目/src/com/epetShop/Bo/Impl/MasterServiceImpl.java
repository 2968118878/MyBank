package com.epetShop.Bo.Impl;

import com.epetShop.Bo.Buyable;
import com.epetShop.Bo.MasterService;
import com.epetShop.Bo.Sellable;
import com.epetShop.Dao.impl.BillDaoImpl;
import com.epetShop.Dao.impl.MasterDaoImpl;
import com.epetShop.Dao.impl.PetDaoImpl;
import com.epetShop.Dao.impl.StoreDaoImpl;
import com.epetShop.entity.Master;
import com.epetShop.entity.Pet;
import com.epetShop.entity.Store;
import org.apache.commons.lang3.StringEscapeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MasterServiceImpl implements Buyable, Sellable, MasterService {
    Scanner in = new Scanner(System.in);
    List<Pet> pets = new ArrayList<>();
    Master master = null;
    List<Store> stores = new ArrayList<>();
    /**
     * 登录
     * @return
     */
    @Override
    public Master login() {
        System.out.print("请先登录，请您输入主人的名字：");
        String name = in.next();
        System.out.print("请您输入主人的密码：");
        String pwd = in.next();
        master = new Master();
        MasterDaoImpl md = new MasterDaoImpl();
        name = StringEscapeUtils.escapeJava(name);
        pwd = StringEscapeUtils.escapeJava(pwd);
        try {
            master.setUserName(name);
            master.setPassword(pwd);
            master = md.findByUserPassword(master);
            if(master!=null){
                System.out.println("--------恭喜您成功登录--------");
                System.out.println("--------您的基本信息--------");
                System.out.println("名字："+master.getUserName());
                System.out.println("元宝数："+master.getMoney());
            }else {
                return null;
            }
        }catch (NullPointerException e){
            System.out.println("登陆失败！");
        }
        return master;
    }

    /**
     * 购买宠物
     * @param pet
     */
    @Override
    public void buy(Pet pet) {
        System.out.println("--------请输入选择要购买范围：只输入选择项的序号--------");
        System.out.println("1:购买库存宠物");
        System.out.println("2.购买新培育宠物");
        int choose = in.nextInt();
        pets.clear();
        PetDaoImpl pd = new PetDaoImpl();
        pet.setState(choose);
        pets = pd.findByState(pet);
        if(choose == 1){
            System.out.println("--------以下是库存宠物--------");
        }else if(choose == 2){
            System.out.println("--------以下是新培育宠物--------");
        }else {
            System.out.println("请输入正确数字！");
            return;
        }
        System.out.println("序号\t宠物名称\t类型\t元宝数");
        for(int i = 0; i < pets.size();i++){
            System.out.println(i+1+"\t\t"+pets.get(i).getName()+"\t"+pets.get(i).getType()+"\t"+pets.get(i).getPrice());
        }
        System.out.println("--------请选择要购买哪个宠物，并输入选择项的序号--------");
        choose = in.nextInt();
        try{
            BillDaoImpl bd = new BillDaoImpl();
            MasterDaoImpl md = new MasterDaoImpl();
            StoreDaoImpl sd = new StoreDaoImpl();
            int result2 = md.downMoney(master,pets.get(choose-1).getPid());
            int result3 = sd.addMoney(pets.get(choose-1).getPrice(),pets.get(choose-1).getStoreName());
            int result = bd.addInfo(pets.get(choose-1).getPid(),master.getMid());
            md.buy(master,pets.get(choose-1).getPid());
            if(result == 1&&result2==1&&result3 == 1) {
                System.out.println("台账成功插入一条信息！");
            }else {
                System.out.println("插入失败！");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 卖出宠物
     */
    @Override
    public void sell(Pet pet) {
        BillDaoImpl bd = new BillDaoImpl();
        MasterDaoImpl md = new MasterDaoImpl();
        StoreDaoImpl sd = new StoreDaoImpl();
        System.out.println("请选择您要卖出的宠物");
        PetDaoImpl pd = new PetDaoImpl();
        pets.clear();
        stores.clear();
        pets = pd.findByMaster(master);
        System.out.println("序号\t宠物名称");
        for(int i = 0; i<pets.size();i++){
            System.out.println((i+1)+"\t\t"+pets.get(i).getName());
        }
        int choose = in.nextInt();
        System.out.println("请选择您要出售的商店：");
        stores = sd.findAll();
        System.out.println("序号\t商店名称");
        for(int i = 0;i<stores.size();i++){
            System.out.println((i+1)+"\t\t"+stores.get(i).getSname());
        }
        int choose2 = in.nextInt();
        try{
            int result2 = md.addMoney(master,pets.get(choose-1).getPid());
            int result3 = sd.downMoney(pets.get(choose-1).getPrice(),stores.get(choose2-1).getSname());
            int result = bd.downInfo(pets.get(choose-1).getPid(),master.getMid(),stores.get(choose2-1).getSname());
            sd.sell(stores.get(choose2-1),pets.get(choose-1).getPid());
            if(result == 1&&result2==1&&result3 == 1) {
                System.out.println("台账成功插入一条信息！");
            }else {
                System.out.println("插入失败！");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
