package com.example.boot.entities.dto;

import com.example.boot.entities.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployerResponse {
    private Long id;
    private String name;
    private String address;

    private Set<Customer> staff=new HashSet<>();

    @Override
    public String toString() {
        return "Employer{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
