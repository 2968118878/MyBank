package com.mybank.Manager;

import com.mybank.Dao.BaseDao;
import com.mybank.Dao.Impl.BankCardDaoImpl;
import com.mybank.Dao.Impl.CustomerDaoImpl;
import com.mybank.Dao.Impl.TransactionsDaoImpl;
import com.mybank.entity.BankCard;
import com.mybank.entity.Customer;
import com.mybank.server.CustomerServer;
import com.mybank.server.Impl.AdminServerImpl;
import com.mybank.server.Impl.CustomerServerImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Text {
    Scanner in = new Scanner(System.in);
    AdminServerImpl as = new AdminServerImpl();
    CustomerServerImpl cs = new CustomerServerImpl();
    List<BankCard> bankCards = null;
    List<Customer> customers = new ArrayList<>();
    BankCard bankCard = new BankCard();


    public static void main(String[] args) {
        Text text = new Text();
        text.init();
    }

    /**
     * 初始菜单
     */
    public void init(){
        System.out.println("*******欢迎使用银行管理系统********");
        System.out.println("请选择登录方式：1.用户登录  2.管理员登录");
        int choose = in.nextInt();
        switch (choose){
            case 1:
                customer();
                break;
            case 2:
                admin();
                break;
            default:
                System.out.println("请输入正确选择");
                break;
        }
    }

    /**
     * 用户页面
     */
    public void customer(){
        System.out.print("请输入卡号：");
        String cardId = in.next();
        System.out.print("请输入密码:");
        int password = in.nextInt();
        bankCard = cs.login(cardId,password);
        if(bankCard!=null){
            System.out.println("登陆成功！欢迎您"+bankCard.getcName()+"！");
            do {
                System.out.println("请选择您要办理的业务：");
                System.out.println("1.查询余额");
                System.out.println("2.转账");
                System.out.println("3.修改密码");
                System.out.println("4.挂失");
                System.out.println("5.退出");
                int choose = in.nextInt();
                switch (choose){
                    case 1:
                        System.out.println("您当前的余额为:"+bankCard.getMoney());
                        System.out.println("是否继续？ y/n");
                        String bool1 = in.next();
                        if(bool1.toLowerCase().equals("y")){
                            break;
                        }else {
                            return;
                        }
                    case 2:
                        System.out.println("请输入您要转账的卡号：");
                        String cardId2 = in.next();
                        System.out.println("请输入您要转入的金额：");
                        double money = in.nextDouble();
                        System.out.println("请输入您的转账备注：");
                        String note = in.next();
                        int rs0 = cs.reduce(money,bankCard.getCardId());        //减少本卡号余额
                        if(rs0!=0){
                            int rs1 = cs.add(money,cardId2);                    //增加转入卡号余额
                            if(rs1 != 0){
                                int rs2 = cs.insertInfo(bankCard.getCardId(),money,1,note);     //插入本卡交易记录
                                if(rs2 != 0){
                                    int rs3 = cs.insertInfo(cardId2,money,2,note);              //插入转入卡交易记录
                                    if(rs3!=0){
                                        System.out.println("转账成功!");
                                    }else {
                                        System.out.println("转账失败！");
                                    }
                                }else {
                                    System.out.println("转账失败！");
                                }
                            }else {
                                System.out.println("转账失败！");
                            }
                        }else {
                            System.out.println("转账失败！");
                        }
                        System.out.println("是否继续？ y/n");
                        String bool2 = in.next();
                        if(bool2.toLowerCase().equals("y")){
                            break;
                        }else {
                            return;
                        }
                    case 3:
                        System.out.println("请输入新密码：");
                        try {
                            int newpwd = in.nextInt();
                            int rs = cs.updateByPassword(newpwd, bankCard.getCardId());
                            if (rs != 0) {
                                System.out.println("修改成功!");
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                            System.out.println("修改失败");
                        }
                        System.out.println("是否继续？ y/n");
                        String bool3 = in.next();
                        if(bool3.toLowerCase().equals("y")){
                            break;
                        }else {
                            return;
                        }
                    case 4:
                        int rs = cs.updateByloss(bankCard.getCardId());
                        if(rs!=0){
                            System.out.println("办理挂失成功！");
                        }
                        System.out.println("是否继续？ y/n");
                        String bool4 = in.next();
                        if(bool4.toLowerCase().equals("y")){
                            break;
                        }else {
                            return;
                        }
                    case 5:
                        return;
                }
            }while (true);
        }else {
            System.out.println("登录失败！");
        }
    }

    /**
     * 管理员页面
     */
    public void admin(){
        bankCards = new ArrayList<>();
        System.out.println("请输入账号：");
        String name = in.next();
        System.out.println("请输入密码：");
        int pwd = in.nextInt();
        if(as.Login(name,pwd)==0){
            System.out.println("登录成功！欢迎您黄芃杰！");
            do {
                System.out.println("请选择需要执行的操作：");
                System.out.println("1.查看本周办卡用户");
                System.out.println("2.查询挂失用户");
                System.out.println("3.查询余额不足用户");
                System.out.println("4.查看所有用户");
                System.out.println("5.查看所有银行卡");
                System.out.println("6.查看所有账单");
                System.out.println("7.退出");
                int choose = in.nextInt();
                switch (choose){
                    case 1:
                        bankCards = as.Week();
                        if(bankCards == null){
                            System.out.println("本周没有办卡的用户！");
                        }else {
                            System.out.println("姓名\t卡号");
                            for (int i = 0; i < bankCards.size(); i++) {
                                System.out.println(bankCards.get(i).getcName() + "\t" + bankCards.get(i).getCardId());
                            }
                        }
                        System.out.println("是否继续？ y/n");
                        String bool1 = in.next();
                        if(bool1.toLowerCase().equals("y")){
                            break;
                        }else {
                            return;
                        }
                    case 2:
                        customers = as.Loss();
                        if(customers == null){
                            System.out.println("没有挂失的用户！");
                        }else {
                            System.out.println("姓名\t电话号码");
                            for (Customer customer : customers) {
                                System.out.println(customer.getcName() + "\t" + customer.getPhone());
                            }
                        }
                        System.out.println("是否继续？ y/n");
                        String bool2 = in.next();
                        if(bool2.toLowerCase().equals("y")){
                            break;
                        }else {
                            return;
                        }
                    case 3:
                        customers = as.Money();
                        if(customers == null){
                            System.out.println("没有余额不足的用户！");
                        }else {
                            System.out.println("姓名\t电话号码");
                            for (Customer customer : customers) {
                                System.out.println(customer.getcName() + "\t" + customer.getPhone());
                            }
                        }
                        System.out.println("是否继续？ y/n");
                        String bool3 = in.next();
                        if(bool3.toLowerCase().equals("y")){
                            break;
                        }else {
                            return;
                        }
                    case 4:
                        as.findCustomerView();
                        System.out.println("是否继续？ y/n");
                        String bool4 = in.next();
                        if(bool4.toLowerCase().equals("y")){
                            break;
                        }else {
                            return;
                        }
                    case 5:
                        as.findBankCardView();
                        System.out.println("是否继续？ y/n");
                        String bool5 = in.next();
                        if(bool5.toLowerCase().equals("y")){
                            break;
                        }else {
                            return;
                        }
                    case 6:
                        as.findTransactionsView();
                        System.out.println("是否继续？ y/n");
                        String bool6 = in.next();
                        if(bool6.toLowerCase().equals("y")){
                            break;
                        }else {
                            return;
                        }
                    case 7:
                        return;
                    default:
                        System.out.println("请输入正确选项");
                }
            }while (true);
        }else {
            System.out.println("登录失败！");
        }
    }
}
