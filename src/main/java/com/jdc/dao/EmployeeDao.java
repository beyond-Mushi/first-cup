package com.jdc.dao;

import com.jdc.ds.Employee;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.List;

@Repository
public class EmployeeDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Employee> findAllEmployees() {
        return jdbcTemplate.query("select employee_id, first_name,last_name,email,phone_number,hire_date,salary from employee",
                this::mapEmployee);
    }

    public void deleteAllEmployee() {
        int numberOfDeletedRow =jdbcTemplate.update("delete from employee");
        System.out.println(String.format("Employees deleted, number of deleted rows =[%d]"
                +numberOfDeletedRow));
    }

    public void saveEmployee(Employee employee) {
        if(employee.getId() < 0){
            throw new IllegalArgumentException("Employee Id has to be greater than zero.");
        }
        int numbersOfRecordsInserted=jdbcTemplate.update(
                "insert into employee(employee_id,first_name,last_name,email,phone_number,hire_date,salary)"+
                        "values (?,?,?,?,?,?,?)",
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getPhoneNumber(),
                employee.getHireDate(),
                employee.getSalary()
        );

        if(numbersOfRecordsInserted == 1){
            System.out.println(String.format("Saved Employee [%d]",
                    employee.getId()));
        }
        else{
            throw new IllegalArgumentException(String.format("Expected 1 record to be inserted" +
                            ", instead retrieved [%d] number of records inserted"
                    ,numbersOfRecordsInserted));
        }
    }

    @SneakyThrows
    public Employee mapEmployee(ResultSet resultSet, int id) {
        return new Employee(
                resultSet.getInt("employee_id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("email"),
                resultSet.getString("phone_number"),
                resultSet.getDate("hire_date"),
                resultSet.getFloat("salary")
        );
    }
}
