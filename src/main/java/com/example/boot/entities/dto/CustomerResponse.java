package com.example.boot.entities.dto;

import com.example.boot.entities.model.Account;
import com.example.boot.entities.model.Employer;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
public class CustomerResponse {
    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
    private Integer age;
    private Set<Employer> employers=new HashSet<>();
    private Set<Account> accounts=new HashSet<>();
    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", accounts=" + accounts +
                '}';
    }
}
