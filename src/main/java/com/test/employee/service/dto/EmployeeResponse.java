package com.test.employee.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.test.employee.service.constatnt.EmployeeDesignation;
import com.test.employee.service.constatnt.EmploymentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeResponse {
    private Integer id;
    private String name;
    private String employmentType;
    private String designation;
    private Integer vacationsTaken;
    private Integer availableVacations;
    private Integer noOfWorkingDays;
    private String message;
}
