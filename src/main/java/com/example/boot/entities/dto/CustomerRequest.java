package com.example.boot.entities.dto;

import com.example.boot.entities.model.Account;
import com.example.boot.entities.model.Employer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {
    private Long id;
    @Size(min=2)
    private String name;
    @Pattern(regexp = "(\\+38|0)[0-9]{9}")

    private String phoneNumber;
    @Pattern(regexp = "^(.+)@(.+)$")
    @Size(min=5)
    private String email;
    @Min(18)
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
