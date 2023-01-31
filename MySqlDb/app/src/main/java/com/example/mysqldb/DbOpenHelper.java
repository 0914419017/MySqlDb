package com.example.mysqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/*Mysql数据库的链接辅助类*/
public class DbOpenHelper {
    private static final String CLS = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://192.168.112.1:3306/bookdb?useUnicode=true&characterEncoding=utf8";
    private static final String USER = "ws";
    private static final String PWD = "123456";

    public static Connection conn;//链接对象
    public static Statement stmt;//命令集
    public static PreparedStatement pStmt;//预编译命令集
    public static ResultSet rs;//结果集

    //取得连接的对象
    public static void getConnection() {
        try {
            Class.forName(CLS);
            conn = DriverManager.getConnection(URL, USER, PWD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //关闭数据库的操作对象
    public void closeAll(){
        try {
            if (rs!= null){
                rs.close();
                rs=null;
            }if (stmt!= null){
                stmt.close();
                stmt=null;
            }if (pStmt!= null){
                pStmt.close();
                pStmt=null;
            }if (conn!= null){
                conn.close();
                conn=null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
