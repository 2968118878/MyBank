package com.epetShop.Dao.impl;

import com.epetShop.Dao.BaseDao;
import com.epetShop.Dao.BillDao;
import com.epetShop.entity.Bill;
import com.epetShop.entity.Pet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 账单Dao实现类
 */
public class BillDaoImpl extends BaseDao implements BillDao {
    List<Bill> billList = new ArrayList<Bill>();

    /**
     * 根据主人姓名查询账单
     *
     * @param bill
     * @return
     */
    @Override
    public List<Bill> findByMaster(Bill bill) {
        ResultSet rs = null;
        billList.clear();
        String sql = "SELECT b.bDate,b.bMoney,\n" +
                "(SELECT tname from bill_type WHERE b.tid = bill_type.tid),\n" +
                "(SELECT sname from store WHERE b.sid = store.sid),\n" +
                "(SELECT name from pet WHERE b.pid = pet.pid),\n" +
                "(SELECT userName from `master` WHERE `master`.mid = b.mid)\n" +
                "FROM bill b\n" +
                "Where (SELECT userName from `master` WHERE `master`.mid = b.mid) = ?";
        Bill bl = null;
        Object[] param = {bill.getMasterName()};
        try {
            rs = mysqlResult(sql,param);
            if (rs!=null) {
                while (rs.next()) {
                    bl = new Bill(rs.getTimestamp(1), rs.getDouble(2), rs.getString(3)
                            , rs.getString(4), rs.getString(5), rs.getString(6));
                    billList.add(bl);
                }
            } else {
                System.out.println("没有记录！");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(rs, null, ps, conn);
        }
        return billList;
    }

    /**
     * 根据商店名称查询账单
     *
     * @param bill
     * @return
     */
    @Override
    public List<Bill> findByStore(Bill bill) {
        ResultSet rs = null;
        billList.clear();
        String sql = "SELECT b.bDate,b.bMoney,\n" +
                "(SELECT tname from bill_type WHERE b.tid = bill_type.tid),\n" +
                "(SELECT sname from store WHERE b.sid = store.sid),\n" +
                "(SELECT name from pet WHERE b.pid = pet.pid),\n" +
                "(SELECT userName from `master` WHERE `master`.mid = b.mid)\n" +
                "FROM bill b " +
                "Where (SELECT sname from store WHERE b.sid = store.sid) = ?";
        Bill bl = null;
        Object[] param = {bill.getStoreName()};
        try {
            rs = mysqlResult(sql, param);
            if (rs!=null) {
                while (rs.next()) {
                    bl = new Bill(rs.getTimestamp(1), rs.getDouble(2), rs.getString(3)
                            , rs.getString(4), rs.getString(5), rs.getString(6));
                    billList.add(bl);
                }
            } else {
                System.out.println("没有记录！");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnection(rs,null,ps,conn);
        }
        return billList;
    }

    /**
     * 增加购买账单记录
     */
    @Override
    public int addInfo(int pid,int mid) {
        openConnection();
        PetDaoImpl pd = new PetDaoImpl();
        Pet pet = pd.findByPid(pid);          //查询交易前宠物信息
        int result = 0;
        if(pet!=null) {
            int type = 2;
            if(pet.getMasterName()==null){
                type = 1;
            }
            String sql = "Insert into bill values(CURTIME(),?,?,(SELECT sid FROM store WHERE sname = ?),?,?)";
            Object[] param = {pet.getPrice(),type,pet.getStoreName(),pet.getPid(),mid};
            result = mysqlUpdate(sql,param);
        }else {
            return 0;
        }
        return result;
    }

    /**
     * 卖出插入账单
     */
    public int downInfo(int pid,int mid,String sname) {
        openConnection();
        PetDaoImpl pd = new PetDaoImpl();
        Pet pet = pd.findByPid(pid);          //查询交易前宠物信息
        int result = 0;
        if(pet!=null) {
            int type = 2;
            if(pet.getMasterName()==null){
                type = 1;
            }
            String sql = "Insert into bill values(CURTIME(),?,?,(SELECT sid FROM store WHERE sname = ?),?,?)";
            Object[] param = {pet.getPrice(),type,sname,pet.getPid(),mid};
            result = mysqlUpdate(sql,param);
        }else {
            return 0;
        }
        return result;
    }
}
