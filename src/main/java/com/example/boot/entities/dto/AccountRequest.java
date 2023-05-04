package com.example.boot.entities.dto;

import com.example.boot.entities.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequest {
    private Long id;
    private String number;
    private String currency;
    @Min(0)
    private Double balance;
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
