package com.example.boot.entities.facade.account;

import com.example.boot.entities.dto.AccountRequest;
import com.example.boot.entities.dto.CustomerRequest;
import com.example.boot.entities.facade.DtoMapperFacade;
import com.example.boot.entities.model.Account;
import org.springframework.stereotype.Service;

@Service
public class AccountRequestMapper extends DtoMapperFacade<Account, AccountRequest> {
    public AccountRequestMapper() {
        super(Account.class, AccountRequest.class);
    }


    @Override
    protected void decorateEntity(Account entity, AccountRequest dto) {



    }
}
