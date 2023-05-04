package com.example.boot.controller;

import com.example.boot.entities.dto.CustomerRequest;
import com.example.boot.entities.dto.CustomerResponse;
import com.example.boot.entities.facade.customer.CustomerRequestMapper;
import com.example.boot.entities.facade.customer.CustomerResponseMapper;
import com.example.boot.entities.model.Account;
import com.example.boot.entities.model.Customer;
import com.example.boot.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/customer")
@Slf4j
public class RestCustomerController {


    private final CustomerService userService;
    private final CustomerResponseMapper customerResponseMapper;
    private final CustomerRequestMapper customerRequestMapper;

    public RestCustomerController(CustomerService userService, CustomerResponseMapper customerResponseMapper, CustomerRequestMapper customerRequestMapper) {
        this.userService = userService;
        this.customerResponseMapper = customerResponseMapper;
        this.customerRequestMapper = customerRequestMapper;
    }

    @GetMapping("/{page}/size/{size}")
    public List<CustomerResponse> getCustomers(@PathVariable int page,
                                                         @PathVariable int size) {
        List<Customer> customers = userService.findAll(page, size);
        if (customers.size() == 0) {
            log.info("empty list");
        }
        return customers.stream().map(customerResponseMapper::convertToDto).collect(Collectors.toList());

    }
    @GetMapping("/{id}")
    public CustomerResponse getById(@PathVariable("id") String userId) {
        Customer customer = userService.findById(Long.parseLong(userId));
        if (customer.equals(new Customer())) {
            log.info("Account isEmpty");
        }
        return customerResponseMapper.convertToDto(customer);

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String userId) {
        Long id = Long.parseLong(userId);
        if (userService.findById(id).equals(new Customer())) {
            log.warn("The list has no element with this id");
        }
        userService.deleteById(id);
    }

    @PutMapping("/update")
    public void update(@RequestBody CustomerRequest dto) {
        Customer customer = customerRequestMapper.convertToEntity(dto);
        log.info(customer + " - Object for update Customer  ");
        userService.update(customer);
    }

    @PostMapping("/create")
    public void create(@Valid @RequestBody CustomerRequest dto) {
        Customer customer = customerRequestMapper.convertToEntity(dto);
        log.info(customer + " - Object for create Customer  ");
        userService.save(customer);
    }

    @ExceptionHandler({Exception.class, MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleException(Exception ex) {
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }

}
