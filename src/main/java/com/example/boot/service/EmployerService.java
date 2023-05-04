package com.example.boot.service;

import com.example.boot.dao.EmployerRepository;
import com.example.boot.entities.model.Customer;
import com.example.boot.entities.model.Employer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional
@Slf4j
public class EmployerService {
    @Autowired
    private EmployerRepository userDao;


    public List<Employer> findAll() {
        return (List<Employer>) userDao.findAll();
    }

    public void save(Employer employer) {
        userDao.save(employer);
    }

    public Employer findById(Long userId) {
        return userDao.findById(userId).orElse(new Employer());

    }

    public void deleteById(Long id) {
        userDao.deleteById(id);

    }


}
