package com.example.boot.controller;

import com.example.boot.dao.CustomerRepository;
import com.example.boot.entities.dto.CustomerResponse;
import com.example.boot.entities.facade.customer.CustomerRequestMapper;
import com.example.boot.entities.facade.customer.CustomerResponseMapper;
import com.example.boot.entities.model.Account;
import com.example.boot.entities.model.Customer;
import com.example.boot.service.CustomerService;
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
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RestCustomerController.class)
public class RestCustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CustomerService customerService;
    @MockBean
    private CustomerRepository customerRepository;
    @MockBean
    private CustomerRequestMapper customerRequestMapper;
    @MockBean
    private CustomerResponseMapper customerResponseMapper;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void getAll() throws Exception {
        Customer  customer = new Customer();

        CustomerResponse  customerResponse = new CustomerResponse();
        customerResponse.setAge(12);
        customerResponse.setEmail("volodimir@gmail.com");
        customerResponse.setName("Volodimir");
        customerResponse.setPhoneNumber("+380731027043");

        when(customerService.findAll(1,2)).thenReturn(List.of(customer));
        when(customerResponseMapper.convertToDto(customer)).thenReturn(customerResponse);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/customer/1/size/2").contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("Volodimir")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email", Matchers.is("volodimir@gmail.com")));
    }

    @Test
    public void getById() throws Exception {
        Customer customer = new Customer();

        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setAge(12);
        customerResponse.setEmail("volodimir@gmail.com");
        customerResponse.setName("Volodimir");
        customerResponse.setPhoneNumber("+380731027043");

        when(customerService.findById(1L)).thenReturn(customer);
        when(customerResponseMapper.convertToDto(customer)).thenReturn(customerResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/customer/{id}", 1L)
                        .contentType("application/json").content(objectMapper.writeValueAsString(customerResponse)))
                .andExpect(status().isOk())
                .andReturn();

    }

    @Test

    public void deleteById() throws Exception {
        customerRepository.deleteById(1L);
        verify(customerRepository, times(1)).deleteById(any(Long.class));


        mockMvc.perform(MockMvcRequestBuilders.delete("/customer/{id}", 1L))
                .andExpect(status().isOk());

    }

    @Test
    public void testCreate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/customer/create")
                        .contentType("application/json")
                        .content(
                                """
                                               {
                                                  		"name": "Vasya",
                                                  		"email": "vasya@gmail.com",
                                                  		"age": 23
                                                  	}
                                        """
                        ))
                .andExpect(status().isOk());

}
    @Test
    public void testUpdate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.put("/customer/update")
                        .contentType("application/json")
                        .content(
                                """
                                             	{
                                                 		"id": 2,
                                                 		"name": "ghfhfgh",
                                                 		"email": "vasyaPupkin2@gmail.com",
                                                 		"age": 45,
                                                 		"employers": [],
                                                 		"accounts": [
                                                 			{
                                                 				"id": 2,
                                                 				"number": "2223-3334-4455",
                                                 				"currency": "USD",
                                                 				"balance": 100.1
                                                 			},
                                                 				{
                                                 		"id": 4,
                                                 		"number": "2222-3333-4444",
                                                 		"currency": "CHFD",
                                                 		"balance": 5.0
                                                 	}
                                                 		]
                                                 	}
                                        """
                        ))
                .andExpect(status().isOk());

    }}

