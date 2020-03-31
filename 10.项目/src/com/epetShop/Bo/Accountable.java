package com.epetShop.Bo;

import com.epetShop.entity.Bill;

import java.util.List;

/**
 * 账单
 */
public interface Accountable {
    public List<Bill> getAccount(int storeId);      //根据商店编号返回账单信息
}
