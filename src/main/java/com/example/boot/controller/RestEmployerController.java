package com.example.boot.controller;

import com.example.boot.entities.dto.EmployerRequest;
import com.example.boot.entities.dto.EmployerResponse;
import com.example.boot.entities.facade.employer.EmployerRequestMapper;
import com.example.boot.entities.facade.employer.EmployerResponseMapper;
import com.example.boot.entities.model.Account;
import com.example.boot.entities.model.Employer;
import com.example.boot.service.EmployerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/employer")
@RestController
@Slf4j
public class RestEmployerController {
    @Autowired
    private EmployerService userService;
    private final EmployerResponseMapper employerResponseMapper;
    private final EmployerRequestMapper employerRequestMapper;


    @GetMapping
    public List<EmployerResponse> getAll() {
        List<Employer> employers = userService.findAll();
        if (employers.size() == 0) {
            log.info("empty list");
        }
        return employers.stream().map(employerResponseMapper::convertToDto).collect(Collectors.toList());

    }

    @GetMapping("/{id}")
    public EmployerResponse getById(@PathVariable("id") String userId) {
        Employer employer = userService.findById(Long.parseLong(userId));
        if (employer.equals(new Employer())) {
            log.info("Employer isEmpty");
        }
        return employerResponseMapper.convertToDto(employer);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String userId) {
        Long id = Long.parseLong(userId);
        if (userService.findById(id).equals(new Employer())) {
            log.warn("The list has no element with this id");
        }
        userService.deleteById(id);
    }

    @PutMapping("/update")
    public void update(@RequestBody EmployerRequest dto) {
        Employer employer =employerRequestMapper.convertToEntity(dto);
        log.info(employer + " - Object for update Employer  ");
        userService.save(employer);
    }

    @PostMapping("/create")
    public void create(@Valid @RequestBody EmployerRequest dto) {
        Employer employer =employerRequestMapper.convertToEntity(dto);
        log.info(employer + " - Object for create Employer  ");
        userService.save(employer);
    }

    @ExceptionHandler({Exception.class, MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleException(Exception ex) {
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }
}
