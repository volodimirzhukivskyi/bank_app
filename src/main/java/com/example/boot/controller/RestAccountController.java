package com.example.boot.controller;

import com.example.boot.entities.dto.AccountRequest;
import com.example.boot.entities.dto.AccountResponse;
import com.example.boot.entities.dto.AccountWithDrawRequest;
import com.example.boot.entities.facade.account.AccountRequestMapper;
import com.example.boot.entities.facade.account.AccountResponseMapper;
import com.example.boot.entities.model.Account;
import com.example.boot.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
@Slf4j
public class RestAccountController {
    private final AccountService userService;
    private final AccountRequestMapper accountRequestMapper;
    private final AccountResponseMapper accountResponseMapper;




    @GetMapping
    public List<AccountResponse> getAll() {
        List<Account> accounts = userService.getAll();
        if (accounts.size() == 0) {
            log.info("empty list");
        }
        return accounts.stream().map(accountResponseMapper::convertToDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public AccountResponse getById(@PathVariable("id") String userId) {
        Account account = userService.findById(Long.parseLong(userId));
        if (account.equals(new Account())) {
            log.info("Account isEmpty");
        }
        return accountResponseMapper.convertToDto(account);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String userId) {
        Long id = Long.parseLong(userId);
        if (userService.findById(id).equals(new Account())) {
            log.warn("The list has no element with this id");
        }
        userService.deleteById(id);
    }

    @PutMapping("/update")
    public void update(@Valid @RequestBody AccountRequest dto) {
        Account account = accountRequestMapper.convertToEntity(dto);
        log.info(account + " - Object for update Account  ");
        userService.save(account);
    }

    @PostMapping("/create")
    public void create(@RequestBody AccountRequest dto) {
        Account account = accountRequestMapper.convertToEntity(dto);
        log.info(account + " - Object for create Account  ");
        userService.save(account);
    }
    @PostMapping("/withdrawMoney")
    public boolean withdrawMoney(@RequestBody AccountWithDrawRequest accountWithDrawRequest) {
        log.info(accountWithDrawRequest.getNumber() + " - Object for create Account  ");
        return  userService.withdrawMoney(accountWithDrawRequest.getNumber(), accountWithDrawRequest.getBalance());
    }

    @ExceptionHandler({Exception.class, MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleException(Exception ex) {
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }

}
