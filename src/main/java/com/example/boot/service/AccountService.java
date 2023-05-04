package com.example.boot.service;

import com.example.boot.dao.AccountRepository;
import com.example.boot.entities.model.Account;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j

public class AccountService {
    @Autowired
    private AccountRepository userDao;


    public List<Account> getAll() {
        return (List<Account>) userDao.findAll();
    }

    public void save(Account account) {
        userDao.save(account);
    }

    public Account findById(Long userId) {

            return userDao.findById(userId).orElse(new Account());

    }

    public void deleteById(Long id) {

            userDao.deleteById(id);


    }

    public boolean withdrawMoney(String number, Double withdrawalSum) {
        Optional<Account> accountOptional = userDao.findAccountByNumber(number);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            double sum = account.getBalance() - withdrawalSum;
            if (sum < 0) {
                System.out.println("на счету не достаточно средств");
                return false;

            } else {
                account.setBalance(sum);
                userDao.save( account);
                System.out.println("деньги успешно сняты");
                return true;
            }

        } else {
            System.out.println("Ваши данные не верные перепроверьте номер счета");
            return false;
        }
    }
}
