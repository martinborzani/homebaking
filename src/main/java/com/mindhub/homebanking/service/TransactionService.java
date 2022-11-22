package com.mindhub.homebanking.service;


import com.mindhub.homebanking.dto.TransactionDTO;
import com.mindhub.homebanking.models.Transaction;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface TransactionService {

    public List<TransactionDTO> getTransactionDTO();

    public TransactionDTO getAccountDTO(long id);

    public void saveTransaction(Transaction transaction);

}
