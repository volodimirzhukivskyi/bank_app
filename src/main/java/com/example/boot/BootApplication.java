package com.example.boot;

import com.example.boot.dao.UserRepository;
import com.example.boot.entities.dto.AccountResponse;
import com.example.boot.entities.model.Account;
import com.example.boot.entities.otherModel.User;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

@SpringBootApplication
@EnableTransactionManagement

public class BootApplication implements ApplicationRunner {

    private final UserRepository repository;

    public BootApplication(UserRepository repository) {this.repository = repository;}

    @PostConstruct
    public void initUsers() {
        List<User> users = Stream.of(
                new User(101, "Vladimir", "password", "Vladimir@gmail.com"),
                new User(102, "user1", "pwd1", "user1@gmail.com"),
                new User(103, "user2", "pwd2", "user2@gmail.com"),
                new User(104, "user3", "pwd3", "user3@gmail.com")
        ).collect(Collectors.toList());
        repository.saveAll(users);
    }


    public static void main(String[] args) {
        SpringApplication. run(BootApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
    }
}
