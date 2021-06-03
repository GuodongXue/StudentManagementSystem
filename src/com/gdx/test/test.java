package com.gdx.test;

import com.gdx.Interface.UserDAO;
import com.gdx.dao.UserDaoImpl;
import com.gdx.entity.Emp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        //完成四个功能
        //findOneEmp();
        //findAllEmps();
        addEmp();
        //deleteEmp();
    }
        //查询指定编号的员工信息
        public static void findOneEmp() {
            //指定员工编号
            Scanner sc = new Scanner(System.in);
            System.out.println("Please input the employee ID number");
            int eno = sc.nextInt();

            UserDAO dao = new UserDaoImpl();
            Emp emp = dao.findEmpbyEmpno(eno);

            if(emp == null){
                System.out.println("No result");
            }else{
                System.out.println(emp);
            }
        }

        public static void findAllEmps(){
            //查询所有员工
            UserDAO dao = new UserDaoImpl();
            List<Emp> list = dao.findAllEmps();
            int count = 0;
            if(list.size()==0){
                System.out.println("No result");
            }else{
                for(Emp e: list){
                    System.out.println(e.toString());
                    count++;
                }
            }
            System.out.println("The company has "+count+" employees.");
        }
        public static void addEmp(){
            //键盘录入8个信息
            Scanner sc = new Scanner(System.in);
            System.out.println("Please input the employee ID");
            int empno = sc.nextInt();

            System.out.println("Please input the employee name");
            String name = sc.next();

            System.out.println("Please input the employee position");
            String job = sc.next();

            System.out.println("Please input the leader's ID");
            int mgr = sc.nextInt();

            System.out.println("Please input the employee hire date");
            String strDate = sc.next();

            System.out.println("Please input the employee salary");
            int sal = sc.nextInt();

            System.out.println("Please input the employee comm");
            int comm = sc.nextInt();

            System.out.println("Please input the employee department");
            int deptno = sc.nextInt();

            //将信息封装成对象
            Emp emp = new Emp();

            emp.setEmpno(empno);
            emp.setEname(name);
            emp.setJob(job);
            emp.setMgr(mgr);
            emp.setSal(sal);
            emp.setDeptno(deptno);
            emp.setComm(comm);

            DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
            Date d = null;
            try {
                d = df.parse(strDate);
                emp.setHiredate(d);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            emp.setHiredate(d);

            //调用dao层
            UserDAO dao = new UserDaoImpl();
            int n = dao.addEmp(emp);

            if(n>0){
                System.out.println("Employees add successfully.");
            }else{
                System.out.println("Employees add failed.");
            }
        }

        public static void deleteEmp(){
            Scanner sc = new Scanner(System.in);
            System.out.println("Please input the employee ID you'll delete");
            int empno = sc.nextInt();

            UserDAO dao = new UserDaoImpl();
            int n = dao.deleteEmp(empno);

            if(n>0){
                System.out.println("Delete successfully");
            }else{
                System.out.println("Delete failed");
            }

        }
}
