package com.test.employee.service.controller;

import com.test.employee.service.dto.Employee;
import com.test.employee.service.dto.EmployeeRequest;
import com.test.employee.service.dto.EmployeeResponse;
import com.test.employee.service.serviceimpl.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    /**
     * Get the employee list based on employment type(HOURLY_BASED/SALARY_BASED). If no employment value receives return all
     * the employees.
     *
     * @param employmentValue
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Employee>> getEmployeesBasedOnEmploymentType(@RequestParam(name="employmentValue", required=false) String employmentValue) {
        try {
            return employeeService.getEmployeeDetails(employmentValue);
        } catch(Exception ex) {
            log.error("Exception while retrieving employee details {}" , ex.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get the employee details based on employee id.
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployById(@PathVariable int id) {
        try {
            return employeeService.getEmployeeById(id);
        } catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * It is used to update the no of working days of an employee
     *
     * @param employeeRequest
     * @return
     */
    @PutMapping("/workingdays")
    public ResponseEntity<EmployeeResponse> updateWorkingDays(@RequestBody EmployeeRequest employeeRequest) {
        try {
            return employeeService.updateWorkingDays(employeeRequest);
        } catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *  It is used to update the no of vacations of an employee
     *
     * @param employeeRequest
     * @return
     */
    @PutMapping("/vacations")
    public ResponseEntity<EmployeeResponse> updateVacations(@RequestBody EmployeeRequest employeeRequest) {
        try {
            return employeeService.updateVacations(employeeRequest);
        } catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
