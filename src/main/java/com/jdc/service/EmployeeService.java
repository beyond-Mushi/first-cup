package com.jdc.service;

import com.jdc.dao.EmployeeDao;
import com.jdc.ds.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    public void saveEmployeeWithoutTransaction(){
        System.out.println("Saving employee without transaction..");
        employeeDao.saveEmployee(new Employee(1,"John","Doe","john@gmail.com","55-55-555", Date.valueOf("2019-06-05"),70000));
    }

    @Transactional
    public void saveEmployeeWithTransaction(){
        System.out.println("Saving employee with transaction..");
        employeeDao.saveEmployee(new Employee(2,"John","Doe","john@gmail.com","55-55-555", Date.valueOf("2019-06-05"),70000));
    }

   public void printEmployees(){
        System.out.println("Printing list of employees");
        employeeDao.findAllEmployees().forEach(System.out::println);
    }

    public void deleteAllEmployees(){
        System.out.println("Deleting all employees");
        employeeDao.deleteAllEmployee();
    }

//    public void saveEmployeeWithoutTransaction(){
//        saveEmployees();
//    }

    @Transactional
    public void saveEmployeesInTransaction(){
        saveEmployees();
    }

    private void saveEmployees(){
        employeeDao
                .saveEmployee(new Employee(1,"John","Doe","john@gmail.com","55-55-555", Date.valueOf("2019-06-05"),70000));
        employeeDao
                .saveEmployee(new Employee(2,"William","Doe","john@gmail.com","55-55-555", Date.valueOf("2019-06-05"),80000));
        employeeDao
                .saveEmployee(new Employee(3,"Thomas","Doe","john@gmail.com","55-55-555", Date.valueOf("2019-06-05"),90000));
        employeeDao
                .saveEmployee(new Employee(-1,"Henry","Doe","john@gmail.com","55-55-555", Date.valueOf("2019-06-05"),100000));
        employeeDao
                .saveEmployee(new Employee(5,"Miley","Doe","john@gmail.com","55-55-555", Date.valueOf("2019-06-05"),120000));
    }
}
