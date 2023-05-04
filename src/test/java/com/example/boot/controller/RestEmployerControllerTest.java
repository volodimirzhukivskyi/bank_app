package com.example.boot.controller;

import com.example.boot.dao.EmployerRepository;
import com.example.boot.entities.dto.EmployerResponse;
import com.example.boot.entities.facade.employer.EmployerRequestMapper;
import com.example.boot.entities.facade.employer.EmployerResponseMapper;
import com.example.boot.entities.model.Employer;
import com.example.boot.service.EmployerService;
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

@WebMvcTest(RestEmployerController.class)
public class RestEmployerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EmployerService employerService;
    @MockBean
    private EmployerRepository employerRepository;
    @MockBean
    private EmployerRequestMapper employerRequestMapper;
    @MockBean
    private EmployerResponseMapper employerResponseMapper;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void getAll() throws Exception {
        Employer employer = new Employer();

        EmployerResponse  employerResponse = new EmployerResponse();
        employerResponse.setAddress("Kyiv");
        employerResponse.setName("Vasya");

        when(employerService.findAll()).thenReturn(List.of(employer));
        when(employerResponseMapper.convertToDto(employer)).thenReturn(employerResponse);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/employer").contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].address", Matchers.is("Kyiv")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("Vasya")));
    }

    @Test
    public void getById() throws Exception {
        Employer employer = new Employer();

        EmployerResponse employerResponse = new EmployerResponse();
        employerResponse.setAddress("Kyiv");
        employerResponse.setName("Vasya");

        when(employerService.findById(1L)).thenReturn(employer);
        when(employerResponseMapper.convertToDto(employer)).thenReturn(employerResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/employer/{id}", 1L)
                        .contentType("application/json").content(objectMapper.writeValueAsString(employerResponse)))
                .andExpect(status().isOk())
                .andReturn();

    }

    @Test

    public void deleteById() throws Exception {
        employerRepository.deleteById(1L);
        verify(employerRepository, times(1)).deleteById(any(Long.class));


        mockMvc.perform(MockMvcRequestBuilders.delete("/employer/{id}", 1L))
                .andExpect(status().isOk());

    }

    @Test
    public void testCreate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/employer/create")
                        .contentType("application/json")
                        .content(
                                """
                                               {
                                               		"name": "GOsGLE",
                                               		"address": "Kyisv",
                                               		"staff": [
                                               			
                                               		]}
                                        """
                        ))
                .andExpect(status().isOk());

}
    @Test
    public void testUpdate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.put("/employer/update")
                        .contentType("application/json")
                        .content(
                                """
                                             	{
                                             		"id": 1,
                                             		"number": "2222n-3333-4444",
                                             		"currency": "CHFd",
                                             		"balance": 5.0,
                                             		"customer":{"id":1}
                                             	}
                                        """
                        ))
                .andExpect(status().isOk());

    }}

