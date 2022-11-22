package com.mindhub.homebanking.controllers;


import com.mindhub.homebanking.dto.ClientDTO;
import com.mindhub.homebanking.dto.ClientLoanDTO;
import com.mindhub.homebanking.dto.LoanApplicationDTO;
import com.mindhub.homebanking.dto.LoanDTO;
import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;
import com.mindhub.homebanking.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class LoanController {


    @Autowired
    LoanService loanService;

    @Autowired
    ClientService clientService;

    @Autowired
    AccountService accountService;

    @Autowired
    TransactionService transactionService;

    @Autowired
    ClientLoanService clientLoanService;



    @RequestMapping("/loans")
    public List<LoanDTO> getLoan(){
        return loanService.getLoanDTO();
    }

    @Transactional
    @PostMapping("/loans")
    public ResponseEntity<?> createLoan(
            @RequestBody LoanApplicationDTO loanApplitacion,
            Authentication authentication
            ){

        Client clientCurrent = clientService.findByEmail(authentication.getName());
        Account accountCurrent = accountService.getAccountCurrent(loanApplitacion.getNumberAccount());
        Loan loanParam = loanService.findById(loanApplitacion.getId());

        double porcentageAmount = 0;


        Loan foundLoan = loanService.findById(loanApplitacion.getId());

        if(loanApplitacion.getNumberAccount().isEmpty()){
            return new ResponseEntity<>("El numero de cuenta esta vacio", HttpStatus.FORBIDDEN);
        }

        if(loanApplitacion.getAmount() <= 0){
            return new ResponseEntity<>("El monto esta vacio o es menor a 0", HttpStatus.FORBIDDEN);
        }

        if(loanApplitacion.getPayment() == 0){
            return new ResponseEntity<>("La cantidad de cuota esta vacia", HttpStatus.FORBIDDEN);
        }

        if(foundLoan == null){
            return new ResponseEntity<>("El prestamo no existe ", HttpStatus.FORBIDDEN);
        }

        if(foundLoan.getMaxAmount() < loanApplitacion.getAmount()){
            return new ResponseEntity<>("El monto pedido supera el monto maximo del prestamo", HttpStatus.FORBIDDEN);
        }

        if(!foundLoan.getPayments().contains(loanApplitacion.getPayment())){
            return new ResponseEntity<>("El prestamo no tiene la cantida de cuotas digitadas", HttpStatus.FORBIDDEN);
        }

        if(accountService.findByNumber(loanApplitacion.getNumberAccount()) == null){
            return new ResponseEntity<>("la cuenta no existe", HttpStatus.FORBIDDEN);
        }

        if(clientCurrent.getAccount().contains(loanApplitacion.getNumberAccount())){
            return new ResponseEntity<>("La cuenta no pertenece a un cliente autenticado", HttpStatus.FORBIDDEN);
        }


        if(loanParam.getName().equals("Hipotecario")){
            porcentageAmount = loanApplitacion.getAmount() * 1.20;
        }

        if(loanParam.getName().equals("Personal")){
            porcentageAmount = loanApplitacion.getAmount() * 1.10;
        }

        if(loanParam.getName().equals("Automotriz")){
            porcentageAmount = loanApplitacion.getAmount() * 1.15;
        }

        Transaction transaction = new Transaction(TransactionType.credito,loanApplitacion.getAmount(),loanParam.getName() + " " +"loan approved", LocalDateTime.now(),accountCurrent);

        ClientLoan clientLoan = new ClientLoan(porcentageAmount,loanApplitacion.getPayment(),clientCurrent,foundLoan);

        transactionService.saveTransaction(transaction);
        clientLoanService.saveClientLoan(clientLoan);

        accountCurrent.setBalance(accountCurrent.getBalance() + loanApplitacion.getAmount());

        accountService.saveAccount(accountCurrent);

        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @PostMapping("/newLoan")
    public ResponseEntity<?> createNewLoan(@RequestParam String loanType, @RequestParam String maxDues, @RequestParam double maxAmount){

        List<Integer> dues = null;

        if(loanType.isEmpty()){
            return new ResponseEntity<>("The loan type is empty", HttpStatus.FORBIDDEN);
        }

        if(maxDues.isEmpty()){
            return new ResponseEntity<>("The max dues is empty", HttpStatus.FORBIDDEN);
        }

        if(maxAmount == 0){
            return new ResponseEntity<>("The max amount is 0", HttpStatus.FORBIDDEN);
        }

        if(maxDues.equals("6-24")){
             dues = List.of(6,12,24);
        }

        if(maxDues.equals("6-36")){
            dues = List.of(6,12,24,36);
        }

        if(maxDues.equals("6-60")){
            dues = List.of(6,12,24,36,60);
        }

        Loan newLoan = new Loan(loanType,maxAmount,dues);

        loanService.saveLoan(newLoan);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
