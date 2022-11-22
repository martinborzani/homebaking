package com.mindhub.homebanking.service;

import com.mindhub.homebanking.dto.AccountDTO;
import com.mindhub.homebanking.models.Account;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Set;

public interface AccountService {

    public Set<String> getAccountNumber();

    public void saveAccount(Account account);

    public List<AccountDTO> getAccountDTO();

    public AccountDTO getClientDTO(long id);

    public Account getAccountCurrent(String account);

    public Account findByNumber(String number);



}
