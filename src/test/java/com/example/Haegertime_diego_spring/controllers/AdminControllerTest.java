package com.example.Haegertime_diego_spring.controllers;

import com.example.Haegertime_diego_spring.model.Address;
import com.example.Haegertime_diego_spring.model.Employee;
import com.example.Haegertime_diego_spring.model.PersonalData;
import com.example.Haegertime_diego_spring.model.builder.EmployeeBuilder;
import com.example.Haegertime_diego_spring.repositories.EmployeeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest()
@AutoConfigureMockMvc
class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeRepository employeeRepository;

    private final String BASEURL = "http://localhost:8080/api/admin/";

    //Get-Methoden
    @Nested
    class getUser{

        @Test
        public void getEmployeeOptimalPath() throws Exception{

            //Given
            Address address = new Address("Rosental 5", "Bonn", "Germany", 53111);
            PersonalData personalData = new PersonalData("user", "i am", "iam@user.com", 1736728723, address);

            Employee testEmployee = new EmployeeBuilder().employeeID(3L).username("AUser").active(true).role(1).personalData(personalData).build();

            Optional<Employee> optionalUser = Optional.of(testEmployee);

            //when
            when(employeeRepository.findById(3L)).thenReturn(optionalUser);

            //then
            ObjectMapper mapper = new ObjectMapper();
            String employeeJson = mapper.writeValueAsString(testEmployee);

            mockMvc.perform(get(BASEURL + "/user/3"))
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(content().json(employeeJson));
        }

        @Test
        public void getEmployeeWrongPath() throws Exception{
            mockMvc.perform(get(BASEURL + "/wrongPath"))
                    .andExpect(status().is4xxClientError());
        }

        @Test
        public void getEmployeeFalseID() throws Exception{

            //Given
            Address address = new Address("Rosental 5", "Bonn", "Germany", 53111);
            PersonalData personalData = new PersonalData("user", "i am", "iam@user.com", 1736728723, address);

            Employee testEmployee = new EmployeeBuilder().employeeID(3L).username("AUser").active(true).role(1).personalData(personalData).build();

            Optional<Employee> optionalUser = Optional.of(testEmployee);

            //when
            when(employeeRepository.findById(3L)).thenReturn(optionalUser);

            mockMvc.perform(get(BASEURL + "/user/12"))
                    .andExpect(status().is4xxClientError());
        }
    }

    @Nested
    class getAllUsers{

        @Test
        public void getAllUsersOptimalpath() throws Exception{

            //Given

            Address address = new Address("Rosental 5", "Bonn", "Germany", 53111);
            PersonalData personalData = new PersonalData("user", "i am", "iam@user.com", 1736728723, address);

            Employee testEmployee1 = new EmployeeBuilder().employeeID(1L).username("AUser").active(true).role(1).personalData(personalData).build();
            Employee testEmployee2 = new EmployeeBuilder().employeeID(2L).username("AUser2").active(false).role(2).personalData(personalData).build();
            Employee testEmployee3 = new EmployeeBuilder().employeeID(2L).username("AUser2").active(false).role(2).personalData(personalData).build();

            List<Employee> employeeList = Arrays.asList(testEmployee1,testEmployee2,testEmployee3);

            //when
            when(employeeRepository.findAll()).thenReturn(employeeList);

            //then

            ObjectMapper mapper = new ObjectMapper();
            String employeeJson = mapper.writeValueAsString(employeeList);

            mockMvc.perform(get(BASEURL)).andExpect(status().is2xxSuccessful())
                    .andExpect(content().json(employeeJson));
        }

        @Test
        public void getAllUsersEmptyData() throws Exception{

            //Given

            List<Employee> employeeList = new ArrayList<>();

            //when
            when(employeeRepository.findAll()).thenReturn(employeeList);

            //then

            ObjectMapper mapper = new ObjectMapper();
            String employeeJson = mapper.writeValueAsString(employeeList);

            mockMvc.perform(get(BASEURL)).andExpect(status().is2xxSuccessful())
                    .andExpect(content().json(employeeJson));
        }
    }

    @Nested
    class addUser{

        @Test
        public void addUserOptimalPath() throws Exception{

            //Given

            Address address = new Address("Rosental 5", "Bonn", "Germany", 53111);
            PersonalData personalData = new PersonalData("user", "i am", "iam@user.com", 1736728723, address);

            Employee testEmployee = new EmployeeBuilder().employeeID(1L).username("AUser").active(true).role(1).personalData(personalData).build();

            //when
            when(employeeRepository.save(testEmployee)).thenReturn(testEmployee);

            //then

            ObjectMapper mapper = new ObjectMapper();
            String employeeJson = mapper.writeValueAsString(testEmployee);

            mockMvc.perform(post(BASEURL).contentType(MediaType.APPLICATION_JSON).content(employeeJson)).andExpect(status().is2xxSuccessful())
                    .andExpect(content().json(employeeJson));
        }

        @Test
        public void addUserAlreadyExists() throws Exception{

            //Given

            Address address = new Address("Rosental 5", "Bonn", "Germany", 53111);
            PersonalData personalData = new PersonalData("user", "i am", "iam@user.com", 1736728723, address);

            Employee testEmployee = new EmployeeBuilder().employeeID(1L).username("AUser").active(true).role(1).personalData(personalData).build();

            //when
            when(employeeRepository.findEmployeeByUsername(testEmployee.getUsername())).thenReturn(Optional.of(testEmployee));

            //then

            ObjectMapper mapper = new ObjectMapper();
            String employeeJson = mapper.writeValueAsString(testEmployee);

            mockMvc.perform(post(BASEURL).contentType(MediaType.APPLICATION_JSON).content(employeeJson)).andExpect(status().is4xxClientError());
        }
    }

    //How to test a delete
    @Nested
    class deleteUser{

        @Test
        public void deleteUserUserNotFound() throws Exception{

            //when
            when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

            //then
            mockMvc.perform(delete(BASEURL + "user/1")).andExpect(status().is4xxClientError());
        }
    }

    @Nested
    class updateEmployeeData{

        @Test
        public void modifyUserUserData() throws Exception{

            Address originalAddress = new Address("Rosental 5", "Bonn", "Germany", 12121);
            PersonalData originalPersonalData = new PersonalData("user", "i am", "iam@user.com", 1736728723, originalAddress);
            Employee fullTestEmployee = new EmployeeBuilder().employeeID(1L).username("AUser").active(true).role(1).personalData(originalPersonalData).build();

            //employeeRepository.save(fullTestEmployee);

            PersonalData newPersonalData = new PersonalData("nameuser", "surnameuser", "iam@newuser.com", 1736728444, originalAddress);
            Employee testEmployee = new EmployeeBuilder().employeeID(1L).personalData(newPersonalData).build();

            Employee modifiedTestEmployee = new EmployeeBuilder().employeeID(1L).username("AUser").active(true).role(1).personalData(newPersonalData).build();

            //when
            when(employeeRepository.findById(1L)).thenReturn(Optional.of(fullTestEmployee));
            when(employeeRepository.save(fullTestEmployee)).thenReturn(modifiedTestEmployee);

            //then

            ObjectMapper mapper = new ObjectMapper();
            String employeeJson = mapper.writeValueAsString(testEmployee);
            String fullEmployeeJson = mapper.writeValueAsString(modifiedTestEmployee);

            mockMvc.perform(put(BASEURL + "user/data").contentType(MediaType.APPLICATION_JSON).content(employeeJson))
                    .andExpect(status().is2xxSuccessful()).andExpect(content().json(fullEmployeeJson));
        }

        @Test
        public void modifyUserUserNotFound() throws Exception{

            Address address = new Address("Rosental 5", "Bonn", "Germany", 53111);
            PersonalData personalData = new PersonalData("user", "i am", "iam@user.com", 1736728723, address);

            Employee testEmployee = new EmployeeBuilder().employeeID(1L).personalData(personalData).build();

            //when
            when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

            //then

            ObjectMapper mapper = new ObjectMapper();
            String employeeJson = mapper.writeValueAsString(testEmployee);

            mockMvc.perform(put(BASEURL + "user/data").contentType(MediaType.APPLICATION_JSON).content(employeeJson)).andExpect(status().is4xxClientError());
        }
    }

    @Nested
    class updateEmployeeActive{

        Address originalAddress;
        PersonalData personalData;
        Employee fullTestEmployee;

        @BeforeEach
        public void prepareData() {
            originalAddress = new Address("Rosental 5", "Bonn", "Germany", 12121);
            personalData = new PersonalData("user", "i am", "iam@user.com", 1736728723, originalAddress);
            fullTestEmployee = new EmployeeBuilder().employeeID(1L).username("AUser").active(true).role(1).personalData(personalData).build();
        }

        @Test
        public void modifyUserUserData() throws Exception{

            Employee testEmployee = new EmployeeBuilder().employeeID(1L).active(false).build();

            Employee modifiedTestEmployee = new EmployeeBuilder().employeeID(1L).username("AUser").active(false).role(1).personalData(personalData).build();

            //when
            when(employeeRepository.findById(1L)).thenReturn(Optional.of(fullTestEmployee));
            when(employeeRepository.save(fullTestEmployee)).thenReturn(modifiedTestEmployee);

            //then

            ObjectMapper mapper = new ObjectMapper();
            String employeeJson = mapper.writeValueAsString(testEmployee);
            String fullEmployeeJson = mapper.writeValueAsString(modifiedTestEmployee);

            mockMvc.perform(put(BASEURL + "user/active").contentType(MediaType.APPLICATION_JSON).content(employeeJson))
                    .andExpect(status().is2xxSuccessful()).andExpect(content().json(fullEmployeeJson));
        }
    }
}