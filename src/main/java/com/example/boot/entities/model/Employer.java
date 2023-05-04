package com.example.boot.entities.model;

import com.example.boot.entities.otherModel.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TBL_EMPLOYERS")
public class Employer extends AbstractEntity {
    private String name;
    private String address;
    @ManyToMany( fetch  = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinTable(
            name="employer_staff",
            joinColumns = @JoinColumn(name="tbl_emloyers_id"),
            inverseJoinColumns = @JoinColumn(name="customer_id")
    )
    private Set<Customer> staff=new HashSet<>();

    @Override
    public String toString() {
        return "Employer{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}


