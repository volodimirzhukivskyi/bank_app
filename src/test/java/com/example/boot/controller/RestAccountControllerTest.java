package com.example.boot.controller;

import com.example.boot.dao.AccountRepository;
import com.example.boot.entities.dto.AccountResponse;
import com.example.boot.entities.facade.account.AccountRequestMapper;
import com.example.boot.entities.facade.account.AccountResponseMapper;
import com.example.boot.entities.model.Account;
import com.example.boot.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(RestAccountController.class)
public class RestAccountControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AccountService accountService;
    @MockBean
    private AccountRepository accountRepository;
    @MockBean
    private AccountRequestMapper accountRequestMapper;
    @MockBean
    private AccountResponseMapper accountResponseMapper;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void getAll() throws Exception {
        Account account = new Account();

        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setBalance(23.00);
        accountResponse.setNumber("2345-1111-2424-6655");

        when(accountService.getAll()).thenReturn(List.of(account));
        when(accountResponseMapper.convertToDto(account)).thenReturn(accountResponse);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/accounts").contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].balance", Matchers.is(23.00)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].number", Matchers.is("2345-1111-2424-6655")));
    }

    @Test
    public void getById() throws Exception {
        Account account = new Account();

        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setBalance(23.00);
        accountResponse.setNumber("2345-1111-2424-6655");

        when(accountService.findById(1L)).thenReturn(account);
        when(accountResponseMapper.convertToDto(account)).thenReturn(accountResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/accounts/{id}", 1L)
                        .contentType("application/json").content(objectMapper.writeValueAsString(accountResponse)))
                .andExpect(status().isOk())
                .andReturn();

    }

    @Test

    public void deleteById() throws Exception {
        accountRepository.deleteById(1L);
        verify(accountRepository, times(1)).deleteById(any(Long.class));


        mockMvc.perform(MockMvcRequestBuilders.delete("/accounts/{id}", 1L))
                .andExpect(status().isOk());

    }

    @Test
    public void testCreate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/accounts/create")
                        .contentType("application/json")
                        .content(
                                """
                                                {
                                                		"number": "2222-3333-4444",
                                                		"currency": "CHdfFd",
                                                		"balance": 5.0,
                                                		"customer":{"id":1}
                                                	}
                                        """
                        ))
                .andExpect(status().isOk());

}
    @Test
    public void testUpdate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.put("/accounts/update")
                        .contentType("application/json")
                        .content(
                                """
                                             	{
                                               		"id": 1,
                                               		"name": "GOsGLE",
                                               		"address": "Kyisv",
                                               		"staff": []
                                               	}
                                        """
                        ))
                .andExpect(status().isOk());

    }}

