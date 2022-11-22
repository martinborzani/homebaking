package com.mindhub.homebanking.controllers;


import com.mindhub.homebanking.dto.AccountDTO;
import com.mindhub.homebanking.dto.ClientDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;

import com.mindhub.homebanking.service.AccountService;
import com.mindhub.homebanking.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    ClientService clientService;



    @RequestMapping("/accounts")
    public List<AccountDTO> getAccounts(){
        return  accountService.getAccountDTO();
    }

    @RequestMapping("accounts/{id}")
    public AccountDTO getClient(@PathVariable long id){
        return accountService.getClientDTO(id);
    }

    @PostMapping("/clients/current/accounts")
    public ResponseEntity<?> createNewAccount(
            @RequestParam String typeAccount,
            Authentication authentication){
        LocalDateTime time = LocalDateTime.now();
        Client clientCurrent = clientService.getClientCurrent(authentication.getName());
        String numAccount;
        Account account;
        Set<Account> allAccount = clientCurrent.getAccount();

        Set<Account> accountExist = allAccount.stream().filter(account1 -> account1.isEnable() == true).collect(Collectors.toSet());

        if(accountExist.size() == 3){
            return new ResponseEntity<>("Error, You have three account", HttpStatus.FORBIDDEN);
        }else{
            long numberAccount = (long)(Math.random() * 100000000 - 1) + 1;
            numAccount = "VIN" + numberAccount;
            account = new Account(numAccount, time, 0,typeAccount,clientCurrent);
            accountService.saveAccount(account);
            clientService.saveClient(clientCurrent);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }


    }



    @PostMapping("/clients/current/accounts/delete")
    public ResponseEntity<?> deleteAccount(
            @RequestParam String numberAccount,
            Authentication authentication
    ){

        Client clientCurrent = clientService.getClientCurrent(authentication.getName());
//        Set <Account> accounts = clientCurrent.getAccount().stream().filter(account1 -> account1.getId() == id).collect(Collectors.toSet());
        if(numberAccount.isEmpty()){
            return new ResponseEntity<>("Not exist the account's number ", HttpStatus.FORBIDDEN);
        }

        if(clientCurrent == null){
            return new ResponseEntity<>("Not exist the client ", HttpStatus.FORBIDDEN);
        }


        Account foundAccount = accountService.findByNumber(numberAccount);
        foundAccount.setEnable(false);
        accountService.saveAccount(foundAccount);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


}
