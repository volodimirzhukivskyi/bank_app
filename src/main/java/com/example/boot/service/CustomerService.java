package com.example.boot.service;

import com.example.boot.dao.CustomerRepository;
import com.example.boot.entities.model.Account;
import com.example.boot.entities.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.Query;
import java.util.List;

@Service
@Transactional
@Slf4j
public class CustomerService {

    @Autowired
    private CustomerRepository userDao;

    public List<Customer> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Customer> customerPage = userDao.findAll(pageable);
        return customerPage.toList();
    }

    public void save(Customer customer) {
        userDao.save(customer);
    }

    public void update(Customer customerUpdate) {
        Customer customer = userDao.findById(customerUpdate.getId()).get();
        customer.setEmail(customerUpdate.getEmail());
        customer.setAge(customerUpdate.getAge());
        customer.setAccounts(customerUpdate.getAccounts());
        customer.setName(customerUpdate.getName());
        customer.setEmployers(customerUpdate.getEmployers());

        userDao.save(customer);
    }

    public Customer findById(Long userId) {


            return userDao.findById(userId).orElse(new Customer());



    }

    public void deleteById(Long id) {


            userDao.deleteById(id);

    }


}
