package com.gdx.util;

import java.sql.*;

public class DBUtils {
    //获取链接

    public static Connection getConnection(){

        String drive;
        String url = "jdbc:mysql://db-space-connection.c8ohasuinryn.us-east-2.rds.amazonaws.com:3306/maozi";
        String username= "admin";
        String password="Aa12345678";
        Connection conn = null;

        try {
             conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }

    public static void closeAll(ResultSet rs, Statement sta, Connection conn){
        try {if (sta!=null)
            sta.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {if(rs!= null)
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {if(conn!= null)
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //提取对增加和删除的方法
    public static int excuteUpdate(String sql, Object ...obj){//用来接受问号具体的值

        //Class.forName("com.mysql.jdbc.Driver");
        Connection conn = null;
        PreparedStatement ps = null;
        int n = 0;
        try {
            //2.获取链接
            conn = DBUtils.getConnection();

            //3.创建会话
            ps = conn.prepareStatement(sql);

            //给？设置值
            for(int i = 0; i<=obj.length-1; i++){
                ps.setObject(i+1,obj[i]);
            }


            //4.处理结果
            n = ps.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

            DBUtils.closeAll(null, ps, conn);
        }

        return n;
    }
}
