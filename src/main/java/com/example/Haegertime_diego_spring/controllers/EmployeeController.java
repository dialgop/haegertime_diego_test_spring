package com.example.Haegertime_diego_spring.controllers;

import com.example.Haegertime_diego_spring.model.Employee;
import com.example.Haegertime_diego_spring.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService =  employeeService;
    }

    @GetMapping
    public List<Employee> getAllUsers() {return employeeService.showAllEmployees();}

}
