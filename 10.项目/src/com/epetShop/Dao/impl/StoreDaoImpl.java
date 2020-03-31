package com.epetShop.Dao.impl;

import com.epetShop.Dao.BaseDao;
import com.epetShop.Dao.StoreDao;
import com.epetShop.entity.Master;
import com.epetShop.entity.Pet;
import com.epetShop.entity.Store;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 商店Dao实现类
 */
public class StoreDaoImpl extends BaseDao implements StoreDao {
    List<Store> stores = new ArrayList<>();

    /**
     * 查询所有商店
     * @return
     */
    @Override
    public List<Store> findAll() {
        openConnection();
        ResultSet rs = null;
        stores.clear();
        String sql = "select * from store";
        Object[] param = {};
        try{
            rs = mysqlResult(sql,param);
            if(rs!=null){
                while (rs.next()){
                    Store store = new Store(rs.getInt(1),rs.getString(2),rs.getInt(3)
                    ,rs.getDouble(4),rs.getString(5));
                    stores.add(store);
                }
            }else {
                System.out.println("没有找到！");
                return null;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnection(rs,null,ps,conn);
        }
        return stores;
    }

    /**
     * 根据账号密码查询商店信息
     * @param store
     * @return
     */
    @Override
    public Store findByNamePassword(Store store) {
        openConnection();
        ResultSet rs = null;
        String sql = "select * from store where sname = ? and password = ?";
        Object[] param = {store.getSname(),store.getPassword()};
        Store st = null;
        try{
            rs = mysqlResult(sql,param);
            if(rs!=null){
                while (rs.next()){
                     st = new Store(rs.getInt(1),rs.getString(2),rs.getInt(3)
                            ,rs.getDouble(4),rs.getString(5));
                }
            }else {
                System.out.println("没有找到！");
                return null;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnection(rs,null,ps,conn);
        }
        return st;
    }

    /**
     * 卖出宠物
     * @param store
     * @param petId
     * @return
     */
    @Override
    public int sell(Store store, int petId) {
        openConnection();
        String sql = "update pet SET mid = null,sid = ?,state = 1 WHERE pid = ?";
        Object[] param = {store.getSid(),petId};
        int result = 0;
        result = mysqlUpdate(sql,param);
        return result;
    }

    /**
     * 根据商店姓名查询商店信息
     */
    @Override
    public Store findByName(String name) {
        openConnection();
        ResultSet rs = null;
        String sql = "select * from store where sname = ?";
        Object[] param = {name};
        Store st = null;
        try{
            rs = mysqlResult(sql,param);
            if(rs!=null){
                while (rs.next()){
                    st = new Store(rs.getInt(1),rs.getString(2),rs.getInt(3)
                            ,rs.getDouble(4),rs.getString(5));
                }
            }else {
                System.out.println("没有找到！");
                return null;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return st;
    }

    /**
     * 卖出宠物增加余额
     */
    public int addMoney(double price,String sname) {
        int result = 0;
        Store store = findByName(sname);
        String sql2 = "update store set balance = balance + ? where sid = ?";
        Object[] param2 = {price,store.getSid()};
        result = mysqlUpdate(sql2,param2);
        return result;
    }

    /**
     * 买入宠物减少余额
     */
    @Override
    public int downMoney(double price,String sname) {
        int result = 0;
        Store store = findByName(sname);
        String sql2 = "update store set balance = balance - ? where sid = ?";
        Object[] param2 = {price,store.getSid()};
        result = mysqlUpdate(sql2,param2);
        return result;
    }
}
