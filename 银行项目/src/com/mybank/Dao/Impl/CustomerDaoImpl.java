package com.mybank.Dao.Impl;

import com.mybank.Dao.BaseDao;
import com.mybank.Dao.CustomerDao;
import com.mybank.entity.Customer;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl extends BaseDao implements CustomerDao {
    /**
     * 查看视图
     * @return
     */
    @Override
    public void findView(){
        ResultSet rs = null;
        String sql = "SELECT * FROM view_userInfo";
        Object[] param = new Object[0];
        try{
            rs = query(sql,param);
            if(rs.next()){
                System.out.println("客户编号\t开户名\t身份证号\t\t\t\t电话号码\t\t\t居住地址");
                do {
                    System.out.println(rs.getInt(1)+"\t\t"+rs.getString(2)+"\t"+
                            rs.getString(3)+"\t"+rs.getString(4)+"\t"+
                            rs.getString(5));
                }while (rs.next());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 查询所有客户
     */
    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        String sql = "select * from customer";
        Customer cs = null;
        Object[] param = new Object[0];
        ResultSet rs = null;
        try{
            rs = query(sql,param);
            if(rs.next()){
                do{
                    cs = new Customer(rs.getInt(1),rs.getString(2),rs.getString(3),
                            rs.getString(4),rs.getString(5));
                    customers.add(cs);
                }while (rs.next());
            }else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeConnection(conn,ps,rs);
        }
        return customers;
    }

    /**
     * 查询挂失用户
     */
    @Override
    public List<Customer> findByLoss() {
        List<Customer> customers = new ArrayList<>();
        String sql = "select u.cid,u.cName,u.identity,u.phone,u.address\n" +
                "from customer u\n" +
                "INNER JOIN card a on u.cid = a.cid\n" +
                "where a.loss = 1";
        Customer cs = null;
        Object[] param = new Object[0];
        ResultSet rs = null;
        try{
            rs = query(sql,param);
            if(rs.next()){
                do{
                    cs = new Customer(rs.getInt(1),rs.getString(2),rs.getString(3),
                            rs.getString(4),rs.getString(5));
                    customers.add(cs);
                }while (rs.next());
            }else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeConnection(conn,ps,rs);
        }
        return customers;
    }

    /**
     * 查询余额不足用户
     */
    @Override
    public List<Customer> findByMoney() {
        List<Customer> customers = new ArrayList<>();
        String sql = "select u.cid,u.cName,u.identity,u.phone,u.address\n" +
                "from customer u\n" +
                "INNER JOIN card a on u.cid = a.cid\n" +
                "where a.money < 200";
        Customer cs = null;
        Object[] param = new Object[0];
        ResultSet rs = null;
        try{
            rs = query(sql,param);
            if(rs.next()){
                do{
                    cs = new Customer(rs.getInt(1),rs.getString(2),rs.getString(3),
                            rs.getString(4),rs.getString(5));
                    customers.add(cs);
                }while (rs.next());
            }else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeConnection(conn,ps,rs);
        }
        return customers;
    }
}
