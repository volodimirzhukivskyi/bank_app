package com.example.boot.entities.facade.employer;

import com.example.boot.entities.dto.EmployerResponse;
import com.example.boot.entities.facade.DtoMapperFacade;
import com.example.boot.entities.model.Customer;
import com.example.boot.entities.model.Employer;
import org.springframework.stereotype.Service;

@Service
public class EmployerResponseMapper extends DtoMapperFacade<Employer, EmployerResponse> {
    public EmployerResponseMapper() {
        super(Employer.class, EmployerResponse.class);
    }


    @Override
    protected void decorateEntity(Employer entity, EmployerResponse dto) {



    }
}
