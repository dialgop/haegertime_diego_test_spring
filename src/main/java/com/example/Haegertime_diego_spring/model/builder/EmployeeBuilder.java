package com.example.Haegertime_diego_spring.model.builder;
import com.example.Haegertime_diego_spring.model.Employee;
import com.example.Haegertime_diego_spring.model.PersonalData;

public class EmployeeBuilder extends PersonalData {
    public long employeeID;
    public int role;
    public String username;
    public PersonalData personalData;
    public Boolean active = true;

    public EmployeeBuilder employeeID(long employeeID) {
        this.employeeID = employeeID;
        return this;
    }

    public EmployeeBuilder role(int role) {
        this.role = role;
        return this;
    }

    public EmployeeBuilder personalData(PersonalData personalData) {
        this.personalData = personalData;
        return this;
    }

    public EmployeeBuilder username(String username) {
        this.username = username;
        return this;
    }

    public EmployeeBuilder active(Boolean active) {
        this.active = active;
        return this;
    }

    public Employee build(){
        return new Employee(this);
    }

}
