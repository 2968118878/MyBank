package com.mybank.Dao;

import com.mysql.jdbc.EscapeTokenizer;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 数据库连接类
 */
public class BaseDao {
    private static String driver;       //数据库驱动
    private static String url;         //数据库连接地址
    private static String userName;     //用户名
    private static String password;     //密码
    protected Connection conn = null;
    protected ResultSet rs = null;
    protected PreparedStatement ps = null;

    static {
        init();
    }

    private static void init(){
        String config = "baseDao.properties";
        Properties properties = new Properties();
        InputStream is = BaseDao.class.getClassLoader().getResourceAsStream(config);
        try{
            properties.load(is);
        }catch (Exception e){
            e.printStackTrace();
        }
        driver = properties.getProperty("driver");
        url = properties.getProperty("url");
        userName = properties.getProperty("userName");
        password = properties.getProperty("password");
    }

    /**
     * 数据库开启连接
     */
    public void openConnection(){
        if(conn == null){
            try{
                Class.forName(driver);
                conn = DriverManager.getConnection(url,userName,password);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 数据库关闭连接
     */
    public void closeConnection(Connection conn, PreparedStatement ps, ResultSet rs){
        try{
            if(conn!=null){
                conn.close();
            }
            if(ps!=null){
                ps.close();
            }
            if(rs!=null){
                rs.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 增删查方法
     */
    public int update(String sql,Object[] param){
        conn = null;
        openConnection();               //打开数据库
        int result = 0;
        try {
            ps = conn.prepareStatement(sql);      //加载sql语句
            for (int i = 0;i<param.length;i++){                     //遍历数组为占位符赋值
                ps.setObject((i+1),param[i]);
            }
            result = ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
        return result;
    }

    /**
     * 查询方法
     */
    public ResultSet query(String sql,Object[] param){
        conn = null;
        openConnection();       //打开数据库
        try{
            ps = conn.prepareStatement(sql);      //加载sql语句
            for(int i = 0;i<param.length;i++){
                ps.setObject((i+1),param[i]);         //遍历数组为占位符赋值
            }
            rs = ps.executeQuery();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return rs;
    }
}
