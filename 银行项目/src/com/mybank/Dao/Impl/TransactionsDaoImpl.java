package com.mybank.Dao.Impl;

import com.mybank.Dao.BaseDao;
import com.mybank.Dao.TransactionsDao;
import com.mybank.entity.Transactions;

import java.sql.ResultSet;
import java.util.List;

public class TransactionsDaoImpl extends BaseDao implements TransactionsDao {

    /**
     * 添加交易记录
     * @param cardId1
     * @param money
     * @param type
     * @param note
     * @return
     */
    @Override
    public int add(String cardId1, double money, int type, String note) {
        int result = 0;
        String sql = "insert into transactions(cardid,Tdate,Tmoney,typeId,note) values(?,CURRENT_TIMESTAMP,?,?,?)";
        Object[] param = {cardId1,money,type,note};
        try{
            result = update(sql,param);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 查看视图
     * @return
     */
    @Override
    public void findView() {
        ResultSet rs = null;
        String sql = "SELECT * FROM view_transInfo";
        Object[] param = new Object[0];
        try{
            rs = query(sql,param);
            if(rs.next()){
                System.out.println("交易日期\t\t交易类型\t卡号\t\t\t\t交易金额\t备注");
                do {
                    System.out.println(rs.getDate(1)+"\t"+rs.getString(2)+"\t"+
                            rs.getString(3)+"\t"+rs.getDouble(4)+"\t"+
                            rs.getString(5));
                }while (rs.next());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
