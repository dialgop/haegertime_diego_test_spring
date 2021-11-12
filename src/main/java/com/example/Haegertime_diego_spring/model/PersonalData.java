package com.example.Haegertime_diego_spring.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

@Embeddable
public class PersonalData {

    private String name;

    @NotEmpty(message = "surname is a mandatory field, please provide it")
    @Size(min = 2, message = "surname size must be bigger than 2")
    private String surname;

    @NotEmpty(message = "email is a mandatory field, please provide it")
    @Email(message = "The email is false")
    private String email;
    private Long telephone;
    @Embedded
    @Valid
    private Address address;

    public PersonalData(String name, String surname, String email, long telephone, Address address) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.telephone = telephone;
        this.address = address;
    }

    public PersonalData(){
        this.name = "";
        this.surname = "";
        this.email = "";
        this.telephone = -1L;
        this.address = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getTelephone() {
        return telephone;
    }

    public void setTelephone(Long telephone) {
        this.telephone = telephone;
    }

    public Address getAddress() { return address; }

    public void setAddress(Address address) { this.address = address; }
}
