package com.example.Haegertime_diego_spring.services;

import com.example.Haegertime_diego_spring.exceptions.UserAlreadyExistsException;
import com.example.Haegertime_diego_spring.exceptions.UserNotFoundException;
import com.example.Haegertime_diego_spring.model.Employee;
import com.example.Haegertime_diego_spring.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> showAllEmployees(){
        return employeeRepository.findAll();
    }

    public Employee showEmployee(Long id) throws UserNotFoundException {
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isPresent()){
            return employee.get();
        }
        else
        {
            throw new UserNotFoundException("Employee with id " + id +
                    " does not exist");
        }
    }

    public String deleteEmployee(Long id) throws UserNotFoundException {
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isPresent()){
            employeeRepository.deleteById(id);
            return "The user with id " + id + " was deleted";
        }
        else
        {
            throw new UserNotFoundException("Employee with id " + id +
                    " does not exist");
        }
    }

    public Employee createEmployee(Employee employee) throws UserAlreadyExistsException {

        Optional<Employee> foundEmployee = employeeRepository.findEmployeeByUsername(employee.getUsername());

        if(foundEmployee.isPresent()){
            throw new UserAlreadyExistsException("The user with username " + employee.getUsername() + " already exists");
        }
        return employeeRepository.save(employee);
    }

    public Employee updateEmployeeData(Employee employee) throws UserNotFoundException {

        Employee employeeOriginal = employeeRepository.findById(employee.getEmployeeID()).orElseThrow(() ->
                new UserNotFoundException("Employee with id " + employee.getEmployeeID() +
                        " does not exist"));

        employeeOriginal.setPersonalData(employee.getPersonalData());

        return employeeRepository.save(employeeOriginal);
    }

    public Employee updateEmployeeActive(Employee employee) throws UserNotFoundException {

        Employee employeeOriginal = employeeRepository.findById(employee.getEmployeeID()).orElseThrow(() ->
                new UserNotFoundException("Employee with id " + employee.getEmployeeID() +
                        " does not exist"));
        employeeOriginal.setActive(employee.getActive());

        return employeeRepository.save(employeeOriginal);
    }

    public Employee updateEmployeeUsername(Employee employee) throws UserNotFoundException, UserAlreadyExistsException {

        Optional<Employee> foundEmployee = employeeRepository.findEmployeeByUsername(employee.getUsername());

        if(foundEmployee.isPresent()){
            throw new UserAlreadyExistsException("The user with username " + employee.getUsername() + " already exists");
        }

        Employee employeeOriginal = employeeRepository.findById(employee.getEmployeeID()).orElseThrow(() ->
                new UserNotFoundException("Employee with id " + employee.getEmployeeID() +
                        " does not exist"));
        employeeOriginal.setUsername(employee.getUsername());
        return employeeRepository.save(employeeOriginal);
    }

    public Employee updateEmployeeRole(Employee employee) throws UserNotFoundException {

        Employee employeeOriginal = employeeRepository.findById(employee.getEmployeeID()).orElseThrow(() ->
                new UserNotFoundException("Employee with id " + employee.getEmployeeID() +
                        " does not exist"));

        employeeOriginal.setRole(employee.getRole());

        return employeeRepository.save(employeeOriginal);
    }

}
