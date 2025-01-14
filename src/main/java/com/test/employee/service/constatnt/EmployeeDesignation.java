package com.test.employee.service.constatnt;

public enum EmployeeDesignation {

    HR("Humar Resorce"),
    SE("Software Engineer"),
    SSE("Senior Software Engineer"),
    LEAD("Team Lead"),
    ACCOUNTANT("Accountant"),
    MANAGER("Manager");

    private String description;

    public String getDescription() {
        return this.description;
    }
    EmployeeDesignation(String description) {
        this.description = description;
    }
}
