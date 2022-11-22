package com.mindhub.homebanking.dto;

import com.mindhub.homebanking.models.Client;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ClientDTO {

    private long id;
    private String firstName;
    private String lastName;
    private String email;

    private Set<AccountDTO> account  = new HashSet<>();

    private  Set<ClientLoanDTO> clientLoan = new HashSet<>();

    private Set<CardDTO> card = new HashSet<>();


    public ClientDTO() {
    }

    public ClientDTO(Client client){
        this.id = client.getId();
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.email = client.getEmail();
        this.account = client.getAccount().stream().map(account -> new AccountDTO(account)).collect(Collectors.toSet());
        this.clientLoan = client.getClientLoan().stream().map(clientLoan1 -> new ClientLoanDTO(clientLoan1)).collect(Collectors.toSet());
        this.card = client.getCard().stream().map(card1 -> new CardDTO(card1)).collect(Collectors.toSet());

    }



    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<AccountDTO> getAccount() {
        return account;
    }

    public Set<ClientLoanDTO> getClientLoan() {
        return clientLoan;
    }

    public Set<CardDTO> getCard() {
        return card;
    }
}
