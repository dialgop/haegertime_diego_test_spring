package com.example.Haegertime_diego_spring.controllers;

import com.example.Haegertime_diego_spring.exceptions.UserAlreadyExistsException;
import com.example.Haegertime_diego_spring.exceptions.UserNotFoundException;
import com.example.Haegertime_diego_spring.model.Employee;
import com.example.Haegertime_diego_spring.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api/admin")
public class AdminController {

    private final EmployeeService employeeService;

    @Autowired
    public AdminController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAllUsers() {
        return employeeService.showAllEmployees();
    }

    @GetMapping(path = "user/{id}")
    public Employee getUser(@PathVariable("id") Long id) {
        try{
            return employeeService.showEmployee(id);
        }catch (UserNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @PostMapping()
    public Employee createNewUser(@Valid @RequestBody Employee employee){
        try{
            return employeeService.createEmployee(employee);
        }catch (UserAlreadyExistsException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @DeleteMapping(path = "user/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        try{
            employeeService.deleteEmployee(id);
        }catch (UserNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @PutMapping(path = "user/data")
    public Employee updateUserData(@RequestBody Employee employee)
    {
        try{
            return employeeService.updateEmployeeData(employee);
        }catch (UserNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }

    }

    @PutMapping(path = "user/active")
    public Employee updateUserActive(@RequestBody Employee employee){
        try{
            return employeeService.updateEmployeeActive(employee);
        }catch (UserNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @PutMapping(path = "user/username")
    public Employee updateUserUsername(@RequestBody Employee employee){
        try{
            return employeeService.updateEmployeeUsername(employee);
        }catch (UserNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }catch (UserAlreadyExistsException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @PutMapping(path = "user/role")
    public Employee updateUserRole(@RequestBody Employee employee){
        try{
            return employeeService.updateEmployeeRole(employee);
        }catch (UserNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }
}
