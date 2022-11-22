package com.mindhub.homebanking.service.implement;


import com.mindhub.homebanking.dto.AccountDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AccountServiceImplement implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public Set<String> getAccountNumber() {
        return accountRepository.findAll().stream().map(account -> account.getNumber()).collect(Collectors.toSet());
    }

    @Override
    public void saveAccount(Account account) {
        accountRepository.save(account);
    }

    @Override
    public List<AccountDTO> getAccountDTO() {
        return  accountRepository.findAll().stream().map(account -> new AccountDTO(account)).collect(Collectors.toList());
    }

    @Override
    public AccountDTO getClientDTO(long id) {
        return accountRepository.findById(id).map(account -> new AccountDTO(account)).orElse(null);
    }

    @Override
    public Account getAccountCurrent(String account) {
        return accountRepository.findBynumber(account);
    }

    public Account findByNumber(String number){
        return accountRepository.findBynumber(number);
    }




}
