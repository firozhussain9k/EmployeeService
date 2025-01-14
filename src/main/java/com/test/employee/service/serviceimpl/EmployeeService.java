package com.test.employee.service.serviceimpl;

import com.test.employee.service.constatnt.EmployeeConstants;
import com.test.employee.service.constatnt.EmployeeDesignation;
import com.test.employee.service.constatnt.EmploymentType;
import com.test.employee.service.dao.EmployeeDao;
import com.test.employee.service.dto.Employee;
import com.test.employee.service.dto.EmployeeRequest;
import com.test.employee.service.dto.EmployeeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class EmployeeService {

    @Autowired
    EmployeeDao employeeDao;


    public ResponseEntity<List<Employee>> getEmployeeDetails(String employmentValue) {
        EmploymentType employmentType = null;
        List<Employee> employeeList = null;
        try {
            if(employmentValue != null)
                employmentType = EmploymentType.valueOf(employmentValue);

            employeeList = employeeDao.getEmployeeList(employmentType);
            if(employeeList != null)
                return new ResponseEntity<>(employeeList, HttpStatus.OK);

        } catch(IllegalArgumentException iaex) {
            log.error("Unidentified Employment Type {}", employmentValue);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (Exception ex) {
            throw ex;
        }
        return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<EmployeeResponse> getEmployeeById(int id) {
        try {
            Employee empDao = employeeDao.getEmployeeById(id);
            if (empDao != null)
                return new ResponseEntity<>(convertIntoEmpResponse(empDao), HttpStatus.OK);

            EmployeeResponse empResponse = new EmployeeResponse();
            empResponse.setMessage("The given employee Id does not exist - "+ id);
            return new ResponseEntity<>(empResponse, HttpStatus.BAD_REQUEST);
        }  catch (Exception ex) {
           throw ex;
        }
    }

    public ResponseEntity<EmployeeResponse> updateWorkingDays( EmployeeRequest employeeRequest) {
        try {
            Employee empDao = employeeDao.getEmployeeById(employeeRequest.getId());
            EmployeeResponse empResponse = new EmployeeResponse();
            if(empDao == null) {
                empResponse.setMessage("The given employee Id does not exist - "+ employeeRequest.getId());
                return new ResponseEntity<>(empResponse, HttpStatus.BAD_REQUEST);
            }
            if(employeeRequest.getNoOfWorkingDays() <= 0) {
                empResponse.setMessage("The no of working days should be > 0 ");
                return new ResponseEntity<>(empResponse, HttpStatus.BAD_REQUEST);
            }
            if (empDao != null && !isMaxNoOFWorkingDaysExceeds(empDao.getNoOfWorkingDays(), empDao.getNoOfVacationsTaken(), employeeRequest.getNoOfWorkingDays())) {
                empDao.setNoOfWorkingDays(empDao.getNoOfWorkingDays() + employeeRequest.getNoOfWorkingDays());
                empResponse.setMessage("Working days updated successfully");
                return new ResponseEntity<>(empResponse, HttpStatus.OK);
            }
            empResponse.setId(empDao.getId());
            empResponse.setVacationsTaken(empDao.getNoOfVacationsTaken());
            empResponse.setNoOfWorkingDays(empDao.getNoOfWorkingDays());
            empResponse.setMessage("The total no of working days of the given employee will be exceeded the max limit of " + EmployeeConstants.MAX_NO_OF_WORKING_DAYS +" working days");
            return new ResponseEntity<>(empResponse, HttpStatus.BAD_GATEWAY);
        }  catch (Exception ex) {
            throw ex;
        }

    }

    public ResponseEntity<EmployeeResponse> updateVacations( EmployeeRequest employeeRequest) {
        try {
            Employee empDao = employeeDao.getEmployeeById(employeeRequest.getId());
            EmployeeResponse empResponse = new EmployeeResponse();
            if(empDao == null) {
                empResponse.setMessage("The given employee Id does not exist - "+ employeeRequest.getId());
                return new ResponseEntity<>(empResponse, HttpStatus.BAD_REQUEST);
            }
            if(employeeRequest.getVacationsTaken() <= 0) {
                empResponse.setMessage("The no of vacations should be > 0 ");
                return new ResponseEntity<>(empResponse, HttpStatus.BAD_REQUEST);
            }

            boolean isMaxNoOFWorkingDaysExceeds = isMaxNoOFWorkingDaysExceeds(empDao.getNoOfWorkingDays(), empDao.getNoOfVacationsTaken(), employeeRequest.getVacationsTaken());
            boolean isMaxNoOFVacationsExceeds = isMaxNoOFVacationsExceeds(empDao, employeeRequest.getVacationsTaken());

            if (empDao != null && !isMaxNoOFWorkingDaysExceeds && !isMaxNoOFVacationsExceeds) {
                empDao.setNoOfVacationsTaken(empDao.getNoOfVacationsTaken() + employeeRequest.getVacationsTaken());
                empResponse.setMessage("Vacations updated successfully");
                return new ResponseEntity<>(empResponse, HttpStatus.OK);
            }

            empResponse = convertIntoEmpResponse(empDao);
            if(isMaxNoOFWorkingDaysExceeds) {
                empResponse.setMessage("The total no of working days of the given employee will be exceeded the max limit of " + EmployeeConstants.MAX_NO_OF_WORKING_DAYS + " days");
            } else if (isMaxNoOFVacationsExceeds) {
                int maxLimit = 0;
                if (empDao.getEmploymentType() == EmploymentType.HOURLY_BASED)
                    maxLimit = EmployeeConstants.TOTAL_NO_VACATIONS_HOURLY_BASED;
                else if (empDao.getEmploymentType() == EmploymentType.SALARY_BASED && empDao.getDesignation() != EmployeeDesignation.MANAGER)
                    maxLimit = EmployeeConstants.TOTAL_NO_VACATIONS_SALARY_BASED;
                else if (empDao.getEmploymentType() == EmploymentType.SALARY_BASED && empDao.getDesignation() == EmployeeDesignation.MANAGER)
                    maxLimit = EmployeeConstants.TOTAL_NO_VACATIONS_SALARY_BASED_MANAGERS;
                empResponse.setMessage("The total no of vacations of the given employee will be exceeded the max limit of " + maxLimit + " vacations");
            }
            return new ResponseEntity<>(empResponse, HttpStatus.BAD_GATEWAY);
        }  catch (Exception ex) {
            throw ex;
        }

    }

    private EmployeeResponse convertIntoEmpResponse(Employee empDao) {

        EmployeeResponse empResponse = new EmployeeResponse();
        empResponse.setId(empDao.getId());
        empResponse.setName(empDao.getName());
        empResponse.setEmploymentType(empDao.getEmploymentType().getDescription());
        empResponse.setDesignation(empDao.getDesignation().getDescription());
        empResponse.setVacationsTaken(empDao.getNoOfVacationsTaken());

        int availableVacations = 0;
        if(empDao.getEmploymentType() == EmploymentType.HOURLY_BASED)
            availableVacations = EmployeeConstants.TOTAL_NO_VACATIONS_HOURLY_BASED - empDao.getNoOfVacationsTaken();
        else if(empDao.getEmploymentType() == EmploymentType.SALARY_BASED && empDao.getDesignation() != EmployeeDesignation.MANAGER)
            availableVacations = EmployeeConstants.TOTAL_NO_VACATIONS_SALARY_BASED - empDao.getNoOfVacationsTaken();
        else if(empDao.getEmploymentType() == EmploymentType.SALARY_BASED && empDao.getDesignation() == EmployeeDesignation.MANAGER)
            availableVacations = EmployeeConstants.TOTAL_NO_VACATIONS_SALARY_BASED_MANAGERS - empDao.getNoOfVacationsTaken();

        empResponse.setAvailableVacations(availableVacations);
        empResponse.setNoOfWorkingDays(empDao.getNoOfWorkingDays());

        return empResponse;
    }

    private boolean isMaxNoOFWorkingDaysExceeds(int noOfWorkingDays, int noOfVacationsTaken, int noOfDaysToSubmit) {
        if((noOfWorkingDays + noOfVacationsTaken + noOfDaysToSubmit) > EmployeeConstants.MAX_NO_OF_WORKING_DAYS)
            return true;

        return false;
    }

    private boolean isMaxNoOFVacationsExceeds(Employee empDao, int noOfVacationsToSubmit) {

        if(empDao.getEmploymentType() == EmploymentType.HOURLY_BASED
                && (empDao.getNoOfVacationsTaken() + noOfVacationsToSubmit > EmployeeConstants.TOTAL_NO_VACATIONS_HOURLY_BASED))
            return true;
        else if (empDao.getEmploymentType() == EmploymentType.SALARY_BASED && empDao.getDesignation() != EmployeeDesignation.MANAGER
                && (empDao.getNoOfVacationsTaken() + noOfVacationsToSubmit > EmployeeConstants.TOTAL_NO_VACATIONS_SALARY_BASED))
         return true;
        else if(empDao.getEmploymentType() == EmploymentType.SALARY_BASED && empDao.getDesignation() == EmployeeDesignation.MANAGER
                && (empDao.getNoOfVacationsTaken() + noOfVacationsToSubmit > EmployeeConstants.TOTAL_NO_VACATIONS_SALARY_BASED_MANAGERS))
            return true;

        return false;
    }
}
