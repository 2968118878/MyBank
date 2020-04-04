package com.mybank.Dao.Impl;

import com.mybank.Dao.BankCardDao;
import com.mybank.Dao.BaseDao;
import com.mybank.entity.BankCard;

import java.sql.ResultSet;
import java.util.List;

public class BankCardDaoImpl extends BaseDao implements BankCardDao{
    /**
     * 登陆查询
     * @param username
     * @param pwd
     * @return
     */
    @Override
    public BankCard findByUserNamePwd(String username, int pwd) {
        BankCard bc = null;
        String sql = "SELECT u.cName,a.cardId,a.`password`,a.currency,d.dName,a.openDate,a.openMoney,a.money,l.lossName\n" +
                "FROM card a \n" +
                "INNER JOIN customer u on a.cid = u.cid \n" +
                "INNER JOIN deposit d on a.dId = d.did\n" +
                "INNER JOIN lost l on a.loss = l.loss\n" +
                "WHERE a.cardId = ? and a.`password` = ?";
        Object[] param = {username,pwd};
        ResultSet rs = query(sql,param);
        try {
            if (rs.next()) {
                do {
                    bc = new BankCard(rs.getString(1),rs.getString(2),
                            rs.getInt(3),rs.getString(4),rs.getString(5),
                            rs.getDate(6),rs.getDouble(7),rs.getDouble(8),
                            rs.getString(9));
                }while (rs.next());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeConnection(conn,ps,rs);
        }
        return bc;
    }

    /**
     * 转账增加余额
     * @param cardId
     * @return
     */
    @Override
    public int addMoney(double money,String cardId) {
        int rs = 0;
        String sql = "UPDATE card set money = money+ ? WHERE cardId = ?";
        Object[] param = {money,cardId};
        try{
            rs = update(sql,param);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeConnection(conn,ps,null);
        }
        return rs;
    }

    /**
     * 转账减少余额
     */
    @Override
    public int reduceMoney(double money,String cardId) {
        int rs = 0;
        String sql = "UPDATE card set money = money-? WHERE cardId = ?";
        Object[] param = {money,cardId};
        try{
            rs = update(sql,param);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeConnection(conn,ps,null);
        }
        return rs;
    }

    /**
     * 更改密码
     */
    @Override
    public int changeByPassword(int password,String cardId) {
        int rs = 0;
        String sql = "UPDATE card set password = ? WHERE cardId = ?";
        Object[] param = {password,cardId};
        try{
            rs = update(sql,param);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeConnection(conn,ps,null);
        }
        return rs;
    }

    /**
     * 挂失银行卡
     * @param cardId
     * @return
     */
    @Override
    public int loss(String cardId) {
        int rs = 0;
        String sql = "UPDATE card set loss = 1 WHERE cardId = ?";
        Object[] param = {cardId};
        try{
            rs = update(sql,param);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeConnection(conn,ps,null);
        }
        return rs;
    }


    /**
     * 查看视图
     */
    @Override
    public void findView() {
        ResultSet rs = null;
        String sql = "SELECT * FROM view_cardInfo";
        Object[] param = new Object[0];
        try{
            rs = query(sql,param);
            if(rs.next()){
                System.out.println("卡号\t\t\t\t客户\t货币种类\t存款类型\t开户日期\t\t余额\t密码\t是否挂失");
                do {
                    System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+
                            rs.getString(3)+"\t\t"+rs.getString(4)+"\t"+
                            rs.getDate(5)+"\t"+rs.getDouble(6)+"\t"+
                            rs.getInt(7)+"\t"+rs.getString(8));
                }while (rs.next());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 查询本周开卡用户
     * @return
     */
    @Override
    public List<BankCard> findByWeek() {
        return null;
    }
}
