package com.example.boot.entities.model;

import com.example.boot.entities.otherModel.AbstractEntity;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="customers")
public class Customer extends AbstractEntity {
    private String name;
    private String email;
    private String phoneNumber;
    private Integer age;
    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "staff", cascade = CascadeType.ALL)
    private Set<Employer> employers=new HashSet<>();
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "customer")
    private Set<Account> accounts=new HashSet<>();

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", age=" + age +
                ", employers=" + employers +
                ", accounts=" + accounts +
                '}';
    }
}
