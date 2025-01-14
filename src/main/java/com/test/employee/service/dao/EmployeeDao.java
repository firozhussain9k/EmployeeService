package com.test.employee.service.dao;

import com.test.employee.service.constatnt.EmployeeDesignation;
import com.test.employee.service.constatnt.EmploymentType;
import com.test.employee.service.dto.Employee;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class EmployeeDao {

    private List<Employee> hourlyBasedEmployees = new ArrayList<>();
    private List<Employee> salaryBasedEmployees = new ArrayList<>();
    private List<Employee> allEmployees = new ArrayList<>();

    @PostConstruct
    public void saveEmployees() {
        //Hourly Based
        Employee hourlyBasedEmp1 = new Employee(1, "Kalyan Kumar", EmploymentType.HOURLY_BASED, EmployeeDesignation.SE, 0,0);
        Employee hourlyBasedEmp2 = new Employee(2, "Nagendra Babu", EmploymentType.HOURLY_BASED, EmployeeDesignation.SSE, 0,0);
        Employee hourlyBasedEmp3 = new Employee(3, "Mohan", EmploymentType.HOURLY_BASED, EmployeeDesignation.SE, 0,0);
        Employee hourlyBasedEmp4 = new Employee(4, "Suraj", EmploymentType.HOURLY_BASED, EmployeeDesignation.SE, 0,0);
        Employee hourlyBasedEmp5 = new Employee(5, "Keerthi", EmploymentType.HOURLY_BASED, EmployeeDesignation.LEAD, 0,0);
        Employee hourlyBasedEmp6 = new Employee(6, "Zarin Khan", EmploymentType.HOURLY_BASED, EmployeeDesignation.SE, 0,0);
        Employee hourlyBasedEmp7 = new Employee(7, "Padmakar", EmploymentType.HOURLY_BASED, EmployeeDesignation.SSE, 0,0);
        Employee hourlyBasedEmp8 = new Employee(8, "Kishore Kumar", EmploymentType.HOURLY_BASED, EmployeeDesignation.SE, 0,0);
        Employee hourlyBasedEmp9 = new Employee(9, "Pooja", EmploymentType.HOURLY_BASED, EmployeeDesignation.LEAD, 0,0);
        Employee hourlyBasedEmp10 = new Employee(10, "Amir Khan", EmploymentType.HOURLY_BASED, EmployeeDesignation.SSE, 0,0);

        hourlyBasedEmployees.add(hourlyBasedEmp1);
        hourlyBasedEmployees.add(hourlyBasedEmp2);
        hourlyBasedEmployees.add(hourlyBasedEmp3);
        hourlyBasedEmployees.add(hourlyBasedEmp4);
        hourlyBasedEmployees.add(hourlyBasedEmp5);
        hourlyBasedEmployees.add(hourlyBasedEmp6);
        hourlyBasedEmployees.add(hourlyBasedEmp7);
        hourlyBasedEmployees.add(hourlyBasedEmp8);
        hourlyBasedEmployees.add(hourlyBasedEmp9);
        hourlyBasedEmployees.add(hourlyBasedEmp10);

        //Salary Based Non Managers
        Employee salaryBasedEmp1 = new Employee(11, "Laxman", EmploymentType.SALARY_BASED, EmployeeDesignation.HR, 0,0);
        Employee salaryBasedEmp2 = new Employee(12, "Kumar Babu", EmploymentType.SALARY_BASED, EmployeeDesignation.ACCOUNTANT, 0,0);
        Employee salaryBasedEmp3 = new Employee(13, "Murali", EmploymentType.SALARY_BASED, EmployeeDesignation.SE, 0,0);
        Employee salaryBasedEmp4 = new Employee(14, "Hussain", EmploymentType.SALARY_BASED, EmployeeDesignation.SSE, 0,0);
        Employee salaryBasedEmp5 = new Employee(15, "Sajal", EmploymentType.SALARY_BASED, EmployeeDesignation.LEAD, 0,0);
        Employee salaryBasedEmp6 = new Employee(16, "Sukrit", EmploymentType.SALARY_BASED, EmployeeDesignation.SE, 0,0);
        Employee salaryBasedEmp7 = new Employee(17, "Joe", EmploymentType.SALARY_BASED, EmployeeDesignation.LEAD, 0,0);
        Employee salaryBasedEmp8 = new Employee(18, "Revathi", EmploymentType.SALARY_BASED, EmployeeDesignation.HR, 0,0);
        Employee salaryBasedEmp9 = new Employee(19, "Pratima", EmploymentType.SALARY_BASED, EmployeeDesignation.HR, 0,0);
        Employee salaryBasedEmp10 = new Employee(20, "Sudheer", EmploymentType.SALARY_BASED, EmployeeDesignation.SSE, 0,0);

        salaryBasedEmployees.add(salaryBasedEmp1);
        salaryBasedEmployees.add(salaryBasedEmp2);
        salaryBasedEmployees.add(salaryBasedEmp3);
        salaryBasedEmployees.add(salaryBasedEmp4);
        salaryBasedEmployees.add(salaryBasedEmp5);
        salaryBasedEmployees.add(salaryBasedEmp6);
        salaryBasedEmployees.add(salaryBasedEmp7);
        salaryBasedEmployees.add(salaryBasedEmp8);
        salaryBasedEmployees.add(salaryBasedEmp9);
        salaryBasedEmployees.add(salaryBasedEmp10);

        //Salary Based Managers
        Employee salaryBasedManager1 = new Employee(21, "Gourav", EmploymentType.SALARY_BASED, EmployeeDesignation.MANAGER, 0,0);
        Employee salaryBasedManager2 = new Employee(22, "Geo", EmploymentType.SALARY_BASED, EmployeeDesignation.MANAGER, 0,0);
        Employee salaryBasedManager3 = new Employee(23, "Shyam Kumar", EmploymentType.SALARY_BASED, EmployeeDesignation.MANAGER, 0,0);
        Employee salaryBasedManager4 = new Employee(24, "Kim", EmploymentType.SALARY_BASED, EmployeeDesignation.MANAGER, 0,0);
        Employee salaryBasedManager5 = new Employee(25, "Abdul Kalam", EmploymentType.SALARY_BASED, EmployeeDesignation.MANAGER, 0,0);
        Employee salaryBasedManager6 = new Employee(26, "Chandrababu", EmploymentType.SALARY_BASED, EmployeeDesignation.MANAGER, 0,0);
        Employee salaryBasedManager7 = new Employee(27, "Pawan Kalyan", EmploymentType.SALARY_BASED, EmployeeDesignation.MANAGER, 0,0);
        Employee salaryBasedManager8 = new Employee(28, "Ram Charan", EmploymentType.SALARY_BASED, EmployeeDesignation.MANAGER, 0,0);
        Employee salaryBasedManager9 = new Employee(29, "NTR", EmploymentType.SALARY_BASED, EmployeeDesignation.MANAGER, 0,0);
        Employee salaryBasedManager10 = new Employee(30, "Chiranjeevi", EmploymentType.SALARY_BASED, EmployeeDesignation.MANAGER, 0,0);

        salaryBasedEmployees.add(salaryBasedManager1);
        salaryBasedEmployees.add(salaryBasedManager2);
        salaryBasedEmployees.add(salaryBasedManager3);
        salaryBasedEmployees.add(salaryBasedManager4);
        salaryBasedEmployees.add(salaryBasedManager5);
        salaryBasedEmployees.add(salaryBasedManager6);
        salaryBasedEmployees.add(salaryBasedManager7);
        salaryBasedEmployees.add(salaryBasedManager8);
        salaryBasedEmployees.add(salaryBasedManager9);
        salaryBasedEmployees.add(salaryBasedManager10);

        allEmployees.addAll(hourlyBasedEmployees);
        allEmployees.addAll(salaryBasedEmployees);

    }

    public List<Employee> getEmployeeList(EmploymentType employmentType) {
        if(employmentType != null)
            return allEmployees.stream().filter(emp -> emp.getEmploymentType() == employmentType).collect(Collectors.toList());

        return allEmployees;
    }

    public Employee getEmployeeById(int id) {
        Optional<Employee> employeeOptional = allEmployees.stream().filter(emp -> emp.getId() == id).findFirst();
        if(employeeOptional.isPresent())
            return employeeOptional.get();

        return null;
    }

}
