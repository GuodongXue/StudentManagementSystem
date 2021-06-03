package com.gdx.Interface;

import com.gdx.entity.Emp;

import java.util.List;

public interface UserDAO {

    //1.查看指定编号的员工
    Emp findEmpbyEmpno(int empno);

    //2. 查询所有员工
    List<Emp> findAllEmps();

    //3.增加员工
    int addEmp(Emp emp);

    //4.删除员工
    int deleteEmp(int empno);
}
