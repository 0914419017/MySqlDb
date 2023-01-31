package com.example.mysqldb;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"all"})
/*用户数据库操作类*/
/*实现用户的crud操作*/
public class UserDao extends DbOpenHelper {
    //查询所有用户的信息
    public List<Userinfo> getAllUserList() {
        List<Userinfo> list = new ArrayList<>();
        try {
            getConnection();//获得链接信息
            String sql = "select * from userinfo";
            pStmt = conn.prepareStatement(sql);
            rs = pStmt.executeQuery();
            while (rs.next()){
                Userinfo item = new Userinfo();
                item.setId(rs.getInt("id"));
                item.setUname(rs.getString("uname"));
                item.setUpass(rs.getString("upass"));
                item.setCreateDt(rs.getString("createDt"));

                list.add(item);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeAll();
        }
        return list;
    }

    /**
     * 按用户名和密码查询用户信息
     *
     * @param uname
     * @param upass
     * @return 实例
     */
    public Userinfo getUserByUnameAndUpass(String uname, String upass) {
        Userinfo item = null;
        try {
            getConnection();//获得链接信息
            String sql = "select * from userinfo where uname=? and upass=?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1,uname);
            pStmt.setString(2,upass);
            rs = pStmt.executeQuery();
            if (rs.next()){
                item = new Userinfo();
                item.setId(rs.getInt("id"));
                item.setUname(uname);
                item.setUpass(upass);
                item.setCreateDt(rs.getString("createDt"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeAll();
        }
        return item;
    }

    /**
     * 添加用户信息
     *
     * @param item 要添加的用户
     * @return int 影响的行数
     */
    public int addUser(Userinfo item) {
        int iRow = 0;
        try {
            getConnection();//获得链接信息
            String sql = "insert into userinfo(uname,upass,createDt) values(?,?,?)";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1,item.getUname());
            pStmt.setString(2,item.getUpass());
            pStmt.setString(3,item.getCreateDt());
            iRow = pStmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeAll();
        }
        return iRow;
    }

    /**
     * 修改用户信息
     *
     * @param item 要修改的用户
     * @return int 影响的行数
     */
    public int editUser(Userinfo item) {
        int iRow = 0;
        try {
            getConnection();//获得链接信息
            String sql = "update userinfo set uname=?,upass=? where id=?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1,item.getUname());
            pStmt.setString(2,item.getUpass());
            pStmt.setInt(3,item.getId());
            iRow = pStmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeAll();
        }
        return iRow;
    }

    /**
     * 根据id 删除用户信息
     * @param id 要删除的用户
     * @return  影响的行数
     */
    public int delUser(int id) {
        int iRow = 0;
        try {
            getConnection();//获得链接信息
            String sql = "delete from userinfo where id=?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1,id);
            iRow = pStmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeAll();
        }
        return iRow;
    }
}
