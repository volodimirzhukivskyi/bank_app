package com.example.boot.entities.facade.customer;

import com.example.boot.entities.dto.CustomerRequest;
import com.example.boot.entities.facade.DtoMapperFacade;
import com.example.boot.entities.model.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerRequestMapper extends DtoMapperFacade<Customer, CustomerRequest> {
    public CustomerRequestMapper() {
        super(Customer.class, CustomerRequest.class);
    }


    @Override
    protected void decorateEntity(Customer entity, CustomerRequest dto) {



    }
}
