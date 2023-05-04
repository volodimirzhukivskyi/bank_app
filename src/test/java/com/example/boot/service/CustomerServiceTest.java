package com.example.boot.service;

import com.example.boot.dao.CustomerRepository;
import com.example.boot.entities.model.Customer;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {



    @Mock
    private CustomerRepository customerRepository;



    @InjectMocks
    private CustomerService customerService;

    @Test
    public void testGetAllPageble() {
        Customer employee = new Customer();
        when(customerRepository.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(employee)));
        List<Customer> employees = customerService.findAll(1, 2);

        assertEquals(employee, employees.get(0));
    }
@Test
    public void testSave(){
    Customer customer = new Customer();
    when(customerRepository.save(any(Customer.class))).thenReturn(customer);

   Customer saveCustomer = customerRepository.save(new Customer());
    assertEquals(customer,saveCustomer );
    }
    @Test
    public void testFindByIdSucess(){
        Customer customer=new Customer();
when(customerRepository.findById(any(Long.class))).thenReturn(Optional.of(customer));

       Customer findCustomer   =  customerRepository.findById(1L).orElse(new Customer());
        assertEquals(customer,findCustomer );
    }
    @Test

    public void testFindByIdFalse(){
        Customer customer=new Customer();
        when(customerRepository.findById(any(Long.class))).thenReturn( Optional.empty());

        Customer findCustomer  =  customerRepository.findById(1L).orElse(customer);
        assertEquals(customer,findCustomer );
    }
    @Test
    public void deleteById(){
        customerRepository.deleteById(1L);
        verify(customerRepository,times(1)).deleteById(any(Long.class));

    }

}