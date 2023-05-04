package com.example.boot.entities.facade.employer;

import com.example.boot.entities.dto.EmployerRequest;
import com.example.boot.entities.facade.DtoMapperFacade;
import com.example.boot.entities.model.Employer;
import org.springframework.stereotype.Service;

@Service
public class EmployerRequestMapper extends DtoMapperFacade<Employer, EmployerRequest> {
    public EmployerRequestMapper() {
        super(Employer.class, EmployerRequest.class);
    }


    @Override
    protected void decorateEntity(Employer entity, EmployerRequest dto) {



    }
}
