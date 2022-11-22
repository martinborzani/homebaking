package com.mindhub.homebanking.service.implement;


import com.mindhub.homebanking.dto.TransactionDTO;
import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.repositories.TransactionReposity;
import com.mindhub.homebanking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImplement implements TransactionService {

    @Autowired
    TransactionReposity transactionReposity;

    @Override
    public List<TransactionDTO> getTransactionDTO() {
        return  transactionReposity.findAll().stream().map(transaction -> new TransactionDTO(transaction)).collect(Collectors.toList());
    }

    @Override
    public TransactionDTO getAccountDTO(long id) {
        return transactionReposity.findById(id).map(transaction -> new TransactionDTO(transaction)).orElse(null);
    }

    @Override
    public void saveTransaction(Transaction transaction) {
        transactionReposity.save(transaction);
    }
}
