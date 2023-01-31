package com.example.mysqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/*直接数据库的辅助工具*/
public class MySqlHelp {
    public static int getUserSize(){
//        以下xxx.xxx.xx.x为本机ip,bookdb为mysql的数据库名
        final String CLS = "com.mysql.jdbc.Driver";
        final String URL = "jdbc:mysql://192.168.112.1:3306/bookdb?useUnicode=true&characterEncoding=utf8";
        final String USER = "ws";
        final String PWD = "123456";

        int count = 0;//用户数量
        try {
            Class.forName(CLS);
            Connection conn = DriverManager.getConnection(URL, USER, PWD);
            String sql = "select count(1) as sl from userinfo";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                count = resultSet.getInt("sl");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
}
