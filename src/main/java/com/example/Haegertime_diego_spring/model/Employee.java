package com.example.Haegertime_diego_spring.model;

import com.example.Haegertime_diego_spring.model.builder.EmployeeBuilder;
import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@JsonIdentityReference(alwaysAsId = true)
public class Employee{

    @Id
    @SequenceGenerator(
            name = "employee_sequence",
            sequenceName = "employee_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "employee_sequence"
    )
    private long employeeID;
    private int role = 3;

    @NotBlank(message = "username is a mandatory field, please provide it")
    @Size(min = 2, message = "username size must be bigger than 2")
    private String username;

    @Column(columnDefinition = "boolean default true")
    private Boolean active = true;

    @Embedded
    @Valid
    private PersonalData personalData;

    public Employee(EmployeeBuilder builder){
        this.employeeID = builder.employeeID;
        this.role = builder.role;
        this.username = builder.username;
        this.personalData = builder.personalData;
        this.active = builder.active;
    }

    public Employee(){
    }

    //Normales Constructor (Create new user)
    public Employee(String username, int role, PersonalData personalData, Boolean active) {
        this.personalData = personalData;
        this.role = role;
        this.username = username;
        this.active = active;
    }

    //Kompletes Constructor (Modify user with ID included)
    public Employee(long employeeID ,String username, int role, PersonalData personalData, Boolean active) {
        this.employeeID = employeeID;
        this.personalData = personalData;
        this.role = role;
        this.username = username;
        this.active = active;
    }

    /*Getters und Setters */

    public long getEmployeeID() {
        return this.employeeID;
    }

    public void setEmployeeID(long employeeID) {
        this.employeeID = employeeID;
    }

    public int getRole() {
        return this.role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
    }

    /*Equals, toString und Hashcodes*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(username, employee.username);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeID=" + employeeID +
                ", role=" + role +
                ", username='" + username + '\'' +
                ", isActive=" + active +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(role, username);
    }
}
