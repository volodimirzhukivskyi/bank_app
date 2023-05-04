package com.example.boot.service;

import com.example.boot.dao.AccountRepository;
import com.example.boot.entities.model.Account;
import com.example.boot.entities.model.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {



    @Mock
    private AccountRepository accountRepository;



    @InjectMocks
    private AccountService accountService;

    @Test
    public void testGetAll() {
        Account account = new Account();
        when(accountRepository.findAll())
                .thenReturn(new ArrayList<>(List.of(account)));
        List<Account> accounts = accountService.getAll();

        assertEquals(account, accounts.get(0));
    }
@Test
    public void testSave(){
    Account account = new Account();
    when(accountRepository.save(any(Account.class))).thenReturn(account);

   Account saveAccount = accountRepository.save(new Account());
    assertEquals(account,saveAccount );
    }
    @Test
    public void testFindByIdSucess(){
        Account account=new Account("2345-2233-4444","EUR",20.00,new Customer());
when(accountRepository.findById(any(Long.class))).thenReturn(Optional.of(account));

       Account findAccount  =  accountRepository.findById(1L).orElse(new Account());
        assertEquals(account,findAccount );
    }
    @Test

    public void testFindByIdFalse(){
        Account account=new Account();
        when(accountRepository.findById(any(Long.class))).thenReturn( Optional.empty());

        Account findAccount  =  accountRepository.findById(1L).orElse(account);
        assertEquals(account,findAccount );
    }
    @Test
    public void deleteById(){
        accountRepository.deleteById(1L);
        verify(accountRepository,times(1)).deleteById(any(Long.class));

    }

}