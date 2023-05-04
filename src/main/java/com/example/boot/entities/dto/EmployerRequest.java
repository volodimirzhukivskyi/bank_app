package com.example.boot.entities.dto;

import com.example.boot.entities.model.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployerRequest {
    private Long id;
    @Size(min=3)
    private String name;
    @Size(min=3)
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
