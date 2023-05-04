package com.example.boot.service;

import com.example.boot.dao.EmployerRepository;
import com.example.boot.entities.model.Employer;
import com.example.boot.entities.model.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployerServiceTest {



    @Mock
    private EmployerRepository employerRepository;



    @InjectMocks
    private EmployerService employerService;

    @Test
    public void testFindAll() {
        Employer employer = new Employer();
        when(employerRepository.findAll())
                .thenReturn(new ArrayList<>(List.of(employer)));
        List<Employer> employers = employerService.findAll();

        assertEquals(employer, employers.get(0));
    }
@Test
    public void testSave(){
    Employer employer = new Employer();
    when(employerRepository.save(any(Employer.class))).thenReturn(employer);

   Employer saveEmployer = employerRepository.save(new Employer());
    assertEquals(employer,saveEmployer );
    }
    @Test
    public void testFindByIdSucess(){
        Employer employer=new Employer();
when(employerRepository.findById(any(Long.class))).thenReturn(Optional.of(employer));

       Employer findEmployer  =  employerRepository.findById(1L).orElse(new Employer());
        assertEquals(employer,findEmployer );
    }
    @Test

    public void testFindByIdFalse(){
        Employer employer=new Employer();
        when(employerRepository.findById(any(Long.class))).thenReturn( Optional.empty());

        Employer findEmloyer  =  employerRepository.findById(1L).orElse(employer);
        assertEquals(employer,findEmloyer );
    }
    @Test
    public void deleteById(){
        employerRepository.deleteById(1L);
        verify(employerRepository,times(1)).deleteById(any(Long.class));

    }

}