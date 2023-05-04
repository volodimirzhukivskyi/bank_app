package com.example.boot.entities.facade.account;

import com.example.boot.entities.dto.AccountResponse;
import com.example.boot.entities.facade.DtoMapperFacade;
import com.example.boot.entities.model.Account;
import org.springframework.stereotype.Service;

@Service
public class AccountResponseMapper extends DtoMapperFacade<Account, AccountResponse> {
    public AccountResponseMapper() {
        super(Account.class, AccountResponse.class);
    }


    @Override
    protected void decorateEntity(Account entity, AccountResponse dto) {



    }
}
