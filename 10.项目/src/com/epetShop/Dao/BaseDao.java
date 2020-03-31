package com.epetShop.Dao;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class BaseDao {
    private static String driver;       //驱动
    private static String url;          //驱动地址
    private static String username;     //用户名
    private static String password;     //密码
    public Connection conn = null;      //数据库连接
    public PreparedStatement ps = null;

    static {     //加载时赋值
        init();
    }

    //赋值方法
    private static void init() {
        Properties properties = new Properties();
        String config = "Database.properties";
        InputStream is = BaseDao.class.getClassLoader().getResourceAsStream(config);
        try {
            properties.load(is);        //加载配置文件
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver = properties.getProperty("driver");
        url = properties.getProperty("url");
        username = properties.getProperty("username");
        password = properties.getProperty("password");
    }

    //打开数据库
    public void openConnection() {
        conn = null;
        if (conn == null) {
            try {
                Class.forName(driver);
                conn = DriverManager.getConnection(url, username, password);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //关闭数据库
    public void closeConnection(ResultSet rs, Statement stat, PreparedStatement ps, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stat != null) {
                stat.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //增删改操作
    public int mysqlUpdate(String sql, Object[] param) {
        int result = 0;
        PreparedStatement ps = null;
        try {
            openConnection();           //打开数据库
            ps = conn.prepareStatement(sql);    //加载sql语句
            for (int i = 0; i < param.length; i++) {        //循环遍历参数
                ps.setObject(i + 1, param[i]);
            }
            result = ps.executeUpdate();        //执行操作
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(null,null,ps,conn);     //关闭数据库
        }
        return result;
    }

    //查询操作
    public ResultSet mysqlResult(String sql,Object[] param){
        ResultSet rs = null;
        try {
            openConnection();           //打开数据库
            ps = conn.prepareStatement(sql);    //加载sql语句
            for (int i = 0; i < param.length; i++) {        //循环遍历参数
                ps.setObject(i + 1, param[i]);
            }
            rs = ps.executeQuery();        //执行操作
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
