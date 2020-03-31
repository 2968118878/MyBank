package com.epetShop.Manager;

import com.epetShop.Bo.Impl.MasterServiceImpl;
import com.epetShop.Bo.Impl.StoreServiceImpl;
import com.epetShop.Dao.impl.BillDaoImpl;
import com.epetShop.Dao.impl.MasterDaoImpl;
import com.epetShop.Dao.impl.PetDaoImpl;
import com.epetShop.Dao.impl.StoreDaoImpl;
import com.epetShop.entity.Bill;
import com.epetShop.entity.Master;
import com.epetShop.entity.Pet;
import com.epetShop.entity.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 */
public class EpetShopManager {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Pet pet = new Pet();
        init();
        System.out.println("请选择登录模式：输入1为宠物主人登录，输入2为宠物商店登录");
        int choose = in.nextInt();
        switch (choose){
            case 1:
                MasterServiceImpl ms = new MasterServiceImpl();
                Master master = ms.login();
                if(master == null){
                    return;
                }
                System.out.println("登陆成功，您可以购买和卖出宠物，如果您想购买宠物清输入1，如果想卖出宠物请输入2");
                int choose2 = in.nextInt();
                switch (choose2){
                    case 1:
                        ms.buy(pet);
                        break;
                    case 2:
                        ms.sell(pet);
                        break;
                    default:
                        System.out.println("请输入正确选择！");
                        return;
                }
                break;
            case 2:
                StoreServiceImpl ss = new StoreServiceImpl();
                Store store = ss.login();
                if(store == null){
                    return;
                }
                break;
            default:
                System.out.println("请输入正确选择！");
                return;
        }

    }

    /**
     * 开始时加载
     */
    public static void init(){
        List<Pet> pets = new ArrayList<>();
        List<Master> masters = new ArrayList<>();
        List<Store> stores = new ArrayList<>();
        PetDaoImpl pd = new PetDaoImpl();
        MasterDaoImpl md = new MasterDaoImpl();
        StoreDaoImpl sd = new StoreDaoImpl();
        System.out.println("宠物商店启动！");
        System.out.println("Wonderland醒来，所有宠物从MySQL中醒来");
        System.out.println("**********************************");
        System.out.println("序号\t宠物名称");
        pets = pd.findAll();
        for(int i = 0;i<pets.size();i++){
            System.out.println(pets.get(i).getPid()+"\t\t"+pets.get(i).getName());
        }
        System.out.println("**********************************");
        System.out.println();
        System.out.println("所有宠物主人从MySQL中醒来");
        System.out.println("**********************************");
        System.out.println("序号\t主人姓名");
        masters = md.findAll();
        for(int i = 0;i<masters.size();i++){
            System.out.println(masters.get(i).getMid()+"\t\t"+masters.get(i).getUserName());
        }
        System.out.println("**********************************");
        System.out.println();
        System.out.println("所有宠物商店从MySQL中醒来");
        System.out.println("**********************************");
        System.out.println("序号\t宠物商店名称");
        stores = sd.findAll();
        for(int i = 0;i<stores.size();i++){
            System.out.println(stores.get(i).getSid()+"\t\t"+stores.get(i).getSname());
        }
        System.out.println("**********************************");
        System.out.println();
    }
}
