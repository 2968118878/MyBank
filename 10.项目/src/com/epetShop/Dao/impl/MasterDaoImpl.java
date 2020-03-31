package com.epetShop.Dao.impl;

import com.epetShop.Dao.BaseDao;
import com.epetShop.Dao.MasterDao;
import com.epetShop.entity.Master;
import com.epetShop.entity.Pet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 主人Dao实现类
 */
public class MasterDaoImpl extends BaseDao implements MasterDao{
    List<Master> masterDaoList = new ArrayList<>();

    /**
     * 查询所有主人信息
     * @return
     */
    @Override
    public List<Master> findAll() {
        ResultSet rs = null;
        masterDaoList.clear();
        String sql = "select mid,username,password,money from master";
        Master bl = new Master();
        Object[] param = {};
        try{
            rs = mysqlResult(sql,param);
            if(rs != null){
                while (rs.next()) {
                    bl = new Master(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4));
                    masterDaoList.add(bl);
                }
            }else {
                System.out.println("没有记录！");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnection(rs,null,ps,conn);
        }
        return masterDaoList;
    }

    /**
     * 根据用户名和密码查询主人信息
     * @param master
     * @return
     */
    @Override
    public Master findByUserPassword(Master master) {
        ResultSet rs = null;
        String sql = "select mid,username,password,money from master where username = ? and password = ?";
        Master mt = null;
        Object[] param = {master.getUserName(),master.getPassword()};
         try{
            rs = mysqlResult(sql,param);
            if(rs.next()){
                mt = new Master(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4));
            }else {
                System.out.println("登录失败！");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnection(rs,null,ps,conn);
        }
        return mt;
    }

    /**
     * 买宠物   修改宠物信息
     * @param master
     */
    @Override
    public int buy(Master master,int petId) {
        openConnection();
        String sql = "update pet SET mid = ?,sid = null,state = null WHERE pid = ?";
        Object[] param = {master.getMid(),petId};
        int result = 0;
        result = mysqlUpdate(sql,param);
        return result;
    }

    /**
     * 购买宠物花费元宝
     * @param master
     * @param petId
     * @return
     */
    @Override
    public int downMoney(Master master, int petId) {
        int result = 0;
        PetDaoImpl pd = new PetDaoImpl();
        Pet pet = pd.findByPid(petId);
        String sql2 = "update master set money = money - ? where mid = ?";
        Object[] param2 = {pet.getPrice(),master.getMid()};
        result = mysqlUpdate(sql2,param2);
        return result;
    }

    /**
     * 卖出宠物获得元宝
     */
    @Override
    public int addMoney(Master master, int petId) {
        int result = 0;
        PetDaoImpl pd = new PetDaoImpl();
        Pet pet = pd.findByPid(petId);
        String sql2 = "update master set money = money + ? where mid = ?";
        Object[] param2 = {pet.getPrice(),master.getMid()};
        result = mysqlUpdate(sql2,param2);
        return result;
    }
}
