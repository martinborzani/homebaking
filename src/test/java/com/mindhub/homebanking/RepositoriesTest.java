package com.mindhub.homebanking;


import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@AutoConfigureTestDatabase(replace=NONE)
public class RepositoriesTest {


    @Autowired
    LoadRepository loanRepository;
    @Autowired
    CardRepository cardRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionReposity transactionReposity;

    @Autowired
    ClientRepository clientRepository;




    @Test
    public void existLoans(){
        List<Loan> loans = loanRepository.findAll();
        assertThat(loans,is(not(empty())));
    }

    @Test
    public void existPersonalLoan(){
        List<Loan> loans = loanRepository.findAll();

        assertThat(loans, hasItem(hasProperty("name", is("Personal"))));
    }


    @Test
    public void existCards(){
        List<Card> cards = cardRepository.findAll();
        assertThat(cards,is(not(empty())));
    }

    @Test
    public void existCardGold(){
        List<Card> cards = cardRepository.findAll();
        assertThat(cards, hasItem(hasProperty("color", is(CardColor.GOLD))));
    }

    @Test
    public void existAccount(){
        List<Account> accounts = accountRepository.findAll();
        assertThat(accounts,is(not(empty())));
    }

    @Test
    public void existNameAccount(){
        List<Account> accounts = accountRepository.findAll();
        assertThat(accounts, hasItem(hasProperty("number", is("VIN001"))));
    }

    @Test
    public void existTransactional(){
        List<Transaction> transactions = transactionReposity.findAll();
        assertThat(transactions,is(not(empty())));
    }

    @Test
    public void existTypeTransaction(){
        List<Transaction> transactions = transactionReposity.findAll();
        assertThat(transactions, hasItem(hasProperty("type", is(TransactionType.credito))));
    }

    @Test
    public void existClients(){
        List<Client> clients = clientRepository.findAll();
        assertThat(clients,is(not(empty())));
    }

    @Test
    public void existEmailClient(){
        List<Client> clients = clientRepository.findAll();
        assertThat(clients, hasItem(hasProperty("email", is("melba@mindhub.com"))));
    }

}
