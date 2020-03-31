package com.epetShop.Dao.impl;

import com.epetShop.Dao.BaseDao;
import com.epetShop.Dao.PetDao;
import com.epetShop.entity.Master;
import com.epetShop.entity.Pet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 宠物Dao实现类
 */
public class PetDaoImpl extends BaseDao implements PetDao{
    List<Pet> pets = new ArrayList<>();     //宠物集合
    /**
     * 查询所有宠物信息
     * @return
     */
    @Override
    public List<Pet> findAll() {
        ResultSet rs = null;
        pets.clear();
        String sql = "SELECT p.pid,p.name,p.age,p.health,p.love,p.price,\n" +
                "(SELECT sname FROM store WHERE store.sid = p.sid),\n" +
                "(SELECT userName FROM `master` WHERE `master`.mid = p.mid),\n" +
                "(SELECT tname FROM pet_type WHERE pet_type.tid = p.tid),p.state\n" +
                "from pet p";
        Object[] param = {};
        try{
            rs = mysqlResult(sql,param);
            if(rs != null){
                while (rs.next()){
                    Pet pet = new Pet(rs.getInt(1),rs.getString(2),
                            rs.getInt(3),rs.getInt(4),rs.getInt(5),
                            rs.getDouble(6),rs.getString(7),rs.getString(8),
                            rs.getString(9),rs.getInt(10));
                    pets.add(pet);
                }
            }else {
                System.out.println("无宠物信息！");
                return null;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnection(rs,null,ps,conn);
        }
        return pets;
    }

    /**
     * 根据主人信息查询宠物信息
     * @param pet
     * @return
     */
    @Override
    public List<Pet> findByMaster(Master master) {
        ResultSet rs = null;
        pets.clear();
        String sql = "SELECT p.pid,p.name,p.age,p.health,p.love,p.price,\n" +
                "(SELECT sname FROM store WHERE store.sid = p.sid),\n" +
                "(SELECT userName FROM `master` WHERE `master`.mid = p.mid),\n" +
                "(SELECT tname FROM pet_type WHERE pet_type.tid = p.tid),p.state\n" +
                "from pet p\n" +
                "where (SELECT userName FROM `master` WHERE `master`.mid = p.mid) = ?";
        Object[] param = {master.getUserName()};
        Pet pt = null;
        try{
            rs = mysqlResult(sql,param);
            if(rs!=null){
                while (rs.next()){
                    pt = new Pet(rs.getInt(1),rs.getString(2),
                            rs.getInt(3),rs.getInt(4),rs.getInt(5),
                            rs.getDouble(6),rs.getString(7),rs.getString(8),
                            rs.getString(9),rs.getInt(10));
                    pets.add(pt);
                }
            }else {
                System.out.println("无宠物信息！");
                return null;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnection(rs,null,ps,conn);
        }
        return pets;
    }

    /**
     * 根据是否是库存宠物查询宠物信息
     * @param pet
     * @return
     */
    @Override
    public List<Pet> findByState(Pet pet) {
        ResultSet rs = null;
        pets.clear();
        String sql = "SELECT p.pid,p.name,p.age,p.health,p.love,p.price,\n" +
                "(SELECT sname FROM store WHERE store.sid = p.sid),\n" +
                "(SELECT userName FROM `master` WHERE `master`.mid = p.mid),\n" +
                "(SELECT tname FROM pet_type WHERE pet_type.tid = p.tid),p.state\n" +
                "from pet p \n" +
                "Where p.state = ?";
        Object[] param = {pet.getState()};
        try{
            rs = mysqlResult(sql,param);
            if(rs!=null){
                while (rs.next()){
                    Pet pt = new Pet(rs.getInt(1),rs.getString(2),
                            rs.getInt(3),rs.getInt(4),rs.getInt(5),
                            rs.getDouble(6),rs.getString(7),rs.getString(8),
                            rs.getString(9),rs.getInt(10));
                    pets.add(pt);
                }
            }else {
                System.out.println("无宠物信息！");
                return null;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnection(rs,null,ps,conn);
        }
        return pets;
    }

    /**
     * 根据商店信息查询宠物信息
     * @param pet
     * @return
     */
    @Override
    public List<Pet> findByStore(Pet pet) {
        ResultSet rs = null;
        pets.clear();
        String sql = "SELECT p.pid,p.name,p.age,p.health,p.love,p.price,\n" +
                "(SELECT sname FROM store WHERE store.sid = p.sid),\n" +
                "(SELECT userName FROM `master` WHERE `master`.mid = p.mid),\n" +
                "(SELECT tname FROM pet_type WHERE pet_type.tid = p.tid),p.state\n" +
                "from pet p \n" +
                "Where (SELECT sname FROM store WHERE store.sid = p.sid) = ?";
        Object[] param = {pet.getStoreName()};
        try{
            rs = mysqlResult(sql,param);
            if(rs!=null){
                while (rs.next()){
                    Pet pt = new Pet(rs.getInt(1),rs.getString(2),
                            rs.getInt(3),rs.getInt(4),rs.getInt(5),
                            rs.getDouble(6),rs.getString(7),rs.getString(8),
                            rs.getString(9),rs.getInt(10));
                    pets.add(pt);
                }
            }else {
                System.out.println("无宠物信息！");
                return null;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnection(rs,null,ps,conn);
        }
        return pets;
    }

    /**
     * 根据宠物编号查询宠物
     * @param name
     * @return
     */
    @Override
    public Pet findByPid(int pid) {
        ResultSet rs = null;
        String sql = "SELECT p.pid,p.name,p.age,p.health,p.love,p.price,\n" +
                "(SELECT sname FROM store WHERE store.sid = p.sid),\n" +
                "(SELECT userName FROM `master` WHERE `master`.mid = p.mid),\n" +
                "(SELECT tname FROM pet_type WHERE pet_type.tid = p.tid),p.state\n" +
                "from pet p \n" +
                "Where p.pid = ?";
        Object[] param = {pid};
        Pet pt = null;
        try{
            rs = mysqlResult(sql,param);
            if(rs!=null){
                while (rs.next()){
                     pt = new Pet(rs.getInt(1),rs.getString(2),
                            rs.getInt(3),rs.getInt(4),rs.getInt(5),
                            rs.getDouble(6),rs.getString(7),rs.getString(8),
                            rs.getString(9),rs.getInt(10));
                }
            }else {
                System.out.println("无宠物信息！");
                return null;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnection(rs,null,ps,conn);
        }
        return pt;
    }
}
