package com.gdx.dao;

import com.gdx.Interface.UserDAO;
import com.gdx.entity.Emp;
import com.gdx.util.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserDaoImpl implements UserDAO {

    @Override
    public Emp findEmpbyEmpno(int empno) {
        //链接数据库， 查询指定编号的员工

        //1.加载驱动
        //Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Drive load successfully");
        Connection conn = null;
        Statement sta = null;
        ResultSet rs = null;
        Emp emp = null;
        try {
            //2.获取链接
            conn = DBUtils.getConnection();

            //3.创建会话
            sta = conn.createStatement();
            rs = sta.executeQuery("select * from emp where empno="+empno);


            //4.处理结果
            if(rs.next()){
                String name = rs.getString(2);
                String job = rs.getString(3);
                int mgr = rs.getInt(4);
                java.sql.Date Date = rs.getDate(5);
                int sal = rs.getInt(6);
                int comm = rs.getInt(7);
                int deptno = rs.getInt(8);

                emp = new Emp();
                emp.setEmpno(empno);
                emp.setEname(name);
                emp.setMgr(mgr);
                emp.setJob(job);
                emp.setSal(sal);
                emp.setDeptno(deptno);
                emp.setComm(comm);
                emp.setHiredate(Date);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {

            DBUtils.closeAll(rs, sta, conn);

        }
        return emp;
    }

    @Override
    public List<Emp> findAllEmps() {
        //链接数据库， 查询所有的员工
        //1.加载驱动
        //Class.forName("com.mysql.jdbc.Driver");

        Connection conn = null;
        Statement sta = null;
        ResultSet rs = null;
        //用来装数据库中查出来的结果
        List<Emp> list = new ArrayList<Emp>();
        try {
            //2.获取链接
            conn = DBUtils.getConnection();

            //3.创建会话
            sta = conn.createStatement();
            rs = sta.executeQuery("select * from emp");


            //4.处理结果
            while(rs.next()){
                int empno = rs.getInt(1);
                String name = rs.getString(2);
                String job = rs.getString(3);
                int mgr = rs.getInt(4);
                java.sql.Date Date = rs.getDate(5);
                int sal = rs.getInt(6);
                int comm = rs.getInt(7);
                int deptno = rs.getInt(8);


                Emp emp = new Emp();
                emp = new Emp();
                emp.setEmpno(empno);
                emp.setEname(name);
                emp.setMgr(mgr);
                emp.setJob(job);
                emp.setSal(sal);
                emp.setDeptno(deptno);
                emp.setComm(comm);
                emp.setHiredate(Date);
                list.add(emp);
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }finally {
            DBUtils.closeAll(rs, sta, conn);
        }
        return list;
    }

    @Override
    public int addEmp(Emp emp) {
        String sql = "insert into emp values(?,?,?,?,?,?,?,?)";

        java.util.Date utilD = emp.getHiredate();
        java.sql.Date sqlD = new java.sql.Date(utilD.getTime());

        Object[] obj = {emp.getEmpno(),
                        emp.getEname(),
                        emp.getJob(),
                        emp.getMgr(),
                        sqlD,
                        emp.getSal(),
                        emp.getComm(),
                        emp.getDeptno()
                        };
         return DBUtils.excuteUpdate(sql,obj);

    }

    @Override
    public int deleteEmp(int empno) {
        //删除员工

        String sql = "delete from emp where empno=?";

        int n =DBUtils.excuteUpdate(sql, empno);

        return n;
    }
}
