package com.test.employee.service.constatnt;

public enum EmploymentType {

    HOURLY_BASED("Hourly Based Employee"),
    SALARY_BASED("Salary Based Employee");

    private String description;

    public String getDescription() {
        return this.description;
    }
    EmploymentType(String description) {
        this.description = description;
    }
}
