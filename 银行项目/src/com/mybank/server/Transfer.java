package com.mybank.server;

/**
 * 转账接口
 */
public interface Transfer {
    /**
     * 增加余额
     * @return
     */
    public int add(double money,String cardId);

    /**
     * 减少余额
     */
    public int reduce(double money,String cardId);

    /**
     * 插入交易记录
     * @param cardId1
     * @param money
     * @param type
     * @param note
     * @return
     */
    public int insertInfo(String cardId1,double money,int type,String note);
}
