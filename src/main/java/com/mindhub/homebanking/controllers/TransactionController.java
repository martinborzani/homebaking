package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dto.ClientDTO;
import com.mindhub.homebanking.dto.TransactionDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.models.TransactionType;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.repositories.TransactionReposity;
import com.mindhub.homebanking.service.AccountService;
import com.mindhub.homebanking.service.ClientService;
import com.mindhub.homebanking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TransactionController {



    @Autowired
    TransactionService transactionService;

    @Autowired
    ClientService clientService;

    @Autowired
    AccountService accountService;





    @RequestMapping("/transactions")
    public List<TransactionDTO> getTransaction(){
        return  transactionService.getTransactionDTO();
    }

    @RequestMapping("/transactions/{id}")
    public TransactionDTO getAccount(@PathVariable long id){
        return transactionService.getAccountDTO(id);
    }

    @Transactional
    @PostMapping("/transactions")
    public ResponseEntity<?> createNewTransaction(
            @RequestParam double amount,
            @RequestParam String description,
            @RequestParam String accountOrigin ,
            @RequestParam String accountDestiny,
            Authentication authentication){

          Client clientCurrent = clientService.findByEmail(authentication.getName());
          Account accountCurrentOrigin = accountService.getAccountCurrent(accountOrigin);
          Account accountCurrentDestiny = accountService.getAccountCurrent(accountDestiny);



            Set<Account> accountClient = clientCurrent.getAccount();

        if(amount <= 0){
            return new ResponseEntity<>("El monto esta vacio o es menor a 0", HttpStatus.FORBIDDEN);
        }else if(description.isEmpty()){
            return new ResponseEntity<>("La descripcion esta vacia", HttpStatus.FORBIDDEN);
        }else if(accountOrigin.isEmpty()){
            return new ResponseEntity<>("La cuenta de origen esta vacia", HttpStatus.FORBIDDEN);
        }else if(accountDestiny.isEmpty()){
            return new ResponseEntity<>("La ceunta destino esta vacia", HttpStatus.FORBIDDEN);
        }

        if(accountOrigin.equals(accountDestiny)){
            return new ResponseEntity<>("Las cuentas son iguales", HttpStatus.FORBIDDEN);
        }

        if(accountCurrentOrigin == null){
            return new ResponseEntity<>("La cuenta de origen no existe ", HttpStatus.FORBIDDEN);
        }

        if(accountCurrentDestiny == null){
            return new ResponseEntity<>("La cuenta de destino no existe ", HttpStatus.FORBIDDEN);
        }


        if(!accountClient.contains(accountCurrentOrigin)){
            return new ResponseEntity<>("La cuenta pertenece al usuario", HttpStatus.FORBIDDEN);
        }

        if(accountCurrentOrigin.getBalance() < amount){
            return new ResponseEntity<>("El balance es menor al monto ingresado  ", HttpStatus.FORBIDDEN);
        }

        double balanceOriginAccount = accountCurrentOrigin.getBalance() - amount;
        double balanceDestinyAccount = accountCurrentDestiny.getBalance() + amount;

        accountCurrentOrigin.setBalance(balanceOriginAccount);
        accountCurrentDestiny.setBalance(balanceDestinyAccount);

        accountService.saveAccount(accountCurrentOrigin);
        accountService.saveAccount(accountCurrentDestiny);

        Transaction originTransaction = new Transaction(TransactionType.debito,amount,description, LocalDateTime.now(),accountCurrentOrigin);
        Transaction destinyTransaction = new Transaction(TransactionType.credito,amount,description, LocalDateTime.now(),accountCurrentDestiny);

        transactionService.saveTransaction(originTransaction);
        transactionService.saveTransaction(destinyTransaction);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
