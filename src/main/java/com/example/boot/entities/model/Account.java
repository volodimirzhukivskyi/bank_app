package com.example.boot.entities.model;

import com.example.boot.entities.otherModel.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts")
public class Account extends AbstractEntity {

    private String number;
    private String currency;
    private Double balance;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Customer customer;

    @Override
    public String toString() {
        return "Account{" +
                 "number='" + number + '\'' +
                ", currency='" + currency + '\'' +
                ", balance=" + balance +
                '}';
    }
}
