package com.jdc;

import com.jdc.service.EmployeeService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class Runner {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Runner.class);

        context.registerShutdownHook();

        EmployeeService employeeService = context.getBean(EmployeeService.class);

        try {
            employeeService.saveEmployeeWithoutTransaction();
        } catch (Exception e) {
            System.out.println("Exception during saving employee:" + e.getMessage());
        }
        employeeService.printEmployees();
        employeeService.deleteAllEmployees();
        System.out.println();

        try {
            employeeService.saveEmployeeWithTransaction();
        }catch (Exception e) {
            System.out.println("Exception during saving employee:" + e.getMessage());
        }
        employeeService.printEmployees();
        employeeService.deleteAllEmployees();
    }
}