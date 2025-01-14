package com.test.employee.service.dto;

import com.test.employee.service.constatnt.EmployeeDesignation;
import com.test.employee.service.constatnt.EmploymentType;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private int id;
    private String name;
    private EmploymentType employmentType;
    private EmployeeDesignation designation;
    private int noOfVacationsTaken;
    private int noOfWorkingDays;
}
