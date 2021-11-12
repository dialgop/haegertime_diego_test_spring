package com.example.Haegertime_diego_spring.repositories;

import com.example.Haegertime_diego_spring.model.Address;
import com.example.Haegertime_diego_spring.model.Employee;
import com.example.Haegertime_diego_spring.model.PersonalData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class EmployeeConfig {

    @Bean
    CommandLineRunner commandLineRunner(EmployeeRepository employeeRepository){
        return args -> {

            Address tonyAddress = new Address("Ippendorfer Allee 100", "Bonn", "Germany", 53127);
            Address trixAddress = new Address("Weberstr 39","Bonn","Germany",53113);

            PersonalData tonyData = new PersonalData("Tony","The-Tiger","tonythetiger@kellogs.com",
                    1736727765,tonyAddress);
            PersonalData trixData = new PersonalData("trix","The-rabbit","trixtherabbit@kellogs.com",
                    1736727765,trixAddress);

            Employee tonyTheTiger = new Employee("tonyTiger", 1,tonyData,false );
            Employee trixTheRabbit = new Employee("trixTheRabbit", 2, trixData, true);

            employeeRepository.saveAll(List.of(tonyTheTiger,trixTheRabbit));
        };
    }
}