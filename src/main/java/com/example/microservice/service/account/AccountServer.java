package com.example.microservice.service.account;

import com.example.microservice.service.post.PostServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@EnableAutoConfiguration
@EnableDiscoveryClient
@RestController
@RequestMapping("rest/account")
public class AccountServer {

    private Logger logger = Logger.getLogger(PostServer.class.getName());

    private final List<Account> accounts = new ArrayList<>();

    public AccountServer() {
        accounts.add(new Account(1, "login1", "pass1"));
        accounts.add(new Account(2, "login2", "pass2"));
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void create(@RequestBody Account account) {
        logger.info("created " + account.toString());
        accounts.add(account);
    }

    @RequestMapping(value = "/{login}", method = RequestMethod.GET)
    public Account read(@PathVariable(value = "login") String login) {
        logger.info("read login " + login);
        return accounts.stream().filter(acc -> acc.getLogin().equals(login)).findFirst().orElse(null);
    }

    @RequestMapping(value = "/{login}", method = RequestMethod.PUT)
    public void update(@PathVariable(value = "login") String login, @RequestBody Account account) {
        logger.info("update " + login);
        accounts.removeIf(acc -> acc.getLogin().equals(login));
        accounts.add(account);
    }

    @RequestMapping(value = "/{login}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "login") String login) {
        logger.info("delete " + login);
        accounts.removeIf(acc -> acc.getLogin().equals(login));
    }

    public static void main(String[] args) {
        // Will configure using account-server.yml
        System.setProperty("spring.config.name", "account-server");
        SpringApplication.run(AccountServer.class, args);
    }
}
