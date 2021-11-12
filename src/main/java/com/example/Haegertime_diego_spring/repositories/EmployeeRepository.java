package com.example.Haegertime_diego_spring.repositories;

import com.example.Haegertime_diego_spring.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    @Query("SELECT e FROM Employee e WHERE e.username = ?1")
    Optional<Employee> findEmployeeByUsername(String username);
}
