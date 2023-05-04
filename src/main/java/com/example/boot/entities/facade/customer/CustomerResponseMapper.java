package com.example.boot.entities.facade.customer;

import com.example.boot.entities.dto.CustomerResponse;
import com.example.boot.entities.facade.DtoMapperFacade;
import com.example.boot.entities.model.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerResponseMapper extends DtoMapperFacade<Customer, CustomerResponse> {
    public CustomerResponseMapper() {
        super(Customer.class, CustomerResponse.class);
    }


    @Override
    protected void decorateEntity(Customer entity, CustomerResponse dto) {



    }
}
